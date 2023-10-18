package com.bdp.ap.app.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bdp.ap.common.Constant;
import com.bdp.ap.common.annotation.NoLogging;

@Controller
public class ErrController implements ErrorController {

	@NoLogging
	@Override
	public String getErrorPath() {
		return "error/error";
	}

	@NoLogging
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String result = "error/error";
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				result = "error/error";
			} else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
				result = "error/error";
			} else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				result = "error/error";
			} else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
				result = "error/error";
			} else if(statusCode == HttpStatus.FORBIDDEN.value()) {
				result = "error/error";
			}
		}
		return result;
	}

	/**
	 * 에러 처리 : ErrorPageController에 정의되지 않는 모든 에러
	 * @return Map<String,String>
	 */
	@NoLogging
	@RequestMapping(value=Constant.ERROR_URL.ERROR)
	public String error() {
		return "error/error";
	}

	/**
	 * 404 : 요청 URL 없는 경우
	 * @return Map<String,String>
	 */
	@NoLogging
	@RequestMapping(value=Constant.ERROR_URL.NOT_FOUND)
	public String errorNotFound() {
		return "error/error";
	}

	/**
	 * 405 : 요청 메소드 없는 경우
	 * @return Map<String,String>
	 */
	@NoLogging
	@RequestMapping(value=Constant.ERROR_URL.METHOD_NOT_ALLOWED)
	public String errorMethodNotAllowed() {
		return "error/error";
	}

	/**
	 * 500 : 요청 처리중 오류 발생한 경우
	 * @return Map<String,String>
	 */
	@NoLogging
	@RequestMapping(value=Constant.ERROR_URL.INTERNAL_SERVER_ERROR)
	public String internalServerError() {
		return "error/error";
	}

	/**
	 * 401 : 사용자 미인증인 경우
	 * @return Map<String,String>
	 */
	@NoLogging
	@RequestMapping(value=Constant.ERROR_URL.UNAUTHORIZED)
	public String unauthorized() {
		return "error/error";
	}

	/**
	 * 403 : 접근 권한이 없는 경우
	 * @return Map<String,String>
	 */
	@NoLogging
	@RequestMapping(value=Constant.ERROR_URL.FORBIDDEN)
	public String forbidden() {
		return "error/error";
	}
}
