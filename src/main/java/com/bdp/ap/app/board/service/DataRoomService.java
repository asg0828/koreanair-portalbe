package com.bdp.ap.app.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bdp.ap.app.board.mapper.DataRoomMapper;
import com.bdp.ap.app.board.model.DataRoomModel;
import com.bdp.ap.app.code.service.CodeService;
import com.bdp.ap.common.paging.Criteria;

@Service
public class DataRoomService {

	@Resource
	private CodeService codeService;

	@Resource
	private DataRoomMapper DataRoomMapper;

	public List<DataRoomModel>selectDataRoomList(Criteria criteria) {
		return DataRoomMapper.selectDataRoomList(criteria);
	}
	public int selectDataRoomListCount(Criteria criteria) {
		return DataRoomMapper.selectDataRoomListCount(criteria);
	}
	public DataRoomModel selectDataRoom(DataRoomModel model) {
		return DataRoomMapper.selectDataRoom(model);
	}
	public long insertDataRoom(DataRoomModel model) {
		return DataRoomMapper.insertDataRoom(model);
	}
	public long updateDataRoom(DataRoomModel model) {
		return DataRoomMapper.updateDataRoom(model);
	}
	public long deleteDataRoom(DataRoomModel model) {
		return DataRoomMapper.deleteDataRoom(model);
	}

	public long addViewCntDataRoom(DataRoomModel model) {
		return DataRoomMapper.addViewCntDataRoom(model);
	}

}
