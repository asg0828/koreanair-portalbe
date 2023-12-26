package com.cdp.portal.config;

import com.cdp.portal.common.encryption.CryptoProvider;
import com.cdp.portal.common.encryption.crypto.AesCrypto;
import com.cdp.portal.common.encryption.crypto.HashCrypto;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

public interface AwsConfigIf {
    KmsClient kmsClient(AwsCredentialsProvider awsCredentialsProvider);
    S3Client s3Client(AwsCredentialsProvider awsCredentialsProvider);
    SecretsManagerClient smClient(AwsCredentialsProvider awsCredentialsProvider);
    AesCrypto aesCrypto(KmsClient kmsClient);
    HashCrypto hashCrypto(KmsClient kmsClient);
    CryptoProvider cryptoProvider(AesCrypto aesCrypto, HashCrypto hashCrypto);
}