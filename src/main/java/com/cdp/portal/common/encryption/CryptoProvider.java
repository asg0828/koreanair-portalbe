package com.cdp.portal.common.encryption;


import org.apache.commons.lang3.StringUtils;

import com.cdp.portal.common.encryption.crypto.AesCrypto;
import com.cdp.portal.common.encryption.crypto.HashCrypto;
//import com.ke.cdp.comn.core.module.encryption.crypto.SafeNetCrypto;

public class CryptoProvider {

    private final AesCrypto aesCrypto;
    private final HashCrypto hashCrypto;
//    private final SafeNetCrypto safeNetCrypto;

    public CryptoProvider(AesCrypto aesCrypto, HashCrypto hashCrypto, String cryptoProperties, String ingrianNaeProperties) {
        this.aesCrypto = aesCrypto;
        this.hashCrypto = hashCrypto;
//        this.safeNetCrypto = new SafeNetCrypto(cryptoProperties, ingrianNaeProperties);
    }

    public String toAesEncryptedText(String input) {
        return StringUtils.isBlank(input) ? null : aesCrypto.encrypt(input);
    }

    public String toAesDecryptedText(String input) {
        return StringUtils.isBlank(input) ? null : aesCrypto.decrypt(input);
    }

    public String toHashEncryptedText(String input) {
        return StringUtils.isBlank(input) ? null : hashCrypto.encrypt(input);
    }
//
//    public String toSafeNetEncryptedText(String input, SafeNetCrypto.Context context) {
//        return StringUtils.isBlank(input) ? null : safeNetCrypto.encrypt(input, context);
//    }
//
//    public String toSafeNetDecryptedText(String input, SafeNetCrypto.Context context) {
//        return StringUtils.isBlank(input) ? null : safeNetCrypto.decrypt(input, context);
//    }
}