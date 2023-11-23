package com.cdp.portal.app.facade.dataroom.service;

import com.cdp.portal.app.facade.dataroom.dto.request.DataRoomReqDto;
import com.cdp.portal.app.facade.dataroom.dto.response.DataRoomResDto;
import com.cdp.portal.app.facade.dataroom.mapper.DataRoomMapper;
import com.cdp.portal.app.facade.dataroom.model.DataRoomModel;

import com.cdp.portal.app.facade.file.mapper.FileMapper;
import com.cdp.portal.app.facade.file.model.FileModel;
import com.cdp.portal.app.facade.file.service.FileService;
import com.cdp.portal.app.facade.notice.dto.response.NoticeResDto;
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
    private final FileService fileService;
    private final FileMapper fileMapper;

    /**
     * 자료실 등록
     *
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

        if (dto.getFileIds() != null) {
            for (String fileId : dto.getFileIds()) {
                FileModel file = new FileModel();
                file.setFileId(fileId);
                file.setRefId(dataId); // 파일의 refId를 공지사항의 ID로 설정
                file.setModiId("admin"); // TODO: 로그인한 사용자 세팅
                fileService.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
            }
        }

        dataRoomMapper.insertDataRoom(dataRoomModel);
    }

    /**
     * 자료실 전체 목록 조회
     *
     * @param
     * @return
     */
    @Transactional
    public DataRoomResDto.DataRoomsResult getDataRooms(PagingDto pagingDto, DataRoomReqDto.SearchDataRoom searchDto) {
        pagingDto.setPaging(dataRoomMapper.selectCount(searchDto));

        return DataRoomResDto.DataRoomsResult.builder()
                .page(pagingDto)
                .search(searchDto)
                .contents(dataRoomMapper.selectAll(pagingDto, searchDto))
                .build();
    }

    /**
     * 자료실 조회
     *
     * @param dataId
     * @return
     */
    @Transactional
    public DataRoomResDto getData(String dataId) {
        DataRoomResDto dataroom = dataRoomMapper.selectByDataId(dataId);
        if (dataroom != null) {
            // 공지사항에 첨부된 파일 목록 조회
            List<FileModel> fileList = fileMapper.selectFileListByRefId(dataId);
            dataroom.setFileList(fileList);
        }
        return dataRoomMapper.selectByDataId(dataId);
    }

    /**
     * 자료실 수정
     *
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

        if (dto.getFileIds() != null) {
            for (String fileId : dto.getFileIds()) {
                FileModel file = new FileModel();
                file.setFileId(fileId);
                file.setRefId(dataId); // 파일의 refId를 공지사항의 ID로 설정
                file.setModiId("admin"); // TODO: 로그인한 사용자 세팅
                fileService.updateFile(file); // 파일 서비스의 updateFile 메서드를 호출하여 파일의 refId를 업데이트
            }
        }

        dataRoomMapper.updateDataRoom(dataRoomModel);
    }

    /**
     * 자료실 삭제
     *
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
     *
     * @param dataId
     * @return
     */
    public void addViewCntData(String dataId) {
        dataRoomMapper.addViewCntData(dataId);
    }
}
