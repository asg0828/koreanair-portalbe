package com.bdp.ap.app.role.mapper;

import java.util.List;

import com.bdp.ap.app.role.model.RoleGroupModel;
import com.bdp.ap.app.role.model.RoleModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

/**
 * Mybatis 권한관리 매핑 Interface
 */
@ConnMapperFirst
public interface RoleMapper {

    /**
     * 권한관리 페이징 조건에 따라서 목록을 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    List<RoleModel> selectList(Criteria criteria);

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
    List<RoleModel> selectAllList();

    /**
     * 권한ID에 맞는 권한모델을 조회한다.
     *
     * @param model 권한ID 사용
     * @return
     */
    RoleModel select(RoleModel model);

    /**
     * 권한ID에 맞는 데이터를 삭제한다.
     *
     * @param model 권한ID 사용
     * @return
     */
    long delete(RoleModel model);

    /**
     * 권한ID에 맞는 데이터를 업데이트 한다.
     *
     * @param model 권한ID 사용
     * @return
     */
    long update(RoleModel model) throws Exception;

    /**
     * 권한모델을 신규생성한다.
     *
     * @param model 권한모델
     * @return
     */
    long insert(RoleModel model) throws Exception;


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

    /**
     * 관리자 권한관리 페이징 조건에 따라서 목록을 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    List<RoleModel> selectMgrSysAuthList(Criteria criteria);

    /**
     * 관리자 권한관리 목록 카운트를 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    int selectMgrSysAuthListCount(Criteria criteria);

    /**
     * 관리자 모든 권한 목록을 조회한다. (셀렉트 박스등에서 사용을 위한 조회리스트)
     *
     * @return
     */
    List<RoleModel> selectMgrSysAuthAllList(RoleModel model);

    /**
     * 관리자 권한ID에 맞는 권한모델을 조회한다.
     *
     * @param model 권한ID 사용
     * @return
     */
    RoleModel selectMgrSysAuth(RoleModel model);

    /**
     * 관리자 권한ID에 맞는 데이터를 삭제한다.
     *
     * @param model 권한ID 사용
     * @return
     */
    long deleteMgrSysAuth(RoleModel model);

    /**
     * 관리자 권한ID에 맞는 데이터를 업데이트 한다.
     *
     * @param model 권한ID 사용
     * @return
     */
    long updateMgrSysAuth(RoleModel model) throws Exception;

    /**
     * 관리자 권한모델을 신규생성한다.
     *
     * @param model 권한모델
     * @return
     */
    long insertMgrSysAuth(RoleModel model) throws Exception;

}
