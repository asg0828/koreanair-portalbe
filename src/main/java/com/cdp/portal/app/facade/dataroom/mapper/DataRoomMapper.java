package com.cdp.portal.app.facade.dataroom.mapper;

import com.cdp.portal.app.facade.dataroom.dto.request.DataRoomReqDto;
import com.cdp.portal.app.facade.dataroom.dto.response.DataRoomResDto;
import com.cdp.portal.app.facade.dataroom.model.DataRoomModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataRoomMapper {

    /**
     * 자료실 전체 목록
     * @param
     * @return
     */
    List<DataRoomResDto> selectDataRoomList();

    /**
     * 자료실 상세
     * @param dataRoomModel
     * @return
     */
    void selectData(DataRoomModel dataRoomModel);

    /**
     * 자료실 조회
     * @param dataId
     * @return
     */
    DataRoomResDto selectByDataId(@Param("dataId") String dataId);

    /**
     * 자료실 등록
     * @param dataRoomModel
     * @return
     */
    Long insertDataRoom(DataRoomModel dataRoomModel);


    /**
     * 자료 수정
     * @param dataRoomModel
     * @return
     */
    Long updateDataRoom(DataRoomModel dataRoomModel);

    /**
     * 자료 삭제
     * @param dto
     * @return
     */
    void deleteDataRoom(DataRoomReqDto.DeleteDataRoomReq dto);
    void deleteDataRoom2(DataRoomReqDto.DeleteDataRoomReq dto);

    /**
     * 조회수 증가
     * @param dataId
     * @return
     */
    void addViewCntData(String dataId);
}
