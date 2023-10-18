package com.bdp.ap.common.logging;

import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bdp.ap.app.audit.mapper.LogMapper;
import com.bdp.ap.app.audit.model.LogModel;
import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.common.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogUtil {

	@Resource(name="logMapper")
	private LogMapper mapper;

    public LogModel convertLogData(Map<String, Object> logData) {

        LogModel model = null;

        if(!logData.isEmpty()) {

            model = new LogModel();
            model.setSysSe("MGR");
            model.setClientIp((String) logData.get(Constant.LOG.CLIENT_IP));
            model.setServerIp((String) logData.get(Constant.LOG.SERVER_IP));
            model.setControllerNm((String) logData.get(Constant.LOG.CONTROLLER_NM));
            model.setMethodNm((String) logData.get(Constant.LOG.METHOD_NM));
            model.setRqstMethod((String) logData.get(Constant.LOG.HTTP_METHOD));
            model.setLogDt((LocalDateTime) logData.get(Constant.LOG.LOG_TIME));
            model.setRqstUri((String) logData.get(Constant.LOG.REQUEST_URI));

            model.setMsg(logData.get(Constant.LOG.PARAMS).toString());

            model.setProgramNm(mapper.selectProgramNm(model));

            // 사용자 정보 저장
            if(logData.get(Constant.LOG.USER_INFO) instanceof MemberModel) {
                MemberModel memberModel = (MemberModel) logData.get(Constant.LOG.USER_INFO);

                model.setUserId(memberModel.getUserId());
                model.setUserNm(memberModel.getUserNm());
                model.setPstnCode(memberModel.getPstnCode());
                model.setPstnNm(memberModel.getPstnNm());
                model.setDeptCode(memberModel.getDeptCode());
                model.setDeptNm(memberModel.getDeptNm());
                model.setAuthId(memberModel.getAuthId());
                model.setAuthNm(memberModel.getAuthNm());
            } else {
                model.setUserId((String) logData.get(Constant.LOG.USER_INFO));
                model.setUserNm("GUEST");
            }
        }

        return model;
    }

}
