package com.bdp.ap.common;

import javax.annotation.Resource;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

/**
 * 암복호화 유틸
 */
@Component
public class CryptUtil {

	@Resource(name="jasyptStringEncryptor")
	private StringEncryptor encryptor;

	/**
	 * 암호화
	 * @param message
	 * @return
	 */
	public String encrypt(String message) {
		return encryptor.encrypt(message);
	}

	/**
	 * 복호화
	 * @param encryptedMessage
	 * @return
	 */
	public String decrypt(String encryptedMessage) {
		return encryptor.decrypt(encryptedMessage);
	}
}
