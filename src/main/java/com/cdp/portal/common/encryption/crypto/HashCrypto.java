package com.cdp.portal.common.encryption.crypto;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cdp.portal.common.error.exception.OneidSysException;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.GenerateMacRequest;
import software.amazon.awssdk.services.kms.model.GenerateMacResponse;
import software.amazon.awssdk.services.kms.model.KmsException;
import software.amazon.awssdk.services.kms.model.MacAlgorithmSpec;

public class HashCrypto {

    private final KmsClient kmsClient;

    @Value("${core.aws.kms.encrypt.sha.keyId}")
    private String keyId;

    public HashCrypto(KmsClient kmsClient){
        this.kmsClient = kmsClient;
    }

    public String encrypt(String input) {

        String returnText = "";

        SdkBytes myBytes = SdkBytes.fromUtf8String(input);

        GenerateMacRequest generateMacRequest = GenerateMacRequest.builder()
                .macAlgorithm(MacAlgorithmSpec.HMAC_SHA_256)
                .keyId(keyId)
                .message(myBytes)
                .build();
        try {
            GenerateMacResponse response = kmsClient.generateMac(generateMacRequest);
            returnText = Base64.getEncoder().encodeToString(response.mac().asByteArray());

        }catch(KmsException e){
            throw new OneidSysException(e.awsErrorDetails().errorCode(), e);

        }
        return returnText;

    }


}