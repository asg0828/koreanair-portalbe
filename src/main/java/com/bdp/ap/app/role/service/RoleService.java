package com.bdp.ap.app.role.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.member.model.MemberCriteria;
import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.app.member.mapper.MemberMapper;
import com.bdp.ap.app.member.service.MemberService;
import com.bdp.ap.app.role.mapper.RoleMapper;
import com.bdp.ap.app.role.model.RoleGroupModel;
import com.bdp.ap.app.role.model.RoleModel;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.paging.Criteria;

import lombok.extern.slf4j.Slf4j;


/**
 * 권한 서비스 클래스
 */
@Slf4j
@Service
public class RoleService {

    @Resource
    private MemberService memberService;
    
    @Resource
    private MemberMapper memberMapper;

    @Resource
    private RoleMapper roleMapper;

    /**
     * 권한모델 목록을 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    public List<RoleModel> selectList(Criteria criteria) {
        return roleMapper.selectList(criteria);
    }

    /**
     * 권한모델 목록 카운트를 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    public int selectListCount(Criteria criteria) {
        return roleMapper.selectListCount(criteria);
    }

    /**
     * 모든 권한 목록을 조회한다.
     *
     * @return
     */
    public List<RoleModel> selectAllList() {
        return roleMapper.selectAllList();
    }

    /**
     * 권한ID에 따른 권한모델을 조회한다.
     *
     * @param model 권한ID
     * @return
     */
    public RoleModel select(RoleModel model) {
        return roleMapper.select(model);
    }

    /**
     * 권한ID에 따른 권한모델을 삭제한다.
     *
     * @param model 권한ID
     * @return
     */   
    @Transactional
    public String delete(RoleModel model) {

        int memberCount = memberService.selectMemberListCountForRoleId(model.getAuthId());
        
        if(memberCount > 0) {
        	MemberCriteria criteria = new MemberCriteria();
            criteria.setRoles(model.getAuthId());
            List<MemberModel> userIdList = memberService.selectMemberList(criteria);
            for(MemberModel memberModel : userIdList) {
            	memberModel.setAuthId(model.getBaseRole());
            	memberModel.setModiId(model.getModiId());
            	memberMapper.upsertAuth(memberModel);
            }
        }
        
        long count = roleMapper.delete(model);

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
    public String save(RoleModel model) {
        RoleModel existModel = select(model);

        if(existModel != null) {
            try {
                long count = roleMapper.update(model);

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
                long count = roleMapper.insert(model);

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

    /**
     * 관리자 권한모델 목록을 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    public List<RoleModel> selectMgrSysAuthList(Criteria criteria) {
        return roleMapper.selectMgrSysAuthList(criteria);
    }

    /**
     * 관리자 권한모델 목록 카운트를 조회한다.
     *
     * @param criteria  페이징 모델
     * @return
     */
    public int selectMgrSysAuthListCount(Criteria criteria) {
        return roleMapper.selectMgrSysAuthListCount(criteria);
    }

    /**
     * 관리자 모든 권한 목록을 조회한다.
     *
     * @return
     */
    public List<RoleModel> selectMgrSysAuthAllList(RoleModel model) {
        return roleMapper.selectMgrSysAuthAllList(model);
    }

    /**
     * 관리자 권한ID에 따른 권한모델을 조회한다.
     *
     * @param model 권한ID
     * @return
     */
    public RoleModel selectMgrSysAuth(RoleModel model) {
        return roleMapper.selectMgrSysAuth(model);
    }

    /**
     * 관리자 권한ID에 따른 권한모델을 삭제한다.
     *
     * @param model 권한ID
     * @return
     */
    @Transactional
    public String deleteMgrSysAuth(RoleModel model) {

        int memberCount = memberService.selectMemberListCountForMgrRoleId(model.getAuthId());

        if(memberCount > 0) {
            return Constant.DB.USE_ROLE_ID;
        } else {
            long count = roleMapper.deleteMgrSysAuth(model);

            if(count > 0) {
                return Constant.DB.DELETE;
            } else {
                return Constant.DB.FAIL;
            }
        }

    }

    /**
     * 관리자 권한모델을 저장한다.
     * 권한ID에 따른 권한모델이 존재하면 업데이트 아니면 신규생성한다.
     *
     * @param model
     * @return
     */
    @Transactional
    public String mgrSave(RoleModel model) {
        RoleModel existModel = selectMgrSysAuth(model);

        if(existModel != null) {
            try {
                long count = roleMapper.updateMgrSysAuth(model);

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
                long count = roleMapper.insertMgrSysAuth(model);

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
    		long count = roleMapper.insertRoleGroup(model);

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
    		long count = roleMapper.updateRoleGroup(model);

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
    		long count = roleMapper.deleteRoleGroup(model);

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
        return roleMapper.selectRoleGroupList(criteria);
    }

    public int selectRoleGroupListCount(Criteria criteria) {
        return roleMapper.selectRoleGroupListCount(criteria);
    }

    public RoleGroupModel selectRoleGroup(RoleGroupModel model) {
        return roleMapper.selectRoleGroup(model);
    }

    @Transactional
    public String insertRoleGroupDtl(RoleGroupModel model) {

    	try {
    		long count = roleMapper.insertRoleGroupDtl(model);

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
    		long count = roleMapper.updateRoleGroupDtl(model);

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
    		long count = roleMapper.deleteRoleGroupDtl(model);

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
        return roleMapper.selectRoleGroupDtlList(criteria);
    }

    public int selectRoleGroupDtlListCount(Criteria criteria) {
        return roleMapper.selectRoleGroupDtlListCount(criteria);
    }

    public RoleGroupModel selectRoleGroupDtl(RoleGroupModel model) {
        return roleMapper.selectRoleGroupDtl(model);
    }
}
