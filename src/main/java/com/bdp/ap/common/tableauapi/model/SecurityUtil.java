package com.bdp.ap.common.tableauapi.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @Class Name  : SecurityUtil.java
 * @Description : 
 * @Modification Information  
 * 
 * @ 수정일			수정자			수정내용
 * @ ------------- ---------- ---------------------------
 * @  2015. 6. 11.	Jin			최초생성
 * 
 * @author Jin
 * @since 2015. 6. 11.
 * @version 1.0
 * @see 
 * 
 * 사용방법
   @Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	String securytiKey = propertiesService.getString("security.key");
	
	ex)
	1. String password = new SecurityUtil(securytiKey).encrypt("암호화할 값");
	2. SecurityUtil securityUtil = new SecurityUtil(securytiKey);
		securityUtil.encrypt("암호화할 값");
 * 
 */
public class SecurityUtil{
	private static String SHA_256 = "SHA-256";
	private String iv;
    private Key keySpec;
	
	/**
	 * 비밀번호 단방향 암호화 SHA-256
	 *
	 * @param passWord
	 * @return
	 *
	 */
	public static String passEncoder(String passWord){
		try{
            MessageDigest md = MessageDigest.getInstance(SHA_256);
            md.update(passWord.getBytes());
            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
	}
	
	/**
	 * 비밀번호 단방향 암호화 비교
	 *
	 * @param passWord
	 * @param encodedPassword
	 * @return
	 *
	 */
	public static boolean passMatch(String passWord, String encodedPassword){
		return passEncoder(passWord).equals(encodedPassword);
	}
	
	public SecurityUtil(String stringKey) throws UnsupportedEncodingException{
		this.iv = stringKey.substring(0, 16);
        byte[] keyBytes = new byte[16];
        byte[] b = stringKey.getBytes("UTF-8");
        int len = b.length;
        if(len > keyBytes.length){
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        this.keySpec = keySpec;
	}
	
	/**
     * AES256 으로 암호화 한다.
     * 
     * @param str 암호화할 문자열
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    public String encrypt(String str) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException{
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
        return enStr;
    }

    /**
     * AES256으로 암호화된 txt 를 복호화한다.
     * 
     * @param str 복호화할 문자열
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    public String decrypt(String str) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
        byte[] byteStr = Base64.decodeBase64(str.getBytes());
        return new String(c.doFinal(byteStr), "UTF-8");
    }
    
   
    /**
     * AES256으로 암호화된 값과 비교한다.
     *
     * @param 비교할 txt
     * @param 암호화된 txt
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws GeneralSecurityException
     *
     */
    public boolean match(String str, String match) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException{
        return encrypt(str).equals(match);
    }
}