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
import com.bdp.ap.config.props.LogSysProps;
import com.bdp.ap.schedule.mapper.JobLogSysMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobLogSysService {

	@Resource(name="logSysProps")
	private LogSysProps props;

	@Resource(name = "commonUtil")
	private CommonUtil util;

	@Resource(name="jobLogSysMapper")
	private JobLogSysMapper mapper;

	private static final int pageSize = 1000;
	private static final String REPLACE_DATE = "{DATE}";
	private static final String[] HEADER_USER_SYS_RQST_CNT = {"log_dt","memb","dept_nm","cnt"};

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
			log.warn("LOG_SYS 이전 파일 삭제 중 오류 발생");
			log.warn(e.getMessage());
		}
	}

	//CSV Format
	private CSVFormat getCSVFormat(String[] headers) {
		return CSVFormat.TDF.withTrim()
				.withHeader(headers).withSkipHeaderRecord()
				.withQuote(null).withEscape(null)
				.withDelimiter(',');
	}

	//파일명 날짜 치환
	private String makeFileName(String fileName) {
		return fileName.replace(REPLACE_DATE,util.getDateString(Constant.DATE_FORMAT.DEFAULT_DAY, -1));
	}

	/**
	 * 로그 - 사용자 접속 로그 : 접속 건수
	 */
	public void userRqstCnt(JSONObject msg) {
		try {
			int count = mapper.selectUserSysRqstCntCount();
			int pageNo = 0;
			if (count > 0) {
				Path path = Paths.get(props.getLogPath(), makeFileName(props.getUserRqstCntFile()));
				BufferedWriter buffer = Files.newBufferedWriter(path, Charset.forName(props.getEncoding()));
				CSVPrinter file = new CSVPrinter(buffer, getCSVFormat(HEADER_USER_SYS_RQST_CNT));

				boolean loop = true;
				while(loop) {
					List<Map<String,Object>> list = mapper.selectUserSysRqstCnt(pageNo);
					if (list != null && list.size() > 0) {
						for (Map<String,Object> row : list) {
							file.printRecord(Arrays.asList(HEADER_USER_SYS_RQST_CNT).stream().map(key -> row.get(key)).collect(Collectors.toList()));
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
			msg.put("userRqstCnt", "Y");
		} catch (IOException e) {
			msg.put("userRqstCnt", "N");
			msg.put("error", e.getMessage());
			log.warn("시스템 수집 로그 - 사용자 접속 요청 건수 처리중 오류 발생");
			log.warn(e.getMessage());
		}
	}

}
