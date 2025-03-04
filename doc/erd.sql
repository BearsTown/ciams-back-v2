
/* Drop Tables */

DROP TABLE IF EXISTS ciams_code;
DROP TABLE IF EXISTS ciams_plan_buildinguse;
DROP TABLE IF EXISTS ciams_plan_environment;
DROP TABLE IF EXISTS ciams_plan_infra;
DROP TABLE IF EXISTS ciams_plan_area;
DROP TABLE IF EXISTS ciams_plan_layer;
DROP TABLE IF EXISTS ciams_plan;
DROP TABLE IF EXISTS ciams_plan_area_link;
DROP TABLE IF EXISTS ciams_plan_content;
DROP TABLE IF EXISTS ciams_plan_content_detail;
DROP TABLE IF EXISTS ciams_plan_content_link;
DROP TABLE IF EXISTS ciams_plan_incen;
DROP TABLE IF EXISTS ciams_plan_incen_app;
DROP TABLE IF EXISTS ciams_plan_incen_limit;
DROP TABLE IF EXISTS ciams_plan_upi;




/* Create Tables */

-- 코드관리
CREATE TABLE ciams_code
(
	-- 코드_고유번호
	code varchar(20) NOT NULL UNIQUE,
	-- 코드명
	code_name varchar(128),
	-- 순번
	sort_sn numeric,
	-- 상위_코드
	parent_code varchar(20),
	-- 코드_변수값
	code_val varchar(128),
	-- 코드_비고
	code_desc varchar(256),
	-- 사용유무
	use_yn varchar(1),
	PRIMARY KEY (code)
) WITHOUT OIDS;


-- 성장관리
CREATE TABLE ciams_plan
(
	-- 성장관리고유번호
	plan_id varchar(36) NOT NULL UNIQUE,
	-- 지정일자
	plan_date date,
	-- 성장관리명
	plan_name varchar(100),
	-- 성장관리_비고
	plan_note varchar(200),
	PRIMARY KEY (plan_id)
) WITHOUT OIDS;


-- 성장관리구역
CREATE TABLE ciams_plan_area
(
	-- 성장관리구역고유번호
	plan_area_id varchar(36) NOT NULL UNIQUE,
	-- 성장관리고유번호
	plan_id varchar(36) NOT NULL UNIQUE,
	-- 대분류
	g_category varchar(5),
	-- 중분류
	m_catagory varchar(5),
	-- 소분류
	s_category varchar(5),
	-- 구역명
	area_title varchar(100),
	-- 지역
	loc varchar(100),
	-- 면적
	area numeric,
	PRIMARY KEY (plan_area_id)
) WITHOUT OIDS;


-- 성장관리구역연계정보
CREATE TABLE ciams_plan_area_link
(
	-- 성장관리구역고유번호
	plan_area_id varchar(36) NOT NULL,
	-- 버전
	ver varchar(10) NOT NULL,
	-- 인센티브계획_고유번호
	plan_incen_id varchar(36),
	-- 인센티브상한고유번호
	plan_incen_limit_id varchar(36),
	-- 성장관리구역컨텐츠연계_고유번호
	plan_content_link_id varchar(36),
	-- 비고
	memo varchar(100),
	-- 등록일
	reg_date date,
	-- 수정일
	chg_date date,
	-- 사용유무
	use_yn varchar(1),
	PRIMARY KEY (plan_area_id, ver)
) WITHOUT OIDS;


-- 건축물용도계획
CREATE TABLE ciams_plan_buildinguse
(
	-- 건축물용도계획_고유번호
	plan_buildinguse_id varchar(36) NOT NULL UNIQUE,
	-- 성장관리구역고유번호
	plan_area_id varchar(36) NOT NULL UNIQUE,
	-- 지정일자
	plan_date date,
	-- 분류
	category varchar(5),
	-- 내용
	contents text,
	PRIMARY KEY (plan_buildinguse_id)
) WITHOUT OIDS;


-- 성장관리계획 운영 컨텐츠
CREATE TABLE ciams_plan_content
(
	-- 성장관리계획 운영 컨텐츠ID
	plan_content_id varchar(36) NOT NULL,
	-- 탭분류(인프라-INFRA, 건축물용도계획-BUILDINGUSE, 환경관리계획-ENVIRONMENT)
	category varchar(30),
	-- 제목
	title varchar(100),
	-- 내용
	contents text,
	-- 펼침 여부
	is_open boolean,
	-- 도로계획 여부
	is_road_plan boolean,
	-- 등록일
	reg_date date,
	-- 수정일
	chg_date date,
	PRIMARY KEY (plan_content_id)
) WITHOUT OIDS;


-- 성장관리계획 운영 세부 컨텐츠
CREATE TABLE ciams_plan_content_detail
(
	-- 성장관리계획 운영 세부 컨텐츠ID
	plan_content_detail_id varchar(36) NOT NULL,
	-- 성장관리계획 운영 컨텐츠ID
	plan_content_id varchar(36),
	-- 타입분류
	type_name varchar(100),
	-- 내용
	contents text,
	-- 순번
	sort_sn numeric,
	PRIMARY KEY (plan_content_detail_id)
) WITHOUT OIDS;


-- 성장관리구역컨텐츠연계
CREATE TABLE ciams_plan_content_link
(
	-- 성장관리구역컨텐츠연계_고유번호
	plan_content_link_id varchar(36) NOT NULL,
	-- 성장관리구역컨텐츠_고유번호
	plan_content_id varchar(36) NOT NULL,
	-- 순번
	sort_sn numeric,
	PRIMARY KEY (plan_content_link_id, plan_content_id)
) WITHOUT OIDS;


-- 환경관리계획
CREATE TABLE ciams_plan_environment
(
	-- 환경관리계획_고유번호
	plan_environment_id varchar(36) NOT NULL UNIQUE,
	-- 성장관리구역고유번호
	plan_area_id varchar(36) NOT NULL UNIQUE,
	-- 지정일자
	plan_date date,
	-- 분류
	category varchar(5),
	-- 내용
	contents text,
	PRIMARY KEY (plan_environment_id)
) WITHOUT OIDS;


-- 인센티브계획
CREATE TABLE ciams_plan_incen
(
	-- 인센티브계획_고유번호
	plan_incen_id varchar(36) NOT NULL UNIQUE,
	-- 용도지역
	area_use varchar(50) NOT NULL,
	-- 지정일자
	plan_date date,
	-- 대분류
	g_category varchar(5),
	-- 중분류
	m_catagory varchar(5),
	-- 소분류
	s_category varchar(5) NOT NULL,
	-- 건폐율 공식
	fomula_cr varchar(200),
	-- 용적률 공식
	fomula_far varchar(200),
	-- 비고
	memo varchar(100),
	PRIMARY KEY (plan_incen_id, area_use, s_category)
) WITHOUT OIDS;


-- 인센티브산정
CREATE TABLE ciams_plan_incen_app
(
	-- 인센티브산정_고유번호
	plan_incen_app_id varchar(36) NOT NULL UNIQUE,
	-- 파일_고유번호
	file_id varchar(36),
	-- 지역
	loc varchar(100),
	-- 면적
	area numeric,
	-- 발급_번호
	issue_no varchar(36),
	-- 발급_일자
	issue_date date,
	PRIMARY KEY (plan_incen_app_id)
) WITHOUT OIDS;


-- 인센티브_상한
CREATE TABLE ciams_plan_incen_limit
(
	-- 인센티브상한고유번호
	plan_incen_limit_id varchar(36) NOT NULL UNIQUE,
	-- 용도지역
	area_use varchar(50),
	-- 건폐율 상한
	plan_limit_cr int,
	-- 용적율 상한
	plan_limit_far int,
	PRIMARY KEY (plan_incen_limit_id)
) WITHOUT OIDS;


-- 기반시설계획관리
CREATE TABLE ciams_plan_infra
(
	-- 기반시설계획관리_고유번호
	plan_infra_id varchar(36) NOT NULL UNIQUE,
	-- 성장관리구역고유번호
	plan_area_id varchar(36) NOT NULL UNIQUE,
	-- 지정일자
	plan_date date,
	-- 분류
	category varchar(5),
	-- 내용
	contents text,
	PRIMARY KEY (plan_infra_id)
) WITHOUT OIDS;


-- 성장관리계획레이어
CREATE TABLE ciams_plan_layer
(
	-- 서비스_키
	service_key varchar(36) NOT NULL,
	-- 레이어_코드
	layer_cd varchar(50) NOT NULL,
	-- 성장관리고유번호
	plan_id varchar(36) NOT NULL UNIQUE,
	-- 대분류
	g_category varchar(5),
	-- 소분류
	s_category varchar(5),
	-- 레이어명
	layer_title varchar(50),
	-- 레이어_비고
	layer_note varchar(200),
	-- 좌표계
	CRS varchar(20),
	PRIMARY KEY (service_key, layer_cd),
	UNIQUE (service_key, layer_cd)
) WITHOUT OIDS;


-- 개발행위허가정보
CREATE TABLE ciams_plan_upi
(
	-- 개발행위허가정보_고유번호
	ciams_plan_upi_id varchar(36) NOT NULL,
	-- 필지고유번호
	pnu varchar(20),
	-- 위치명
	loc varchar(100),
	-- 지목
	jimok varchar(8),
	-- 면적
	area numeric,
	-- 구분명
	type varchar(20),
	-- 신청일
	app_date date,
	-- 허가일
	permit_date date,
	-- 개발행위목적
	purpose varchar(100),
	-- 사유
	note varchar(200),
	PRIMARY KEY (ciams_plan_upi_id)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE ciams_plan_area
	ADD FOREIGN KEY (plan_id)
	REFERENCES ciams_plan (plan_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ciams_plan_layer
	ADD FOREIGN KEY (plan_id)
	REFERENCES ciams_plan (plan_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ciams_plan_buildinguse
	ADD FOREIGN KEY (plan_area_id)
	REFERENCES ciams_plan_area (plan_area_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ciams_plan_environment
	ADD FOREIGN KEY (plan_area_id)
	REFERENCES ciams_plan_area (plan_area_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ciams_plan_infra
	ADD FOREIGN KEY (plan_area_id)
	REFERENCES ciams_plan_area (plan_area_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



/* Comments */

COMMENT ON TABLE ciams_code IS '코드관리';
COMMENT ON COLUMN ciams_code.code IS '코드_고유번호';
COMMENT ON COLUMN ciams_code.code_name IS '코드명';
COMMENT ON COLUMN ciams_code.sort_sn IS '순번';
COMMENT ON COLUMN ciams_code.parent_code IS '상위_코드';
COMMENT ON COLUMN ciams_code.code_val IS '코드_변수값';
COMMENT ON COLUMN ciams_code.code_desc IS '코드_비고';
COMMENT ON COLUMN ciams_code.use_yn IS '사용유무';
COMMENT ON TABLE ciams_plan IS '성장관리';
COMMENT ON COLUMN ciams_plan.plan_id IS '성장관리고유번호';
COMMENT ON COLUMN ciams_plan.plan_date IS '지정일자';
COMMENT ON COLUMN ciams_plan.plan_name IS '성장관리명';
COMMENT ON COLUMN ciams_plan.plan_note IS '성장관리_비고';
COMMENT ON TABLE ciams_plan_area IS '성장관리구역';
COMMENT ON COLUMN ciams_plan_area.plan_area_id IS '성장관리구역고유번호';
COMMENT ON COLUMN ciams_plan_area.plan_id IS '성장관리고유번호';
COMMENT ON COLUMN ciams_plan_area.g_category IS '대분류';
COMMENT ON COLUMN ciams_plan_area.m_catagory IS '중분류';
COMMENT ON COLUMN ciams_plan_area.s_category IS '소분류';
COMMENT ON COLUMN ciams_plan_area.area_title IS '구역명';
COMMENT ON COLUMN ciams_plan_area.loc IS '지역';
COMMENT ON COLUMN ciams_plan_area.area IS '면적';
COMMENT ON TABLE ciams_plan_area_link IS '성장관리구역연계정보';
COMMENT ON COLUMN ciams_plan_area_link.plan_area_id IS '성장관리구역고유번호';
COMMENT ON COLUMN ciams_plan_area_link.ver IS '버전';
COMMENT ON COLUMN ciams_plan_area_link.plan_incen_id IS '인센티브계획_고유번호';
COMMENT ON COLUMN ciams_plan_area_link.plan_incen_limit_id IS '인센티브상한고유번호';
COMMENT ON COLUMN ciams_plan_area_link.plan_content_link_id IS '성장관리구역컨텐츠연계_고유번호';
COMMENT ON COLUMN ciams_plan_area_link.memo IS '비고';
COMMENT ON COLUMN ciams_plan_area_link.reg_date IS '등록일';
COMMENT ON COLUMN ciams_plan_area_link.chg_date IS '수정일';
COMMENT ON COLUMN ciams_plan_area_link.use_yn IS '사용유무';
COMMENT ON TABLE ciams_plan_buildinguse IS '건축물용도계획';
COMMENT ON COLUMN ciams_plan_buildinguse.plan_buildinguse_id IS '건축물용도계획_고유번호';
COMMENT ON COLUMN ciams_plan_buildinguse.plan_area_id IS '성장관리구역고유번호';
COMMENT ON COLUMN ciams_plan_buildinguse.plan_date IS '지정일자';
COMMENT ON COLUMN ciams_plan_buildinguse.category IS '분류';
COMMENT ON COLUMN ciams_plan_buildinguse.contents IS '내용';
COMMENT ON TABLE ciams_plan_content IS '성장관리계획 운영 컨텐츠';
COMMENT ON COLUMN ciams_plan_content.plan_content_id IS '성장관리계획 운영 컨텐츠ID';
COMMENT ON COLUMN ciams_plan_content.category IS '탭분류(인프라-INFRA, 건축물용도계획-BUILDINGUSE, 환경관리계획-ENVIRONMENT)';
COMMENT ON COLUMN ciams_plan_content.title IS '제목';
COMMENT ON COLUMN ciams_plan_content.contents IS '내용';
COMMENT ON COLUMN ciams_plan_content.is_open IS '펼침 여부';
COMMENT ON COLUMN ciams_plan_content.is_road_plan IS '도로계획 여부';
COMMENT ON TABLE ciams_plan_content_detail IS '성장관리계획 운영 세부 컨텐츠';
COMMENT ON COLUMN ciams_plan_content_detail.plan_content_detail_id IS '성장관리계획 운영 세부 컨텐츠ID';
COMMENT ON COLUMN ciams_plan_content_detail.plan_content_id IS '성장관리계획 운영 컨텐츠ID';
COMMENT ON COLUMN ciams_plan_content_detail.type_name IS '타입분류';
COMMENT ON COLUMN ciams_plan_content_detail.contents IS '내용';
COMMENT ON COLUMN ciams_plan_content_detail.sort_sn IS '순번';
COMMENT ON TABLE ciams_plan_content_link IS '성장관리구역컨텐츠연계';
COMMENT ON COLUMN ciams_plan_content_link.plan_content_link_id IS '성장관리구역컨텐츠연계_고유번호';
COMMENT ON COLUMN ciams_plan_content_link.plan_content_id IS '성장관리구역컨텐츠_고유번호';
COMMENT ON COLUMN ciams_plan_content_link.sort_sn IS '순번';
COMMENT ON TABLE ciams_plan_environment IS '환경관리계획';
COMMENT ON COLUMN ciams_plan_environment.plan_environment_id IS '환경관리계획_고유번호';
COMMENT ON COLUMN ciams_plan_environment.plan_area_id IS '성장관리구역고유번호';
COMMENT ON COLUMN ciams_plan_environment.plan_date IS '지정일자';
COMMENT ON COLUMN ciams_plan_environment.category IS '분류';
COMMENT ON COLUMN ciams_plan_environment.contents IS '내용';
COMMENT ON TABLE ciams_plan_incen IS '인센티브계획';
COMMENT ON COLUMN ciams_plan_incen.plan_incen_id IS '인센티브계획_고유번호';
COMMENT ON COLUMN ciams_plan_incen.area_use IS '용도지역';
COMMENT ON COLUMN ciams_plan_incen.plan_date IS '지정일자';
COMMENT ON COLUMN ciams_plan_incen.g_category IS '대분류';
COMMENT ON COLUMN ciams_plan_incen.m_catagory IS '중분류';
COMMENT ON COLUMN ciams_plan_incen.s_category IS '소분류';
COMMENT ON COLUMN ciams_plan_incen.fomula_cr IS '건폐율 공식';
COMMENT ON COLUMN ciams_plan_incen.fomula_far IS '용적률 공식';
COMMENT ON COLUMN ciams_plan_incen.memo IS '비고';
COMMENT ON TABLE ciams_plan_incen_app IS '인센티브산정';
COMMENT ON COLUMN ciams_plan_incen_app.plan_incen_app_id IS '인센티브산정_고유번호';
COMMENT ON COLUMN ciams_plan_incen_app.file_id IS '파일_고유번호';
COMMENT ON COLUMN ciams_plan_incen_app.loc IS '지역';
COMMENT ON COLUMN ciams_plan_incen_app.area IS '면적';
COMMENT ON COLUMN ciams_plan_incen_app.issue_no IS '발급_번호';
COMMENT ON COLUMN ciams_plan_incen_app.issue_date IS '발급_일자';
COMMENT ON TABLE ciams_plan_incen_limit IS '인센티브_상한';
COMMENT ON COLUMN ciams_plan_incen_limit.plan_incen_limit_id IS '인센티브상한고유번호';
COMMENT ON COLUMN ciams_plan_incen_limit.area_use IS '용도지역';
COMMENT ON COLUMN ciams_plan_incen_limit.plan_limit_cr IS '건폐율 상한';
COMMENT ON COLUMN ciams_plan_incen_limit.plan_limit_far IS '용적율 상한';
COMMENT ON TABLE ciams_plan_infra IS '기반시설계획관리';
COMMENT ON COLUMN ciams_plan_infra.plan_infra_id IS '기반시설계획관리_고유번호';
COMMENT ON COLUMN ciams_plan_infra.plan_area_id IS '성장관리구역고유번호';
COMMENT ON COLUMN ciams_plan_infra.plan_date IS '지정일자';
COMMENT ON COLUMN ciams_plan_infra.category IS '분류';
COMMENT ON COLUMN ciams_plan_infra.contents IS '내용';
COMMENT ON TABLE ciams_plan_layer IS '성장관리계획레이어';
COMMENT ON COLUMN ciams_plan_layer.service_key IS '서비스_키';
COMMENT ON COLUMN ciams_plan_layer.layer_cd IS '레이어_코드';
COMMENT ON COLUMN ciams_plan_layer.plan_id IS '성장관리고유번호';
COMMENT ON COLUMN ciams_plan_layer.g_category IS '대분류';
COMMENT ON COLUMN ciams_plan_layer.s_category IS '소분류';
COMMENT ON COLUMN ciams_plan_layer.layer_title IS '레이어명';
COMMENT ON COLUMN ciams_plan_layer.layer_note IS '레이어_비고';
COMMENT ON COLUMN ciams_plan_layer.CRS IS '좌표계';
COMMENT ON TABLE ciams_plan_upi IS '개발행위허가정보';
COMMENT ON COLUMN ciams_plan_upi.ciams_plan_upi_id IS '개발행위허가정보_고유번호';
COMMENT ON COLUMN ciams_plan_upi.pnu IS '필지고유번호';
COMMENT ON COLUMN ciams_plan_upi.loc IS '위치명';
COMMENT ON COLUMN ciams_plan_upi.jimok IS '지목';
COMMENT ON COLUMN ciams_plan_upi.area IS '면적';
COMMENT ON COLUMN ciams_plan_upi.type IS '구분명';
COMMENT ON COLUMN ciams_plan_upi.app_date IS '신청일';
COMMENT ON COLUMN ciams_plan_upi.permit_date IS '허가일';
COMMENT ON COLUMN ciams_plan_upi.purpose IS '개발행위목적';
COMMENT ON COLUMN ciams_plan_upi.note IS '사유';



