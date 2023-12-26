package com.cdp.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cdp.portal.common.encryption.CryptoProvider;
import com.cdp.portal.common.encryption.crypto.AesCrypto;
import com.cdp.portal.common.encryption.crypto.HashCrypto;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@ConditionalOnExpression("'${core.aws.role-switching.enable:false}'.equals('true')")
@Configuration
public class AwsDefaultCredentialConfig implements AwsConfigIf {

    @Value("${core.safenet.crypto-properties.dir:safenet/default/CryptoContexts.properties}")
    private String cryptoProperties;
    @Value("${core.safenet.IngrianNAE-properties.dir:safenet/default/IngrianNAE.properties}")
    private String ingrianNaeProperties;
    @Value("${core.aws.region:ap-northeast-2}")
    private String region;

    @Bean
    @Override
    public KmsClient kmsClient(AwsCredentialsProvider awsCredentialsProvider) {
        KmsClient kmsClient = KmsClient.builder()
                .credentialsProvider(awsCredentialsProvider)
                .region(Region.of(region))
                .build();
        return kmsClient;
    }

    @Bean
    @Override
    public S3Client s3Client(AwsCredentialsProvider awsCredentialsProvider) {
        S3Client s3Client = S3Client.builder()
                .credentialsProvider(awsCredentialsProvider)
                .region(Region.of(region))
                .build();
        return s3Client;
    }

    @Bean
    @Override
    public SecretsManagerClient smClient(AwsCredentialsProvider awsCredentialsProvider) {
        SecretsManagerClient smClient = SecretsManagerClient.builder()
                .credentialsProvider(awsCredentialsProvider)
                .region(Region.of(region))
                .build();
        return smClient;
    }

    @Bean
    @Override
    public AesCrypto aesCrypto(KmsClient kmsClient){
        return new AesCrypto(kmsClient);
    }

    @Bean
    @Override
    public HashCrypto hashCrypto(KmsClient kmsClient){
        return new HashCrypto(kmsClient);
    }

    @Bean
    @Override
    public CryptoProvider cryptoProvider(AesCrypto aesCrypto, HashCrypto hashCrypto) {
        return new CryptoProvider(aesCrypto, hashCrypto, cryptoProperties, ingrianNaeProperties);
    }

}