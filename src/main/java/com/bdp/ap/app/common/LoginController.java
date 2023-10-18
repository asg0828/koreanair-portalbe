package com.bdp.ap.app.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bdp.ap.config.security.mapper.SecurityMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 스프링 시큐리티 로그인등의 페이지를 커스텀하기 위한 컨트롤러
 */
@Controller
@Slf4j
public class LoginController {

	@Resource(name = "securityMapper")
	private SecurityMapper securityMapper;

	/**
	 * 로그인
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login", method= {RequestMethod.GET,RequestMethod.POST})
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

		log.debug("call /login");

		// Spring Security 인증 실패 시 실패 메시지 추출
		Object obj = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (obj != null && obj instanceof Exception) {
			Exception e = (Exception) obj;
			model.addAttribute("result",e.getMessage());
			request.getSession().removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		} else {
			// 세션에서 사용자 정보 조회 : SSO 인증 성공이면 사용자 정보 있음
			HttpSession session = request.getSession();
			Object userInfo = session.getAttribute("DEFAULT_SESSION_USERID");
			String userId = null;
			if (userInfo != null) {
				userId = userInfo.toString();
				// 사용자 정보가 있으면 JSP로 전달
				if (StringUtils.isNotBlank(userId)) {
					model.addAttribute("userInfo",userId);
				}
			}
		}
		return "login";
	}

}
