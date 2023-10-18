package com.bdp.ap.app.vgroup.mapper;

import java.util.List;

import com.bdp.ap.app.vgroup.model.VgroupModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

/**
 * Mybatis 가상그룹관리 매핑 Interface
 */
@ConnMapperFirst
public interface VgroupMapper {

    long insertVgroup(VgroupModel model) throws Exception;
    long updateVgroup(VgroupModel model) throws Exception;
    long deleteVgroup(VgroupModel model) throws Exception;

    List<VgroupModel> selectVgroupList(VgroupModel model);
    int selectVgroupListCount(VgroupModel model);
    VgroupModel selectVgroup(VgroupModel model);

    long insertVgroupDtl(VgroupModel model) throws Exception;
    long updateVgroupDtl(VgroupModel model) throws Exception;
    long deleteVgroupDtl(VgroupModel model) throws Exception;

    List<VgroupModel> selectVgroupDtlList(VgroupModel model);
    int selectVgroupDtlListCount(VgroupModel model);
    VgroupModel selectVgroupDtl(VgroupModel model);

}
