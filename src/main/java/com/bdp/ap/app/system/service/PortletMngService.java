package com.bdp.ap.app.system.service;

import com.bdp.ap.app.system.model.PortletMngModel;

public interface PortletMngService {

  PortletMngModel selectPortletMng();

  int insertPortletMng(PortletMngModel portletModel);

  int updatePortletMng(PortletMngModel portletModel);
}
