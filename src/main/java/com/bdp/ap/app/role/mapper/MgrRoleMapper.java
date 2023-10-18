package com.bdp.ap.app.role.mapper;

import java.util.List;

import com.bdp.ap.app.role.model.MgrRoleModel;
import com.bdp.ap.app.role.model.RoleGroupModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

/**
 * Mybatis 권한관리 매핑 Interface
 */
@ConnMapperFirst
public interface MgrRoleMapper {

    /**
     * 권한관리 페이징 조건에 따라서 목록을 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    List<MgrRoleModel> selectList(Criteria criteria);

    /**
     * 권한관리 목록 카운트를 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    int selectListCount(Criteria criteria);

    /**
     * 모든 권한 목록을 조회한다. (셀렉트 박스등에서 사용을 위한 조회리스트)
     *
     * @return
     */
    List<MgrRoleModel> selectAllList();

    /**
     * 권한ID에 맞는 권한모델을 조회한다.
     *
     * @param model 권한ID 사용
     * @return
     */
    MgrRoleModel select(MgrRoleModel model);

    /**
     * 권한ID에 맞는 데이터를 삭제한다.
     *
     * @param model 권한ID 사용
     * @return
     */
    long delete(MgrRoleModel model);

    /**
     * 권한ID에 맞는 데이터를 업데이트 한다.
     *
     * @param model 권한ID 사용
     * @return
     */
    long update(MgrRoleModel model) throws Exception;

    /**
     * 권한모델을 신규생성한다.
     *
     * @param model 권한모델
     * @return
     */
    long insert(MgrRoleModel model) throws Exception;


    long insertRoleGroup(RoleGroupModel model) throws Exception;
    long updateRoleGroup(RoleGroupModel model) throws Exception;
    long deleteRoleGroup(RoleGroupModel model) throws Exception;

    List<RoleGroupModel> selectRoleGroupList(Criteria criteria);
    int selectRoleGroupListCount(Criteria criteria);
    RoleGroupModel selectRoleGroup(RoleGroupModel model);

    long insertRoleGroupDtl(RoleGroupModel model) throws Exception;
    long updateRoleGroupDtl(RoleGroupModel model) throws Exception;
    long deleteRoleGroupDtl(RoleGroupModel model) throws Exception;

    List<RoleGroupModel> selectRoleGroupDtlList(Criteria criteria);
    int selectRoleGroupDtlListCount(Criteria criteria);
    RoleGroupModel selectRoleGroupDtl(RoleGroupModel model);


}
