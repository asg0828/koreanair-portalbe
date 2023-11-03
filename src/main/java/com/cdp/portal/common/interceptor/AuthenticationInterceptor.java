package com.cdp.portal.common.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cdp.portal.app.facade.session.dto.SessionDto;
import com.cdp.portal.app.facade.session.service.AuthenticationService;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.error.exception.CdpPortalException;
import com.cdp.portal.common.util.SessionScopeUtil;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
	private static final String HTTP_METHOD_OPTIONS = "OPTIONS";
	private static final String HTTP_METHOD_POST = "POST";
	private static final String SESSION_URI = ".*/session/v1/login";
	private final AuthenticationService authenticationService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Pattern pattern = Pattern.compile(SESSION_URI);
		Matcher matcher = pattern.matcher(request.getRequestURI());
		boolean isMatched = matcher.find();
		
		if (!(isMatched && HTTP_METHOD_POST.equals(request.getMethod())) && !HTTP_METHOD_OPTIONS.equals(request.getMethod())) {
			String sessionId = request.getHeader(CommonConstants.X_SESSION_ID);
			if (ObjectUtils.isEmpty(sessionId)) {
				throw new CdpPortalException(CdpPortalError.SESSION_EXPIRE);
			}

			SessionDto sessionUser = authenticationService.getSession(sessionId);

			if (sessionUser == null) {
				throw new CdpPortalException(CdpPortalError.SESSION_EXPIRE);
			} else {
				SessionScopeUtil.setContextSession(sessionUser);
			}
		}

		return true;
	}
}
