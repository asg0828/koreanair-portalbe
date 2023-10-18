package com.bdp.ap.app.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdp.ap.app.board.mapper.BbsMapper;
import com.bdp.ap.app.board.model.BbsModel;
import com.bdp.ap.common.paging.Criteria;

@Service
public class BbsService {
	@Resource
	private BbsMapper bbsMapper;
	
	public List<BbsModel>selectBbsList(Criteria criteria){
		return bbsMapper.selectBbsList(criteria);
	}
	
	public int selectBbsListCount(Criteria criteria) {
		return bbsMapper.selectBbsListCount(criteria);
	}
	
	public BbsModel selectBbsDetail(BbsModel model) {
		return bbsMapper.selectBbsDetail(model);
	}
	
	@Transactional
	public long insertBbs(BbsModel model) {
		ArrayList<String> arrBoardItemId=new ArrayList<String>();
		
		if(model.getItemIds() !=null) {  //boardItemId:itemUseYn:itemMarkYn:itemEssYn|
			BbsModel itemModel = null;
			//for(String itemId : model.getItemIds()) {
				//String[] sArr=itemId.split("|");
				for(String sVal: model.getItemIds()) {
					itemModel = new BbsModel();
					String[]sArr1 = sVal.split(":");
					itemModel.setBoardId(model.getBoardId());
					itemModel.setBoardItemId(sArr1[0]);
					itemModel.setItemUseYn(sArr1[1]);
					itemModel.setItemMarkYn(sArr1[2]);
					itemModel.setItemEssYn(sArr1[3]);
					itemModel.setRgstId(model.getRgstId());
					itemModel.setModiId(model.getModiId());
					itemModel.setDelYn("N");
					
					arrBoardItemId.add(sArr1[0]);					
					bbsMapper.upsertBbsItemMapping(itemModel);
					}
				//}
		}
		
		if(model.getKindIds() !=null) { //board_kind_id:kind_nm:kind_ord
			BbsModel kindModel = null;
			for(String kindId : model.getKindIds()) {
				String[] sArr=kindId.split("|");
				for(String sVal: sArr) {
					kindModel = new BbsModel();
					String[]sArr1 = sVal.split(":");
					
					kindModel.setBoardId(model.getBoardId());
					kindModel.setBoardKindId(sArr1[0]);
					kindModel.setKindNm(sArr1[1]);
					kindModel.setKindOrd(Integer.parseInt(sArr1[2]));
					kindModel.setRgstId(model.getRgstId());
					kindModel.setModiId(model.getModiId());
					kindModel.setDelYn("N");
					bbsMapper.upsertBbsKind(kindModel);
				}
			}
		}
		
		if(model.getFindAuthIds() !=null) {
			BbsModel findAuthModel = null;
			for(String findAuthId : model.getFindAuthIds()) {
				findAuthModel = new BbsModel();
				findAuthModel.setBoardAuthId(findAuthId);
				findAuthModel.setBoardId(model.getBoardId());
				findAuthModel.setAuthDivCd("01");  //조회권한
				findAuthModel.setRgstId(model.getRgstId());
				findAuthModel.setModiId(model.getModiId());
				findAuthModel.setDelYn("N");
				bbsMapper.upsertBbsAuth(findAuthModel);
			}
		}
		
		if(model.getWkAuthIds() !=null) {
			BbsModel wkAuthModel = null;
			for(String wkAuthId : model.getWkAuthIds()) {
				wkAuthModel = new BbsModel();
				wkAuthModel.setBoardAuthId(wkAuthId);
				wkAuthModel.setBoardId(model.getBoardId());
				wkAuthModel.setAuthDivCd("02");  //작성권한
				wkAuthModel.setRgstId(model.getRgstId());
				wkAuthModel.setModiId(model.getModiId());
				wkAuthModel.setDelYn("N");
				bbsMapper.upsertBbsAuth(wkAuthModel);
			}
		}
		
		if(model.getMngAuthIds() !=null) {
			BbsModel mngAuthModel = null;
			for(String mngAuthId : model.getMngAuthIds()) {
				mngAuthModel = new BbsModel();
				mngAuthModel.setBoardAuthId(mngAuthId);
				mngAuthModel.setBoardId(model.getBoardId());
				mngAuthModel.setAuthDivCd("03");  //관리권한
				mngAuthModel.setRgstId(model.getRgstId());
				mngAuthModel.setModiId(model.getModiId());
				mngAuthModel.setDelYn("N");
				bbsMapper.upsertBbsAuth(mngAuthModel);
			}
		}
		
		bbsMapper.insertBbs(model);
		
		Map<String,Object> itemList = new HashMap<>();
		itemList.put("itemList", arrBoardItemId);
		List<String>itemFeildList =null;
		if (arrBoardItemId !=null && arrBoardItemId.size()>0) {
			itemFeildList=bbsMapper.selectItemFieldList(itemList);
		}
		
		Map<String,Object> fieldList = new HashMap<>();
		fieldList.put("fieldList", itemFeildList);
		fieldList.put("tblNm", model.getBoardId());
		long lRet=0;
		if("01".equals(model.getTypCd())) {
			lRet=bbsMapper.createNomalTable(fieldList);
		}else {
			lRet=bbsMapper.createReplyTable(fieldList);
			bbsMapper.createCommentTable(fieldList);
		}
		
		return lRet;
	}
	
	@Transactional
	public long updateBbs(BbsModel model) {
		ArrayList<String> arrBoardItemId=new ArrayList<String>();
		if(model.getItemIds() !=null) {  //boardItemId:itemUseYn:itemMarkYn:itemEssYn|
			BbsModel itemModel = null;
			//for(String itemId : model.getItemIds()) {
				//String[] sArr=itemId.split("|");
				for(String sVal: model.getItemIds()) {
					itemModel = new BbsModel();
					String[]sArr1 = sVal.split(":");
					itemModel.setBoardId(model.getBoardId());
					itemModel.setBoardItemId(sArr1[0]);
					itemModel.setItemUseYn(sArr1[1]);
					itemModel.setItemMarkYn(sArr1[2]);
					itemModel.setItemEssYn(sArr1[3]);
					itemModel.setRgstId(model.getRgstId());
					itemModel.setModiId(model.getModiId());
					itemModel.setDelYn("N");
					
					arrBoardItemId.add(sArr1[0]);			
					
					bbsMapper.upsertBbsItemMapping(itemModel);
					}
				//}
		}
		
		if(model.getKindIds() !=null) { //board_kind_id:kind_nm:kind_ord
			BbsModel kindModel = null;
			for(String kindId : model.getKindIds()) {
				String[] sArr=kindId.split("|");
				for(String sVal: sArr) {
					kindModel = new BbsModel();
					String[]sArr1 = sVal.split(":");
					
					kindModel.setBoardId(model.getBoardId());
					kindModel.setBoardKindId(sArr1[0]);
					kindModel.setKindNm(sArr1[1]);
					kindModel.setKindOrd(Integer.parseInt(sArr1[2]));
					kindModel.setRgstId(model.getRgstId());
					kindModel.setModiId(model.getModiId());
					kindModel.setDelYn("N");
					bbsMapper.upsertBbsKind(kindModel);
				}
			}
		}
		
		bbsMapper.deleteBbsAuth(model);
		
		if(model.getFindAuthIds() !=null) {
			BbsModel findAuthModel = null;
			for(String findAuthId : model.getFindAuthIds()) {
				findAuthModel = new BbsModel();
				findAuthModel.setBoardAuthId(findAuthId);
				findAuthModel.setBoardId(model.getBoardId());
				findAuthModel.setAuthDivCd("01");  //조회권한
				findAuthModel.setRgstId(model.getRgstId());
				findAuthModel.setModiId(model.getModiId());
				findAuthModel.setDelYn("N");
				bbsMapper.upsertBbsAuth(findAuthModel);
			}
		}
		
		if(model.getWkAuthIds() !=null) {
			BbsModel wkAuthModel = null;
			for(String wkAuthId : model.getWkAuthIds()) {
				wkAuthModel = new BbsModel();
				wkAuthModel.setBoardAuthId(wkAuthId);
				wkAuthModel.setBoardId(model.getBoardId());
				wkAuthModel.setAuthDivCd("02");  //작성권한
				wkAuthModel.setRgstId(model.getRgstId());
				wkAuthModel.setModiId(model.getModiId());
				wkAuthModel.setDelYn("N");
				bbsMapper.upsertBbsAuth(wkAuthModel);
			}
		}
		
		if(model.getMngAuthIds() !=null) {
			BbsModel mngAuthModel = null;
			for(String mngAuthId : model.getMngAuthIds()) {
				mngAuthModel = new BbsModel();
				mngAuthModel.setBoardAuthId(mngAuthId);
				mngAuthModel.setBoardId(model.getBoardId());
				mngAuthModel.setAuthDivCd("03");  //관리권한
				mngAuthModel.setRgstId(model.getRgstId());
				mngAuthModel.setModiId(model.getModiId());
				mngAuthModel.setDelYn("N");
				bbsMapper.upsertBbsAuth(mngAuthModel);
			}
		}
		
		bbsMapper.updateBbs(model);
		
		Map<String,Object> itemList = new HashMap<>();
		itemList.put("itemList", arrBoardItemId);
		List<String>itemFeildList =null;
		if (arrBoardItemId !=null && arrBoardItemId.size()>0) {
			itemFeildList=bbsMapper.selectItemFieldList(itemList);
		} 
		Map<String,Object> fieldList = new HashMap<>();
		fieldList.put("fieldList", itemFeildList);
		fieldList.put("tblNm", model.getBoardId());
		fieldList.put("comment", model.getTypCd());
		
		bbsMapper.dropTable(fieldList);
		long lRet=0;
		if("01".equals(model.getTypCd())) {
			lRet=bbsMapper.createNomalTable(fieldList);
		}else {
			lRet=bbsMapper.createReplyTable(fieldList);
			bbsMapper.createCommentTable(fieldList);
		}
		
		return lRet;
		
	}
	
	@Transactional
	public long deleteBbs(BbsModel model) {
		
		Map<String,Object> fieldList = new HashMap<>(); 
		fieldList.put("tblNm", model.getBoardId());
		
		bbsMapper.deleteBbsItemMapping(model);
		bbsMapper.deleteBbsKind(model);
		bbsMapper.deleteBbsAuth(model);
		bbsMapper.deleteBbs(model);
		
		return bbsMapper.dropTable(fieldList);
	}
	
	public List<BbsModel>selectBbsItemList(BbsModel model){
		return bbsMapper.selectBbsItemList(model);
	}
	
	public List<BbsModel>selectBbsItemMappList(BbsModel model){
		return bbsMapper.selectBbsItemMappList(model);
	}
	
	public long upsertBbsItemMapping(BbsModel model) {
		return bbsMapper.upsertBbsItemMapping(model);
	}
	
	public long deleteBbsItemMapping(BbsModel model) {
		return bbsMapper.deleteBbsItemMapping(model);
	}
	
	public List<BbsModel>selectBbsKindList(BbsModel model){
		return bbsMapper.selectBbsKindList(model);
	}
	
	public long upsertBbsKind(BbsModel model) {
		return bbsMapper.upsertBbsKind(model);
	}
	
	public long deleteBbsKind(BbsModel model) {
		return bbsMapper.deleteBbsKind(model);
	}
	
	public List<BbsModel>selectBbsAuthList(BbsModel model){
		return bbsMapper.selectBbsAuthList(model);
	}
	
	public List<BbsModel>selectBbsMngAuthList(BbsModel model){
		return bbsMapper.selectBbsMngAuthList(model);
	}
	
	public long upsertBbsAuth(BbsModel model) {
		return bbsMapper.upsertBbsAuth(model);
	}
	
	public long deleteBbsAuth(BbsModel model) {
		return bbsMapper.deleteBbsAuth(null);
	}
	
	public long createTable() {
		ArrayList<String> arrBoardItemId=new ArrayList<String>();
		
		arrBoardItemId.add("bi00000001");
		arrBoardItemId.add("bi00000002");
		arrBoardItemId.add("bi00000003");
		arrBoardItemId.add("bi00000004");
		arrBoardItemId.add("bi00000005");
		arrBoardItemId.add("bi00000006");
		arrBoardItemId.add("bi00000007");
		 
		Map<String,Object> itemList = new HashMap<>();
		itemList.put("itemList", arrBoardItemId);
		
		List<String>itemFeildList=bbsMapper.selectItemFieldList(itemList);
		Map<String,Object> fieldList = new HashMap<>();
		fieldList.put("fieldList", itemFeildList);
		fieldList.put("tblNm", "si0000001");
		bbsMapper.createNomalTable(fieldList); 
		return 0;
	}
	
}
