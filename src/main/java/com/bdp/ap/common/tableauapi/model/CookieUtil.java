package com.bdp.ap.common.tableauapi.model;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

public class CookieUtil {
	
	/**
	 * 쿠키 정보에서 key매핑 되는 값을 가지고 온다.
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getCookie(final HttpServletRequest request, final String key){
		Cookie[] cookies = request.getCookies();
		String str = "";
		if(cookies != null){
			for(Cookie cookie: cookies){
				if(cookie.getName().equals(key)){
					str = cookie.getValue();
					break;
				}
			}
		}
		
		return str;		
	}
	
	/**
	 * 쿠키값 설정
	 * @param response
	 * @param key
	 * @param value
	 * @throws UnsupportedEncodingException
	 */
	public static void setCookie(final HttpServletResponse response, final String key, final String value) throws UnsupportedEncodingException{
		String val = StringUtils.isEmpty(value)? java.net.URLEncoder.encode(value.toString(),"UTF-8"):"";
		Cookie cookie = new Cookie(key,val);
		//cookie.setMaxAge(60*60*24*365);            // 쿠키 유지 기간 - 1년
		//cookie.setPath("/");                               // 모든 경로에서 접근 가능하도록 
		response.addCookie(cookie);                // 쿠키저장
	}
	
	/**
	 * 쿠키값 삭제
	 * @param response
	 * @param key
	 * @param value
	 * @throws UnsupportedEncodingException
	 */
	public static void removeCookie(final HttpServletResponse response, final String key){
		Cookie killCookie = new Cookie(key, null);
		killCookie.setMaxAge(0);
		response.addCookie(killCookie);		
	}
}
