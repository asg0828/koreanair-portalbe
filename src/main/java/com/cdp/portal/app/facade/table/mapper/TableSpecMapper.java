package com.cdp.portal.app.facade.table.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cdp.portal.app.facade.table.dto.request.TableSpecReqDto;
import com.cdp.portal.app.facade.table.dto.response.TableSpecResDto;
import com.cdp.portal.app.facade.table.model.TableSpecModel;
import com.cdp.portal.common.dto.PagingDto;

@Mapper
public interface TableSpecMapper {
    
    Long insert(TableSpecModel tableSpecModel);
    
    Boolean isExistsByMtsEnNm(String mtsEnNm);
    
    List<TableSpecResDto.TableSpecs> selectAll(@Param("paging") PagingDto pagingDto, @Param("search") TableSpecReqDto.SearchTableSpec searchDto);
    
    int selectCount(@Param("search") TableSpecReqDto.SearchTableSpec searchDto);
    
    TableSpecResDto.TableSpec selectById(String mtsId);
    
    Long update(TableSpecModel tableSpecModel);
    
    Long updateDelYnById(@Param("modiId") String modiId, @Param("mtsId") String mtsId);

}
