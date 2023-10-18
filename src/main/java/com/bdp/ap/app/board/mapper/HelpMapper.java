package com.bdp.ap.app.board.mapper;

import java.util.List;

import com.bdp.ap.app.board.model.HelpModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

@ConnMapperFirst
public interface HelpMapper {

	List<HelpModel> selectHelpList(Criteria criteria);
	int selectHelpListCount(Criteria criteria);
	HelpModel selectHelp(HelpModel model);
	long deleteHelp(HelpModel model);
	long insertHelp(HelpModel model);
	long updateHelp(HelpModel model);
	long addViewCntHelp(HelpModel model);

}
