package com.bdp.ap.common.logging;

import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.ap.app.audit.service.LogService;
import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.config.security.AuthUser;

import lombok.extern.slf4j.Slf4j;

/**
 * 컨트롤러에 요청한 로그를 출력하기 위한 AOP 클래스
 */
@Component
@Aspect
@Slf4j
public class LoggerAspect {
    @Pointcut("within(com.bdp.ap.app..*Controller)) && !@annotation(com.bdp.ap.common.annotation.NoLogging)") // 이런 패턴이 실행될 경우 수행
    public void loggerPointCut() {
    }

    @Resource
    private CommonUtil commonUtil;

    @Resource
    private LogUtil logUtil;

    @Resource
    private LogService logService;

    /**
     * 파라미터에 담긴 정보를 logger로 출력한다. 출력대상은 아래 주석 참고
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("loggerPointCut()")
    public Object methodLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); // request 정보를 가져온다.
    	StopWatch sw = new StopWatch();
    	Object result = null;
    	
        try {
		    sw.start();
		    
		    String controllerName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
		    String methodName = proceedingJoinPoint.getSignature().getName();
		    AuthUser customAuthUser = null;
		    Object token = null;
            Map<String, Object> params = new HashMap<>();

            if (SecurityContextHolder.getContext().getAuthentication() != null) {

	            if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AuthUser) {
	                customAuthUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	                //log.info(customAuthUser.getMemberModel().toString());
	            } else {
	                token = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	                log.info("토크 정보 확인...");
	                log.info("Token : {} " + token.toString());
	                log.info("토크 정보 없네...");
	            }
	            
	            try {
	                params.put(Constant.LOG.CLIENT_IP, commonUtil.getClientIP(request));
	                params.put(Constant.LOG.SERVER_IP, commonUtil.getServerIP());
	                params.put(Constant.LOG.CONTROLLER_NM, controllerName);            // 컨트롤러명
	                params.put(Constant.LOG.METHOD_NM, methodName);                    // 호출메소드명
	                params.put(Constant.LOG.PARAMS, getParams(request));               // 파라미터
	                params.put(Constant.LOG.LOG_TIME, LocalDateTime.now());            // 호출시간
	                params.put(Constant.LOG.REQUEST_URI, request.getRequestURI());     // 호출URI
	                params.put(Constant.LOG.HTTP_METHOD, request.getMethod());         // http메소드
	                if(customAuthUser != null) {
	                    params.put(Constant.LOG.USER_INFO, customAuthUser.getMemberModel());    // 로그인 사용자 정보
	                } else if(token != null){
	                    params.put(Constant.LOG.USER_INFO, token.toString());
	                }

	            } catch (Exception e) {
	                log.error("LoggerAspect error", e);
	            }
	            
			    log.info("##########Start Controller##########");
			    log.info(String.format("Request URI: %s", request.getRequestURI()));
	            log.info("Params : {}", params); // param에 담긴 정보들을 한번에 로깅한다.
	            
	            result = proceedingJoinPoint.proceed();

	            logService.insert(logUtil.convertLogData(params));
            }
            
            return result;
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
        	sw.stop();
        	
        	log.info(String.format("소요 시간: %f초", sw.getTotalTimeSeconds()));
        	log.info("##########End Controller##########");
        }
    }

    /**
     * request 에 담긴 정보를 JSONObject 형태로 반환한다.
     * @param request
     * @return
     */
    private static JSONObject getParams(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            String replaceParam = param.replaceAll("\\.", "-");
            String value = request.getParameter(param);
            if (StringUtils.startsWith(value, "{") && StringUtils.endsWith(value, "}")) {
            	try {
            		JSONObject obj = new JSONObject(value);
            		jsonObject.put(replaceParam, obj);
            	} catch (Exception e) {
            		jsonObject.put(replaceParam, value);
            	}
            } else if (StringUtils.startsWith(value, "[") && StringUtils.endsWith(value, "]")) {
            	try {
            		JSONArray obj = new JSONArray(value);
            		jsonObject.put(replaceParam, obj);
            	} catch (Exception e) {
            		jsonObject.put(replaceParam, value);
            	}
            } else {
            	jsonObject.put(replaceParam, value);
            }
        }
        return jsonObject;
    }
}