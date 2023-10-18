package com.bdp.ap.app.help.mapper;

import java.util.List;

import com.bdp.ap.app.help.model.ScreenHelpModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

@ConnMapperFirst
public interface ScreenHelpMapper {

  List<ScreenHelpModel> selectScreenHelpList(Criteria criteria);

  int selectScreenHelpListCount(Criteria criteria);

  Long insertScreenHelp(ScreenHelpModel screenHelpModel);

  Long updateScreenHelp(ScreenHelpModel screenHelpModel);

  Long deleteScreenHelp(ScreenHelpModel screenHelpModel);

  ScreenHelpModel selectScreenHelp(ScreenHelpModel screenHelpModel);

}
