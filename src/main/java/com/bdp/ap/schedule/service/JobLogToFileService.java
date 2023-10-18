package com.bdp.ap.schedule.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.config.props.LogToFileProps;
import com.bdp.ap.schedule.mapper.JobLogToFileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobLogToFileService {

	@Resource(name="logToFileProps")
	private LogToFileProps props;

	@Resource(name = "commonUtil")
	private CommonUtil util;

	@Resource(name="jobLogToFileMapper")
	private JobLogToFileMapper mapper;

	private static final int pageSize = 1000;
	private static final String REPLACE_DATE = "{DATE}";
	private static final String[] HEADER_USER = {"log_dt","user_id","user_nm","pstn_code","dept_code","adof_dept_code","hdept_code","company_code","duty_se","use_yn","last_log_dt","start_dt","end_dt","modi_se","rgst_id","rgst_dt","modi_id","modi_dt","bf_dept_code","dept_updt_dt"};
	private static final String[] HEADER_USER_HIST = {"log_dt","user_id","user_nm","pstn_code","pstn_nm","dept_code","dept_nm","adof_dept_code","adof_dept_nm","hdept_code","hdept_nm","company_code","company_nm","duty_se","use_yn","last_log_dt","start_dt","end_dt","modi_se","rgst_id","rgst_dt","modi_id","modi_dt","mgr_auth_id","mgr_auth_cl","mgr_auth_nm","mgr_auth_use_yn","user_auth_id","user_auth_cl","user_auth_nm","user_auth_use_yn","bf_dept_code","dept_updt_dt"};
	private static final String[] HEADER_RQST_MGR_SYS = {"log_dt","user_id","user_nm","dept_code","dept_nm","pstn_code","pstn_nm","client_ip","server_ip","rqst_uri","rqst_method","method_nm","msg","auth_id","auth_nm","program_nm","controller_nm"};
	private static final String[] HEADER_RQST_USER_SYS = {"log_dt","user_id","user_nm","dept_code","dept_nm","pstn_code","pstn_nm","client_ip","server_ip","rqst_uri","rqst_method","method_nm","msg","auth_id","auth_nm","program_nm","controller_nm"};
	private static final String[] HEADER_TABLEAU_MGR_SYS = {"log_dt","user_id","user_nm","dept_code","dept_nm","pstn_code","pstn_nm","report_id","ver","tableau_workbook_id","tableau_param","tableau_user_id"};
	private static final String[] HEADER_TABLEAU_USER_SYS = {"log_dt","user_id","user_nm","dept_code","dept_nm","pstn_code","pstn_nm","report_id","ver","tableau_workbook_id","tableau_param","tableau_user_id"};
	private static final String[] HEADER_BIZMETA_MGR_SYS = {"log_dt"};
	private static final String[] HEADER_BIZMETA_USER_SYS = {"log_dt"};
	private static final String[] HEADER_AWS_MGR_SYS = {"log_dt"};
	private static final String[] HEADER_AWS_USER_SYS = {"log_dt"};
	private static final String[] HEADER_APRV_RQST = {"log_dt"};

	/**
	 * 이전 파일 삭제
	 */
	public void deleteFile() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, props.getDeletePd());
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd",Locale.KOREAN);
		try {
			List<Path> result = Files.list(Paths.get(props.getLogPath())).filter(Files::isRegularFile).collect(Collectors.toList());
			if (result != null && result.size() > 0) {
				for (Path path : result) {
					String de = StringUtils.substringAfterLast(FilenameUtils.getBaseName(path.getFileName().toString()),"_");
					try {
						if (c.getTime().after(f.parse(de))) {
							Files.delete(path);
						}
					} catch (ParseException e) {
						Files.delete(path);
					}
				}
			}
		} catch (IOException e) {
			log.warn("LOG TO FILE 이전 파일 삭제 중 오류 발생");
			log.warn(e.getMessage());
		}
	}

	//CSV Format
	private CSVFormat getCSVFormat(String[] headers) {
		return CSVFormat.TDF.withTrim()
				.withHeader(headers).withSkipHeaderRecord()
				.withQuote(null).withEscape(null)
				.withDelimiter('|');
	}

	//파일명 날짜 치환
	private String makeFileName(String fileName) {
		return fileName.replace(REPLACE_DATE,util.getDateString(Constant.DATE_FORMAT.DEFAULT_DAY, -1));
	}



	/**
	 * 공통 - 사용자
	 */
	public void user(JSONObject msg) {
		try {
			int count = mapper.selectUserCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getUserFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_USER));

				//Header
				//file.printRecord(Arrays.asList(HEADER_USER).stream().map(key -> key.toUpperCase()).collect(Collectors.toList()));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectUser(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_USER).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("USER", "Y");
		} catch (IOException e) {
			msg.put("USER", "N");
			msg.put("USER_ERROR", e.getMessage());
			log.warn("EAI 수집 공통 - 사용자 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}



	/**
	 * 이력 - 사용자 이력
	 */
	public void userHist(JSONObject msg) {
		try {
			int count = mapper.selectUserHistCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getUserHistFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_USER_HIST));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectUserHist(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_USER_HIST).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("USER_HIST", "Y");
		} catch (IOException e) {
			msg.put("USER_HIST", "N");
			msg.put("USER_HIST_ERROR", e.getMessage());
			log.warn("EAI 수집 이력 - 사용자 이력 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}



	/**
	 * 로그 - 관리자 시스템 접속 로그
	 */
	public void rqstMgrSys(JSONObject msg) {
		try {
			int count = mapper.selectRqstMgrSysCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getRqstMgrSysFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_RQST_MGR_SYS));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectRqstMgrSys(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_RQST_MGR_SYS).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("RQST_MGR_SYS", "Y");
		} catch (IOException e) {
			msg.put("RQST_MGR_SYS", "N");
			msg.put("RQST_MGR_SYS_ERROR", e.getMessage());
			log.warn("EAI 수집 로그 - 관리자 시스템 접속 로그 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}

	/**
	 * 로그 - 사용자 시스템 접속 로그
	 */
	public void rqstUserSys(JSONObject msg) {
		try {
			int count = mapper.selectRqstUserSysCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getRqstUserSysFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_RQST_USER_SYS));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectRqstUserSys(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_RQST_USER_SYS).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("RQST_USER_SYS", "Y");
		} catch (IOException e) {
			msg.put("RQST_USER_SYS", "N");
			msg.put("RQST_USER_SYS_ERROR", e.getMessage());
			log.warn("EAI 수집 로그 - 사용자 시스템 접속 로그 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}

	/**
	 * 로그 - 관리자 시스템 태블로 요청 로그
	 */
	public void tableauMgrSys(JSONObject msg) {
		try {
			int count = mapper.selectTableauMgrSysCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getTableauMgrSysFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_TABLEAU_MGR_SYS));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectTableauMgrSys(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_TABLEAU_MGR_SYS).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("TABLEAU_MGR_SYS", "Y");
		} catch (IOException e) {
			msg.put("TABLEAU_MGR_SYS", "N");
			msg.put("TABLEAU_MGR_SYS_ERROR", e.getMessage());
			log.warn("EAI 수집 로그 - 관리자 시스템 태블로 요청 로그 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}

	/**
	 * 로그 - 사용자 시스템 태블로 요청 로그
	 */
	public void tableauUserSys(JSONObject msg) {
		try {
			int count = mapper.selectTableauUserSysCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getTableauUserSysFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_TABLEAU_USER_SYS));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectTableauUserSys(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_TABLEAU_USER_SYS).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("TABLEAU_USER_SYS", "Y");
		} catch (IOException e) {
			msg.put("TABLEAU_USER_SYS", "N");
			msg.put("TABLEAU_USER_SYS_ERROR", e.getMessage());
			log.warn("EAI 수집 로그 - 사용자 시스템 태블로 요청 로그 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}

	/**
	 * 로그 - 포털 관리자 시스템 비즈메타 접속 로그
	 */
	public void bizmetaMgrSys(JSONObject msg) {
		try {
			int count = mapper.selectBizmetaMgrSysCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getBizmetaMgrSysFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_BIZMETA_MGR_SYS));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectBizmetaMgrSys(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_BIZMETA_MGR_SYS).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("BIZMETA_MGR_SYS", "Y");
		} catch (IOException e) {
			msg.put("BIZMETA_MGR_SYS", "N");
			msg.put("BIZMETA_MGR_SYS_ERROR", e.getMessage());
			log.warn("EAI 수집 로그 - 관리자 시스템 비즈메타 접속 로그 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}

	/**
	 * 로그 - 포털 사용자 시스템 비즈메타 접속 로그
	 */
	public void bizmetaUserSys(JSONObject msg) {
		try {
			int count = mapper.selectBizmetaUserSysCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getBizmetaUserSysFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_BIZMETA_USER_SYS));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectBizmetaUserSys(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_BIZMETA_USER_SYS).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("BIZMETA_USER_SYS", "Y");
		} catch (IOException e) {
			msg.put("BIZMETA_USER_SYS", "N");
			msg.put("BIZMETA_USER_SYS_ERROR", e.getMessage());
			log.warn("EAI 수집 로그 - 사용자 시스템 비즈메타 접속 로그 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}

	/**
	 * 로그 - 포털 관리자 시스템 AWS 접속 로그
	 */
	public void awsMgrSys(JSONObject msg) {
		try {
			int count = mapper.selectAwsMgrSysCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getAwsMgrSysFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_AWS_MGR_SYS));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectAwsMgrSys(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_AWS_MGR_SYS).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("AWS_MGR_SYS", "Y");
		} catch (IOException e) {
			msg.put("AWS_MGR_SYS", "N");
			msg.put("AWS_MGR_SYS_ERROR", e.getMessage());
			log.warn("EAI 수집 로그 - 관리자 시스템 AWS 접속 로그 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}

	/**
	 * 로그 - 포털 사용자 시스템 AWS 접속 로그
	 */
	public void awsUserSys(JSONObject msg) {
		try {
			int count = mapper.selectAwsUserSysCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getAwsUserSysFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_AWS_USER_SYS));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectAwsUserSys(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_AWS_USER_SYS).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("AWS_USER_SYS", "Y");
		} catch (IOException e) {
			msg.put("AWS_USER_SYS", "N");
			msg.put("AWS_USER_SYS_ERROR", e.getMessage());
			log.warn("EAI 수집 로그 - 사용자 시스템 AWS 접속 로그 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}



	/**
	 * 업무 - 승인 요청 정보 중 승인 상태가 승인완료, 반려(회수) 인 승인 건
	 */
	public void aprvRqst(JSONObject msg) {
		try {
			int count = mapper.selectAprvRqstCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getAprvRqstFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_APRV_RQST));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectAprvRqst(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_APRV_RQST).stream().map(key -> row.get(key)).collect(Collectors.toList()));
						}
					}
					if (count <= ((pageNo+1) * pageSize)) {
						loop = false;
					}
					pageNo++;
				}

				file.flush();
				file.close();
			}
			msg.put("APRV_RQST", "Y");
		} catch (IOException e) {
			msg.put("APRV_RQST", "N");
			msg.put("APRV_RQST_ERROR", e.getMessage());
			log.warn("EAI 수집 로그 - 승인 요청 정보 중 승인 상태가 승인완료, 반려(회수) 인 승인 건 처리 중 오류 발생");
			log.warn(e.getMessage());
		}
	}
}
