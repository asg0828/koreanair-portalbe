package com.bdp.ap.app.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdp.ap.app.system.mapper.PortletItemMapper;
import com.bdp.ap.app.system.model.PortletItemModel;
import com.bdp.ap.common.Constant;
import com.bdp.ap.config.security.AuthUser;

@Service
public class PortletItemServiceImpl implements PortletItemService {
	@Autowired
	PortletItemMapper portletItemMapper;

	public List<PortletItemModel> selectItemList(String codeId, AuthUser authUser) {
		List<PortletItemModel> portletItemList = null;
		
		switch (codeId) {
		case Constant.Code.PL_NOTICE:
			portletItemList = portletItemMapper.selectPlNotice();
			break;
		case Constant.Code.PL_FAQ:
			portletItemList = portletItemMapper.selectPlFaq();
			break;
		case Constant.Code.PL_HELP:
			portletItemList = portletItemMapper.selectPlHelp();
			break;
		case Constant.Code.PL_ANAL_SRC_SHARE:
			portletItemList = portletItemMapper.selectPlAnalSrcShare();
			break;
		case Constant.Code.PL_QNA:
			portletItemList = portletItemMapper.selectPlQna();
			break;
		case Constant.Code.PL_DATAROOM:
			portletItemList = portletItemMapper.selectPlDataroom();
			break;
		case Constant.Code.MP_RECENT_LIST:
			portletItemList = portletItemMapper.selectMpRecentList();
			break;
		case Constant.Code.RL_RECENT_LIST:
			portletItemList = portletItemMapper.selectRlRecentList();
			break;
		case Constant.Code.RL_POP_LIST:
			portletItemList = portletItemMapper.selectRlPopList();
			break;
		case Constant.Code.MY_REPORT_RECENT:
			portletItemList = portletItemMapper.selectMyReportRecent(authUser.getMemberModel().getUserId());
			break;
		case Constant.Code.RS_SELECTED_REPORT:
			portletItemList = portletItemMapper.selectSelectedReport();
			break;
		case Constant.Code.AM_RECENT_LIST:
			portletItemList = portletItemMapper.selectAmRecentList();
			break;
		case Constant.Code.SC_RECENT_LIST:
			portletItemList = portletItemMapper.selectScRecentList();
			break;
		case Constant.Code.RI_RECENT_LIST:
			portletItemList = portletItemMapper.selectRiRecentList();
			break;
		default:
			break;
		}
		
		return portletItemList;
	}
}
