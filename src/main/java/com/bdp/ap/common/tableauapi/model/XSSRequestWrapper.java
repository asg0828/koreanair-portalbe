package com.bdp.ap.common.tableauapi.model;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
 
public final class XSSRequestWrapper extends HttpServletRequestWrapper {
 
	public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
         
        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
        	String value = values[i];
        	parameter = parameter.toLowerCase();
        	if(parameter.indexOf("cont") < 0 && parameter.indexOf("mempwd") < 0){
        		value = xssHtmlEncording(values[i]);
        	}
            encodedValues[i] = stripXSS(value);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
        	return null;
        }
        parameter = parameter.toLowerCase();
    	if(parameter.indexOf("cont") < 0 && parameter.indexOf("mempwd") < 0){
    		value = xssHtmlEncording(value);
    	}
        return stripXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null){             
        	return null;         
   	  	}
        return stripXSS(value);
    }

 // for Cross-Site Scripting
 	static public String xssHtmlEncording(String sSrc) {
 		String sRes = "";
 		char ch;
 		for (int i = 0, iEnd = sSrc.length(); i < iEnd; i++) {

 			switch (ch = sSrc.charAt(i)) {
 			case '&':
 				sRes += "&#38;";
 				break;
 			case '<':
 				sRes += "&lt;";
 				break;
 			case '>':
 				sRes += "&gt;";
 				break;
 			case '#':
 				sRes += "&#35;";
 				break;
 			case '\"':
 				sRes += "&quot;";
 				break;
 			case '(':
 				sRes += "&#40;";
 				break;
 			case ')':
 				sRes += "&#41;";
 				break;
 			case '\'':
 				sRes += "&apos;";
 				break;
 			default:
 				sRes += ch;
 			}
 		}

 		return sRes;
 	}
    static final String XSSfilstr = "form,javascript,vbscript,expression,applet,meta,xml,blink,link,script,embed,object"
			+ "iframe,frame,frameset,ilayer,document,append,binding,embed,ilayer,applet,cookie,javascript";
	static public String[] XSSfilstrArray = XSSfilstr.replaceAll(" ", "").replaceAll("\\(", "\\\\(").replaceAll("\\#", "\\\\#").split(",");
	static public String[] XSSfilExceptUrls = { "http://[\\w]{2,15}\\.skhynix\\.com" };
	
    private String stripXSS(String value) {
    	//You'll need to remove the spaces from the html entities below         
    	  
    	  String str2 = "";
    	  if (XSSfilstrArray != null) {
    		  for (int x = 0; x < XSSfilstrArray.length; x++) {
    			  str2 = value.replaceAll("(?i)<\\s*" + XSSfilstrArray[x], "<_" + XSSfilstrArray[x] + "_");
    			  str2 = str2.replaceAll("(?i)<\\/\\s*" + XSSfilstrArray[x], "</_" + XSSfilstrArray[x] + "_");
    			  if (!str2.equals(value)) {
    				  // logger.info("Filter  : [" + XSSfilstrArray[x] + "]");
    				  // logger.info("Src     : [" + str + "]");
    				  // logger.info("replace : [" + str2 + "]");
    				  value = str2;
    			  }
    		  }
    		  /* 특정 사이트 핕터 pass */
    		  if (value.toLowerCase().indexOf("href") > 0) {
    			  String regex = "(?ix)href[=\"'\\s]+(https?|ftp|file)://[^\\s>]*";
    			  Pattern pattern = Pattern.compile(regex);
    			  Matcher m = pattern.matcher(value);
    			  while (m.find()) {
    				  for (int i = 0; i < m.groupCount(); i++) {
    					  int nMatched = 0;
    					  for (String urls : XSSfilExceptUrls) {
    						  if (m.group(i).matches("(?i).*" + urls + ".*")) {
    							  nMatched++;
    							  break;
    						  }
    					  }
    					  if (nMatched < 1) {
    						  // logger.info("Filter  : [href]");
    						  // logger.info("Src     : [" + str + "]");
    						  value = value.replace(m.group(i), m.group(i).replaceAll("(?i)href", "_href_"));
    						  // logger.info("replace : [" + str + "]");
    					  }
    				  }
    			  }
    		  }
    	  }
    	  
    	  //value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");         
    	  
    	  //value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");         
    	  
    	  //value = value.replaceAll("'", "& #39;");        
    	  
    	  //value = value.replaceAll("eval\\((.*)\\)", "");         
    	  
    	  //value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");         
    	  
    	  //value = value.replaceAll("script", "");
    	  
        /*if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }*/
        return value;
    }
}