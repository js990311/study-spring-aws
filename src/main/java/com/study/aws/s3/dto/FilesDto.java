package com.study.aws.s3.dto;

import com.study.aws.s3.entity.Files;
import lombok.Data;

@Data
public class FilesDto {
    private Long id;
    private String filename;
    private String storePath;
    private String extension;

    public FilesDto(Files files) {
        this.id = files.getId();
        this.filename = files.getFilename();
        this.extension = files.getExtension();
    }
}
