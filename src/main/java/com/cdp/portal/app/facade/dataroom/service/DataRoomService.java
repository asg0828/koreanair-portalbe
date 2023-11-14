package com.cdp.portal.app.facade.dataroom.service;

import com.cdp.portal.app.facade.dataroom.dto.request.DataRoomReqDto;
import com.cdp.portal.app.facade.dataroom.dto.response.DataRoomResDto;
import com.cdp.portal.app.facade.dataroom.mapper.DataRoomMapper;
import com.cdp.portal.app.facade.dataroom.model.DataRoomModel;

import com.cdp.portal.common.IdUtil;
import com.cdp.portal.common.dto.PagingDto;
import com.cdp.portal.common.enumeration.CdpPortalError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataRoomService {
    private final DataRoomMapper dataRoomMapper;
    private final IdUtil idUtil;

    /**
     * 자료실 등록
     * @param dto
     */
    @Transactional
    public void createData(DataRoomReqDto.CreateDataRoomReq dto) {

        final String dataId = idUtil.getDataId();

        DataRoomModel dataRoomModel = DataRoomModel.builder()
                .dataId(dataId)
                .sj(dto.getSj())
                .cn(dto.getCn())
                .useYn(dto.getUseYn())
                .rgstId(dto.getRgstId())
                .modiId(dto.getModiId())
                .build();

        dataRoomMapper.insertDataRoom(dataRoomModel);
    }

    /**
     * 자료실 전체 목록 조회
     * @param
     * @return
     */
    @Transactional
    public DataRoomResDto.DataRoomsResult getDataRooms(PagingDto pagingDto, DataRoomReqDto.SearchDataRoom searchDto) {
        pagingDto.setPaging(dataRoomMapper.selectCount(searchDto));

        return DataRoomResDto.DataRoomsResult.builder()
                .page(pagingDto)
                .search(searchDto)
                .contents(dataRoomMapper.selectAll(pagingDto,searchDto))
                .build();
    }

    /**
     * 자료실 조회
     * @param dataId
     * @return
     */
    @Transactional
    public DataRoomResDto getData(String dataId) {
        return dataRoomMapper.selectByDataId(dataId);
    }

    /**
     * 자료실 수정
     * @param dataId
     * @param dto
     */
    @Transactional
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
                .modiId(dto.getModiId())    // TODO: 로그인한 사용자 세팅
                .build();

        dataRoomMapper.updateDataRoom(dataRoomModel);
    }

    /**
     * 자료실 삭제
     * @param dataId
     */
    @Transactional
    public void deleteDataRoom(String dataId) {
        dataRoomMapper.deleteDataRoom(dataId);
    }
    @Transactional
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
