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

    public ResourceDto(Resource resource, String fileName) throws MalformedURLException {
        this.resource = resource;
        this.header = new StringBuilder()
                .append("attachment;")
                .append("filename=\"")
                .append(fileName)
                .append("\"").toString();

    }
}
