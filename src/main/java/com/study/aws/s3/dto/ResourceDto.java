package com.study.aws.s3.dto;

import com.study.aws.s3.entity.Files;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

@Data
public class ResourceDto {
    private FilesDto filesDto;
    private Resource resource;
    private String header;

    public ResourceDto(Files files) throws MalformedURLException {
        this.filesDto = new FilesDto(files);
        resource = new UrlResource("file:"+files.getStorePath());
        this.header = new StringBuilder()
                .append("attachment;")
                .append("filename=\"")
                .append(files.getStorePath())
                .append("\"").toString();

    }
}
