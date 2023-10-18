package com.bdp.ap.app.dept.mapper;

import java.util.List;

import com.bdp.ap.app.dept.model.DeptClModel;
import com.bdp.ap.app.menu.model.MgrMenuModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface DeptMapper {

	//부서 분류 조회
	DeptClModel selectDeptCl(String deptCode);
	List<DeptClModel> selectDeptClList();
	List<DeptClModel> selectDeptClSearchList(DeptClModel model);
	List<DeptClModel>selectDeptList(DeptClModel model);
	long insert(DeptClModel model);
	long update(DeptClModel model);
	long delete(DeptClModel model);
	long insertHst(DeptClModel model);
	List<DeptClModel>selectList(DeptClModel model);
	long insertHist(String companyCode);
	long deleteAll(String companyCode);
}
