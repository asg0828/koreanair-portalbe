package com.cdp.portal.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.cdp.portal.common.constants.CommonConstants;

@Configuration
public class AwsConfig {

    @Value("${cloud.aws.role.arn}")
    private String roleArn;

    @Bean("awsCredentialsProviderOri")
    @Primary
    @Profile({CommonConstants.Profile.PRD, CommonConstants.Profile.DEV, CommonConstants.Profile.LOCAL})
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new STSAssumeRoleSessionCredentialsProvider.Builder(roleArn, "SESSION_NAME")
                .build();
    }

    @Bean("amazonS3")
    @Primary
    @Profile({CommonConstants.Profile.PRD, CommonConstants.Profile.DEV, CommonConstants.Profile.LOCAL})
    public AmazonS3 amazonS3(@Qualifier("awsCredentialsProviderOri") AWSCredentialsProvider awsCredentialsProvider) {
        AWSCredentialsProviderChain credentialsProviderChain = new AWSCredentialsProviderChain(
                awsCredentialsProvider,
                new DefaultAWSCredentialsProviderChain()
        );

        return AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProviderChain)
                .withRegion(CommonConstants.AWS.REGION.getName())
                .build();
    }
}