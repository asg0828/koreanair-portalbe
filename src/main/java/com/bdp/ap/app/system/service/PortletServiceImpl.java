package com.bdp.ap.app.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdp.ap.app.system.mapper.PortletMapper;
import com.bdp.ap.app.system.model.PortletModel;
import com.bdp.ap.app.system.model.PortletReqModel;

@Service
public class PortletServiceImpl implements PortletService {

  @Autowired
  PortletMapper portletMapper;

  @Override
  public List<PortletModel> selectPortletList(PortletReqModel criteria) {
    
    return portletMapper.selectPortletList(criteria);
  }

  @Override
  public int selectPortletListCnt(PortletReqModel criteria) {
    
    return portletMapper.selectPortletListCnt(criteria);
  }

  @Override
  public PortletModel selectPortlet(String portletId) {
    
    return portletMapper.selectPortlet(portletId);
  }

  @Override
  public int insertPortlet(PortletModel portletModel) {

    return portletMapper.insertPortlet(portletModel);
  }

  @Override
  public int updatePortlet(PortletModel portletModel) {

    return portletMapper.updatePortlet(portletModel);
  }

  

}
