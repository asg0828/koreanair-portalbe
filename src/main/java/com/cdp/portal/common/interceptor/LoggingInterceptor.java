//package com.cdp.portal.common.interceptor;
//
//import com.kal.stpc.constants.HttpHeaderConstants;
//import com.kal.stpc.wrapper.CachingRequestWrapper;
//import com.kal.stpc.wrapper.CachingResponseWrapper;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.io.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@Component
//@RequiredArgsConstructor
//public class LoggingInterceptor implements HandlerInterceptor {
//
//	@Value("${app.security.mask}")
//	private List<String> maskList;
//
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	private Pattern multilinePattern;
//
//	long requestStartTime = 0;
//	long requestEndTime = 0;
//	long elapseTime = 0;
//
//	@PostConstruct
//	private void postConstruct() {
//		multilinePattern = Pattern.compile(
//				maskList.stream().map((str) -> "\\\"" + str + "\\\"\\s*:\\s*\\\"(.*?)\\\"").collect(Collectors.joining("|")), Pattern.MULTILINE);
//	}
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		requestStartTime = System.currentTimeMillis();
//
//		if (request instanceof CachingRequestWrapper) {
//
//			String requestLog = "";
//			if (request.getQueryString() != null) {
//				requestLog += request.getQueryString();
//			}
//			requestLog += IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
//
//			logger.info("correlation id : {}, uri : {} , request : {}", request.getHeader(HttpHeaderConstants.X_CORRELATION_ID),
//					request.getRequestURI(), maskingProcessor(requestLog));
//		}
//		return true;
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//		requestEndTime = System.currentTimeMillis();
//		elapseTime = (long) ((requestEndTime - requestStartTime));
//		if (response instanceof CachingResponseWrapper) {
//			String responseLog = IOUtils.toString(((CachingResponseWrapper) response).getContentInputStream(), response.getCharacterEncoding());
//			logger.info("correlation id : {} , elapse time : {}, http status code : {}, response : {}",
//					response.getHeader(HttpHeaderConstants.X_CORRELATION_ID), elapseTime, response.getStatus(), maskingProcessor(responseLog));
//		}
//	}
//
//	private String maskingProcessor(String requestLog) {
//
//		StringBuilder stringBuilder = new StringBuilder(requestLog);
//		Matcher patternMatcher = multilinePattern.matcher(stringBuilder);
//
//		while (patternMatcher.find()) {
//			IntStream.rangeClosed(1, patternMatcher.groupCount()).forEach(matchingGroup -> {
//				if (patternMatcher.group(matchingGroup) != null) {
//					IntStream.range(patternMatcher.start(matchingGroup), patternMatcher.end(matchingGroup))
//							.forEach(eachCharacter -> stringBuilder.setCharAt(eachCharacter, '*'));
//				}
//			});
//		}
//		return stringBuilder.toString();
//	}
//}