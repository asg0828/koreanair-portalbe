package com.cdp.portal.app.bo.faq.dto.response;

import java.util.List;

import com.cdp.portal.app.bo.faq.model.FaqModel;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "FAQ 관리 응답")
public class FaqResDto extends FaqModel{

    public static class FaqResDtoResult extends ApiResDto<List<FaqResDto>> {}
}
