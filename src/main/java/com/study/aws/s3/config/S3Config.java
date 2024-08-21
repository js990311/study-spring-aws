package com.study.aws.s3.config;

import com.study.aws.s3.service.FileService;
import com.study.aws.s3.service.LocalFileIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class S3Config {
    @Value("${s3.path}")
    private String fileDir;

    @Bean
    public FileService fileService(){
        return new LocalFileIService(fileDir);
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
