package com.bdp.ap.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.bdp.ap.common.Constant;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.http.apache.ProxyConfiguration;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

	@Bean("awsCredentialsProvider")
	@Primary
	@Profile({Constant.Profile.PROD,Constant.Profile.DEV})
	public AwsCredentialsProvider awsCredentialsProvider() {
		AwsCredentialsProvider provider = InstanceProfileCredentialsProvider.create();
		return provider;
	}
	
	@Bean("awsCredentialsProvider")
	@Primary
	@Profile({Constant.Profile.LOCAL})
	public AwsCredentialsProvider awsCredentialsProviderLOCAL() {
		AwsCredentialsProvider provider = StaticCredentialsProvider.create(AwsBasicCredentials.create(Constant.AWS.LOCAL_ACCESS_NAME, Constant.AWS.LOCAL_SECRET_NAME));
		return provider;
	}
	
	/**
	 * AWS S3 운영 및 개발
	 * 2021.03.09. S3 host 변경으로 설정 수정 : 로컬 설정과 통합
	 * @return
	 */
	@Bean("s3Client")
	@Profile({Constant.Profile.PROD,Constant.Profile.DEV})
	public S3Client s3Client(AwsCredentialsProvider awsCredentialsProvider) {
		return S3Client.builder().region(Constant.AWS.REGION).credentialsProvider(awsCredentialsProvider).build();
	}
	
	/**
	 * AWS S3 로컬
	 * @return
	 */
	@Bean("s3Client")
	@Profile({Constant.Profile.LOCAL})
	public S3Client s3ClientLOCAL(AwsCredentialsProvider awsCredentialsProvider) {
		SdkHttpClient httpClient = ApacheHttpClient.builder()
				.proxyConfiguration(ProxyConfiguration.builder().useSystemPropertyValues(true)
						.endpoint(URI.create("http://s3.ap-northeast-2.amazonaws.com:3129")).build())
				.build();
		return S3Client.builder().region(Constant.AWS.REGION).credentialsProvider(awsCredentialsProvider).httpClient(httpClient).build();
	}
}
