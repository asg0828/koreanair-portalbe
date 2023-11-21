package com.cdp.portal.config;

import com.amazonaws.auth.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.cdp.portal.common.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class AwsConfig {

    @Value("${cloud.aws.role.arn}") // application.properties 또는 application.yml에 역할 ARN을 설정하세요.
    private String roleArn;

    @Bean("awsCredentialsProvider")
    @Primary
    @Profile({CommonConstants.Profile.PROD, CommonConstants.Profile.DEV, CommonConstants.Profile.LOCAL})
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new STSAssumeRoleSessionCredentialsProvider.Builder(roleArn, "SESSION_NAME")
                .build();
    }

    @Bean("amazonS3")
    @Primary
    @Profile({CommonConstants.Profile.PROD, CommonConstants.Profile.DEV, CommonConstants.Profile.LOCAL})
    public AmazonS3 amazonS3(AWSCredentialsProvider awsCredentialsProvider) {
        AWSCredentialsProviderChain credentialsProviderChain = new AWSCredentialsProviderChain(
                awsCredentialsProvider,
                new DefaultAWSCredentialsProviderChain()
        );

        return AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProviderChain)
                .withRegion(CommonConstants.AWS.REGION.getName()) // 지역 이름(String)을 사용
                .build();
    }
}