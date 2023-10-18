-- sample data

SET SEARCH_PATH TO ptl;

-- 연계    연계 부서
TRUNCATE TABLE T_IF_DEPT;



-- 연계    연계 본부
TRUNCATE TABLE T_IF_HDEPT;



-- 연계    연계 직위
TRUNCATE TABLE T_IF_PSTN;



-- 연계    연계 사용자
TRUNCATE TABLE T_IF_USER;



-- 연계    연계 부서 분류
TRUNCATE TABLE T_IF_DEPT_CL;



-- 연계    연계 태블로 프로젝트
TRUNCATE TABLE T_IF_TABLEAU_PROJECT;



-- 연계    연계 태블로 워크북
TRUNCATE TABLE T_IF_TABLEAU_WORKBOOK;



-- 연계    연계 태블로 뷰
TRUNCATE TABLE T_IF_TABLEAU_VIEW;



-- 연계    연계 태블로 사용자
TRUNCATE TABLE T_IF_TABLEAU_USER;



-- 로그    로그 참조 정보
TRUNCATE TABLE T_LOG_REF_INFO;
INSERT INTO T_LOG_REF_INFO (SYS_SE,CONTROLLER_NM,METHOD_NM,PROGRAM_NM,RGST_ID,RGST_DT) VALUES
('MGR','LoginSuccessLoggingAuthenticationSuccessHandler','onAuthenticationSuccess','로그인 성공','SYSTEM',NOW()),
('MGR','LogoutHandler','onLogoutSuccess','로그아웃','SYSTEM',NOW()),
('MGR','FaqController','faq','FAQ 목록','SYSTEM',NOW()),
('MGR','FaqController','regist','FAQ 등록','SYSTEM',NOW()),
('MGR','FaqController','modify','FAQ 수정','SYSTEM',NOW()),
('MGR','FaqController','insert','FAQ 등록','SYSTEM',NOW()),
('MGR','FaqController','update','FAQ 수정','SYSTEM',NOW()),
('MGR','FaqController','delete','FAQ 삭제','SYSTEM',NOW()),
('MGR','NoticeController','notice','공지사항 목록','SYSTEM',NOW()),
('MGR','NoticeController','select','공지사항 상세','SYSTEM',NOW()),
('MGR','NoticeController','insert','공지사항 등록','SYSTEM',NOW()),
('MGR','NoticeController','update','공지사항 수정','SYSTEM',NOW()),
('MGR','NoticeController','delete','공지사항 삭제','SYSTEM',NOW()),
('MGR','QnaController','qna','Q&A 목록','SYSTEM',NOW()),
('MGR','QnaController','qnaPost','Q&A 목록','SYSTEM',NOW()),
('MGR','QnaController','qnaRegist','Q&A 등록','SYSTEM',NOW()),
('MGR','QnaController','qnaUpdate','Q&A 수정','SYSTEM',NOW()),
('MGR','QnaController','delete','Q&A 삭제','SYSTEM',NOW()),
('MGR','CodeController','code','코드 등록','SYSTEM',NOW()),
('MGR','CodeController','groupSave','코드 그룹 ID 등록','SYSTEM',NOW()),
('MGR','CodeController','groupDelete','코드 그룹 ID 삭제','SYSTEM',NOW()),
('MGR','CodeController','codesForGroupCd','코드 상세','SYSTEM',NOW()),
('MGR','CodeController','save','코드 등록','SYSTEM',NOW()),
('MGR','CodeController','delete','코드 삭제','SYSTEM',NOW()),
('MGR','IndexController','index','HOME','SYSTEM',NOW()),
('MGR','LoginController','login','로그인','SYSTEM',NOW()),
('MGR','ExtrnlController','extrnl','외부 시스템 목록','SYSTEM',NOW()),
('MGR','ExtrnlController','save','외부 시스템 등록','SYSTEM',NOW()),
('MGR','ExtrnlController','delete','외부 시스템 삭제','SYSTEM',NOW()),
('MGR','ExtrnlController','select','외부 시스템 상세','SYSTEM',NOW()),
('MGR','FileController','upload','파일 업로드','SYSTEM',NOW()),
('MGR','FileController','download','파일 다운로드','SYSTEM',NOW()),
('MGR','FileController','delete','파일 삭제','SYSTEM',NOW()),
('MGR','LicenseController','list','라이선스 목록','SYSTEM',NOW()),
('MGR','LicenseController','insert','라이선스 등록','SYSTEM',NOW()),
('MGR','LicenseController','delete','라이선스 삭제','SYSTEM',NOW()),
('MGR','LicenseController','select','라이선스 상세','SYSTEM',NOW()),
('MGR','LogController','list','로그 목록','SYSTEM',NOW()),
('MGR','MemberController','list','사용자 목록','SYSTEM',NOW()),
('MGR','MemberController','select','사용자 상세','SYSTEM',NOW()),
('MGR','MemberController','insert','사용자 수정','SYSTEM',NOW()),
('MGR','MemberController','popupList','사용자 검색','SYSTEM',NOW()),
('MGR','MenuController','menu','메뉴 목록','SYSTEM',NOW()),
('MGR','MenuController','save','메뉴 등록','SYSTEM',NOW()),
('MGR','MenuController','delete','메뉴 삭제','SYSTEM',NOW()),
('MGR','MenuController','menuPopup','메뉴 검색','SYSTEM',NOW()),
('MGR','MenuAuthController','menu','메뉴 권한 목록','SYSTEM',NOW()),
('MGR','MenuAuthController','AuthSearch','메뉴 권한 목록','SYSTEM',NOW()),
('MGR','ProjectController','project','프로젝트 목록','SYSTEM',NOW()),
('MGR','ProjectController','projectRegist','프로젝트 등록','SYSTEM',NOW()),
('MGR','ProjectController','select','프로젝트 상세','SYSTEM',NOW()),
('MGR','ProjectController','insert','프로젝트 등록','SYSTEM',NOW()),
('MGR','ProjectController','update','프로젝트 수정','SYSTEM',NOW()),
('MGR','ProjectController','delete','프로젝트 삭제','SYSTEM',NOW()),
('MGR','ReportAuthController','reportAuth','보고서 권한 목록','SYSTEM',NOW()),
('MGR','ReportAuthController','reportAuthRegist','보고서 권한 등록','SYSTEM',NOW()),
('MGR','ReportAuthController','select','보고서 권한 상세','SYSTEM',NOW()),
('MGR','ReportAuthController','insert','보고서 권한 등록','SYSTEM',NOW()),
('MGR','ReportAuthController','update','보고서 권한 수정','SYSTEM',NOW()),
('MGR','ReportAuthController','delete','보고서 권한 삭제','SYSTEM',NOW()),
('MGR','ReportController','report','보고서 목록','SYSTEM',NOW()),
('MGR','ReportController','reportRegist','보고서 등록','SYSTEM',NOW()),
('MGR','ReportController','select','보고서 상세','SYSTEM',NOW()),
('MGR','ReportController','insert','보고서 등록','SYSTEM',NOW()),
('MGR','ReportController','update','보고서 수정','SYSTEM',NOW()),
('MGR','ReportController','delete','보고서 삭제','SYSTEM',NOW()),
('MGR','ReportController','reportView','보고서 상세','SYSTEM',NOW()),
('MGR','ReportController','reportPreview','보고서 상세','SYSTEM',NOW()),
('MGR','ReportExtrnlController','reportExtrnl','보고서 외부 배포 목록','SYSTEM',NOW()),
('MGR','ReportExtrnlController','reportExtrnlRegist','보고서 외부 배포 등록','SYSTEM',NOW()),
('MGR','ReportExtrnlController','select','보고서 외부 배포 상세','SYSTEM',NOW()),
('MGR','ReportExtrnlController','insert','보고서 외부 배포 등록','SYSTEM',NOW()),
('MGR','ReportExtrnlController','update','보고서 외부 배포 수정','SYSTEM',NOW()),
('MGR','ReportExtrnlController','delete','보고서 외부 배포 삭제','SYSTEM',NOW()),
('MGR','RoleController','list','권한 목록','SYSTEM',NOW()),
('MGR','RoleController','insert','권한 등록','SYSTEM',NOW()),
('MGR','RoleController','delete','권한 삭제','SYSTEM',NOW()),
('MGR','RoleController','select','권한 상세','SYSTEM',NOW()),
('MGR','WrkCatController','wrkCat','업무 카테고리 목록','SYSTEM',NOW()),
('MGR','WrkCatController','save','업무 카테고리 등록','SYSTEM',NOW()),
('MGR','WrkCatController','delete','업무 카테고리 삭제','SYSTEM',NOW()),
('MGR','WrkCatController','select','업무 카테고리 상세','SYSTEM',NOW()),
('USER','LoginSuccessLoggingAuthenticationSuccessHandler','onAuthenticationSuccess','로그인 성공','SYSTEM',NOW()),
('USER','LogoutHandler','onLogoutSuccess','로그아웃','SYSTEM',NOW()),
('USER','BizwordController','bizword','업무 표준 용어 목록','SYSTEM',NOW()),
('USER','BizwordController','bizwordSearch','업무 표준 용어 검색','SYSTEM',NOW()),
('USER','BizwordController','metadataDetail','메타 데이터 상세','SYSTEM',NOW()),
('USER','BizwordController','metadataNewPopup','메타 데이터 검색','SYSTEM',NOW()),
('USER','BizwordController','bizwordRgst','업무 표준 용어 등록','SYSTEM',NOW()),
('USER','BizwordController','bizwordExtrnlData','외부 데이터 구매 자산 관리','SYSTEM',NOW()),
('USER','FaqController','faq','FAQ 목록','SYSTEM',NOW()),
('USER','FaqController','select','FAQ 상세','SYSTEM',NOW()),
('USER','NoticeController','notice','공지사항 목록','SYSTEM',NOW()),
('USER','NoticeController','select','공지사항 상세','SYSTEM',NOW()),
('USER','QnaController','qna','Q&A 목록','SYSTEM',NOW()),
('USER','QnaController','notice','Q&A 목록','SYSTEM',NOW()),
('USER','QnaController','select','Q&A 상세','SYSTEM',NOW()),
('USER','QnaController','regist','Q&A 등록','SYSTEM',NOW()),
('USER','QnaController','insert','Q&A 등록','SYSTEM',NOW()),
('USER','QnaController','modify','Q&A 수정','SYSTEM',NOW()),
('USER','QnaController','update','Q&A 수정','SYSTEM',NOW()),
('USER','QnaController','delete','Q&A 삭제','SYSTEM',NOW()),
('USER','CodeController','code','코드 목록','SYSTEM',NOW()),
('USER','CodeController','groupSave','코드 그룹 ID 등록','SYSTEM',NOW()),
('USER','CodeController','groupDelete','코드 그룹 ID 삭제','SYSTEM',NOW()),
('USER','CodeController','codesForGroupCd','코드 상세','SYSTEM',NOW()),
('USER','CodeController','save','코드 등록','SYSTEM',NOW()),
('USER','CodeController','delete','코드 삭제','SYSTEM',NOW()),
('USER','IndexController','index','HOME','SYSTEM',NOW()),
('USER','IndexController','main1','요약 HOME','SYSTEM',NOW()),
('USER','IndexController','main2','포털 HOME','SYSTEM',NOW()),
('USER','IndexController','main3','분석 HOME','SYSTEM',NOW()),
('USER','IndexController','headSearch','통합 검색','SYSTEM',NOW()),
('USER','IndexController','registStateCount','비즈메타 등록 조회','SYSTEM',NOW()),
('USER','IndexController','dashBoardNewPopup','대시보드','SYSTEM',NOW()),
('USER','LoginController','login','로그인','SYSTEM',NOW()),
('USER','FileController','upload','파일 업로드','SYSTEM',NOW()),
('USER','FileController','download','파일 다운로드','SYSTEM',NOW()),
('USER','FileController','delete','파일 삭제','SYSTEM',NOW()),
('USER','MemberController','pupupList','사용자 검색','SYSTEM',NOW()),
('USER','MemberController','pupupSelect','사용자 목록','SYSTEM',NOW()),
('USER','MetaController','metadata','메타 데이터 검색','SYSTEM',NOW()),
('USER','MetaController','metadataDetail','메타 데이터 상세','SYSTEM',NOW()),
('USER','MetaController','metadataCategorySearch','메타 카테고리 검색','SYSTEM',NOW()),
('USER','MetaController','metadataNewPopup','메타 검색 팝업','SYSTEM',NOW()),
('USER','MypageController','mypageApproval','결재 현황 목록','SYSTEM',NOW()),
('USER','MypageController','mypageApprovalWrite','결재 현황 등록','SYSTEM',NOW()),
('USER','MypageController','mypageApprovalDetail1','결재 현황 상세 - 보고서 권한','SYSTEM',NOW()),
('USER','MypageController','mypageApprovalDetail2','결재 현황 상세 - 보고서 등록','SYSTEM',NOW()),
('USER','MypageController','mypageApprovalDetail3','결재 현황 상세 - 프로젝트 등록','SYSTEM',NOW()),
('USER','MypageController','mypageInitScreanSet','초기 화면 설정','SYSTEM',NOW()),
('USER','MypageController','mypageInitScreanSetInsert','초기 화면 설정 등록','SYSTEM',NOW()),
('USER','MypageController','mypageReportSet','보고서 설정','SYSTEM',NOW()),
('USER','MypageController','mypageReportList','보고서 설정 목록','SYSTEM',NOW()),
('USER','MypageController','mypageReportSave','보고서 설정 등록','SYSTEM',NOW()),
('USER','MypageController','mypageModelDeploy','모델 배포 신청 현황','SYSTEM',NOW()),
('USER','MypageController','mypageProjectStatus','프로젝트 신청 현황','SYSTEM',NOW()),
('USER','MypageController','mypageProjectStatusDetail1','프로젝트 신쳥 상세 - 머신러닝','SYSTEM',NOW()),
('USER','MypageController','mypageProjectStatusDetail2','프로젝트 신쳥 상세 - 시각화','SYSTEM',NOW()),
('USER','MypageController','mypageReportStatus','보고서 신청 현황','SYSTEM',NOW()),
('USER','MypageController','mypageReportStatusWrite','보고서 신청 등록','SYSTEM',NOW()),
('USER','MypageController','mypageReportStatusDetail','보고서 신청 상세','SYSTEM',NOW()),
('USER','ProjectController','project','프로젝트 목록','SYSTEM',NOW()),
('USER','ProjectController','projectRegist','프로젝트 등록','SYSTEM',NOW()),
('USER','ProjectController','select','프로젝트 상세','SYSTEM',NOW()),
('USER','ProjectController','insert','프로젝트 등록','SYSTEM',NOW()),
('USER','ProjectController','update','프로젝트 수정','SYSTEM',NOW()),
('USER','ProjectController','delete','프로젝트 삭제','SYSTEM',NOW()),
('USER','ReportAuthController','reportAuth','보고서 권한 목록','SYSTEM',NOW()),
('USER','ReportController','reportView','보고서 상세','SYSTEM',NOW()),
('USER','ReportController','reportList','보고서 목록','SYSTEM',NOW()),
('USER','ReportController','reportRegist','보고서 등록','SYSTEM',NOW()),
('USER','ReportController','insert','보고서 등록','SYSTEM',NOW()),
('USER','ReportController','select','보고서 상세','SYSTEM',NOW()),
('USER','ReportController','update','보고서 수정','SYSTEM',NOW()),
('USER','ReportExtrnlController','reportExtrnl','보고서 외부 배포 목록','SYSTEM',NOW()),
('USER','SearchController','searchTotal','통합 검색','SYSTEM',NOW());



-- 로그    로그 연계
TRUNCATE TABLE T_LOG_IF;



-- 로그    로그 요청 관리자 시스템
TRUNCATE TABLE T_LOG_RQST_MGR_SYS;



-- 로그    로그 요청 사용자 시스템
TRUNCATE TABLE T_LOG_RQST_USER_SYS;



-- 로그    로그 검색 키워드
TRUNCATE TABLE T_LOG_SRCH_KWD;



-- 로그    로그 태블로 관리자 시스템
TRUNCATE TABLE T_LOG_TABLEAU_MGR_SYS;



-- 로그    로그 태블로 사용자 시스템
TRUNCATE TABLE T_LOG_TABLEAU_USER_SYS;



-- 이력    부서 이력
TRUNCATE TABLE T_DEPT_HIST;



-- 이력    본부 이력
TRUNCATE TABLE T_HDEPT_HIST;



-- 이력    직위 이력
TRUNCATE TABLE T_PSTN_HIST;



-- 이력    사용자 이력
TRUNCATE TABLE T_USER_HIST;



-- 이력    프로젝트 이력
TRUNCATE TABLE T_PROJECT_HIST;



-- 이력    프로젝트 사용자 이력
TRUNCATE TABLE T_PROJECT_USER_HIST;



-- 이력    보고서 이력
TRUNCATE TABLE T_REPORT_HIST;



-- 이력    보고서 사용자 이력
TRUNCATE TABLE T_REPORT_USER_HIST;



-- 공통    코드
TRUNCATE TABLE T_CODE;
INSERT INTO T_CODE (GROUP_ID,CODE_ID,CODE_NM,CODE_DSC,ORD_SEQ,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('GROUP_ID','ACCOUNT_LOCK_PD','미사용 잠금 기간 설정',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('ACCOUNT_LOCK_PD','LOCK_PD','180 days','장기 미사용 사용자 잠금 기간',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','ACTIVE_YN','활성화 여부',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('ACTIVE_YN','N','비활성',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('ACTIVE_YN','Y','활성',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','APPROVE_YN','승인 여부',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APPROVE_YN','N','반려',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APPROVE_YN','Y','승인',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','APRV_CLASSIFY','승인 분류',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_CLASSIFY','aprvReportOpen','보고서 게시',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_CLASSIFY','aprvReportRole','보고서 권한',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_CLASSIFY','aprvProject','프로젝트',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_CLASSIFY','aprvProjectNight','프로젝트 야간 사용',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_CLASSIFY','aprvAuthChange','사용자 권한',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_CLASSIFY','aprvDataResource','데이터 권한',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_CLASSIFY','aprvModelDeploy','모델 배포',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','APRV_MTHD_GW','골드윙 결재 방법',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_MTHD_GW','0','대기',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_MTHD_GW','001','결재',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_MTHD_GW','002','확인',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_MTHD_GW','003','전결',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','APRV_STAT_GW','골드윙 결재 상태',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_STAT_GW','000','미결',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_STAT_GW','001','대기',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_STAT_GW','002','진행',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APRV_STAT_GW','003','완료',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','COMPANY_CODE','회사 구분',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('COMPANY_CODE','BC','BICNS',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('COMPANY_CODE','BS','베스핀글로벌',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('COMPANY_CODE','DS','신한DS',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('COMPANY_CODE','DST','데이터스트림즈',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('COMPANY_CODE','GS','신한금융투자',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('COMPANY_CODE','KBSYS','KBSYS',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('COMPANY_CODE','PST','펜타시스템테크놀러지',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('COMPANY_CODE','RD','리얼데이터',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','DATA_TRNS_CYCLE','데이터 전송 주기','외부 데이터',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_CYCLE','day','일',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_CYCLE','month','월',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_CYCLE','realtime','실시간',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_CYCLE','week','주',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','DATA_TRNS_MTHD','데이터 전송 방법','외부 데이터',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_MTHD','api','API',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_MTHD','db direct link','DB 직접 연계',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_MTHD','db purchase','DB 구입',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_MTHD','email','E-MAIL',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_MTHD','etc','기타',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_MTHD','internet search','인터넷 조회',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_MTHD','private terminal','전용 단말기',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_MTHD','realtime batch','실시간 배치',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_MTHD','system link','시스템 연계 - 인터넷',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TRNS_MTHD','web scrap','웹서버 스크래핑',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','DATA_TY','데이터 타입','외부 데이터',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TY','open','Open',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DATA_TY','purchase','구매',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','DEFAULT_USER_ID','배치 처리시 기본 등록 ID',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DEFAULT_USER_ID','PROJECT','admin',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DEFAULT_USER_ID','REPORT','admin',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DEFAULT_USER_ID','LICENSE','admin',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','FAQ_CAT','FAQ 카테고리',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('FAQ_CAT','ETC','기타',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('FAQ_CAT','LOGIN','로그인',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('FAQ_CAT','MANUAL','사용문의',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('FAQ_CAT','PROJECT','프로젝트',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('FAQ_CAT','SYSTEM','시스템',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','FILE','대용량 파일 설정','배치에서 사용',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('FILE','PD','180 days','대용량 파일 유지 기간 / 단위 일(INTERVAL)',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('FILE','LIMIT','10485760','대용량 파일 사이즈 / 단위 byte',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','IMPORTANT_YN','중요 여부',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('IMPORTANT_YN','N','일반',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('IMPORTANT_YN','Y','중요',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','LANG','언어',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LANG','chn','중국어',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LANG','eng','영어',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LANG','jpn','일본어',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LANG','kor','한국어',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','LOGIN_MESSAGE','로그인 실패 메시지',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOGIN_MESSAGE','ACCOUNT_DISABLE','계정 미사용','사용자 계정으로 로그인 할 수 없습니다.',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOGIN_MESSAGE','ACCOUNT_EXPIRE','계정 만료','사용자 계정이 만료되었습니다.',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOGIN_MESSAGE','ACCOUNT_LOCK','계정 잠김','사용자 계정이 잠겨있습니다.',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOGIN_MESSAGE','AUTH_FAIL','계정 권한 없음','사용자 계정의 권한이 없습니다.',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOGIN_MESSAGE','LOGIN_FAIL','기본 실패 메시지','로그인을 하지 못하였습니다.',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOGIN_MESSAGE','SSO_CONNECT_FAIL','SSO 연결 실패','SSO 인증 서버와 연결이 원활하지 않습니다.',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOGIN_MESSAGE','SSO_LOGIN_FAIL','SSO 인증 실패','SSO 인증을 실패하였습니다.',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOGIN_MESSAGE','UDB_CONNECT_FAIL','UDB 연결 실패','UDB 인증 서버와 연결이 원활하지 않습니다.',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOGIN_MESSAGE','UDB_LOGIN_FAIL','UDB 인증 실패','UDB 인증을 실패하였습니다.',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOGIN_MESSAGE','USER_NOT_FOUND','사용자 조회 불가','사용자 정보를 조회하지 못하였습니다.',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','MENU_SE','메뉴 구분',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('MENU_SE','F','기능',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('MENU_SE','M','메뉴 구분',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','OPEN_YN','공개 여부',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('OPEN_YN','N','비공개',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('OPEN_YN','Y','공개',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','POPUP_YN','팝업 사용 여부','공지사항 팝업 설정',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('POPUP_YN','N','팝업 미사용',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('POPUP_YN','Y','팝업 사용',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','PRJ_SE_CODE','프로젝트 구분',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('PRJ_SE_CODE','ALL','전체 공유',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('PRJ_SE_CODE','COM','일반',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','PRJ_STAT_CODE','프로젝트 승인 상태',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('PRJ_STAT_CODE','A','신청중',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('PRJ_STAT_CODE','C','승인완료',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('PRJ_STAT_CODE','R','반려',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','PRJ_TYPE_CODE','프로젝트 타입',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('PRJ_TYPE_CODE','AI','머신러닝',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('PRJ_TYPE_CODE','VW','시각화',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','QNA_CAT','QNA 카테고리',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('QNA_CAT','ETC','기타',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('QNA_CAT','LOGIN','로그인',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('QNA_CAT','MANUAL','사용문의',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('QNA_CAT','PROJECT','프로젝트',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('QNA_CAT','SYSTEM','시스템',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','QNA_STAT_CODE','QNA 답변 상태',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('QNA_STAT_CODE','ANSWERED','답변 완료',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('QNA_STAT_CODE','READED','확인중',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('QNA_STAT_CODE','UNREAD','미확인',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','RPT_ATMC_APRV_YN','보고서 자동 승인',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('RPT_ATMC_APRV_YN','N','결재 승인',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('RPT_ATMC_APRV_YN','Y','자동 승인',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','RPT_STAT_CODE','보고서 승인 상태',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('RPT_STAT_CODE','A','신청중',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('RPT_STAT_CODE','C','승인완료',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('RPT_STAT_CODE','R','반려',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','RPT_TY','보고서 유형',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('RPT_TY','AI','머신러닝',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('RPT_TY','DEPT','부서',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('RPT_TY','ENTERPRISE','전사',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('RPT_TY','GROUP','그룹',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('RPT_TY','PERSON','개인',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','SCHEDULE_CL','배치 구분',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SCHEDULE_CL','HR','HR 연동 배치',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SCHEDULE_CL','TABLEAU','태블로 연동 배치',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SCHEDULE_CL','NEWS','외부 자료 연동 배치',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SCHEDULE_CL','KWD','검색 키워드 배치',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SCHEDULE_CL','LOG','로그 배치',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SCHEDULE_CL','FILE','파일 배치',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SCHEDULE_CL','AWS','AWS 배치',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','SRC_SYS_CODE','소스 시스템 코드','외부 데이터',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SRC_SYS_CODE','system1','시스템1',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SRC_SYS_CODE','system2','시스템2',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SRC_SYS_CODE','system3','시스템3',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','USER_SEARCH_CODE','사용자 검색 구분',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('USER_SEARCH_CODE','deptNm','부서명',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('USER_SEARCH_CODE','userId','사번',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('USER_SEARCH_CODE','userNm','성명',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','USE_YN','사용 여부',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('USE_YN','N','미사용',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('USE_YN','Y','사용',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','aprvReportOpen','보고서 게시',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvReportOpen','recvDeptCode','GS713','수신처 부서 코두',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvReportOpen','recvDetpNm','데이터사이언스팀','수신처 부서 명',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvReportOpen','subject','보고서 게시','기안 제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvReportOpen','subjectSub','보고서 게시','기안 부제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','aprvReportRole','보고서 역할',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvReportRole','recvDeptCode','GS713','수신처 부서 코두',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvReportRole','recvDetpNm','데이터사이언스팀','수신처 부서 명',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvReportRole','subject','보고서 역할','기안 제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvReportRole','subjectSub','보고서 역할','기안 부제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','aprvProject','프로젝트',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvProject','recvDeptCode','GS713','수신처 부서 코두',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvProject','recvDetpNm','데이터사이언스팀','수신처 부서 명',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvProject','subject','프로젝트','기안 제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvProject','subjectSub','프로젝트','기안 부제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','aprvProjectNight','프로젝트 야간 사용',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvProjectNight','subject','프로젝트 야간 사용','기안 제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvProjectNight','subjectSub','프로젝트 야간 사용','기안 부제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','aprvAuthChange','시스템 권한',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvAuthChange','recvDeptCode','GS713','수신처 부서 코두',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvAuthChange','recvDetpNm','데이터사이언스팀','수신처 부서 명',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvAuthChange','subject','시스템 권한','기안 제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvAuthChange','subjectSub','시스템 권한','기안 부제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','aprvDataResource','데이터 권한',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvDataResource','recvDeptCode','GS713','수신처 부서 코두',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvDataResource','recvDetpNm','데이터사이언스팀','수신처 부서 명',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvDataResource','subject','데이터 권한','기안 제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvDataResource','subjectSub','데이터 권한','기안 부제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('GROUP_ID','aprvModelDeploy','모델 배포',NULL,NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvModelDeploy','recvDeptCode','GS713','수신처 부서 코두',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvModelDeploy','recvDetpNm','데이터사이언스팀','수신처 부서 명',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvModelDeploy','subject','모델 배포','기안 제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('aprvModelDeploy','subjectSub','모델 배포','기안 부제목',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 공통    부서
TRUNCATE TABLE T_DEPT;
INSERT INTO T_DEPT (DEPT_CODE,DEPT_NM,HDEPT_CODE,USE_YN,MODI_SE,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('D0','KBSYS',NULL,'Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D1','경영지원그룹','D0','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D2','경영지원부','D1','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D3','영업부','D1','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D4','개발 그룹','D0','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D5','개발부','S1','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D6','디자인부','S1','Y','C','SYSTEM',NOW(),'SYSTEM',NOW());



-- 공통    본부
TRUNCATE TABLE T_HDEPT;
INSERT INTO T_HDEPT (HDEPT_CODE,HDEPT_NM,USE_YN,MODI_SE,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('S1','포털사업본부','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('S2','개발본부','N','C','SYSTEM',NOW(),'SYSTEM',NOW());



-- 공통    직위
TRUNCATE TABLE T_PSTN;
INSERT INTO T_PSTN (PSTN_CODE,PSTN_NM,USE_YN,MODI_SE,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('P1','책임','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('P2','수석','Y','C','SYSTEM',NOW(),'SYSTEM',NOW());



-- 공통    사용자
TRUNCATE TABLE T_USER;
INSERT INTO T_USER (USER_ID,USER_NM,PSTN_CODE,DEPT_CODE,HDEPT_CODE,ADOF_DEPT_CODE,COMPANY_CODE,DUTY_SE,LAST_LOG_DT,START_DT,END_DT,FILE_URL,MGR_SYS_ENV,USER_SYS_HOME,USER_SYS_ENV,BF_DEPT_CODE,DEPT_UPDT_DT,USE_YN,MODI_SE,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('SYSTEM','시스템','P2','D1','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'N','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('admin','관리자','P2','D1','S1',NULL,'10','Y',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test11','테스트11','P2','D1','S1','D2','10','Y',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test12','테스트12','P1','D1','S1',NULL,'10','Y',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test13','테스트13','P1','D1','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test14','테스트14','P1','D1','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test15','테스트15','P1','D1','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test21','테스트21','P1','D2','S1','D1','10','Y',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test22','테스트22','P1','D2','S1',NULL,'10','Y',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test23','테스트23','P1','D2','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test24','테스트24','P2','D2','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test25','테스트25','P2','D2','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test26','테스트26','P2','D2','S1','D1','10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test27','테스트27','P2','D2','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test28','테스트28','P2','D2','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('test29','테스트29','P2','D2','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'Y','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('fail11','실패11','P1','D1','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'N','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('fail12','실패12','P1','D1','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'N','R','SYSTEM',NOW(),'SYSTEM',NOW()),
('fail13','실패13','P2','D1','S1',NULL,'10','N',TO_TIMESTAMP('2020-12-31 12:32:12','YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2020-11-01','YYYY-MM-DD'),TO_TIMESTAMP('2021-12-31','YYYY-MM-DD'),NULL,NULL,NULL,NULL,NULL,NULL,'N','R','SYSTEM',NOW(),'SYSTEM',NOW());



-- 공통    사용자 테스트
TRUNCATE TABLE T_USER_TEST;
INSERT INTO T_USER_TEST (USER_ID,USER_NM,RGST_ID,RGST_DT) VALUES
('SYSTEM','시스템','SYSTEM',NOW()),
('admin','관리자','SYSTEM',NOW()),
('test11','테스트11','SYSTEM',NOW()),
('test12','테스트12','SYSTEM',NOW()),
('test13','테스트13','SYSTEM',NOW()),
('test14','테스트14','SYSTEM',NOW()),
('test15','테스트15','SYSTEM',NOW()),
('test21','테스트21','SYSTEM',NOW()),
('test22','테스트22','SYSTEM',NOW()),
('test23','테스트23','SYSTEM',NOW()),
('test24','테스트24','SYSTEM',NOW()),
('test25','테스트25','SYSTEM',NOW()),
('test26','테스트26','SYSTEM',NOW()),
('test27','테스트27','SYSTEM',NOW()),
('test28','테스트28','SYSTEM',NOW()),
('test29','테스트29','SYSTEM',NOW()),
('fail11','실패11','SYSTEM',NOW()),
('fail12','실패12','SYSTEM',NOW()),
('fail13','실패13','SYSTEM',NOW());



-- 공통    부서 분류
TRUNCATE TABLE T_DEPT_CL;
INSERT INTO T_DEPT_CL (DEPT_CODE,UP_DEPT_CODE,DEPT_NM,ORD_SEQ,LV,DEPT_PATH,GROUP_CODE,UP_GROUP_CODE,USE_YN,MODI_SE,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('D0','Top','KBSYS','1','1','top/D0','D0','Top','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D1','D0','경영지원그룹','2','2','top/D0/D1','D1','D0','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D2','D1','경영지원부','3','3','top/D0/D1/D2','D2','D1','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D3','D1','영업부','4','3','top/D0/D1/D3','D3','D1','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D4','D0','개발 그룹','5','2','top/D0/D4','D4','D0','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D5','D4','개발부','6','3','top/D0/D4/D5','D5','D4','Y','C','SYSTEM',NOW(),'SYSTEM',NOW()),
('D6','D4','디자인부','7','3','top/D0/D4/D6','D6','D4','Y','C','SYSTEM',NOW(),'SYSTEM',NOW());



-- 공통    ID 순번
TRUNCATE TABLE T_ID_SN;



-- 공통    파일
TRUNCATE TABLE T_FILE;



-- 공통    게시판 공지사항
TRUNCATE TABLE T_BBS_NOTICE;
INSERT INTO T_BBS_NOTICE (NOTICE_ID,SJ,CN,IMPORTANT_YN,ORD_SEQ,FILE_ID,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('nt20000000001','공지사항 1','공지사항 내용 1','N','2',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('nt20000000002','공지사항 2','공지사항 내용 2','N','1',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('nt20000000003','공지사항 3','공지사항 내용 3','Y','3',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 공통    게시판 FAQ
TRUNCATE TABLE T_BBS_FAQ;
INSERT INTO T_BBS_FAQ (FAQ_ID,CL_CODE,QSTN,ANSW,ORD_SEQ,FILE_ID,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('fq20000000001','LOGIN','로그인 질문 1','로그인 답변 1','1',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('fq20000000002','LOGIN','로그인 질문 2','로그인 답변 2','2',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('fq20000000003','SYSTEM','시스템 질문 1','시스템 답변 1','2',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('fq20000000004','SYSTEM','시스템 질문 2','시스템 답변 2','1',NULL,'Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 공통    게시판 QNA
TRUNCATE TABLE T_BBS_QNA;



-- 공통    뉴스 정보
TRUNCATE TABLE T_NEWS_INFO;



-- 공통    리서치 정보
TRUNCATE TABLE T_RESRCH_INFO;



-- 공통    순위 정보
TRUNCATE TABLE T_RANK_INFO;



-- 관리자    관리자 권한
TRUNCATE TABLE T_MGR_AUTH;
INSERT INTO T_MGR_AUTH (USER_ID,AUTH_ID,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('admin','au20000000001','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test11','au20000000001','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test12','au20000000001','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test13','au20000000001','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test14','au20000000002','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test15','au20000000002','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 관리자    관리자 시스템 권한
TRUNCATE TABLE T_MGR_SYS_AUTH;
INSERT INTO T_MGR_SYS_AUTH (AUTH_ID,AUTH_CL,AUTH_NM,AUTH_DSC,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('au20000000001','M','관리자','관리자','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','A','분석관리자','분석관리자','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 관리자    관리자 시스템 메뉴
TRUNCATE TABLE T_MGR_SYS_MENU;
INSERT INTO T_MGR_SYS_MENU (MENU_ID,UP_MENU_ID,MENU_NM,MENU_URL,MENU_DSC,ORD_SEQ,MENU_SE,MENU_ATTR,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('mn20000000001',NULL,'HOME','/','','1','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000002','mn20000000001','시스템 관리','/system','','2','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000003','mn20000000002','공통코드 관리','/system/code','','3','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000004','mn20000000002','사용자 관리','/system/member','','4','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000005','mn20000000002','권한 그룹 관리','/system/role','','5','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000006','mn20000000002','메뉴 관리','/system/menu','','7','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000007','mn20000000002','메뉴 권한 관리','/system/menuAuth','','8','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000008','mn20000000002','업무 카테고리 관리','/system/wrkCat','','9','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000009','mn20000000002','라이선스 관리','/system/license','','6','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000010','mn20000000001','게시판','/board','','10','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000011','mn20000000010','공지 관리','/board/notice','','11','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000012','mn20000000010','FAQ 관리','/board/faq','','12','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000013','mn20000000010','Q&amp;A 관리','/board/qna','','13','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000014','mn20000000010','자유게시판 관리','/board/free','','14','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000015','mn20000000001','외부 자산 관리','/external','','15','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000016','mn20000000015','외부 시스템 관리','/external/extrnl','','16','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000017','mn20000000015','외부 데이터 관리','/external/data','','17','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000018','mn20000000015','외부 데이터 엑셀 업로드','/external/data/excelUpload','','18','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000019','mn20000000015','외부 데이터 일괄 등록','/external/data/multiInsert','','19','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000020','mn20000000001','분석 관리','/project','','20','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000021','mn20000000020','프로젝트 관리','/project/project','','21','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000022','mn20000000020','보고서 관리','/project/report','','22','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000023','mn20000000020','데이터 권한 신청 관리','/project/dataNew','','23','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000024','mn20000000020','모델 신청 관리','/project/modelResource','','24','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000025','mn20000000020','모델 배포 관리','/project/modelDeploy','','25','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000026','mn20000000001','모니터링','/monitor','','26','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000027','mn20000000026','배치 모니터링','/monitor/batchMonitoring','','27','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000028','mn20000000026','ETL 모니터링','/monitor/etlMonitoring','','28','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000029','mn20000000026','로그 모니터링','/monitor/logMonitoring','','29','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000030','mn20000000001','감사 관리','/audit','','30','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000031','mn20000000030','자원 접근 이력','/audit/extrnlLog','','31','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000032','mn20000000030','야간 사용 관리','/audit/nightUsingAprv','','32','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000033','mn20000000030','사용자 로그 관리','/audit/userLog','','33','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20000000034','mn20000000030','관리자 로그 관리','/audit/mgrLog','','34','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 관리자    관리자 시스템 메뉴 권한
TRUNCATE TABLE T_MGR_SYS_MENU_AUTH;
INSERT INTO T_MGR_SYS_MENU_AUTH (AUTH_ID,MENU_ID,MENU_ATTR,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('au20000000001','mn20000000001','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000002','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000003','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000004','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000005','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000006','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000007','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000008','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000009','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000010','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000011','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000012','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000013','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000014','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000015','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000016','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000017','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000018','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000019','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000020','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000021','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000022','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000023','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000024','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000025','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000026','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000027','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000028','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000029','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000030','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000031','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000032','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000033','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000001','mn20000000034','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000001','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000002','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000003','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000004','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000005','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000006','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000007','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000008','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000009','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000010','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000011','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000012','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000013','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000014','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000015','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000016','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000017','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000018','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000019','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000020','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000021','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000022','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000023','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000024','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000025','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000026','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000027','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000028','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000029','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000030','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000031','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000032','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000033','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20000000002','mn20000000034','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 관리자    관리자 시스템 스케줄
TRUNCATE TABLE T_MGR_SYS_SCHEDULE;
INSERT INTO T_MGR_SYS_SCHEDULE (SCHEDULE_NM,SCHEDULE_CL,EXECUT_CYCLE,TIME_ZONE,SCHEDULE_DSC,OBJECT_NM,TRIGGER_NM,LOCK_YN,LOCK_CYCLE,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('HR','HR','0 10 7 ? * 2-6','Asia/Seoul','HR 사용자 정보',NULL,NULL,'Y','90 minutes','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('DEPT_CL','HR','0 0 7 ? * 2-6','Asia/Seoul','HR 조직도 정보',NULL,NULL,'Y','90 minutes','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('USER_PHOTO','HR','0 20 7 ? * 2-6','Asia/Seoul','HR 사용자 사진',NULL,NULL,'Y','90 minutes','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('TABLEAU','TABLEAU','0 30 7 ? * 2-6','Asia/Seoul','TABLEAU 정보',NULL,NULL,'Y','90 minutes','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('NEWS','NEWS','0 0/30 7-20 ? * 2-6','Asia/Seoul','외부 제공 뉴스',NULL,NULL,'Y','90 minutes','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('SRCH_KWD','KWD','0 0 8-18 ? * 2-6','Asia/Seoul','검색 키워드',NULL,NULL,'Y','90 minutes','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOG_SYS','LOG','0 0 7 * * ?','Asia/Seoul','시스템 로그 수집',NULL,NULL,'Y','90 minutes','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('LOG_TO_FILE','LOG','0 10 0 * * ?','Asia/Seoul','정보 보안 로그 수집',NULL,NULL,'Y','90 minutes','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('FILE','FILE','0 30 0 * * ?','','대용량 파일 삭제',NULL,NULL,'Y','90 minutes','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 관리자    관리자 시스템 스케줄 락
TRUNCATE TABLE T_MGR_SYS_SCHEDULE_LOCK;



-- 사용자    사용자 권한
TRUNCATE TABLE T_USER_AUTH;
INSERT INTO T_USER_AUTH (USER_ID,AUTH_ID,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('admin','au20100000003','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test11','au20100000003','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test12','au20100000003','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test13','au20100000003','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test14','au20100000003','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test15','au20100000003','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test21','au20100000001','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test22','au20100000001','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test23','au20100000001','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test24','au20100000001','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test25','au20100000001','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test26','au20100000002','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test27','au20100000002','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test28','au20100000002','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('test29','au20100000002','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 사용자    사용자 시스템 권한
TRUNCATE TABLE T_USER_SYS_AUTH;
INSERT INTO T_USER_SYS_AUTH (AUTH_ID,AUTH_CL,AUTH_NM,AUTH_DSC,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('au20100000001','U','일반사용자','일반사용자 설명','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','D','일반사용자(임원)','일반사용자(임원) 설명','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','A','분석사용자','분석사용자 설명','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 사용자    사용자 시스템 메뉴
TRUNCATE TABLE T_USER_SYS_MENU;
INSERT INTO T_USER_SYS_MENU (MENU_ID,UP_MENU_ID,MENU_NM,MENU_URL,MENU_DSC,ORD_SEQ,MENU_SE,MENU_ATTR,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('mn20100000001',NULL,'HOME','/','','1','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000002','mn20100000001','요약Type','/main1','','2','F','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000003','mn20100000001','포털Type','/main2','','3','F','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000004','mn20100000001','분석Type','/main3','','4','F','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000005','mn20100000001','통합 검색','/search/total','','5','F','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000006','mn20100000001','My Page','/mypage','','6','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000007','mn20100000006','보고서 신청 현황','/mypage/reportStatus','','7','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000008','mn20100000006','프로젝트 신청 현황','/mypage/projectStatus','','8','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000009','mn20100000006','모델 배포 신청 현황','/mypage/modelDeploy','','9','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000010','mn20100000006','사용자 권한','/mypage/role/user','','10','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000011','mn20100000006','환경 설정','/mypage/envSet','','11','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000012','mn20100000011','초기 화면 설정','/mypage/initScreenSet','','12','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000013','mn20100000011','업무 보고서 설정','/mypage/reportSet','','13','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000014','mn20100000001','분석 관리','/project','','14','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000015','mn20100000014','프로젝트 조회','/project/project','','15','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000016','mn20100000014','프로젝트 생성','/project/project/regist','','16','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000017','mn20100000014','보고서 조회','/project/report/search','','17','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000018','mn20100000014','보고서 생성','/project/report/make','','18','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000019','mn20100000014','보고서 동록 요청','/project/report/rgst','','19','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000020','mn20100000014','모델 배포 신청','/project/modelDeploy','','20','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000021','mn20100000001','업무 용어 관리','/bizword','','21','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000022','mn20100000021','업무 표준 용어 검색','/bizword/meta/search','','22','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000023','mn20100000021','업무 표준 용어 등록&amp;수정','/bizword/meta/rgst','','23','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000024','mn20100000001','자산 현황','/data','','24','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000025','mn20100000024','데이터 조회','/data/role/data','','25','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000026','mn20100000024','시각화 데이터 조회','/data/role/data/itmetaData','','26','F','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000027','mn20100000024','ML 데이터 조회','/data/role/data/ctlgData','','27','F','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000028','mn20100000024','외부 데이터 현황','/data/extrnl','','28','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000029','mn20100000001','이용 안내','/info','','29','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000030','mn20100000029','공지사항','/info/notice','','30','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000031','mn20100000029','FAQ','/info/faq','','31','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000032','mn20100000029','Q&amp;A','/info/qna','','32','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('mn20100000033','mn20100000029','자유 게시판','/info/free','','33','M','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 사용자    사용자 시스템 메뉴 권한
TRUNCATE TABLE T_USER_SYS_MENU_AUTH;
INSERT INTO T_USER_SYS_MENU_AUTH (AUTH_ID,MENU_ID,MENU_ATTR,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('au20100000001','mn20100000001','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000002','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000003','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000004','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000005','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000006','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000007','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000008','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000009','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000010','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000011','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000012','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000013','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000014','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000015','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000016','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000017','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000018','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000019','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000020','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000021','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000022','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000023','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000024','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000025','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000026','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000027','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000028','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000029','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000030','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000031','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000032','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000001','mn20100000033','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000001','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000002','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000003','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000004','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000005','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000006','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000007','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000008','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000009','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000010','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000011','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000012','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000013','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000014','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000015','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000016','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000017','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000018','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000019','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000020','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000021','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000022','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000023','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000024','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000025','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000026','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000027','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000028','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','N','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000029','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000030','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000031','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000032','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000002','mn20100000033','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000001','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000002','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000003','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000004','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000005','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000006','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000007','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000008','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000009','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000010','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000011','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000012','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000013','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000014','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000015','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000016','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000017','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000018','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000019','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000020','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000021','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000022','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000023','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000024','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000025','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000026','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000027','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000028','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000029','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000030','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000031','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000032','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('au20100000003','mn20100000033','{"attr":{"insert":true,"update":true,"delete":true,"detail":true}}','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 업무    라이선스
TRUNCATE TABLE T_LICENSE;



-- 업무    외부 시스템
TRUNCATE TABLE T_EXTRNL_SYS;
INSERT INTO T_EXTRNL_SYS (EXTRNL_ID,EXTRNL_NM,EXTRNL_URL,EXTRNL_DSC,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('ex20000000001','외부 시스템 1','htttp://www.google.co.kr','외부 시스템 1 설명','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('ex20000000002','외부 시스템 2','htttp://www.naver.com','외부 시스템 2 설명','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('ex20000000003','외부 시스템 3','http://www.daum.net','외부 시스템 3 설명','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 업무    외부 데이터
TRUNCATE TABLE T_EXTRNL_DATA;



-- 업무    검색 키워드
TRUNCATE TABLE T_SRCH_KWD;



-- 업무    업무 카테고리
TRUNCATE TABLE T_WRK_CAT;



-- 업무    역할 그룹
TRUNCATE TABLE T_ROLE_GROUP;



-- 업무    역할 그룹 상세
TRUNCATE TABLE T_ROLE_GROUP_DTL;



-- 업무    프로젝트 신청
TRUNCATE TABLE T_PROJECT_APPLY;



-- 업무    프로젝트
TRUNCATE TABLE T_PROJECT;



-- 업무    프로젝트 사용자
TRUNCATE TABLE T_PROJECT_USER;



-- 업무    보고서
TRUNCATE TABLE T_REPORT;



-- 업무    보고서 사용자
TRUNCATE TABLE T_REPORT_USER;



-- 업무    AWS 인스턴스
TRUNCATE TABLE T_AWS_INSTANCE;
INSERT INTO T_AWS_INSTANCE (INSTANCE_SE,INSTANCE_NM,INSTANCE_CAT,VCPU,MEMORY,GPU,DFLT_YN,WRANGLER_YN,DFLT_SETUP,MAX_SETUP,DFLT_LIST,ADVC_LIST,ORD_SEQ,USE_YN,RGST_ID,RGST_DT,MODI_ID,MODI_DT) VALUES
('APP','ml.t3.medium','General purpose','2','4','0','Y','N','0','0','0','0','1','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.t3.large','General purpose','2','8','0','N','N','0','0','0','0','2','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.t3.xlarge','General purpose','4','16','0','N','N','0','0','0','0','3','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.t3.2xlarge','General purpose','8','32','0','N','N','0','0','0','0','4','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.m5.large','General purpose','2','8','0','N','N','0','0','0','0','5','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.m5.xlarge','General purpose','4','16','0','N','N','0','0','0','0','6','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.m5.2xlarge','General purpose','8','32','0','N','N','0','0','0','0','7','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.m5.4xlarge','General purpose','16','64','0','N','N','0','0','0','0','8','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.m5.8xlarge','General purpose','32','128','0','N','Y','0','0','0','0','9','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.m5.12xlarge','General purpose','48','192','0','N','N','0','0','0','0','10','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.m5.16xlarge','General purpose','64','256','0','N','N','0','0','0','0','11','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.m5.24xlarge','General purpose','96','384','0','N','N','0','0','0','0','12','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.c5.large','Compute optimized','2','4','0','N','N','0','0','0','0','13','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.c5.xlarge','Compute optimized','4','8','0','N','N','0','0','0','0','14','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.c5.2xlarge','Compute optimized','8','16','0','N','N','0','0','0','0','15','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.c5.4xlarge','Compute optimized','16','32','0','N','N','0','0','0','0','16','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.c5.9xlarge','Compute optimized','32','72','0','N','N','0','0','0','0','17','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.c5.12xlarge','Compute optimized','48','96','0','N','N','0','0','0','0','18','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.c5.18xlarge','Compute optimized','64','144','0','N','N','0','0','0','0','19','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.c5.24xlarge','Compute optimized','96','192','0','N','N','0','0','0','0','20','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.p3.2xlarge','Acceletatoed computing','8','61','1','N','N','0','0','0','0','21','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.p3.8xlarge','Acceletatoed computing','32','244','2','N','N','0','0','0','0','22','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.p3.16xlarge','Acceletatoed computing','64','488','8','N','N','0','0','0','0','23','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.g4dn.xlarge','Acceletatoed computing','4','16','1','N','N','0','0','0','0','24','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.g4dn.2xlarge','Acceletatoed computing','8','32','1','N','N','0','0','0','0','25','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.g4dn.4xlarge','Acceletatoed computing','16','64','1','N','N','0','0','0','0','26','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.g4dn.8xlarge','Acceletatoed computing','32','128','1','N','N','0','0','0','0','27','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.g4dn.12xlarge','Acceletatoed computing','48','192','4','N','N','0','0','0','0','28','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('APP','ml.g4dn.16xlarge','Acceletatoed computing','64','256','1','N','N','0','0','0','0','29','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.m5.large','General purpose','2','8','0','Y','N','1','5','0','0','1','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.m5.xlarge','General purpose','4','16','0','N','N','1','5','1','0','2','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.m5.2xlarge','General purpose','8','32','0','N','N','1','5','0','0','3','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.m5.4xlarge','General purpose','16','64','0','N','N','1','5','0','2','4','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.m5.12xlarge','General purpose','48','192','0','N','N','1','5','0','0','5','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.m5.24xlarge','General purpose','96','384','0','N','N','1','5','0','0','6','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.c5.xlarge','Compute optimized','4','8','0','Y','N','1','5','0','0','7','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.c5.2xlarge','Compute optimized','8','16','0','N','N','1','5','1','0','8','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.c5.4xlarge','Compute optimized','16','32','0','N','N','1','5','0','2','9','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.c5.9xlarge','Compute optimized','36','72','0','N','N','1','5','0','0','10','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.c5.18xlarge','Compute optimized','72','144','0','N','N','1','5','0','0','11','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.c5n.xlarge','Compute optimized','4','10.5','0','N','N','1','5','0','0','12','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.c5n.2xlarge','Compute optimized','8','21','0','N','N','1','5','0','0','13','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.c5n.4xlarge','Compute optimized','16','42','0','N','N','1','5','0','0','14','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.c5n.9xlarge','Compute optimized','36','96','0','N','N','1','5','0','0','15','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.c5n.18xlarge','Compute optimized','72','192','0','N','N','1','5','0','0','16','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.p3.2xlarge','Acceletatoed computing','8','61','1','N','N','1','5','0','0','17','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.p3.8xlarge','Acceletatoed computing','32','244','4','N','N','1','5','0','0','18','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.p3.16xlarge','Acceletatoed computing','64','488','8','N','N','1','5','0','0','19','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.p3dn.24xlarge','Acceletatoed computing','96','768','8','N','N','1','5','0','0','20','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.p4d.24xlarge','Acceletatoed computing','96','1152','8','N','N','1','5','0','0','21','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.g4dn.xlarge','Acceletatoed computing','4','16','1','N','N','1','5','0','0','22','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.g4dn.2xlarge','Acceletatoed computing','8','32','1','N','N','1','5','0','0','23','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.g4dn.4xlarge','Acceletatoed computing','16','64','1','N','N','1','5','0','0','24','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.g4dn.8xlarge','Acceletatoed computing','32','128','1','N','N','1','5','0','0','25','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.g4dn.12xlarge','Acceletatoed computing','48','192','4','N','N','1','5','0','0','26','Y','SYSTEM',NOW(),'SYSTEM',NOW()),
('JOB','ml.g4dn.16xlarge','Acceletatoed computing','64','256','1','N','N','1','5','0','0','27','Y','SYSTEM',NOW(),'SYSTEM',NOW());



-- 업무    태블로 프로젝트
TRUNCATE TABLE T_TABLEAU_PROJECT;



-- 업무    태블로 워크북
TRUNCATE TABLE T_TABLEAU_WORKBOOK;



-- 업무    태블로 뷰
TRUNCATE TABLE T_TABLEAU_VIEW;



-- 업무    태블로 사용자
TRUNCATE TABLE T_TABLEAU_USER;



-- 업무    승인 요청
TRUNCATE TABLE T_APRV_RQST;



-- 업무    승인 요청 상세
TRUNCATE TABLE T_APRV_RQST_DTL;


