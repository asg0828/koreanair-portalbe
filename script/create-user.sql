-- PostgreSQL
-- 관리자 권한으로 실행

-- 계정 생성
CREATE USER ptl_ssn PASSWORD 'ptlssn#1';
CREATE USER ptl_app PASSWORD 'ptlapp#1';

-- 스키마 생성 및 권한 부여
CREATE SCHEMA ssn_mgr AUTHORIZATION ptl_ssn;
CREATE SCHEMA ssn_usr AUTHORIZATION ptl_ssn;

CREATE SCHEMA ptl AUTHORIZATION ptl_app;


