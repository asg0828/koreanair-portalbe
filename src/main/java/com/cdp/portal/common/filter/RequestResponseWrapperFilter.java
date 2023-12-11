package com.cdp.portal.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cdp.portal.common.wrapper.CachingRequestWrapper;
import com.cdp.portal.common.wrapper.CachingResponseWrapper;

@Component
public class RequestResponseWrapperFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(new CachingRequestWrapper(request), new CachingResponseWrapper(response));
    }
    
}
