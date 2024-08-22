package com.study.aws.s3.service;

import com.study.aws.s3.dto.FilesDto;
import com.study.aws.s3.dto.ResourceDto;
import io.awspring.cloud.s3.S3Operations;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;

@Getter
public class S3FileService implements FileService{

    private final String bucketName;
    private final S3Client s3Client;

    public S3FileService(String bucketName, S3Client s3Client) {
        this.bucketName = bucketName;
        this.s3Client = s3Client;
    }

    @Override
    public void saveFile(MultipartFile file) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .key(file.getOriginalFilename())
                    .build();

            RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
            s3Client.putObject(putObjectRequest, requestBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FilesDto> getAllFiles() {
        return null;
    }

    @Override
    public ResourceDto getFileByFilename(String filename) {
        return null;
    }

    @Override
    public void deleteFile(String filename) {

    }
}
