package com.bdp.ap.app.audit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bdp.ap.app.audit.mapper.AuditDataSetMapper;
import com.bdp.ap.app.audit.model.AuditDataSetModel;
import com.bdp.ap.app.audit.model.UsingTableModel;
import com.bdp.ap.common.paging.Criteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuditDataSetService {

	@Resource
	private AuditDataSetMapper auditDataSetMapper;

	//데이터셋 목록 추출
	public List<AuditDataSetModel> selectDataSetList(Criteria criteria) {
		return auditDataSetMapper.selectDataSetList(criteria);
	}

	//데이터셋 목록 카운트
	public int selectDataSetCount(Criteria criteria) {
		return auditDataSetMapper.selectDataSetCount(criteria);
	}

	//데이터셋 목록 >  목록 추출
	public List<UsingTableModel> selectUsingTableList(String datasetId) {
		return auditDataSetMapper.selectUsingTableList(datasetId);
	}

	//관리자 접근로그 카운트
	public int selectUsingTableCount(Criteria criteria) {
		return auditDataSetMapper.selectUsingTableCount(criteria);
	}

}
