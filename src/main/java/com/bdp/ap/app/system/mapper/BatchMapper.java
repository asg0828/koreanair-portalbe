package com.bdp.ap.app.system.mapper;

import java.util.List;

import com.bdp.ap.app.system.model.BatchModel;
import com.bdp.ap.app.system.model.BatchReqModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface BatchMapper {

  List<BatchModel> selectBatchList(BatchReqModel criteria);

  int selectBatchListCount(BatchReqModel criteria);

  BatchModel selectBatch(String batchId);

  int insertBatch(BatchModel batchModel);

  int updateBatch(BatchModel batchModel);

  int deleteBatch(BatchModel batchModel);
  
  int selectSameBatchNmCount(BatchReqModel criteria);

}
