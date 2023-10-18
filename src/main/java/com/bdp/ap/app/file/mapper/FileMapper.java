package com.bdp.ap.app.file.mapper;

import java.util.List;

import com.bdp.ap.app.file.model.FileModel;
import com.bdp.ap.common.annotation.ConnMapperFirst;

@ConnMapperFirst
public interface FileMapper {

	FileModel selectPhotoFile(String fileUrl);

	FileModel selectPreviewFile(String fileUrl);
	FileModel selectViewFile(String fileUrl);

	FileModel selectEditorImageFile(String fileUrl);

	List<FileModel> selectFileListByFileId(String fileId);

	List<FileModel> selectFileList(FileModel model);
	FileModel selectFile(FileModel model);
	long insertFile(FileModel model);
    long updateFile(FileModel model);
    long deleteFile(FileModel model);
    long deleteFileParent(FileModel model);
    long insertRefVerFile(FileModel model);
}
