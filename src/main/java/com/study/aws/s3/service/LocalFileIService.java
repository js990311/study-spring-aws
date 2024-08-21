package com.study.aws.s3.service;

import com.study.aws.s3.dto.FilesDto;
import com.study.aws.s3.dto.ResourceDto;
import com.study.aws.s3.entity.Files;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

public class LocalFileIService implements FileService{
    private final String fileDir;
    private final Map<String, Files> filesRepository;

    public LocalFileIService(String fileDir) {
        this.fileDir = fileDir;
        filesRepository = new HashMap<>();
    }

    @Override
    public void saveFile(MultipartFile file) {
        try {
            String filepath = fileDir + UUID.randomUUID();
            Files files = new Files(file.getOriginalFilename(), filepath);
            file.transferTo(new File(files.getStorePath()));
            filesRepository.put(files.getFilename(),files);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FilesDto> getAllFiles(){
        return filesRepository.values().stream().map(FilesDto::new).toList();
    }

    @Override
    public ResourceDto getFileByFilename(String filename) {
        try {
            return new ResourceDto(filesRepository.get(filename));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String filename) {
        File file = new File(filesRepository.get(filename).getStorePath());
        try{
            if(file.exists()){
                if(file.delete()){
                   filesRepository.remove(filename);
                }else {
                    throw new RuntimeException();
                }
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
