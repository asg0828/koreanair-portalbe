package com.bdp.ap.app.audit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bdp.ap.app.audit.mapper.DataMapper;
import com.bdp.ap.app.audit.model.DataModel;
import com.bdp.ap.common.paging.Criteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataService {

	@Resource
	private DataMapper dataMapper;

	//데이터 현황
	public List<DataModel> selectDataList(Criteria criteria) {
		return dataMapper.selectDataList(criteria);
	}
	//데이터 카운트
	public int selectDataListCount(Criteria criteria) {
		return dataMapper.selectDataListCount(criteria);
	}

}
