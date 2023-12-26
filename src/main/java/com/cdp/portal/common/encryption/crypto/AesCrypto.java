package com.cdp.portal.common.encryption.crypto;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cdp.portal.common.error.exception.OneidSysException;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.DecryptRequest;
import software.amazon.awssdk.services.kms.model.DecryptResponse;
import software.amazon.awssdk.services.kms.model.EncryptRequest;
import software.amazon.awssdk.services.kms.model.EncryptResponse;
import software.amazon.awssdk.services.kms.model.KmsException;

public class AesCrypto {

    private final KmsClient kmsClient;

    @Value("${core.aws.kms.encrypt.aes.keyId}")
    private String keyId;

    public AesCrypto(KmsClient kmsClient) {
        this.kmsClient = kmsClient;
    }

    public String encrypt(String input) {

        String returnText = "";

        SdkBytes myBytes = SdkBytes.fromUtf8String(input);
        EncryptRequest encryptRequest = EncryptRequest.builder()
                .plaintext(myBytes)
                .keyId(keyId)
                .build();

        try {
            EncryptResponse response = kmsClient.encrypt(encryptRequest);

            SdkBytes encryptedData = response.ciphertextBlob();
            returnText = Base64.getEncoder().encodeToString(encryptedData.asByteArray());
        }catch(KmsException e){
            throw new OneidSysException(e.awsErrorDetails().errorCode(), e);
        }
        return returnText;

    }
    public String decrypt(String input) {

        String returnText = "";


        SdkBytes myBytes = SdkBytes.fromByteArray(Base64.getDecoder().decode(input));
        DecryptRequest decryptRequest = DecryptRequest.builder()
                .ciphertextBlob(myBytes)
                .keyId(keyId)
                .build();
        try {
            DecryptResponse response = kmsClient.decrypt(decryptRequest);

            SdkBytes decryptedData = response.plaintext();
            returnText = decryptedData.asUtf8String();

        }catch(KmsException e){
            throw new OneidSysException(e.awsErrorDetails().errorCode(), e);
        }
        return returnText;
    }

}