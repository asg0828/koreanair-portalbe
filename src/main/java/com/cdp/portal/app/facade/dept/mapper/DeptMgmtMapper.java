package com.cdp.portal.app.facade.dept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.dept.dto.request.DeptMgmtReqDto;
import com.cdp.portal.app.facade.dept.dto.response.DeptMgmtResDto;
import com.cdp.portal.app.facade.dept.model.DeptAuthModel;
import com.cdp.portal.app.facade.dept.model.DeptModel;

@Mapper
public interface DeptMgmtMapper {
	
	Long insert(DeptModel deptModel);
	
	Long update(DeptModel deptModel);
	
	List<DeptMgmtResDto.Dept> selectAll(@Param("search") DeptMgmtReqDto.SearchDept searchDto);
	
	int selectCount(@Param("search") DeptMgmtReqDto.SearchDept searchDto);
	
	Long delete(@Param("deptCode") String deptCode);
	
	Boolean isExistsByDeptCode(@Param("deptCode") String deptCode);
	
//	Long insert(@Param("depts") List<DeptModel> depts);
}
