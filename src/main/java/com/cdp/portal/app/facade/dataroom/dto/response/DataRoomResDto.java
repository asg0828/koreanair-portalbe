package com.cdp.portal.app.facade.dataroom.dto.response;

import java.util.List;

import com.cdp.portal.app.facade.dataroom.model.DataRoomModel;
import com.cdp.portal.common.dto.ApiResDto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "자료실 관리 응답")
public class DataRoomResDto extends DataRoomModel {
    public static class DataRoomResDtoResult extends ApiResDto<List<DataRoomResDto>> {}
}
