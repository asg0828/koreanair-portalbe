package com.bdp.ap.app.system.mapper;

import java.util.List;

import com.bdp.ap.app.system.model.PortletModel;
import com.bdp.ap.app.system.model.PortletReqModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface PortletMapper {

  List<PortletModel> selectPortletList(PortletReqModel criteria);

  int selectPortletListCnt(PortletReqModel criteria);

  PortletModel selectPortlet(String portletId);

  int insertPortlet(PortletModel portletModel);

  int updatePortlet(PortletModel portletModel);

}
