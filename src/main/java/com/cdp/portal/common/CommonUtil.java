package com.cdp.portal.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.cdp.portal.common.constants.CommonConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * 공통 유틸
 * @since 2020.12.16.
 */
@Slf4j
@Component
public class CommonUtil {

	@Autowired
	private Environment env;

	@Autowired
	private ApplicationContext applicationContext;

	//서버 IP
	private String serverIP;
	//서버 Mac
	private String serverMac;

	//spring.profiles.active
	private List<String> profiles;
	private String profile;

	/**
	 * spring profiles
	 */
	public List<String> getProfiles() {
		if (profiles == null || profiles.size() == 0) {
			profiles = Arrays.asList(env.getActiveProfiles());
		}
		return profiles;
	}

//	/**
//	 * spring profile
//	 */
//	public String getProfile() {
//		if (StringUtils.isBlank(profile)) {
//			if (getProfiles().contains(CommonConstants.Profile.PROD)) {
//				profile = CommonConstants.Profile.PROD;
//			} else if (getProfiles().contains(CommonConstants.Profile.DEV)) {
//				profile = CommonConstants.Profile.DEV;
//			} else {
//				profile = CommonConstants.Profile.LOCAL;
//			}
//		}
//		return profile;
//	}
//
//	/**
//	 * spring profile is prod ?
//	 */
//	public boolean isProd() {
//		return StringUtils.equals(CommonConstants.Profile.PROD, getProfile());
//	}

	/**
	 * get Bean by name
	 */
	public Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * get Bean by name and class type
	 */
	public <T>T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * HttpServletRequest Header DEBUG 로그 출력
	 * @since 2020.12.16.
	 * @param HttpServletRequest request 로그 출력할 HttpServletRequest 객체
	 * @return void
	 */
	public void getHeaderLog(HttpServletRequest request) {
		String name = null;
		log.debug("##### HttpServletRequest Header ######################################## Begin");
		Enumeration<?> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			name = (String)headerNames.nextElement();
			log.debug("##### HttpServletRequest Header - " + name + " : " + request.getHeader(name));
		}
		log.debug("##### HttpServletRequest - remoteAddr : " + request.getRemoteAddr());
		log.debug("##### HttpServletRequest - remoteHost : " + request.getRemoteHost());
		log.debug("##### HttpServletRequest Header ######################################## End");
		return;
	}

	/**
	 * 현재 시간을 문자열로 변환하여 반환
	 * @param String format 변환할 문자열 형식
	 */
	public String getDateString(String format) {
		return new SimpleDateFormat(format, Locale.KOREAN).format(new Date());
	}

	/**
	 * 현재 시간 기준으로 지정한 날짜를 계산한 일시를 문자열로 변환하여 반환
	 * @param String format 변환할 문자열 형식
	 */
	public String getDateString(String format, int pd) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, pd);
		return new SimpleDateFormat(format, Locale.KOREAN).format(c.getTime());
	}
	
	/**
	 * AWS SageMaker API 호출 시 사용
	 * @param dt 변환할 시간
	 * @param format 변환할 시간의 포맷
	 * @param timeZone 변환할 시간의 타임존
	 * @return
	 */
	public Instant getInstant(String dt, String format, String timeZone) {
		return LocalDateTime.parse(dt,DateTimeFormatter.ofPattern(format)).atZone(ZoneId.of(timeZone)).toInstant();
	}

	/**
	 * 예외의 Stack Trace 반환
	 */
	public String getExceptionTrace(Exception e) {
		String trace = ExceptionUtils.getStackTrace(e);
		return trace.length() > 2000 ? trace.substring(0, 2000) : trace;
	}

	/**
	 * 전달받은 파일 경로에 파일이 있는지 확인
	 * @since 2020.12.16.
	 * @param Path path 확인할 파일
	 * @return boolean
	 */
	public boolean checkFile(Path path) {
		return path.toFile().exists();
	}

	/**
	 * 전달받은 파일 경로에 디렉토리가 있는지 확인 후 없으면 생성
	 * @since 2020.12.16.
	 * @param Path path 확인할 경로
	 * @return boolean
	 */
	public boolean checkPath(Path path) {
		boolean rslt = false;
		File directory = path.toFile();
		if (directory.exists()) {
			rslt = true;
		} else {
			if (directory.mkdirs()) {
				rslt = true;
			} else {
				log.warn("전달된 경로에 디렉토리 생성 실패 : " + path.toString());
				rslt = false;
			}
		}
		return rslt;
	}

	/**
	 * resource 에 있는 파일을 읽어 스트림으로 반환
	 * @since 2020.12.16.
	 * @param String 스트림으로 변환할 파일 경로 및 이름
	 * @return boolean
	 */
	public InputStream getResourceFile(Path path) {
		InputStream rslt = null;
		String filePath = path.toString();
		ClassPathResource classPathResource = new ClassPathResource(filePath);
		try {
			rslt = classPathResource.getInputStream();
		} catch (IOException e) {
			log.warn("ClassPathResource : 파일 IOException");
			log.debug(e.getMessage());
		}
		return rslt;
	}

	/**
	 * resource 에 있는 파일을 읽어 스트림으로 반환
	 * @since 2020.12.16.
	 * @param String 스트림으로 변환할 파일 경로 및 이름
	 * @return boolean
	 */
	public InputStream getResourceFile(String path) {
		InputStream rslt = null;
		String filePath = null;
		if (path.startsWith("classpath:")) {
			filePath = path.replace("classpath:", "");
		} else {
			filePath = path;
		}
		ClassPathResource classPathResource = new ClassPathResource(filePath);
		try {
			rslt = classPathResource.getInputStream();
		} catch (IOException e) {
			log.warn("ClassPathResource : 파일 IOException");
			log.debug(e.getMessage());
		}
		return rslt;
	}

	/**
	 * Client IP 확인
	 * @since 2020.12.16.
	 * @param HttpServletRequest request 요청
	 * @param String result 추출한 IP
	 * @return String
	 */
	public String getClientIP(HttpServletRequest request) {
		String result = CommonConstants.EMPTY_STRING;
		String name = CommonConstants.EMPTY_STRING;
		String lowerName = CommonConstants.EMPTY_STRING;
		int order = CommonConstants.IP_HEADER.size();
		Enumeration<?> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			name = (String)headerNames.nextElement();
			lowerName = name.toLowerCase();
			if (CommonConstants.IP_HEADER.contains(lowerName)) {
				if (CommonConstants.IP_HEADER.indexOf(lowerName) < order) {
					order = CommonConstants.IP_HEADER.indexOf(lowerName);
					result = request.getHeader(name);
				}
			}
		}
		if (StringUtils.isBlank(result)) {
			result = request.getRemoteAddr();
		}
		return result;
	}

	/**
	 * 서버 IP 확인
	 * @since 2020.12.16.
	 * @return String
	 */
	public String getServerIP() {
		if (StringUtils.isBlank(serverIP) || StringUtils.equals(serverIP, CommonConstants.UNKNOWN)) {
			try {
				List<String> ips = new ArrayList<>();
				for (Enumeration<NetworkInterface> eni = NetworkInterface.getNetworkInterfaces(); eni.hasMoreElements();) {
					NetworkInterface nif = eni.nextElement();
					for (Enumeration<InetAddress> eia = nif.getInetAddresses(); eia.hasMoreElements();) {
						InetAddress ia = eia.nextElement();
						if (!ia.isLoopbackAddress() && !ia.isLinkLocalAddress() && ia.isSiteLocalAddress()) {
							ips.add(ia.getHostAddress().toString());
						}
					}
				}
				if (ips.size() > 0) {
					if (ips.size() == 1) {
						serverIP = ips.get(0);
					} else {
						int size = ips.size();
						StringBuilder sb = new StringBuilder();
						for(String ip : ips) {
							size--;
							sb.append(ip);
							if (size > 0) {
								sb.append("");
							}
						}
						serverIP = sb.toString();
					}
				}
				if (StringUtils.isBlank(serverIP)) {
					serverIP = CommonConstants.UNKNOWN;
				}
			} catch (Exception e) {
				log.debug("서버 IP를 확인지 못했음.");
				serverIP = CommonConstants.UNKNOWN;
			}
		}
		return serverIP;
	}

	/**
	 * 서버 Mac Address 확인
	 * @since 2020.12.16.
	 * @return String
	 */
	public String getServerMac() {
		if (StringUtils.isBlank(serverMac) || StringUtils.equals(serverMac, CommonConstants.UNKNOWN)) {
			try {
				for (Enumeration<NetworkInterface> eni = NetworkInterface.getNetworkInterfaces(); eni.hasMoreElements();) {
					NetworkInterface nif = eni.nextElement();
					for (Enumeration<InetAddress> eia = nif.getInetAddresses(); eia.hasMoreElements();) {
						InetAddress ia = eia.nextElement();
						if (!ia.isLoopbackAddress() && !ia.isLinkLocalAddress() && ia.isSiteLocalAddress()) {
							StringBuilder sb = new StringBuilder();
							for (byte b : nif.getHardwareAddress()) {
								sb.append(String.format("%02x", b));
							}
							serverMac = sb.toString();
						}
					}
				}
				if (StringUtils.isBlank(serverMac)) {
					serverMac = CommonConstants.UNKNOWN;
				}
			} catch (Exception e) {
				log.debug("서버 Mac Address를 확인지 못했음.");
				serverMac = CommonConstants.UNKNOWN;
			}
		}
		return serverMac;
	}
	
	/**
	 * 인코딩된 쿼리 문자열을 디코딩 
	 * @param query
	 * @return
	 */
	public String getDecodedQueryStr(String query) {
	    if (StringUtils.isEmpty(query)) return null;
	    
	    String result = StringUtils.EMPTY;
	    
	    try {
	        result = URLDecoder.decode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	    
	    return result;
	}
}
