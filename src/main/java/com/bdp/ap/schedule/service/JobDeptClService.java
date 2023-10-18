package com.bdp.ap.schedule.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.common.CommonUtil;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.ParseUtil;
import com.bdp.ap.config.props.HrProps;
import com.bdp.ap.schedule.mapper.JobDeptClMapper;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobDeptClService {

	@Resource(name="hrProps")
	private HrProps props;

	@Resource(name = "jobDeptClMapper")
	private JobDeptClMapper mapper;

	@Resource(name="commonUtil")
	private CommonUtil util;

	@Resource(name="parseUtil")
	private ParseUtil parseUtil;

	/**
	 * 부서 분류 연계 파일 확인
	 */
	public void checkFiles(JSONObject msg) {
		// 부서 분류 파일 있는지 확인
		JSONObject checkFiles = new JSONObject();
		JSONObject detail = new JSONObject();
		boolean check = util.checkFile(Paths.get(props.getBasePath(),props.getDeptClFile()));
		detail.put(Constant.HR.DEPT_CL, check?Constant.YES:Constant.NO);
		checkFiles.put(Constant.DETAIL, detail);
		checkFiles.put(Constant.RESULT, check?Constant.YES:Constant.NO);
		msg.put(Constant.HR.CHECK_FILES, checkFiles);
	}

	/**
	 * 부서 분류 연계 파일 파싱
	 * @return
	 * @throws Exception
	 */
	public Map<String,List<Map<String,String>>> parsingFiles(JSONObject msg) throws Exception {
		Map<String,List<Map<String,String>>> result = new HashMap<>();
		JSONObject parsingFiles = new JSONObject();
		JSONObject detail = new JSONObject();
		try {
			// 부서 파일 파싱
			result.put(Constant.HR.DEPT_CL, parseUtil.parseCSV(Paths.get(props.getBasePath(),props.getDeptClFile()), Constant.HR.HEADER_DEPT_CL, null, Constant.HR.SEPERATOR, props.getEncoding()));
			detail.put(Constant.HR.DEPT_CL, result.get(Constant.HR.DEPT_CL).size());

			parsingFiles.put(Constant.DETAIL, detail);
			parsingFiles.put(Constant.RESULT, Constant.YES);
			msg.put(Constant.HR.PARSING_FILES, parsingFiles);
		} catch (Exception e) {
			parsingFiles.put(Constant.DETAIL, detail);
			parsingFiles.put(Constant.RESULT, Constant.NO);
			msg.put(Constant.HR.PARSING_FILES, parsingFiles);
			log.warn("부서 분류 연동 파일 파싱 처리 중 오류 발생");
			log.warn(e.getMessage());
			msg.put(Constant.ERROR_MESSAGE, util.getExceptionTrace(e));
		}
		return result;
	}

	/**
	 * 부서 분류 연계 파일 백업
	 */
	public void backupFiles(JSONObject msg) throws Exception {
		JSONObject backupFiles = new JSONObject();
		JSONObject detail = new JSONObject();
		if (StringUtils.equals(Constant.YES, msg.getJSONObject(Constant.HR.CHECK_FILES).getString(Constant.RESULT))) {

			String ymd = util.getDateString(Constant.DATE_FORMAT.DEFAULT_DAY);
			String yr = ymd.substring(0,4);
			// 년도 폴더 생성 확인
			util.checkPath(Paths.get(props.getBackupPath(),yr));
			// 년월일 폴더 생성 확인
			util.checkPath(Paths.get(props.getBackupPath(),yr,ymd));

			// 부서 파일 백업
			Path file = Files.move(Paths.get(props.getBasePath(),props.getDeptClFile()), Paths.get(props.getBackupPath(),yr,ymd,props.getDeptClFile()), StandardCopyOption.REPLACE_EXISTING);
			if (file.toFile().exists()) {
				detail.put(Constant.HR.DEPT_CL, Constant.YES);
			} else {
				detail.put(Constant.HR.DEPT_CL, Constant.NO);
			}
			backupFiles.put(Constant.DETAIL, detail);
			backupFiles.put(Constant.RESULT, Constant.YES);
		}  else {
			detail.put(Constant.HR.DEPT_CL, Constant.NO);
			backupFiles.put(Constant.DETAIL, detail);
			backupFiles.put(Constant.RESULT, Constant.NO);
		}
		return;
	}

	/**
	 * 부서 분류 데이터 등록
	 */
	@Transactional
	public JSONObject insertData(Map<String,List<Map<String,String>>> data) throws Exception {
		JSONObject result = new JSONObject();

		//부서 분류
		JSONObject deptClResult = new JSONObject();
		int deptClDeleteCount = mapper.deleteIfDeptCl();
		deptClResult.put("delete", deptClDeleteCount);
		List<Map<String,String>> deptClList = data.get(Constant.HR.DEPT_CL);
		List<List<Map<String,String>>> deptClPartitionList = Lists.partition(deptClList, 100);
		int deptClInsertCount = 0;
		for (List<Map<String,String>> list : deptClPartitionList) {
			Map<String,List<Map<String,String>>> map = new HashMap<>();
			map.put(Constant.HR.DEPT_CL, list);
			int count = mapper.insertIfDeptCl(map);
			deptClInsertCount += count;
		}
		deptClResult.put("insert", deptClInsertCount);
		result.put(Constant.HR.DEPT_CL, deptClResult);

		result.put(Constant.RESULT, Constant.YES);
		return result;
	}

	/**
	 * 부서 분류 데이터 등록
	 */
	@Transactional
	public JSONObject syncData() throws Exception {
		JSONObject result = new JSONObject();

		//부서 분류
		JSONObject deptResult = new JSONObject();
		//삭제 부서
		deptResult.put("delete", mapper.syncDeleteDeptCl());
		//변경 부서
		deptResult.put("update", mapper.syncUpdateDeptCl());
		//삭제 부서 중 변경 값 없는 부서 복구
		mapper.syncDeleteRecoveryDeptCl();
		//신규 부서
		deptResult.put("insert", mapper.syncInsertDeptCl());
		result.put(Constant.HR.DEPT_CL,deptResult);
		//부서 수정 구분 정리
		mapper.updateModiSeDeptCl();

		result.put(Constant.RESULT, Constant.YES);

		return result;
	}
}
