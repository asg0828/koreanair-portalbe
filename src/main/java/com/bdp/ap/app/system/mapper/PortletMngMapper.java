package com.bdp.ap.app.system.mapper;

import com.bdp.ap.app.system.model.PortletMngModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface PortletMngMapper {

  PortletMngModel selectPortletMng();

  int insertPortletMng(PortletMngModel portletMngModel);

  int updatePortletMng(PortletMngModel portletMngModel);

}
