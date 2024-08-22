package com.study.aws.s3.config;

import com.study.aws.s3.repository.FilesRepository;
import com.study.aws.s3.service.FileService;
import com.study.aws.s3.service.S3FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@RequiredArgsConstructor
@Configuration
public class S3Config {
    @Value("${s3.bucket.name}")
    private String s3BucketName;

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;
    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String privateKey;


    @Bean
    public AwsBasicCredentials awsBasicCredentials(){
        return AwsBasicCredentials.create(
                accessKey, privateKey
        );
    }

    @Bean
    public S3Client s3Client(){
        return S3Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials()))
                .build();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}
