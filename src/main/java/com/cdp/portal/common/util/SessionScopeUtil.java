package com.cdp.portal.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cdp.portal.app.facade.session.dto.SessionDto;
import com.cdp.portal.common.constants.CommonConstants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SessionScopeUtil {

	public static void setRequestContext(String name, Object value) {
		ServletRequestAttributes reqAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (reqAttr != null) {
			reqAttr.setAttribute(name, value, RequestAttributes.SCOPE_REQUEST);
		}
	}

	public static Object getRequestContext(String name) {
		Object result = null;
		ServletRequestAttributes reqAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (reqAttr != null) {
			result = reqAttr.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
		}
		return result;
	}

	public static SessionDto getContextSession() {
		return (SessionDto) getRequestContext(CommonConstants.SESSION_NAME);
	}

	public static void setContextSession(Object value) {
		setRequestContext(CommonConstants.SESSION_NAME, value);
	}

	public static String getRequestIp() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		String ip = null;
		String xForwardedForStr = req.getHeader(CommonConstants.X_FORWARDED_FOR);

		if (xForwardedForStr != null && !xForwardedForStr.isEmpty()) {
			String[] xForwardedForIpArray;
			try {
				xForwardedForIpArray = xForwardedForStr.split(",");
				ip = xForwardedForIpArray[0];
			} catch (Exception e) {
				ip = null;
			}
		}

		if (ObjectUtils.isEmpty(ip)) {
			ip = req.getRemoteAddr();
		}

		if (ObjectUtils.isEmpty(ip)) {
			ip = "unknown";
		}

		return ip;
	}
}
