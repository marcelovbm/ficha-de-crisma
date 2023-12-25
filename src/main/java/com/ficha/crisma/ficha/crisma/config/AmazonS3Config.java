package com.ficha.crisma.ficha.crisma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AmazonS3Config {

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .credentialsProvider(ProfileCredentialsProvider.create())
                .region(Region.SA_EAST_1)
                .build();
    }

}
