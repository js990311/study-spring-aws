package com.study.aws.s3.config;

import com.study.aws.s3.service.FileService;
import com.study.aws.s3.service.LocalFileIService;
import com.study.aws.s3.service.S3FileService;
import io.awspring.cloud.s3.S3Operations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import software.amazon.awssdk.services.s3.S3Client;

@RequiredArgsConstructor
@Configuration
public class S3Config {
    @Value("${s3.bucket.name}")
    private String s3BucketName;

    private final S3Client s3Client;

    @Bean
    public FileService fileService(){
        return new S3FileService(s3BucketName, s3Client);
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}
