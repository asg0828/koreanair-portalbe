package com.bdp.ap.app.vgroup.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.member.service.MemberService;
import com.bdp.ap.app.role.model.MgrRoleModel;
import com.bdp.ap.app.role.model.RoleModel;
import com.bdp.ap.app.vgroup.mapper.VgroupMapper;
import com.bdp.ap.app.vgroup.model.VgroupModel;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.IdUtil;
import com.bdp.ap.common.paging.Criteria;

import lombok.extern.slf4j.Slf4j;


/**
 * 가상 그룹 서비스 클래스
 */
@Slf4j
@Service
public class VgroupService {

    @Resource
    private MemberService memberService;

    @Resource
    private VgroupMapper vgroupMapper;

    @Resource
	IdUtil idUtil;
    
    @Transactional
    public String vgroupSave(VgroupModel model) {
    	VgroupModel existModel = selectVgroup(model);

        if(existModel != null) {
            try {
                long count = vgroupMapper.updateVgroup(model);

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
            	model.setGroupId(idUtil.getVgroupId());
                long count = vgroupMapper.insertVgroup(model);

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
    public String insertVgroup(VgroupModel model) {

    	try {
    		long count = vgroupMapper.insertVgroup(model);

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
    public String updateVgroup(VgroupModel model) {

    	try {
    		long count = vgroupMapper.updateVgroup(model);

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
    public String deleteVgroup(VgroupModel model) {
    	try {
    		long count = vgroupMapper.deleteVgroup(model);

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

    public List<VgroupModel> selectVgroupList(VgroupModel model) {
        return vgroupMapper.selectVgroupList(model);
    }

    public int selectVgroupListCount(VgroupModel model) {
        return vgroupMapper.selectVgroupListCount(model);
    }

    public VgroupModel selectVgroup(VgroupModel model) {
        return vgroupMapper.selectVgroup(model);
    }

    @Transactional
    public String insertVgroupDtl(VgroupModel model) {

    	try {
    		long count = vgroupMapper.insertVgroupDtl(model);

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
    public String updateVgroupDtl(VgroupModel model) {

    	try {
    		long count = vgroupMapper.updateVgroupDtl(model);

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
    public String deleteVgroupDtl(VgroupModel model) {
    	try {
    		long count = vgroupMapper.deleteVgroupDtl(model);

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

    public List<VgroupModel> selectVgroupDtlList(VgroupModel model) {
        return vgroupMapper.selectVgroupDtlList(model);
    }

    public int selectVgroupDtlListCount(VgroupModel model) {
        return vgroupMapper.selectVgroupDtlListCount(model);
    }

    public VgroupModel selectVgroupDtl(VgroupModel model) {
        return vgroupMapper.selectVgroupDtl(model);
    }
}
