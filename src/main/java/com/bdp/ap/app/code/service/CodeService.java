package com.bdp.ap.app.code.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.code.mapper.CodeMapper;
import com.bdp.ap.app.code.model.CodeModel;
import com.bdp.ap.app.code.model.PstnModel;
import com.bdp.ap.common.Constant;
import com.bdp.ap.common.paging.Criteria;

/**
 * 코드 서비스 클래스
 */
@Service
public class CodeService {

    @Resource
    private CodeMapper codeMapper;

    /**
     * 그룹 ID 리스트를 조회한다.
     *
     * @param groupId
     * @return
     */
    public List<CodeModel> selectGroupIdList(String groupId, Criteria criteria) {
        criteria.setGroupId(groupId);       // 그룹코드 추가
        return codeMapper.selectGroupIdList(criteria);
    }
    
    public List<CodeModel> selectGroupLikeIdList(String groupId, Criteria criteria) {
        criteria.setGroupId(groupId);       // 그룹코드 추가
        return codeMapper.selectGroupLikeIdList(criteria);
    }

    public int selectGroupIdListCount(String groupId) {
        return codeMapper.selectGroupIdListCount(groupId);
    }


    /**
     * 그룹 ID 리스트를 조회한다.(페이징X)
     *
     * @param groupId
     * @return
     */
    public List<CodeModel> selectGroupIdAllList(String groupId) {
        return codeMapper.selectGroupIdAllList(groupId);
    }

    /**
     * 그룹ID와 코드ID가 동일한 코드모델을 조회한다.
     *
     * @param model
     * @return
     */
    public CodeModel select(CodeModel model) {
        return codeMapper.select(model);
    }

    /**
     * 그룹ID와 코드ID가 동일한 코드모델을 삭제한다.
     *
     * @param model
     * @return
     */
    @Transactional
    public String delete(CodeModel model) {

        if(model.getGroupId().equals("GROUP_ID")) {
            int codeCount = selectCodeCountForGroupId(model);

            if(codeCount > 0) {
                return Constant.DB.USE_CODE_ID;
            }
        }

        long count = codeMapper.delete(model);

        if(count > 0) {
            return Constant.DB.DELETE;
        } else {
            return Constant.DB.FAIL;
        }
    }

    /**
     * 코드모델을 저장한다. 그룹ID와 코드ID가 동일한 코드모델이 존재하면 업데이트 아니면 신규등록한다.
     *
     * @param model
     * @return
     */
    @Transactional
    public String save(CodeModel model) {
        CodeModel existModel = select(model);

        if(existModel != null) {
            long count = codeMapper.update(model);

            if(count > 0) {
                return Constant.DB.UPDATE;
            } else {
                return Constant.DB.FAIL;
            }
        } else {
            long count = codeMapper.insert(model);

            if(count > 0) {
                return Constant.DB.INSERT;
            } else {
                return Constant.DB.FAIL;
            }
        }
    }

    public int selectCodeCountForGroupId(CodeModel model) {
        return codeMapper.selectCodeCountForGroupId(model);
    }

     /**
     * 직위 조회한다.
     *
     * @param model
     * @return
     */
    public List<CodeModel> pstnSelect(String companyCode) {
        return codeMapper.pstnSelect( companyCode);
    }
    
    /**
     * 부서 조회
     * @param companyCode
     * @return
     */
    public List<CodeModel> deptSelect(String companyCode) {
        return codeMapper.deptSelect( companyCode);
    }
    
    /**
     * 직책 조회
     * @param companyCode
     * @return
     */
    public List<CodeModel> rankSelect(String companyCode) {
        return codeMapper.rankSelect( companyCode);
    }
    
    /**
     * 직급조회
     * @param companyCode
     * @return
     */
    public List<CodeModel> dutySelect(String companyCode) {
        return codeMapper.dutySelect( companyCode);
    }
    
    /**
     * 그룹 ID로 하위 트리 목록 조회
     */
    public List<CodeModel> selectGroupTreeList(String groupdId) {
    	return codeMapper.selectGroupTreeList(groupdId);
    }
    
    public String selectCodeNm(String codeId) {
    	return codeMapper.selectCodeNm(codeId);
	}
}
