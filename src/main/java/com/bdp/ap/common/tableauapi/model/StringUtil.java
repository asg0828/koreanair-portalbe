package com.bdp.ap.common.tableauapi.model;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class StringUtil extends TagSupport {

	private static final long serialVersionUID = -112174328123953375L;
	
	private String actionGb;		//액션구분(replace, comma...)
	private String strValue;		//문자열
	private String searchChar;		//바꿀문자
	private String replaceChar;		//바뀔문자
	private String returnValue;		//화면에 보여질 값
	private int currentPage;		//현재페이지
	private int totalCount;			//총카운트
	private int listCount;			//화면에 보여줄 리스트 수
	private int length;				//말줄임 길이
	
	@Override
	public int doEndTag() throws JspException {
	
		try {
			JspWriter out = pageContext.getOut();
			
			if(actionGb.equals("replace")){
				stringReplace();
			}else if(actionGb.equals("email")){
				eMailOptions();
			}else if(actionGb.equals("tel")){
				telOptions();
			}else if(actionGb.equals("hp")){
				hpOptions();
			}else if(actionGb.equals("content")){
				contentReplace();
			}else if(actionGb.equals("page")){
				pageString();
			}else if(actionGb.equals("ellipsis")){
				ellipsis();
			}else if(actionGb.equals("comma")){
				comma();
			}
			
			out.println(returnValue);
			
			return EVAL_PAGE;
		} catch (Exception e) {
			throw new JspException("Error: " + e.getMessage());
		}
	}
	
	/**
	 * 문자 바꾸기
	 */
	public void stringReplace(){
		returnValue = "";
		returnValue =  strValue.replaceAll(searchChar, replaceChar);
	}
	
	/**
	 * 게시판 내용 특수문자 변경
	 */
	public void contentReplace(){
		strValue = strValue.replaceAll("&lt;", "<");
		strValue = strValue.replaceAll("&quot;", "\"");
		strValue = strValue.replaceAll("&amp;", "&");
		returnValue = strValue.replaceAll("&gt;", ">");
		
	}
	
	/**
	 * 화면 페이지 표시
	 */
	public void pageString(){
		int totalPage = (int)Math.ceil((double)totalCount / listCount);
		
		returnValue = "총"+totalCount+"건 ("+currentPage+"page/"+totalPage+"page)";
	}
	
	/**
	 * 말줄임
	 */
	public void ellipsis(){
		int strLen = strValue.length();
		if(strLen < length){
			returnValue = strValue;
		}else{
			returnValue = strValue.substring(0, length) + "...";
		}
	}
	
	public void comma(){
		DecimalFormat df = new DecimalFormat("#,##0");
		returnValue = "(총 "+df.format(totalCount)+"건)";
	}

	/**
	 * 메일주소 option
	 */
	public void eMailOptions(){
		returnValue = "";
		returnValue += "<option value=\"naver.com\">naver.com</option>";
		returnValue += "<option value=\"hanmail.net\">hanmail.net</option>";
		returnValue += "<option value=\"nate.com\">nate.com</option>";
		returnValue += "<option value=\"gmail.com\">gmail.com</option>";
		returnValue += "<option value=\"hotmail.com\">hotmail.com</option>";
	}
	
	/**
	 * 지역번호 option
	 */
	public void telOptions(){
		returnValue = "";
		returnValue += "<option value=\"02\">02</option>";
		returnValue += "<option value=\"031\">031</option>";
		returnValue += "<option value=\"032\">032</option>";
		returnValue += "<option value=\"033\">033</option>";
		returnValue += "<option value=\"041\">041</option>";
		returnValue += "<option value=\"042\">042</option>";
		returnValue += "<option value=\"043\">043</option>";
		returnValue += "<option value=\"044\">044</option>";
		returnValue += "<option value=\"051\">051</option>";
		returnValue += "<option value=\"052\">052</option>";
		returnValue += "<option value=\"053\">053</option>";
		returnValue += "<option value=\"054\">054</option>";
		returnValue += "<option value=\"055\">055</option>";
		returnValue += "<option value=\"061\">061</option>";
		returnValue += "<option value=\"062\">062</option>";
		returnValue += "<option value=\"063\">063</option>";
		returnValue += "<option value=\"064\">064</option>";
		returnValue += "<option value=\"070\">070</option>";
		returnValue += "<option value=\"080\">080</option>";
	}
	
	/**
	 * 휴대전화번호 option
	 */
	public void hpOptions(){
		returnValue = "";
		returnValue += "<option value=\"010\">010</option>";
		returnValue += "<option value=\"011\">011</option>";
		returnValue += "<option value=\"016\">016</option>";
		returnValue += "<option value=\"017\">017</option>";
		returnValue += "<option value=\"018\">018</option>";
		returnValue += "<option value=\"019\">019</option>";
	}
	
	public void setActionGb(String actionGb) {
		this.actionGb = actionGb;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public void setSearchChar(String searchChar) {
		this.searchChar = searchChar;
	}

	public void setReplaceChar(String replaceChar) {
		this.replaceChar = replaceChar;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	* 해당 Object를 String으로 형변형하여 리턴한다.
	* <p>
	* @param    obj  변환할 객체
	* @return   	변환된 문자열 
	*/
	public static String nvl(Object obj){
		String str="";
		
		if (obj == null)
			str = "";
		else{
			try{				
				str = (String)obj;	
			}catch(Exception e){
				try{
					str = obj.toString();				
				}catch(Exception ex){
					str = "";
				}
			}
		}
		
		if(str.equals("null") || (str.length() == 0))
			str = "";
		
		return str;
	}
	public static String nvl(Object obj, String nullvalue) {

		if ((obj == null) || (obj.equals(""))) {
			return nullvalue;
		}
		else {
			 // 공백제거 한다.
			return obj.toString().trim();
		}
	}
	
	public static String nvl(String str) {
		if ((str == null) || (str == "") || (str.equals("null")) || (str.length() == 0)) {
			return "";
		}
		else {
			return new String(str);
		}
	}
	public static String nvl(String str, String nullvalue) {

		if ((str == null) || (str == "") || (str.equals("null")) || (str.length() == 0)) {
			return nullvalue;
		}
		else {

			str = str.trim(); // 공백제거 한다.
			return new String(str);
		}
	}

	public static int nvl(String str, int nullvalue) {
		if ((str == null) || str.equals("") || (str.equals("null")) || (str.length() == 0)) {
			return nullvalue;
		}
		else {
			return Integer.parseInt(str);
		}
	}
	public static int nvl(Object obj, int nullvalue) {
		if ((obj == null) || obj.equals("") || obj.equals("null")) {
			return nullvalue;
		}
		else {
			return Integer.parseInt(obj.toString());
		}
	}

	public static long nvl(String str, long nullvalue) {
		if ((str == null) || (str == "") || (str.equals("null")) || (str.length() == 0)) {
			return nullvalue;
		}
		else {
			return Long.parseLong(str);
		}
	}
	
	public static double nvl(String str, double nullvalue) {
		if ((str == null) || (str == "") || (str.equals("null")) || (str.length() == 0)) {
			return nullvalue;
		}
		else {
			return Double.parseDouble(str);
		}
	}
	
	public static int nvl(Integer val, int nullvalue) {
		if(val == null)
			return nullvalue;
		else
			return val.intValue();
	}
	
	/**
	 * 넘겨받은 Object 값이 null 이면 true 아니면 false
	 * 
	 *  @param object
	 *  @return boolean
	 */
	public static boolean bNvl(Object obj) {

		if ((obj == null) || (obj.equals(""))) {
			return true;
		}
		else {
			 // 공백제거 한다.
			return false;
		}
	}
	
	/**
	 * 주민번호 유효성 체크
	 * @param 주민번호 
	 * @return boolean
	 */
	public static boolean isJuminNumber(String number) {
        String regEx = "\\d{6}\\-[1-4]\\d{6}";       
        return Pattern.matches(regEx, number);
    }
}
