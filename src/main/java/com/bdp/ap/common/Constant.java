package com.bdp.ap.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.quartz.Job;

import com.bdp.ap.schedule.job.JobDeptCl;
import com.bdp.ap.schedule.job.JobLogSys;
import com.bdp.ap.schedule.job.JobLogToFile;
import com.bdp.ap.schedule.job.JobUserPhoto;

import software.amazon.awssdk.regions.Region;

/**
 * 공통 상수 처리
 */
public interface Constant {

	// common
	public static final String DEFAULT_USER_PHOTO = "../../images/icon_gnb_user.png";
	//public static final String DEFAULT_USER_PHOTO = "../../images/icon_top_user.png";
	public static final String DEFAULT_TABLEAU_PREVIEW = "../../images/img_noimg.png";
	public static final String LOGIN_MESSAGE ="접근 권한이 없습니다.\\n담당자에게 연락주십시오.\\n담당자: 데이터사이언스팀 박성근/김현우/이경재/장수정";//임시 로그인 메시지
	public static final String FAIL_PASSWORD = "비밀번호가 다릅니다.\\n비밀번호를 확인해 주세요.";//임시 로그인 메시지
	public static final String EMPTY_STRING = "";
	public static final String UNKNOWN = "unknown";
	public static final String RESULT = "result";
	public static final String DETAIL = "detail";
	public static final String RESULT_CODE = "rsltCode";
	public static final String RESULT_MESSAGE = "rsltMsg";
	public static final String ERROR_MESSAGE = "errorMsg";
	public static final String YES = "Y";
	public static final String NO = "N";

	//File
	public interface File {
		public static final String AWS_S3 = "S3";
		public static final String PHOTO = "PHOTO";
		public static final String PREVIEW = "PREVIEW";
		public static final String VIEW = "VIEW";
		public static final String BOARD = "BOARD";
		public static final String BOARDN = "BOARD_N";//공지사항
		public static final String BOARDF = "BOARD_F";//FAQ
		public static final String BOARDH = "BOARD_H";//도움말
		public static final String BOARDA = "BOARD_A";//분석소스공유
		public static final String BOARDQ = "BOARD_Q";//QNA
		public static final String BOARDD = "BOARD_D";//자료실
		public static final String BOARDS = "BOARD_S";//표준계수
		public static final String BOARDSH = "BOARD_SH";//화면도움말
		public static final String EDITOR = "EDITOR";
	}

	// DB
	public interface DB {
		public static final String FAIL = "Fail"; // DB 등록 실패시 리턴 메시지
		public static final String UPDATE = "Update"; // DB Update 성공시 리턴 메시지
		public static final String DELETE = "Delete"; // DB Delete 성공시 리턴 메시지
		public static final String INSERT = "Insert"; // DB Insert 성공시 리턴 메시지
		public static final String USE_ROLE_ID = "UseRoleId"; // Role권한이 사용중일때 메시지
		public static final String USE_CODE_ID = "UseCodeId"; // 그룹코드가 사용중일떄
	}

	//spring profile
	public interface Profile {
		public static final String LOCAL = "local";
		public static final String DEV = "dev";
		public static final String PROD = "prod";
	}

	// Jasypt
	public interface Jasypt {
		public static final String KEY = "D9QkX8wx3yhCT6Wm";
		public static final String ALGORITHM = "PBEWITHHMACSHA512ANDAES_256";
	}
	
	// Code
	public interface Code {
		static final String PORTLET_TYPE = "PORTLET_TYPE";
		static final String PL_NOTICE = "PL_NOTICE";
		static final String PL_FAQ = "PL_FAQ";
		static final String PL_HELP = "PL_HELP";
		static final String PL_ANAL_SRC_SHARE = "PL_ANAL_SRC_SHARE";
		static final String PL_QNA = "PL_QNA";
		static final String PL_DATAROOM = "PL_DATAROOM";
		static final String MP_RECENT_LIST = "MP_RECENT_LIST";
		static final String RL_RECENT_LIST = "RL_RECENT_LIST";
		static final String RL_POP_LIST = "RL_POP_LIST";
		static final String MY_REPORT_RECENT = "MY_REPORT_RECENT";
		static final String RS_SELECTED_REPORT = "RS_SELECTED_REPORT";
		static final String AM_RECENT_LIST = "AM_RECENT_LIST";
		static final String SC_RECENT_LIST = "SC_RECENT_LIST";
		static final String RI_RECENT_LIST = "RI_RECENT_LIST";
	}

	//URL Pattern : 메뉴 조회 및 권한 확인 시 사용
	public interface UrlPattern {
		public static final Pattern MENU_CHECK_PATTERN = Pattern.compile("(/detail$|/detail/|/regist$|/regist/|/modify$|/modify/)");
		public static final Pattern AUTH_CHECK_PATTERN = Pattern.compile("(/detail$|/detail/|/regist$|/regist/|/modify$|/modify/|/insert$|/insert/|/update$|/update/|/delete$|/delete/)");
		public static final Pattern AUTH_IGNORE_PATTERN = Pattern.compile("(^/error/|^/file/|^/cmm/|^/popup/|/popup$|/ajax/|/ajax$|/excel/|/excel$|/complete/|/complete$)");
		public static final Map<String,String> AUTH_CHECK_TYPE = new HashMap<String,String>() {
			private static final long serialVersionUID = -1145562349837093088L;
			{
				put("detail","detail");
				put("regist","insert");
				put("modify","update");
				put("insert","insert");
				put("update","update");
				put("delete","delete");
			}
		};
	}

	// Header Name
	public static final List<String> IP_HEADER = new ArrayList<>(
			Arrays.asList("x-forwarded-for", "x-real-ip", "proxy-client-ip", "wl-proxy-client-ip",
					"http_x_forwarded_for", "http_x_forwarded", "http_x_cluster_client_ip", "http_client_ip",
					"http_forwarded_for", "http_forwarded", "http_via", "remote_addr"));

	// Error Page URL
	public interface ERROR_URL {
		public static final String ERROR = "/error/error";//별도로 지정되지 않은 에러
		public static final String NOT_FOUND = "/error/notFound";//404 : 요청 URL 없는 경우
		public static final String METHOD_NOT_ALLOWED = "/error/methodNotAllowed";//405 : 요청 메소드 없는 경우
		public static final String INTERNAL_SERVER_ERROR = "/error/internalServerError";//500 : 요청 처리중 오류 발생한 경우
		public static final String UNAUTHORIZED = "/error/unauthorized";//401 : 사용자 미인증인 경우
		public static final String FORBIDDEN = "/error/forbidden";//403 : 접근 권한이 없는 경우
	}

	// 로그인 처리 메시지
	public interface LoginMessage {
		public static final String ACCOUNT_DISABLE = "ACCOUNT_DISABLE";
		public static final String ACCOUNT_EXPIRE = "ACCOUNT_EXPIRE";
		public static final String ACCOUNT_LOCK = "ACCOUNT_LOCK";
		public static final String AUTH_FAIL = "AUTH_FAIL";
		public static final String LOGIN_FAIL = "LOGIN_FAIL";
		public static final String SSO_CONNECT_FAIL = "SSO_CONNECT_FAIL";
		public static final String SSO_LOGIN_FAIL = "SSO_LOGIN_FAIL";
		public static final String UDB_CONNECT_FAIL = "UDB_CONNECT_FAIL";
		public static final String UDB_LOGIN_FAIL = "UDB_LOGIN_FAIL";
		public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
	}


	// 로그 항목
	public interface LOG {
		public static final String CLIENT_IP = "client_ip"; 		// 클라이언트 아이피
		public static final String SERVER_IP = "server_ip"; 		// 서버 IP
		public static final String CONTROLLER_NM = "controller_nm";	// 호출 컨트롤러(호출 클래스)
		public static final String METHOD_NM = "method_nm";			// 호출 메소드
		public static final String PARAMS = "msg";				// 파라미터
		public static final String REQUEST_URI = "rqst_uri";		// 호출 URL
		public static final String HTTP_METHOD = "rqst_method";		// HTTP 메소드
		public static final String USER_INFO = "user_info";			// 사용자 정보
		public static final String LOG_TIME = "log_time";			// 로그일시
	}

	// HR 연동 배치
	public interface HR {
		public static final char SEPERATOR = '|';
		public static final String RSLT = "result";
		public static final String CHECK_FILES = "checkFiles";
		public static final String PARSING_FILES = "parsingFiles";
		public static final String BACKUP_FILES = "backupFiles";
		public static final String USER = "EMP";
		public static final String HDEPT = "HDEPT";
		public static final String DEPT = "DEPT";
		public static final String PSTN = "POSITION";
		public static final String[] HEADER_USER = {"userId","userNm","mobiNo","email","pstnCode","deptCode","dutySe","adofDeptCode"};
		public static final String[] HEADER_HDEPT = {"hdeptNm","hdeptCode"};
		public static final String[] HEADER_DEPT = {"deptNm","deptCode","hdeptCode"};
		public static final String[] HEADER_PSTN = {"pstnCode","pstnNm"};
		//부서 분류
		public static final String DEPT_CL = "deptinfo";
		public static final String[] HEADER_DEPT_CL = {"groupCode","deptCode","deptNm","upGroupCode","ordSeq","lv","deptPath"};
	}

	//날짜, 시간 형식
	public interface DATE_FORMAT {
		public static final String YEAR = "yy";
		public static final String DATE = "yyyy-MM-dd";
		public static final String DEFAULT_DAY = "yyyyMMdd";
		public static final String DEFAULT_TIME = "HHmmss";
		public static final String DEFAULT_DATETIME = "yyyyMMddHHmmss";
	}

	//ID
	public static interface ID {
		//ID 타입 키, ID 구분 키
		public static final String ID_TY = "idTy";
		public static final String ID_SE = "idSe";
		//파일
		public static final String FILE = "fl";
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
		//라이선스
		public static final String LICENSE = "lc";
		//알림
		public static final String ALRM = "al";
		//외부 시스템
		public static final String EXTERNAL = "ex";
		//외부 데이터
		public static final String EXTERNAL_DATA = "ed";
		//업무 카테고리
		public static final String WORK = "wk";
		//프로젝트
		public static final String PROJECT = "pj";
		//리포트
		public static final String REPORT = "rp";
		//승인
		public static final String APPROVAL = "ar";
		//EAI SOCKET
		public static final String EAI_SOCKET = "es";
		//로그
		public static final String BATCH = "ba";
		// 화면도움말
		public static final String SCREEN_HELP = "sh";
		// 시각화보고서관리
		public static final String VISIBLE_REPORT = "vr";
		// 배치 관리
		public static final String BATCH_JOB = "bj";

		//표준계수ID
		public static final String STD_COEFFT_ID="sc";

		//보고서정보ID
		public static final String RPT_INF_ID="rf";

		//분석마트ID
		public static final String ANLZ_MRT_ID="am";

		//보고서ID
		public static final String RPT_ID="ri";

		//디멘젼ID
		public static final String DIM_ID="di";

		//메젼ID
		public static final String MES_ID="mi";

		//키항목ID
		public static final String KEY_ITM_ID="ki";

		//데이터마트ID
		public static final String DT_MRT_ID="dm";

		//BizMeta FILE ID
		public static final String BIZ_FILE_ID="bf";

		//관리사용자ID
		public static final String MNG_USER_ID="mu";

		//기피부서ID
		public static final String AVOID_DEPT_CD="ad";

		//관리부서ID
		public static final String MNG_DEPT_CD="md";

		//검색태그ID
		public static final String FND_TAG_ID="ft";

		//관리정보ID
		public static final String MNG_INF_ID="mf";

		//메타테이블ID
		public static final String META_TBL_ID="mt";


		//메타컬럼ID
		public static final String META_COL_ID="mc";

		//포틀릿ID
		public static final String PORTLET_ID="pt";
		//포틀릿 관리 ID
		public static final String PORTLET_MNG_ID="pm";
		
		//부서ID
		public static final String DEPT_CD="dc";
		
		//직위ID
		public static final String PSTN_CD="pc";
		
		//직급ID
		public static final String RANK_CD="rc";
		
		//직책ID
		public static final String DUTY_CD="tc";
		
		//게시판 ID
		public static final String BBS_ID = "si";
		
		//게시판 항목 ID
		public static final String BBS_ITEM = "bi";

		//가상 그룹 ID
		public static final String VGROUP = "vg";
		
		//결재템플릿 ID
		public static final String APRV_TEMPLATE_ID = "at";

		//결재라인 ID
		public static final String APRV_LINE_ID = "ag";

		//결재요청 ID
		public static final String APRV_RQST_ID = "rq";
		
		//자료실 ID
		public static final String DATA_ID = "dt";

		//표준용어
		public static final String STANDARD_TERMS = "st";
		
		//협업공간
		public static final String COLLABORATE_SPACE = "cs";
		
		//협업공간TASK
		public static final String COLLABORATE_SPACE_TASK = "ck";
		public static final String COMMENT = "co";
		
	}

	// JSON KEY
	public interface KEY {
		public static final String USER_ID = "userId";
		public static final String USER_NM = "userNm";
		public static final String DEPT_CODE = "deptCode";
		public static final String PSTN_CODE = "pstnCode";
	}

	//프로젝트
	public interface PROJECT {
		//프로젝트 시각화 타입
		public static final String TYPE_VW = "VW";
	}

	//Schedule Job Class
	public static final Map<String,Class<? extends Job>> SCHEDULE_JOB = new HashMap<String,Class<? extends Job>>() {
		private static final long serialVersionUID = 6455119810429953719L;
		{
			put("DEPT_CL",JobDeptCl.class);
			put("USER_PHOTO",JobUserPhoto.class);
			put("LOG_TO_FILE",JobLogToFile.class);
			put("LOG_SYS",JobLogSys.class);
		}
	};

	//AWS
	public interface AWS {
		//public static final String LOCAL_ACCESS_NAME = "AKIA3TQQXNCNIJU2H46J";//713033
		//public static final String LOCAL_SECRET_NAME = "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";//713033
		public static final String LOCAL_ACCESS_NAME = "AKIA3TQQXNCNMRRQEK3G";//713054
		public static final String LOCAL_SECRET_NAME = "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";//713054
		public static final Region REGION = Region.AP_NORTHEAST_2;
		public static final String SECRET_NAME_DEV = "W-SHI-AN2-DAP-DEV-SEM-PT-ADM";
		public static final String SECRET_NAME_PRD = "W-SHI-AN2-DAP-PRD-SEM-PT-ADM";
		public static final String JASYPT_KEY_NAME_DEV = "DAP-PTL-DEV-JASYPT-KEY";
		public static final String JASYPT_KEY_NAME_PRD = "DAP-PTL-PRD-JASYPT-KEY";

		//Lambda Function Name
		public interface LambdaName {
			//프로젝트 생성
			public static final String PROJECT_CREATE = "W-SHI-AN2-DAP-DEV-LM-SBX-PROJECT-CREATE";
			//프로젝트 삭제
			public static final String PROJECT_DELETE = "W-SHI-AN2-DAP-DEV-LM-SBX-PROJECT-DELETE";
			//프로젝트 수정
			public static final String PROJECT_UPDATE = "W-SHI-AN2-DAP-DEV-LM-SBX-PROJECT-UPDATE";
		}
	}

	//Goldwing 승인타입
	/* T_APRV_RQST 승인 요청, T_APRV_RQST_DTL 승인 요청 상세
	 *   1. BI 보고서 게시(등록) 요청.html
	 *   2. BI 보고서 권한등록 요청.html
	 *   3. 프로젝트 등록(sandbox 지원요청).html
	 *   4. 권한변경요청.html
	 *   5. 데이터 자원 요청.html
	 */
	public interface GoldwingAprvType {
		public static final String REPORT_OPEN 		= "aprvReportOpen";		//태블로에서 땡겨온 보고서를 오픈하려 할때 결재
		public static final String REPORT_ROLE 		= "aprvReportRole";		//개인별 보고서 권한부여 요청시 결재
		public static final String PROJECT 			= "aprvProject";		//프로젝트 생성시 결재
		public static final String PROJECT_NIGHT_USE = "aprvProjectNight";//프로젝트 야간 사용 결재
		public static final String AUTH_CHANGE 		= "aprvAuthChange";		//사용자의 권한변경시 결재(일반사용자<=>분석사용자)
		public static final String DATA_RESOURCE 	= "aprvDataResource";	//데이터(테이블)에 대해 권한 요청
		public static final String MODEL_DEPLOYED   = "aprvModelDeploy"; //모델 배포 승인
	}

	// EAI Socket Server JSONbject Key
	public interface SocketServer {
		//request data JSONObject
		public static final String APRV_TYPE = "aprType";
		public static final String CALL_USER = "callUser";
		public static final String CALL_COMPANY = "callCompany";
		public static final String APRV_ID = "aprvId";
		public static final String APRV_CL = "aprvCl";
		public static final String DOC_ID = "docId";
		public static final String DOC_NO = "docNum";
		public static final String FORM_ID = "formId";
		public static final String USER_ID = "userId";
		public static final String USER_NM = "userNm";
		public static final String DEPT_CODE = "deptCode";
		public static final String DEPT_NM = "deptNm";
		public static final String APRV_DTL_INFO = "aprvDtlInfo";
		public static final String APRV_SEQ = "aprvSeq";
		public static final String APRV_STAT = "aprvStat";
		public static final String APRV_SE = "aprvSe";
		public static final String APRV_DT = "aprvDt";
		public static final String APRVR_ID = "aprvrId";
		public static final String APRVR_NM = "aprvrNm";

		//승인 상태
		public static final String STAT_APPLY = "A";
		public static final String STAT_APRV = "C";
		public static final String STAT_REJECT = "R";

		//response data JSONObject
		public static final String RESULT = "result";
		public static final String ERROR_REASON = "errorReason";
		public static final String HTML_DATA = "htmlData";
		public static final String TITLE = "subject";
		public static final String SUB_TITLE = "subjectSub";
		public static final String RCV_DEPT_CODE = "recvDeptCode";
		public static final String RCV_DEPT_NM = "recvDeptNm";

		//상수값
		public static final String SUCCESS = "OK";
		public static final String ERROR = "ERROR";
		public static final String ERROR_MSG = "전자결재 처리 중 오류가 발생하였습니다.";
	}

}
