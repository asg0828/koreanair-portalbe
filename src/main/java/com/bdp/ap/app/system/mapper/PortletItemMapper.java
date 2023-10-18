package com.bdp.ap.app.system.mapper;

import java.util.List;

import com.bdp.ap.app.system.model.PortletItemModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface PortletItemMapper {
	List<PortletItemModel> selectPlNotice();
	List<PortletItemModel> selectPlFaq();
	List<PortletItemModel> selectPlHelp();
	List<PortletItemModel> selectPlAnalSrcShare();
	List<PortletItemModel> selectPlQna();
	List<PortletItemModel> selectPlDataroom();
	List<PortletItemModel> selectMpRecentList();
	List<PortletItemModel> selectRlRecentList();
	List<PortletItemModel> selectRlPopList();
	List<PortletItemModel> selectMyReportRecent(String authUserId);
	List<PortletItemModel> selectSelectedReport();
	List<PortletItemModel> selectAmRecentList();
	List<PortletItemModel> selectScRecentList();
	List<PortletItemModel> selectRiRecentList();
}
