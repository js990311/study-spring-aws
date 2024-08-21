package com.study.aws.s3.service;

import com.study.aws.s3.dto.FilesDto;
import com.study.aws.s3.dto.ResourceDto;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    public void saveFile(MultipartFile file);
    public List<FilesDto> getAllFiles();
    public ResourceDto getFileByFilename(String filename);
    public void deleteFile(String filename);
}
