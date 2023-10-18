package com.bdp.ap.app.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdp.ap.app.system.mapper.PortletMngMapper;
import com.bdp.ap.app.system.model.PortletMngModel;

@Service
public class PortletMngServiceImpl implements PortletMngService {

  @Autowired
  PortletMngMapper portletMngMapper;

  @Override
  public PortletMngModel selectPortletMng() {
    
    return portletMngMapper.selectPortletMng();
  }

  @Override
  public int insertPortletMng(PortletMngModel portletMngModel) {

    return portletMngMapper.insertPortletMng(portletMngModel);
  }

  @Override
  public int updatePortletMng(PortletMngModel portletMngModel) {

    return portletMngMapper.updatePortletMng(portletMngModel);
  }

  

}
