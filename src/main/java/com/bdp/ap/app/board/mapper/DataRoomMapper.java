package com.bdp.ap.app.board.mapper;

import java.util.List;

import com.bdp.ap.app.board.model.DataRoomModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;
import com.bdp.ap.common.paging.Criteria;

@ConnMapperFirst
public interface DataRoomMapper {

	List<DataRoomModel> selectDataRoomList(Criteria criteria);
	int selectDataRoomListCount(Criteria criteria);
	DataRoomModel selectDataRoom(DataRoomModel model);
    long insertDataRoom(DataRoomModel model);
    long updateDataRoom(DataRoomModel model);
	long deleteDataRoom(DataRoomModel model);
	long addViewCntDataRoom(DataRoomModel model);

}
