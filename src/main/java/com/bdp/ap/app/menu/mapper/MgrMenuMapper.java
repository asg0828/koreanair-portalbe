package com.bdp.ap.app.menu.mapper;

import java.util.List;
import java.util.Map;

import com.bdp.ap.app.menu.model.MgrMenuModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

/**
 * Mybatis 코드관리 매핑 Interface
 */
@ConnMapperFirst
public interface MgrMenuMapper {

    /**
     * 메뉴모델 리스트를 조회한다.
     *
     * @return
     */
    List<MgrMenuModel> selectList(String searchKeyword);

    /**
     * 메뉴모델을 조회한다.
     *
     * @param model
     * @return
     */
    MgrMenuModel select(MgrMenuModel model);

    /**
     * 메뉴모델을 삭제한다.
     * @param model
     * @return
     */
    long delete(MgrMenuModel model);

    /**
     * 메뉴모델을 업데이트 한다.
     *
     * @param model
     * @return
     */
    long update(MgrMenuModel model);

    /**
     * 메뉴모델을 생성한다.
     *
     * @param model
     * @return
     */
    long insert(MgrMenuModel model);

    
    /**
     * 최상위 메뉴 조회
     */
    List<MgrMenuModel> selectTopMenuListWithAuth(String authId);

    /**
     * 권한과 상위 메뉴 ID에 따른 메뉴 리스트를 조회
     */
    List<MgrMenuModel> selectLeftMenuListWithAuth(Map<String,String> param);

    /**
     * 권한으로 접근 가능한 첫번째 메뉴 URL 조회
     */
    String selectFirstMenuUrl(Map<String,String> param);

    /**
     * 권한별 메뉴 리스트
     */
    List<MgrMenuModel> selectMenuListWithAuth(String authId);

    /**
     * 권한별 메뉴를 업데이트 한다.
     *
     * @param model
     * @return
     */
    long updateMenuListWithAuth(MgrMenuModel model);
    
    long insertHst();
    
    long deleteAll();
}
