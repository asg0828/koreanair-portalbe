package com.cdp.portal.app.facade.bizmeta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.bizmeta.dto.response.TableColumnSpecResDto;
import com.cdp.portal.app.facade.bizmeta.model.TableColumnSpecModel;

@Mapper
public interface TableColumnSpecMapper {
    
    Long insert(TableColumnSpecModel tableColumnSpecModel);
    
    List<TableColumnSpecResDto> selectByMtsId(String mtsId);
    
    Long deleteByMtsId(String mtsId);
    
    Long updateYnByMtsId(@Param("modiId") String modiId, @Param("mtsId") String mtsId);

}
