package com.bdp.ap.schedule.mapper;

import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.app.member.model.MemberModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface JobUserPhotoMapper {

	MemberModel selectUser(String userId) throws Exception;
	int insertFile(FileModel model) throws Exception;
	int updateUserFileInfo(FileModel model) throws Exception;
	FileModel selectFile(String fileId) throws Exception;
	int updateFile(FileModel model) throws Exception;

}
