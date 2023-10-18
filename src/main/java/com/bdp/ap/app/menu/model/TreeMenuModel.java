package com.bdp.ap.app.menu.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 메뉴모델
 *
 */
@Data
public class TreeMenuModel {

	MenuModel menuModel;
	MgrMenuModel mgrMenuModel;
	List<TreeMenuModel> subMenuModel = new ArrayList<>();
}
