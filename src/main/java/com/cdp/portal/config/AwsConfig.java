package com.cdp.portal.config;

import java.net.URI;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.cdp.portal.common.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;


@Configuration
public class AwsConfig {

    @Bean("awsCredentialsProvider")
    @Primary
    @Profile({CommonConstants.Profile.PROD, CommonConstants.Profile.DEV})
    public AWSCredentials awsCredentialsProvider() {
        return InstanceProfileCredentialsProvider.getInstance().getCredentials();
    }

    @Bean("awsCredentialsProvider")
    @Primary
    @Profile({CommonConstants.Profile.LOCAL})
    public AWSCredentials awsCredentialsProviderLOCAL() {
        return new BasicAWSCredentials(CommonConstants.AWS.LOCAL_ACCESS_NAME, CommonConstants.AWS.LOCAL_SECRET_NAME);
    }

    /**
     * AWS S3 운영 및 개발
     * 2021.03.09. S3 host 변경으로 설정 수정 : 로컬 설정과 통합
     *
     * @return
     */
    @Bean("amazonS3")
    @Profile({CommonConstants.Profile.PROD, CommonConstants.Profile.DEV})
    public AmazonS3 amazonS3(AWSCredentials awsCredentialsProvider) {
        return AmazonS3Client.builder().withCredentials(new AWSStaticCredentialsProvider(awsCredentialsProvider))
                .withRegion(CommonConstants.AWS.REGION.getName())  // 지역 이름(String)을 사용
                .build();
    }

    /**
     * AWS S3 로컬
     *
     * @return
     */
    @Bean("amazonS3")
    @Profile({CommonConstants.Profile.LOCAL})
    public AmazonS3 amazonS3LOCAL(AWSCredentials awsCredentialsProvider) {
        return AmazonS3Client.builder().withCredentials(new AWSStaticCredentialsProvider(awsCredentialsProvider))
                .withRegion("ap-northeast-2")  // 리전을 명시적으로 설정
                .build();
    }
}