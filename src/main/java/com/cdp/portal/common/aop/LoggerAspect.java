package com.cdp.portal.common.aop;

import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cdp.portal.app.facade.log.dto.request.LogMgrReqDto;
import com.cdp.portal.app.facade.log.dto.request.LogUserReqDto;
import com.cdp.portal.app.facade.log.service.LogMgrService;
import com.cdp.portal.app.facade.log.service.LogUserService;
import com.cdp.portal.common.CommonUtil;
import com.cdp.portal.common.constants.CommonConstants;
import com.cdp.portal.common.util.SessionScopeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAspect {
    
    private final LogMgrService logMgrService;
    private final LogUserService logUserService;
    private final Environment environment;
    private final CommonUtil commonUtil;
    
    @Pointcut("within(com.cdp.portal.app..*Controller)) && !@annotation(com.cdp.portal.common.annotation.NoLogging)")
    public void loggerPointCut() {
    
    }

    @Around("loggerPointCut()")
    public Object methodLogger(ProceedingJoinPoint proceedingJoinPoint) {
        StopWatch stopWatch = new StopWatch();
        Object result = null;
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        
        try {
            stopWatch.start();
            
            if (log.isDebugEnabled()) {
                log.debug("#################### Start LoggerAspect ####################");
//                log.debug("Request header : {}", this.getRequestHeader(request));
//                log.debug("Request session : {}", this.getUserId());
//                log.debug("Request uri : {}", request.getRequestURI());
//                log.debug("Request method : {}", request.getMethod());
//                log.debug("Request query : {}", commonUtil.getDecodedQueryStr(request.getQueryString()));
//                log.debug("Request body : {}", this.getRequestBodyStr(request));
            }
            
            result = proceedingJoinPoint.proceed();
            
            if (log.isDebugEnabled()) {
//                log.debug("Response body : {}", this.getResponseBodyStr(result));    
            }
            
            if (!StringUtils.equals(this.getProfile(environment), "local")) {
                if (request.getRequestURI().contains(CommonConstants.API_BO_PREFIX) || request.getRequestURI().contains(CommonConstants.API_EXT_PREFIX)) {
                    this.createLogMgr(request, result);
                } else if (request.getRequestURI().contains(CommonConstants.API_FO_PREFIX)){
                    this.createLogUser(request, result);
                } 
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            stopWatch.stop();
            
            log.debug(String.format("total time seconds: %fsec", stopWatch.getTotalTimeSeconds()));
            log.debug("#################### End LoggerAspect ####################");
        }
        
        return result;
    }
    
    private void createLogMgr(HttpServletRequest request, Object result) {
        LogMgrReqDto.CreateLogMgr dto = LogMgrReqDto.CreateLogMgr.builder()
                .logDt(LocalDateTime.now())
                .userId(this.getUserId())
                .clientIp(commonUtil.getClientIP(request))
                .rqstUri(request.getRequestURI())
                .rqstMethod(request.getMethod())
                .rqstQuery(commonUtil.getDecodedQueryStr(request.getQueryString()))
                .rqstBody(this.getRequestBodyStr(request))
                .rsptBody(this.getResponseBodyStr(result))
                .build();
        
        logMgrService.createLogMgr(dto);
    }
    
    private void createLogUser(HttpServletRequest request, Object result) {
        LogUserReqDto.CreateLogUser dto = LogUserReqDto.CreateLogUser.builder()
                .logDt(LocalDateTime.now())
                .userId(this.getUserId())
                .clientIp(commonUtil.getClientIP(request))
                .rqstUri(request.getRequestURI())
                .rqstMethod(request.getMethod())
                .rqstQuery(commonUtil.getDecodedQueryStr(request.getQueryString()))
                .rqstBody(this.getRequestBodyStr(request))
                .rsptBody(this.getResponseBodyStr(result))
                .build();
        
        logUserService.createLogUser(dto);
    }
    
    private String getUserId() {
        return !Objects.isNull(SessionScopeUtil.getContextSession()) ? SessionScopeUtil.getContextSession().getUserId() : null;
    }
    
    private Map<String, Object> getRequestHeader(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            result.put(headerName, request.getHeader(headerName));
        });
        
        return result;
    }
    
    private String getRequestBodyStr(HttpServletRequest request) {
        String result = null;
        JSONObject jObj = null;
        String line = null;
        
        try {
            BufferedReader reader = request.getReader();
            
            StringBuilder sb = new StringBuilder();
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
            
            if (!StringUtils.isEmpty(sb.toString())) {
                jObj = new JSONObject(sb.toString());
                
                result = jObj.toString();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    private String getResponseBodyStr(Object data) {
        String result = null;
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        
        try {
            result = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    private String getProfile(Environment environment) {
        if (environment == null) return StringUtils.EMPTY;

        String[] activeProfiles = environment.getActiveProfiles();

        String profile = StringUtils.EMPTY;
        for (String activeProfile : activeProfiles) {
            profile = activeProfile;
            break;
        }

        return profile;
    }
    
}
