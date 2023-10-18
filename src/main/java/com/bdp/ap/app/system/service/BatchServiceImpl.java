package com.bdp.ap.app.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdp.ap.app.system.mapper.BatchMapper;
import com.bdp.ap.app.system.model.BatchModel;
import com.bdp.ap.app.system.model.BatchReqModel;

@Service
public class BatchServiceImpl implements BatchService {

  @Autowired
  BatchMapper batchMapper;

  @Override
  public List<BatchModel> selectBatchList(BatchReqModel criteria) {

    return batchMapper.selectBatchList(criteria);
  }

  @Override
  public int selectBatchListCount(BatchReqModel criteria) {

    return batchMapper.selectBatchListCount(criteria);
  }

  @Override
  public int insertBatch(BatchModel batchModel) {

    return batchMapper.insertBatch(batchModel);

  }

  @Override
  public int updateBatch(BatchModel batchModel) {

    return batchMapper.updateBatch(batchModel);

  }

  @Override
  public BatchModel selectBatch(String batchModel) {

    return batchMapper.selectBatch(batchModel);
  }
  
  @Override
  public int deleteBatch(BatchModel batchModel) {

    return batchMapper.deleteBatch(batchModel);
  }
  
  @Override
  public int selectSameBatchNmCount(BatchReqModel criteria) {

    return batchMapper.selectSameBatchNmCount(criteria);
  }

}
