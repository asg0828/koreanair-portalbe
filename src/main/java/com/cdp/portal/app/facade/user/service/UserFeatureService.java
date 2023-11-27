package com.cdp.portal.app.facade.user.service;

import java.util.Arrays;import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdp.portal.app.facade.user.dto.request.UserFeatureReqDto;
import com.cdp.portal.app.facade.user.dto.response.UserFeatureResDto;
import com.cdp.portal.app.facade.user.mapper.UserFeatureMapper;
import com.cdp.portal.app.facade.user.model.UserFeatureModel;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFeatureService {
    
    private final UserFeatureMapper userFeatureMapper;
    
    @Transactional
    public void createUserFeature(String userId, UserFeatureReqDto.CreateUserFeature dto) {
        Boolean isExists = userFeatureMapper.isExistsByUserIdAndFeatureId(userId, dto.getFeatureId());
        if (isExists) {
            throw CdpPortalError.USER_FEATURE_DUPLICATED.exception();
        }
        
        UserFeatureModel userFeatureModel = UserFeatureModel.builder()
                .userId(userId)
                .featureId(dto.getFeatureId())
                .rgstId("admin")    // TODO: 로그인한 사용자 세팅
                .build();
        
        userFeatureMapper.insert(userFeatureModel);
    }
    
    public UserFeatureResDto.UserFeaturesResult getUserFeatures(String userId, PagingDto pagingDto) {
        pagingDto.setPaging(userFeatureMapper.selectCount(userId));
     
        return UserFeatureResDto.UserFeaturesResult.builder()
                .contents(userFeatureMapper.selectAll(userId, pagingDto))
                .page(pagingDto)
                .build();
    }
    
    @Transactional
    public void deleteUserFeature(String userId, String featureId) {
        Boolean isExists = userFeatureMapper.isExistsByUserIdAndFeatureId(userId, featureId);
        if (!isExists) {
            throw CdpPortalError.USER_FEATURE_NOT_EXISTS.exception();
        }
        
        userFeatureMapper.delete(userId, featureId);
    }
    
    @Transactional
    public void deleteUserFeatures(String userId, String[] featureIds) {
        Arrays.asList(featureIds).forEach(featureId -> {
            userFeatureMapper.delete(userId, featureId);
        });
    }
    
    public List<UserFeatureResDto.UserPopularFeatures> getUserPopularFeatures() {
        return userFeatureMapper.selectPopularFeature("admin"); // TODO: 로그인한 사용자 세팅
    }

}
