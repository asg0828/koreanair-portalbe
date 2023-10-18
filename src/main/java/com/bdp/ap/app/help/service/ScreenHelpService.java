package com.bdp.ap.app.help.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bdp.ap.app.help.mapper.ScreenHelpMapper;
import com.bdp.ap.app.help.model.ScreenHelpModel;
import com.bdp.ap.common.paging.Criteria;

@Service
public class ScreenHelpService {

  @Resource
  private ScreenHelpMapper screenHelpMapper;

  public List<ScreenHelpModel> selectScreenHelpList(Criteria criteria) {
    return screenHelpMapper.selectScreenHelpList(criteria);
  }

  public int selectScreenHelpListCount(Criteria criteria) {
    return screenHelpMapper.selectScreenHelpListCount(criteria);
  }

  public Long insertScreenHelp(ScreenHelpModel screenHelpModel) {
    return screenHelpMapper.insertScreenHelp(screenHelpModel);
  }

  public Long updateScreenHelp(ScreenHelpModel screenHelpModel) {
    return screenHelpMapper.updateScreenHelp(screenHelpModel);
  }

  public Long deleteScreenHelp(ScreenHelpModel screenHelpModel) {
    return screenHelpMapper.deleteScreenHelp(screenHelpModel);
  }

  public ScreenHelpModel selectScreenHelp(ScreenHelpModel screenHelpModel) {
    return screenHelpMapper.selectScreenHelp(screenHelpModel);
  }

}
