package com.study.aws.s3.service;

import com.study.aws.s3.dto.FilesDto;
import com.study.aws.s3.dto.ResourceDto;
import com.study.aws.s3.entity.Files;
import com.study.aws.s3.repository.FilesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.net.MalformedURLException;
import java.util.List;

@Getter
@Transactional(readOnly = true)
@Service
public class S3FileService implements FileService{
    private final FilesRepository filesRepository;
    private final S3Client s3Client;
    
    @Value("${s3.bucket.name}")
    private String bucketName;

    public S3FileService(FilesRepository filesRepository, S3Client s3Client) {
        this.filesRepository = filesRepository;
        this.s3Client = s3Client;
    }

    @Transactional
    @Override
    public void saveFile(MultipartFile file) {
        try {
            Files files = new Files(file.getOriginalFilename());
            Long id = filesRepository.save(files).getId();
            
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .key(id + "." + files.getExtension())
                    .build();
            RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
            s3Client.putObject(putObjectRequest, requestBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FilesDto> getAllFiles() {
        return filesRepository.findAll().stream().map(FilesDto::new).toList();
    }

    @Override
    public ResourceDto getFileByFilename(Long fileId) {
        try {
            Files files = filesRepository.findById(fileId).orElseThrow(EntityNotFoundException::new);
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(files.getStorePath())
                    .build();
            ResponseInputStream<GetObjectResponse> object = s3Client.getObject(getObjectRequest);
            Resource r = new InputStreamResource(object);
            return new ResourceDto(r, files.getStorePath());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void deleteFile(Long fileId) {
        try {
            Files files = filesRepository.findById(fileId).orElseThrow(EntityNotFoundException::new);
            filesRepository.delete(files);
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(files.getStorePath())
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
