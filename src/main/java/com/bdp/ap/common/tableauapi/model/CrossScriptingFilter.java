package com.bdp.ap.common.tableauapi.model;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.apache.http.ProtocolException;
import org.apache.http.impl.client.RequestWrapper;
 
 
public class CrossScriptingFilter implements Filter {
 
	public FilterConfig filterConfig = null;
//	private String[] parameterNames = null;
	
    public void init(FilterConfig filterConfig) throws ServletException {
//    	String names = filterConfig.getInitParameter("parameterNames");
//    	StringTokenizer st = new StringTokenizer(names, ",");
//    	parameterNames = new String[st.countTokens()];
//    	
//    	for(int i = 0; st.hasMoreTokens(); i++){
//    		parameterNames[i] = st.nextToken();
//    	}
        this.filterConfig = filterConfig;
    }
 
    public void destroy() {
        this.filterConfig = null;
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {   	
    	chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
    }
}