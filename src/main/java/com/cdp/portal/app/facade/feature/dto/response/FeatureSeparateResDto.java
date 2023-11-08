package com.cdp.portal.app.facade.feature.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.feature.model.FeatureSeparateModel;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "피쳐 구분 응답")
public class FeatureSeparateResDto extends FeatureSeparateModel {
    
    public static class ApiResFeatureSeparateResDtos extends ApiResDto<List<FeatureSeparateResDto>> {}

}
