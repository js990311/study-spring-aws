package com.study.aws.s3.entity;

import lombok.Getter;

@Getter
public class Files {
    private Long id;
    private String filename;
    private String storePath;
    private String extension;

    public Files(String filename, String storePath) {
        this.filename = filename;
        this.extension = getExtention(filename);
        this.storePath = storePath + "." + this.extension;
    }

    private String getExtention(String filename){
        int doxIdx = filename.lastIndexOf('.');
        return filename.substring(doxIdx+1);
    }

}
