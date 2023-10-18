-- role 테이블
create table role (
    ROLE_ID VARCHAR(15) NOT NULL,
    ROLE_NM VARCHAR(100),
    ROLE_DESC VARCHAR(300),
    USE_YN CHAR(1),
    RGST_ID VARCHAR(30),
    RGST_DTTM TIMESTAMP,
    MODI_ID VARCHAR(30),
    MODI_DTTM TIMESTAMP,
    PRIMARY KEY (ROLE_ID)
);

COMMENT ON TABLE role is '권한';

COMMENT ON COLUMN role.ROLE_ID IS '권한ID';
COMMENT ON COLUMN role.ROLE_NM IS '권한명';
COMMENT ON COLUMN role.ROLE_DESC IS '권한설명';
COMMENT ON COLUMN role.USE_YN IS '사용여부';
COMMENT ON COLUMN role.RGST_ID IS '등록자ID';
COMMENT ON COLUMN role.RGST_DTTM IS '등록일시';
COMMENT ON COLUMN role.MODI_ID IS '수정자ID';
COMMENT ON COLUMN role.MODI_DTTM IS '수정일시';

-- role sample 데이터
insert into role values ('ROLE001','일반사용자','일반사용자 설명','Y','admin', now(), 'admin',now());
insert into role values ('ROLE002','분석사용자','분석사용자 설명','Y','admin', now(), 'admin',now());
insert into role values ('ROLE003','관리자','관리자 설명','Y','admin', now(), 'admin',now());

-- code 테이블
create table code (
    GROUP_ID VARCHAR(15) NOT NULL,
    CODE_ID VARCHAR(15) NOT NULL,
    CODE_NM VARCHAR(100),
    CODE_DESC VARCHAR(300),
    USE_YN CHAR(1),
    RGST_ID VARCHAR(30),
    RGST_DTTM TIMESTAMP,
    MODI_ID VARCHAR(30),
    MODI_DTTM TIMESTAMP,
    PRIMARY KEY (GROUP_ID, CODE_ID)
);

-- GROUP_ID가 GROUP_CD인 코드들이 코드 그룹이고 GROUP_ID가 CODE_ID인 항목들이 하위그룹으로 구성됩니다.
COMMENT ON TABLE code is '공통코드';

COMMENT ON COLUMN code.GROUP_ID IS '그룹ID';
COMMENT ON COLUMN code.CODE_ID IS '코드ID';
COMMENT ON COLUMN code.CODE_NM IS '코드명';
COMMENT ON COLUMN code.CODE_DESC IS '코드설명';
COMMENT ON COLUMN code.USE_YN IS '사용여부';
COMMENT ON COLUMN code.RGST_ID IS '등록자ID';
COMMENT ON COLUMN code.RGST_DTTM IS '등록일시';
COMMENT ON COLUMN code.MODI_ID IS '수정자ID';
COMMENT ON COLUMN code.MODI_DTTM IS '수정일시';

-- code sample 테이블
insert into code values ('GROUP_CD', 'PRJ_TYPE_CD', '프로젝트 타입 코드', '프로젝트 타입 코드 설명', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('PRJ_TYPE_CD', '10', '인공지능', '인공지능 설명', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('PRJ_TYPE_CD', '20', '시각화', '시각화 설명', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('GROUP_CD', 'PRJ_STAT_CD', '프로젝트 상태 코드', '프로젝트 상태 코드 설명', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('GROUP_CD', 'JOB_CD', '직위 코드', 'pst_code와 동일 코드', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('JOB_CD', '52', '과장', '', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('JOB_CD', '53', '대리', '', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('JOB_CD', '54', '주임', '', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('GROUP_CD', 'ORG_CD', '부서 코드', 'dbrn_code와 동일 코드', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('ORG_CD', '001', '여의도지점', '', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('ORG_CD', '002', '마포지점', '', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('ORG_CD', '520', '인사부', '', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('ORG_CD', '900', '임원실', '', 'Y' , 'admin', now(), 'admin', now());


insert into code values ('GROUP_CD', 'USE_YN', '사용 여부', '', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('USE_YN', 'Y', '사용', '', 'Y', 'admin', now(), 'admin', now());
insert into code values ('USE_YN', 'N', '미사용', '', 'Y', 'admin', now(), 'admin', now());

insert into code values ('GROUP_CD', 'COMPANY_CD', '회사 구분', '', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('COMPANY_CD', '10', '직원', '', 'Y', 'admin', now(), 'admin', now());
insert into code values ('COMPANY_CD', '20', '외주', '', 'Y', 'admin', now(), 'admin', now());

insert into code values ('GROUP_CD', 'MEM_SEARCH_CD', '검색 구분', '', 'Y' , 'admin', now(), 'admin', now());
insert into code values ('MEM_SEARCH_CD', 'memberId', '사번', '', 'Y', 'admin', now(), 'admin', now());
insert into code values ('MEM_SEARCH_CD', 'memberNm', '성명', '', 'Y', 'admin', now(), 'admin', now());


-- license 테이블
create table license (
    ROLE_ID VARCHAR(15) NOT NULL,
    LICENSE_ID VARCHAR(15) NOT NULL,
    TABLEAU_ID VARCHAR(100),
    TABLEAU_PWD VARCHAR(150),
    AWS_ID VARCHAR(100),
    AWS_PWD VARCHAR(150),
    LICENSE_DESC VARCHAR(300),
    USE_YN CHAR(1),
    RGST_ID VARCHAR(30),
    RGST_DTTM TIMESTAMP,
    MODI_ID VARCHAR(30),
    MODI_DTTM TIMESTAMP,
    PRIMARY KEY (ROLE_ID, LICENSE_ID)
);

COMMENT ON TABLE license is '라이선스';

COMMENT ON COLUMN license.ROLE_ID IS '권한ID';
COMMENT ON COLUMN license.LICENSE_ID IS '라이센스ID';
COMMENT ON COLUMN license.TABLEAU_ID IS '태블로ID';
COMMENT ON COLUMN license.TABLEAU_PWD IS '태블로비밀번호';
COMMENT ON COLUMN license.AWS_ID IS 'AWS ID';
COMMENT ON COLUMN license.AWS_PWD IS 'AWS 비밀번호';
COMMENT ON COLUMN license.LICENSE_DESC IS '라이센스설명';
COMMENT ON COLUMN license.USE_YN IS '사용여부';
COMMENT ON COLUMN license.RGST_ID IS '등록자ID';
COMMENT ON COLUMN license.RGST_DTTM IS '등록일시';
COMMENT ON COLUMN license.MODI_ID IS '수정자ID';
COMMENT ON COLUMN license.MODI_DTTM IS '수정일시';

-- member 테이블
create table member (
    MEMBER_ID VARCHAR(30) NOT NULL,
    MEMBER_NM VARCHAR(50),
    JOB_CD VARCHAR(15),
    ORG_CD VARCHAR(15),
    COMPANY_CD VARCHAR(15),
    ROLE_ID VARCHAR(15),
    USE_YN CHAR(1),
    LASTLOG_DTTM TIMESTAMP,
    START_DTTM TIMESTAMP,
    END_DTTM TIMESTAMP,
    RGST_ID VARCHAR(30),
    RGST_DTTM TIMESTAMP,
    MODI_ID VARCHAR(30),
    MODI_DTTM TIMESTAMP,
    PRIMARY KEY (MEMBER_ID)
);

COMMENT ON TABLE member is '사용자';

COMMENT ON COLUMN member.MEMBER_ID IS '사번';
COMMENT ON COLUMN member.MEMBER_NM IS '사용자명';
COMMENT ON COLUMN member.JOB_CD IS '직위코드';
COMMENT ON COLUMN member.ORG_CD IS '부서코드';
COMMENT ON COLUMN member.COMPANY_CD IS '회사코드';
COMMENT ON COLUMN member.ROLE_ID IS '권한ID';
COMMENT ON COLUMN member.USE_YN IS '사용여부';
COMMENT ON COLUMN member.LASTLOG_DTTM IS '마지막 로그인 일시';
COMMENT ON COLUMN member.START_DTTM IS '시작일시';
COMMENT ON COLUMN member.END_DTTM IS '종료일시';
COMMENT ON COLUMN member.RGST_ID IS '등록자ID';
COMMENT ON COLUMN member.RGST_DTTM IS '등록일시';
COMMENT ON COLUMN member.MODI_ID IS '수정자ID';
COMMENT ON COLUMN member.MODI_DTTM IS '수정일시';

-- member sample 데이터
insert into member (member_id, member_nm, job_cd, org_cd, company_cd, RGST_ID, RGST_dttm, MODI_ID, MODI_DTTM) values ('admin', '관리자', '53', '001', '10', 'admin', now(), 'admin', now());

-- extrnl_sys 테이블
create table extrnl_sys (
      EXTRNL_ID VARCHAR(15) NOT NULL,
      EXTRNL_NM VARCHAR(100),
      EXTRNL_URL VARCHAR(500),
      EXTRNL_DESC VARCHAR(300),
      USE_YN CHAR(1),
      RGST_ID VARCHAR(30),
      RGST_DTTM TIMESTAMP,
      MODI_ID VARCHAR(30),
      MODI_DTTM TIMESTAMP,
      PRIMARY KEY (EXTRNL_ID)
);

COMMENT ON TABLE extrnl_sys is '외부시스템';

COMMENT ON COLUMN extrnl_sys.EXTRNL_ID IS '외부시스템ID';
COMMENT ON COLUMN extrnl_sys.EXTRNL_NM IS '외부시스템명';
COMMENT ON COLUMN extrnl_sys.EXTRNL_URL IS '외부시스템 URL';
COMMENT ON COLUMN extrnl_sys.EXTRNL_DESC IS '외부시스템 설명';
COMMENT ON COLUMN extrnl_sys.USE_YN IS '사용여부';
COMMENT ON COLUMN extrnl_sys.RGST_ID IS '등록자ID';
COMMENT ON COLUMN extrnl_sys.RGST_DTTM IS '등록일시';
COMMENT ON COLUMN extrnl_sys.MODI_ID IS '수정자ID';
COMMENT ON COLUMN extrnl_sys.MODI_DTTM IS '수정일시';

-- extrnl_sys sample 데이터
insert into extrnl_sys values ('EXTRNL001','시스템1',null,'시스템1 설명','Y','admin', now(), 'admin',now());
insert into extrnl_sys values ('EXTRNL002','시스템2',null,'시스템2 설명','Y','admin', now(), 'admin',now());
insert into extrnl_sys values ('EXTRNL003','시스템3',null,'시스템3 설명','Y','admin', now(), 'admin',now());

-- wrk_cat 테이블
create table wrk_cat (
        WRK_ID VARCHAR(15) NOT NULL,
        WRK_NM VARCHAR(100),
        WRK_DESC VARCHAR(300),
        USE_YN CHAR(1),
        RGST_ID VARCHAR(30),
        RGST_DTTM TIMESTAMP,
        MODI_ID VARCHAR(30),
        MODI_DTTM TIMESTAMP,
        PRIMARY KEY (WRK_ID)
);

COMMENT ON TABLE wrk_cat is '업무카테고리';

COMMENT ON COLUMN wrk_cat.WRK_ID IS '업무카테고리 ID';
COMMENT ON COLUMN wrk_cat.WRK_NM IS '업무카테고리명';
COMMENT ON COLUMN wrk_cat.WRK_DESC IS '업무카테로기 설명';
COMMENT ON COLUMN wrk_cat.USE_YN IS '사용여부';
COMMENT ON COLUMN wrk_cat.RGST_ID IS '등록자ID';
COMMENT ON COLUMN wrk_cat.RGST_DTTM IS '등록일시';
COMMENT ON COLUMN wrk_cat.MODI_ID IS '수정자ID';
COMMENT ON COLUMN wrk_cat.MODI_DTTM IS '수정일시';

-- wrk_cat sample 데이터
insert into wrk_cat values ('WRK001','업무1','업무1 설명','Y','admin', now(), 'admin',now());
insert into wrk_cat values ('WRK002','업무2','업무2 설명','Y','admin', now(), 'admin',now());
insert into wrk_cat values ('WRK003','업무3','업무3 설명','Y','admin', now(), 'admin',now());


-- menu 테이블
create table menu (
    MENU_ID NUMERIC(20) NOT NULL,
    UPPER_MENU_ID NUMERIC(20),
    MENU_NM VARCHAR(100),
    MENU_URL VARCHAR(300),
    MENU_DESC VARCHAR(300),
    MENU_ORDR NUMERIC(5),
    RGST_ID VARCHAR(30),
    RGST_DTTM TIMESTAMP,
    MODI_ID VARCHAR(30),
    MODI_DTTM TIMESTAMP,
    PRIMARY KEY(MENU_ID)
);

-- 상위메뉴가 0이면 최상위 이고 상위메뉴가 특정메뉴ID이면 서브메뉴가 되는 형태의 구조입니다.

COMMENT ON TABLE menu is '메뉴';

COMMENT ON COLUMN menu.MENU_ID IS '메뉴 ID';
COMMENT ON COLUMN menu.UPPER_MENU_ID IS '상위메뉴 ID';
COMMENT ON COLUMN menu.MENU_NM IS '메뉴명';
COMMENT ON COLUMN menu.MENU_URL IS '메뉴 URL';
COMMENT ON COLUMN menu.MENU_DESC IS '메뉴 설명';
COMMENT ON COLUMN menu.MENU_ORDR IS '메뉴 순서';
COMMENT ON COLUMN menu.RGST_ID IS '등록자ID';
COMMENT ON COLUMN menu.RGST_DTTM IS '등록일시';
COMMENT ON COLUMN menu.MODI_ID IS '수정자ID';
COMMENT ON COLUMN menu.MODI_DTTM IS '수정일시';

INSERT INTO menu VALUES (0, 0, 'root', '/', '메뉴트리', 1, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (200000, 0, '시스템 관리', '/code', '시스템 관리 입니다1', 2, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (300000, 0, '프로젝트 관리', '/project', '프로젝트 관리 입니다', 3, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (200001, 200000, '코드 관리', '/code', '시스템 관리/코드 관리', 1, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (200002, 200000, '메뉴 관리', '/menu', '시스템 관리/메뉴 관리', 2, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (300001, 300000, '프로젝트 관리', '/project', '프로젝트 관리/프로젝트 관리', 1, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (300002, 300000, '보고서 관리', '/report', '프로젝트 관리/보고서 관리', 2, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (300003, 300000, '보고서권한 관리', '/reportAuth', '프로젝트 관리/보고서권한 관리', 3, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (300004, 300000, '외부보고서배포 관리', '/reportExtrnl', '프로젝트 관리/외부보고서배포 관리', 4, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (100001, 100000, '사용자 관리', '/member', '사용자 관리/사용자 관리', 1, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (100002, 100000, '권한 관리', '/role', '사용자 관리/권한 관리', 2, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (100003, 100000, '라이선스 관리', '/license', '사용자 관리/라이선스 관리', 3, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (200003, 200000, '외부시스템 관리', '/extrnl', '시스템 관리/외부시스템 관리', 3, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (200004, 200000, '업무카테고리 관리', '/wrkCat', '시스템 관리/업무카테고리 관리', 4, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (100000, 0, '사용자 관리', '/member', '사용자 관리 입니다', 1, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (400000, 0, '자원 관리', '#none', '자원 관리', 4, 'admin', now(), 'admin', now());
INSERT INTO menu VALUES (500000, 0, '감사 관리', '#none', '감사 관리', 5, 'admin', now(), 'admin', now());

-- log 테이블
create table log (
     LOG_ID BIGSERIAL NOT NULL,
     MEMBER_ID VARCHAR(30),
     MEMBER_NM VARCHAR(50),
     JOB_CD VARCHAR(15),
     JOB_NM VARCHAR(100),
     ORG_CD VARCHAR(15),
     ORG_NM VARCHAR(100),
     ROLE_ID VARCHAR(15),
     ROLE_NM VARCHAR(100),
     CLIENT_IP VARCHAR(100),
     CONTROLLER_NM VARCHAR(100),
     METHOD_NM VARCHAR(300),
     PROGRAM_NM VARCHAR(300),
     HTTP_METHOD VARCHAR(10),
     REQUEST_URI VARCHAR(300),
     PARAMS JSONB,
     LOG_DTTM TIMESTAMP,
     PRIMARY KEY(LOG_ID)
);

COMMENT ON TABLE log is '로그이력';

COMMENT ON COLUMN log.LOG_ID IS '로그 ID';
COMMENT ON COLUMN log.MEMBER_ID IS '사번';
COMMENT ON COLUMN log.MEMBER_NM IS '성명';
COMMENT ON COLUMN log.JOB_CD IS '직책코드';
COMMENT ON COLUMN log.JOB_NM IS '직책명';
COMMENT ON COLUMN log.ORG_CD IS '부서코드';
COMMENT ON COLUMN log.ORG_NM IS '부서명';
COMMENT ON COLUMN log.ROLE_ID IS '권한ID';
COMMENT ON COLUMN log.ROLE_NM IS '권한명';
COMMENT ON COLUMN log.CLIENT_IP IS '접속IP';
COMMENT ON COLUMN log.CONTROLLER_NM IS '컨트롤러명';
COMMENT ON COLUMN log.METHOD_NM IS '메소드명';
COMMENT ON COLUMN log.PROGRAM_NM IS '접근 프로그램 명';
COMMENT ON COLUMN log.HTTP_METHOD IS 'HTTP 메소드';
COMMENT ON COLUMN log.REQUEST_URI IS '요청 URI';
COMMENT ON COLUMN log.PARAMS IS '파라미터';
COMMENT ON COLUMN log.LOG_DTTM IS '로그일시';