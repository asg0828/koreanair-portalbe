package com.cdp.portal.app.facade.file.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cdp.portal.app.facade.file.model.FileLinkModel;

@Mapper
public interface FileLinkMapper {

    /**
     * 파일 링크 등록
     * @param FileLinkModel
     * @return
     */
    Long insertFileLink(FileLinkModel model);

    /**
     * 파일 링크 목록 조회
     * @param
     * @return
     */
    List<String> selectFileLinkListByRefId(String refId);
    
    /**
     * 파일 링크 삭제
     * @param model
     * @return
     */
    long deleteFileLinkByRefId(String refId);
}
