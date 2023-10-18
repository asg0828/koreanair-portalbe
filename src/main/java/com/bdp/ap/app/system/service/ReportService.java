package com.bdp.ap.app.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bdp.ap.app.system.mapper.ReportMapper;
import com.bdp.ap.app.system.model.ReportModel;

@Service
public class ReportService {

    @Resource
	private ReportMapper reportMapper;

    public List<ReportModel> selectReportListType(ReportModel model) {
		return reportMapper.selectReportListType(model);
	}
    
	public int selectReportListTypeCount(ReportModel model) {
		return reportMapper.selectReportListTypeCount(model);
	}

    public ReportModel selectReport(ReportModel model) {
		return reportMapper.selectReport(model);
	}


}