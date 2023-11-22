package com.cdp.portal.app.facade.file.mapper;

import com.cdp.portal.app.facade.file.dto.response.FileResDto;
import com.cdp.portal.app.facade.file.model.FileModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {

    /**
     * 파일 등록
     * @param fileModel
     * @return
     */
    Long insertFile(FileModel fileModel);

    /**
     * 파일 목록 조회
     * @param
     * @return
     */
    List<FileResDto> selectFileList();
    
    /**
     * 파일 조회
     * @param fileId
     * @return
     */
    FileModel selectByFileId(@Param("fileId") String fileId);

    /**
     * 파일 수정
     * @param model
     * @return
     */
    long updateFile(FileModel model);

    /**
     * 파일 삭제
     * @param model
     * @return
     */
    long deleteFile(FileModel model);
}
