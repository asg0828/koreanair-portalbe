package com.bdp.ap.app.member.mapper;

import java.util.List;

import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

/**
 * Mybatis 사용자관리 매핑 Interface
 */
@ConnMapperFirst
public interface MemberMapper {

    /**
     * 사용자관리 목록을 조회한다.
     *
     * @param criteria 페이징 모델
     * @return
     */
    List<MemberModel> selectMemberList(Criteria criteria);

    /**
     * 사용자관리 목록 카운트를 조회한다.
     *
     * @param criteria
     * @return
     */
    int selectMemberListCount(Criteria criteria);

    /**
     * 사용자 목록을 조회한다.(전체)
     *
     * @param criteria 페이징 모델
     * @return
     */
    List<MemberModel> selectMemberAllList(Criteria criteria);

    /**
     * 사용자모델을 조회한다.
     *
     * @param memberModel 사번을 사용
     * @return
     */
    MemberModel selectMember(MemberModel memberModel);

    /**
     * 권한을 사용중인 사용자 카운트를 조회한다
     *
     * @param roleId 권한ID
     * @return
     */
    int selectMemberListCountForRoleId(String roleId);

    /**
     * 사용자 권한을 등록/수정 한다.
     * @param model 사번을 사용
     * @return
     */
    long upsertAuth(MemberModel model);

    /**
     * 관리자 권한을 등록/수정 한다.
     * @param model 사번을 사용
     * @return
     */
    long upsertMgrAuth(MemberModel model);

    /**
     * 사용자 모델을 업데이트 한다.
     * @param model 사번을 사용
     * @return
     */
    long update(MemberModel model);
    long insert(MemberModel model);

    /**
     * 사용자 모델 이력정보 insert
     * @param model 사번을 사용
     * @return
     */
    long insertUserHst(MemberModel model);

    /**
     * 관리자 권한을 사용중인 사용자 카운트를 조회한다
     *
     * @param roleId 권한ID
     * @return
     */
    int selectMemberListCountForMgrRoleId(String roleId);

    long unlockAccount(MemberModel memberModel);
}
