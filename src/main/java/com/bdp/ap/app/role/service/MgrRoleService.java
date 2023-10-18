package com.bdp.ap.app.role.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.member.mapper.MemberMapper;
import com.bdp.ap.app.member.model.MemberCriteria;
import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.app.member.service.MemberService;
import com.bdp.ap.app.role.mapper.MgrRoleMapper;
import com.bdp.ap.app.role.model.RoleGroupModel;
import com.bdp.ap.app.role.model.MgrRoleModel;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.paging.Criteria;

import lombok.extern.slf4j.Slf4j;


/**
 * 권한 서비스 클래스
 */
@Slf4j
@Service
public class MgrRoleService {

    @Resource
    private MemberService memberService;
    
    @Resource
    private MemberMapper memberMapper;

    @Resource
    private MgrRoleMapper mgrRoleMapper;

    /**
     * 권한모델 목록을 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    public List<MgrRoleModel> selectList(Criteria criteria) {
        return mgrRoleMapper.selectList(criteria);
    }

    /**
     * 권한모델 목록 카운트를 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    public int selectListCount(Criteria criteria) {
        return mgrRoleMapper.selectListCount(criteria);
    }

    /**
     * 모든 권한 목록을 조회한다.
     *
     * @return
     */
    public List<MgrRoleModel> selectAllList() {
        return mgrRoleMapper.selectAllList();
    }

    /**
     * 권한ID에 따른 권한모델을 조회한다.
     *
     * @param model 권한ID
     * @return
     */
    public MgrRoleModel select(MgrRoleModel model) {
        return mgrRoleMapper.select(model);
    }

    /**
     * 권한ID에 따른 권한모델을 삭제한다.
     *
     * @param model 권한ID
     * @return
     */
    @Transactional
    public String delete(MgrRoleModel model) {

        int memberCount = memberService.selectMemberListCountForMgrRoleId(model.getAuthId());

        if(memberCount > 0) {
        	MemberCriteria criteria = new MemberCriteria();
            criteria.setMgrRoles(model.getAuthId());
            List<MemberModel> userIdList = memberService.selectMemberList(criteria);
            for(MemberModel memberModel : userIdList) {
            	memberModel.setAuthId(model.getBaseRole());
            	memberModel.setModiId(model.getModiId());
            	memberMapper.upsertMgrAuth(memberModel);
            }
        }

        long count = mgrRoleMapper.delete(model);

        if(count > 0) {
            return Constant.DB.DELETE;
        } else {
            return Constant.DB.FAIL;
        }
        

    }

    /**
     * 권한모델을 저장한다.
     * 권한ID에 따른 권한모델이 존재하면 업데이트 아니면 신규생성한다.
     *
     * @param model
     * @return
     */
    @Transactional
    public String save(MgrRoleModel model) {
        MgrRoleModel existModel = select(model);

        if(existModel != null) {
            try {
                long count = mgrRoleMapper.update(model);

                if (count > 0) {
                    return Constant.DB.UPDATE;
                } else {
                    return Constant.DB.FAIL;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                return Constant.DB.FAIL;
            }
        } else {
            try {
                long count = mgrRoleMapper.insert(model);

                if (count > 0) {
                    return Constant.DB.INSERT;
                } else {
                    return Constant.DB.FAIL;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                return Constant.DB.FAIL;
            }
        }
    }

    @Transactional
    public String insertRoleGroup(RoleGroupModel model) {

    	try {
    		long count = mgrRoleMapper.insertRoleGroup(model);

            if (count > 0) {
                return Constant.DB.INSERT;
            } else {
                return Constant.DB.FAIL;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Constant.DB.FAIL;
        }
    }

    @Transactional
    public String updateRoleGroup(RoleGroupModel model) {

    	try {
    		long count = mgrRoleMapper.updateRoleGroup(model);

            if (count > 0) {
                return Constant.DB.UPDATE;
            } else {
                return Constant.DB.FAIL;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Constant.DB.FAIL;
        }
    }

    @Transactional
    public String deleteRoleGroup(RoleGroupModel model) {
    	try {
    		long count = mgrRoleMapper.deleteRoleGroup(model);

            if (count > 0) {
                return Constant.DB.DELETE;
            } else {
                return Constant.DB.FAIL;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Constant.DB.FAIL;
        }
    }

    public List<RoleGroupModel> selectRoleGroupList(Criteria criteria) {
        return mgrRoleMapper.selectRoleGroupList(criteria);
    }

    public int selectRoleGroupListCount(Criteria criteria) {
        return mgrRoleMapper.selectRoleGroupListCount(criteria);
    }

    public RoleGroupModel selectRoleGroup(RoleGroupModel model) {
        return mgrRoleMapper.selectRoleGroup(model);
    }

    @Transactional
    public String insertRoleGroupDtl(RoleGroupModel model) {

    	try {
    		long count = mgrRoleMapper.insertRoleGroupDtl(model);

            if (count > 0) {
                return Constant.DB.INSERT;
            } else {
                return Constant.DB.FAIL;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Constant.DB.FAIL;
        }
    }

    @Transactional
    public String updateRoleGroupDtl(RoleGroupModel model) {

    	try {
    		long count = mgrRoleMapper.updateRoleGroupDtl(model);

            if (count > 0) {
                return Constant.DB.UPDATE;
            } else {
                return Constant.DB.FAIL;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Constant.DB.FAIL;
        }
    }

    @Transactional
    public String deleteRoleGroupDtl(RoleGroupModel model) {
    	try {
    		long count = mgrRoleMapper.deleteRoleGroupDtl(model);

            if (count > 0) {
                return Constant.DB.DELETE;
            } else {
                return Constant.DB.FAIL;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Constant.DB.FAIL;
        }
    }

    public List<RoleGroupModel> selectRoleGroupDtlList(Criteria criteria) {
        return mgrRoleMapper.selectRoleGroupDtlList(criteria);
    }

    public int selectRoleGroupDtlListCount(Criteria criteria) {
        return mgrRoleMapper.selectRoleGroupDtlListCount(criteria);
    }

    public RoleGroupModel selectRoleGroupDtl(RoleGroupModel model) {
        return mgrRoleMapper.selectRoleGroupDtl(model);
    }
}
