//package com.cdp.portal.common.encryption.crypto;
//
//
//import com.cdp.portal.common.CommonUtil;
//import com.cdp.portal.common.encryption.CryptoProvider;
//import com.cdp.portal.common.error.exception.OneidSysException;
//import com.safenet.datasecure.crypto.exceptions.CryptoException;
//
//public class SafeNetCrypto {
//
//    private static String defaultCryptoProperties;
//    private static String defaultIngrianNaeProperties;
//
//    public SafeNetCrypto(String cryptoProperties, String ingrianNaeProperties){
//        defaultCryptoProperties = System.getProperty("com.ingrian.security.nae.Crypto_Context_File");
//        if(!CommonUtil.notEmpty(defaultCryptoProperties)){
//            System.setProperty("com.ingrian.security.nae.Crypto_Context_File", cryptoProperties);
//        }
//        defaultIngrianNaeProperties = System.getProperty("com.ingrian.security.nae.IngrianNAE_Properties_Conf_Filename");
//        if(!CommonUtil.notEmpty(defaultIngrianNaeProperties)){
//            System.setProperty("com.ingrian.security.nae.IngrianNAE_Properties_Conf_Filename", ingrianNaeProperties);
//        }
//    }
//
//    public enum Context {
//
//        HMACSHA512_FOR_CCN("HMACSHA512ForCCN", "KAL_CCN_HMAC"), //HMACSHA512ForCCN
//        HMACSHA512("HMACSHA512", "KAL_HMAC"), //HMACSHA512
//        PASSPORT("Passport", "KAL_PPN"), //Passport
//        CREDIT_CARD_NUMBER("Credit Card Number" ,"KAL_CCN"), //Credit Card Number
//        BANK_ACCOUNT_NUMBER("Bank Account Number","KAL_BAN"), //Bank Account Number
//        EMAIL_ADDRESS("Email Address","KAL_EML"), //Email Address
//        TELEPHONE_NUMBER("Telephone Number", "KAL_TEL"), //Telephone Number
//        SYSTEM_INTERFACE_PASSWORD("System Interface Password","KAL_PWD"), //System Interface Password
//        WEB_URL("Web URL","KAL_URL"), //Web URL
//        WEB_COOKIE("Web Cookie","KAL_CKI"), //Web Cookie
//        WEB_USER_ID("Web User ID", "KAL_KID"); //Web User ID
//
//        private final String name;
//        private final String code;
//
//        Context(String name, String code) {
//            this.name = name;
//            this.code = code;
//        }
//
//    }
//
//
//    public String encrypt(String input, Context context) {
//        String returnText = "";
//        try {
//            returnText = CryptoProvider.EncryptStringAsHex(input, context.code);
//        } catch (CryptoException e) {
//            throw new OneidSysException(e);
//        }
//
//        return returnText;
//    }
//
//
//    public String decrypt(String input, Context context) {
//
//        String returnText = "";
//        try {
//            returnText = CryptoProvider.DecryptHexAsString(input, context.code);
//        } catch (CryptoException e) {
//            throw new OneidSysException(e);
//        }
//
//        return returnText;
//    }
//
//}