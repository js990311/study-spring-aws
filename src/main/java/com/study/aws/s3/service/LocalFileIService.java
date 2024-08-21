package com.study.aws.s3.service;

import com.study.aws.s3.dto.FilesDto;
import com.study.aws.s3.entity.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LocalFileIService implements FileService{
    private final String fileDir;
    private final List<Files> filesRepository;

    public LocalFileIService(String fileDir) {
        this.fileDir = fileDir;
        filesRepository = new ArrayList<>();
    }

    @Override
    public void saveFile(MultipartFile file) {
        try {
            String filepath = fileDir + UUID.randomUUID();
            Files files = new Files(file.getOriginalFilename(), filepath);
            file.transferTo(new File(files.getStorePath()));
            filesRepository.add(files);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FilesDto> getAllFiles(){
        return filesRepository.stream().map(FilesDto::new).toList();
    }

}
