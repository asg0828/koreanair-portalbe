package com.bdp.ap.app.system.service;

import com.bdp.ap.app.system.model.BatchModel;
import com.bdp.ap.app.system.model.BatchReqModel;
import com.bdp.ap.common.paging.Criteria;

public interface BatchService {

  Object selectBatchList(BatchReqModel criteria);

  int selectBatchListCount(BatchReqModel criteria);

  int insertBatch(BatchModel batchModel);

  int updateBatch(BatchModel batchModel);

  BatchModel selectBatch(String batchModel);
  
  int deleteBatch(BatchModel batchModel);
  
  int selectSameBatchNmCount(BatchReqModel criteria);

}
