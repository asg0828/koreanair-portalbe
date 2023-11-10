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
    List<FileResDto> selectAll();
    
    /**
     * 파일 조회
     * @param fileId
     * @return
     */
    FileResDto selectByFileId(@Param("fileId") String fileId);
}
