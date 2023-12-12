package com.cdp.portal.app.facade.user.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.user.dto.request.UserFeatureReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserFeatureResDto;
import com.cdp.portal.app.facade.user.mapper.UserFeatureMapper;
import com.cdp.portal.app.facade.user.model.UserFeatureModel;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import com.cdp.portal.common.util.SessionScopeUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFeatureService {
    
    private final UserFeatureMapper userFeatureMapper;
    
    @Transactional
    public void createUserFeature(String userId, UserFeatureReqDto.CreateUserFeature dto) {
        this.isValidUser(userId);
        
        Boolean isExists = userFeatureMapper.isExistsByUserIdAndFeatureId(userId, dto.getFeatureId());
        if (isExists) {
            throw CdpPortalError.USER_FEATURE_DUPLICATED.exception();
        }
        
        UserFeatureModel userFeatureModel = UserFeatureModel.builder()
                .userId(userId)
                .featureId(dto.getFeatureId())
                .rgstId(SessionScopeUtil.getContextSession().getUserId())
                .build();
        
        userFeatureMapper.insert(userFeatureModel);
    }
    
    @Transactional(readOnly = true)
    public UserFeatureResDto.UserFeaturesResult getUserFeatures(String userId, PagingDto pagingDto) {
        this.isValidUser(userId);
        
        pagingDto.setPaging(userFeatureMapper.selectCount(userId));
     
        return UserFeatureResDto.UserFeaturesResult.builder()
                .contents(userFeatureMapper.selects(userId, pagingDto))
                .page(pagingDto)
                .build();
    }
    
    @Transactional
    public void deleteUserFeature(String userId, String featureId) {
        this.isValidUser(userId);
        
        Boolean isExists = userFeatureMapper.isExistsByUserIdAndFeatureId(userId, featureId);
        if (!isExists) {
            throw CdpPortalError.USER_FEATURE_NOT_EXISTS.exception();
        }
        
        userFeatureMapper.delete(userId, featureId);
    }
    
    @Transactional
    public void deleteUserFeatures(String userId, String[] featureIds) {
        this.isValidUser(userId);
        
        Arrays.asList(featureIds).forEach(featureId -> {
            userFeatureMapper.delete(userId, featureId);
        });
    }
    
    @Transactional(readOnly = true)
    public List<UserFeatureResDto.UserPopularFeatures> getUserPopularFeatures() {
        return userFeatureMapper.selectPopularFeatures(SessionScopeUtil.getContextSession().getUserId());
    }
    
    private void isValidUser(String userId) {
        if (!StringUtils.equals(userId, SessionScopeUtil.getContextSession().getUserId())) {
            throw CdpPortalError.USER_INVALID_ID.exception();
        }
    }

}
