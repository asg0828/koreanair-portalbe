CREATE TABLE ptl.t_portlet (
	portlet_id varchar(64) NOT NULL, -- 포틀릿 ID
	portlet_nm varchar(200) NULL, -- 포틀릿 명
	portlet_dsc text NULL, -- 포틀릿 설명
	portlet_type_cd varchar(16) NULL, -- 포틀릿 유형 코드(GROUP_ID=PORTLET_TYPE_CODE	포틀릿 타입)
	system_cd varchar(16) NULL, -- 시스템 코드(GGROUP_ID=SYSTEM_CD	시스템 코드)
	move_yn varchar(1) NULL, -- 이동 가능 여부
	portlet_link_type_cd varchar(64) NULL, -- 포틀릿 링크 유형 코드(GROUP_ID=PORTLET_LINK_TYPE_CD	포틀릿 링크 유형 코드)
	default_view_url varchar(256) NULL, -- 기본 view URL
	more_view_yn varchar(1) NULL, -- 더보기 가능 여부
	more_view_url varchar(256) NULL, -- 더보기 view URL
	share_yn  varchar(1) NULL, -- 공용여부
	use_yn varchar(1) NULL DEFAULT 'N'::character varying, -- 사용 여부
	portlet_auth varchar(64) NULL -- 권한 범위,
	modi_se varchar(32) NULL, -- 수정 구분[I: 등록 / U: 수정 / D: 삭제 / C: 완료 / R: 삭제완료]
	rgst_id varchar(32) NOT NULL, -- 등록 ID
	rgst_dt timestamp NOT NULL DEFAULT now(), -- 등록 일시
	modi_id varchar(32) NOT NULL, -- 수정 ID
	modi_dt timestamp NOT NULL DEFAULT now(), -- 수정 일시
	CONSTRAINT t_portlet_pk PRIMARY KEY (portlet_id)
);
COMMENT ON TABLE ptl.t_tableau_project IS '포틀릿';
