package com.cdp.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;

@Configuration
public class SecretsManagerConfig {
    
    @Value("${cloud.aws.region}")
    private String region;

    @Bean
    public AWSSecretsManager secretsManagerClient() {
        return AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .build();
    }
    
}
