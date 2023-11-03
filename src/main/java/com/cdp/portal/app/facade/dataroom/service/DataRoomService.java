package com.cdp.portal.app.facade.dataroom.service;

import com.cdp.portal.app.facade.dataroom.dto.request.DataRoomReqDto;
import com.cdp.portal.app.facade.dataroom.dto.response.DataRoomResDto;
import com.cdp.portal.app.facade.dataroom.mapper.DataRoomMapper;
import com.cdp.portal.app.facade.dataroom.model.DataRoomModel;

import com.cdp.portal.app.facade.notice.dto.request.NoticeReqDto;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
import com.cdp.portal.app.facade.notice.model.NoticeModel;
import com.cdp.portal.common.enumeration.CdpPortalError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DataRoomService {
    private final DataRoomMapper dataRoomMapper;

    /**
     * 자료실 전체 목록 조회
     * @param
     * @return
     */

    public List<DataRoomResDto> getDataRoomAllList() {
        return dataRoomMapper.selectDataRoomList();
    }

    /**
     * 자료실 상세 조회
     * @param DataRoomModel
     * @return
     */
    public void selectData(DataRoomModel dataRoomModel){
        dataRoomMapper.selectData(dataRoomModel);
    }

    /**
     * 자료실 상세조회 v2
     * @param dataId
     * @return
     */
    public DataRoomResDto getData(String dataId) {
        return dataRoomMapper.selectByDataId(dataId);
    }

    /**
     * 자료실 등록
     * @param dto
     */
    public void createData(DataRoomReqDto.CreateDataRoomReq dto) {
        DataRoomModel dataRoomModel = DataRoomModel.builder()
                .dataId(dto.getDataId())
                .sj(dto.getSj())
                .cn(dto.getCn())
                .useYn(dto.getUseYn())
                .rgstId("admin")
                .modiId("admin")
                .build();

        dataRoomMapper.insertDataRoom(dataRoomModel);
    }

    /**
     * 자료실 수정
     * @param dataId
     * @param dto
     */
    public void updateDataRoom(final String dataId, DataRoomReqDto.UpdateDataRoomReq dto) {
        DataRoomResDto dataRoomResDto = this.getData(dataId);
        if (Objects.isNull(dataRoomResDto)) {
            throw CdpPortalError.CODE_NOT_FOUND.exception(dataId);
        }

        DataRoomModel dataRoomModel = DataRoomModel.builder()
                .dataId(dataId)
                .sj(dto.getSj())
                .cn(dto.getCn())
                .useYn(dto.getUseYn())
                .modiId("admin")    // TODO: 로그인한 사용자 세팅
                .build();

        dataRoomMapper.updateDataRoom(dataRoomModel);
    }

    public void deleteDataRoom(DataRoomReqDto.DeleteDataRoomReq dto) {
        dataRoomMapper.deleteDataRoom(dto);
    }
    public void deleteDataRoom2(DataRoomReqDto.DeleteDataRoomReq dto) {
        dataRoomMapper.deleteDataRoom2(dto);
    }
    
    /**
     * 조회수 증가
     * @param dataId
     * @return
     */
    public void addViewCntData(String dataId) {
        dataRoomMapper.addViewCntData(dataId);
    }
}
