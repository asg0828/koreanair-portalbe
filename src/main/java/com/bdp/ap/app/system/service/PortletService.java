package com.bdp.ap.app.system.service;

import java.util.List;

import com.bdp.ap.app.system.model.PortletModel;
import com.bdp.ap.app.system.model.PortletReqModel;

public interface PortletService {

  List<PortletModel> selectPortletList(PortletReqModel criteria);

  int selectPortletListCnt(PortletReqModel criteria);

  PortletModel selectPortlet(String portletId);

  int insertPortlet(PortletModel portletModel);

  int updatePortlet(PortletModel portletModel);
}
