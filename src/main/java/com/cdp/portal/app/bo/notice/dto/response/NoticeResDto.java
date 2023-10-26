package com.cdp.portal.app.bo.notice.dto.response;

import java.util.List;

import com.cdp.portal.app.bo.notice.model.NoticeModel;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "공지사항 관리 응답")
public class NoticeResDto extends NoticeModel {

    public static class NoticeResDtoResult extends ApiResDto<List<NoticeResDto>> {}
    
}
