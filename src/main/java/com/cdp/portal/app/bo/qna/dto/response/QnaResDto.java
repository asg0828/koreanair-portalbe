package com.cdp.portal.app.bo.qna.dto.response;

import java.util.List;

import com.cdp.portal.app.bo.qna.model.QnaModel;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "QNA 관리 응답")
public class QnaResDto extends QnaModel {
    public static class QnaResDtoResult extends ApiResDto<List<QnaResDto>> {}
}
