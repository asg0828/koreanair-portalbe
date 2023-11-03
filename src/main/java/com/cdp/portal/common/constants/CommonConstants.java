package com.cdp.portal.common.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonConstants {
	//common
    public static final String API_BO_PREFIX = "/bo";
    public static final String API_FO_PREFIX = "/fo";
    public static final String EMPTY_STRING = "";
	public static final String UNKNOWN = "unknown";

	//session
	public static final String SESSION_NAME = "sessionInfo";
	public static final String SECURE_SHA1 = "SHA-1";
	public static final String SECURE_SHA1PRNG = "SHA1PRNG";
//	public static final String APIGEE_ERROR_ACTOR = "cdp-portal";
	
	//HttpHeader
	public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
	public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	public static final String ACCEPT = "Accept";
	public static final String ORIGIN = "Origin";
	public static final String CONTENT_TYPE_KEY = "Content-Type";
	public static final String CONTENT_LENGTH = "Content-Length";
	public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
	public static final String CHARSET_UTF8 = "charset=UTF-8";
	public static final String CONTENTS_TYPE_JSON = "application/json";
	public static final String CONTENTS_TYPE_MULTIPART = "multipart/form-data";
	public static final String X_SESSION_ID = "x-session-id";
	public static final String X_CORRELATION_ID = "x-correlation-id";
	public static final String SOAP_ACTION = "SOAPAction";
	public static final String X_FORWARDED_FOR = "X-Forwarded-For";
	
	//ID 생성
	public static interface ID {
		//ID 타입 키, ID 구분 키
		public static final String ID_TY = "idTy";
		public static final String ID_SE = "idSe";
		//파일
		public static final String FILE = "fl";
		//메타테이블정의
		public static final String META_TBL_ID="mt";
		//메타테이블컬럼정의
		public static final String META_TBL_COL_ID="mc";
		//피쳐
		public static final String FEATURE_ID="ft";
		//공지사항
		public static final String NOTICE = "nt";
		//분석소스공유관리
		public static final String ANLZ_SOURCE = "as";
		//도움말
		public static final String HELP = "hp";
		//FAQ
		public static final String FAQ = "fq";
		//QNA
		public static final String QNA = "qn";
		//자유게시판
		public static final String FREE = "fr";
		//권한
		public static final String AUTH = "au";
		//메뉴
		public static final String MENU = "mn";
		//알림
		public static final String ALRM = "al";
		//리포트
		public static final String REPORT = "rp";
		//로그
		public static final String BATCH = "ba";
		// 화면도움말
		public static final String SCREEN_HELP = "sh";
		// 배치 관리
		public static final String BATCH_JOB = "bj";
		//부서ID
		public static final String DEPT_CD="dc";
		//게시판 ID
		public static final String BBS_ID = "si";
		//게시판 항목 ID
		public static final String BBS_ITEM = "bi";
		//가상 그룹 ID
		public static final String VGROUP = "vg";
		//자료실 ID
		public static final String DATA_ID = "dt";
		//표준용어
		public static final String STANDARD_TERMS = "st";
	}
	
	//날짜, 시간 형식
	public interface DATE_FORMAT {
		public static final String YEAR = "yy";
		public static final String DATE = "yyyy-MM-dd";
		public static final String DEFAULT_DAY = "yyyyMMdd";
		public static final String DEFAULT_TIME = "HHmmss";
		public static final String DEFAULT_DATETIME = "yyyyMMddHHmmss";
	}
	
	// Header Name
	public static final List<String> IP_HEADER = new ArrayList<>(
			Arrays.asList("x-forwarded-for", "x-real-ip", "proxy-client-ip", "wl-proxy-client-ip",
					"http_x_forwarded_for", "http_x_forwarded", "http_x_cluster_client_ip", "http_client_ip",
					"http_forwarded_for", "http_forwarded", "http_via", "remote_addr"));
}

