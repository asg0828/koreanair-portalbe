package com.bdp.ap.app.system.service;

import java.util.List;

import com.bdp.ap.app.system.model.PortletItemModel;
import com.bdp.ap.config.security.AuthUser;

public interface PortletItemService {
	List<PortletItemModel> selectItemList(String codeId, AuthUser authUser);
}
