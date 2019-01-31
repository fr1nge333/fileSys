package cn.lhs.filesys.service;

import cn.lhs.filesys.entity.MyFile;

import java.util.List;

public interface FileManageService {
    int saveMyFile(MyFile file);
    int delMyFile(String uploaderId,String fileUrl);
    int modifyMyFile(String uploaderId,String fileUrl,String fileName);

    List<MyFile> getMyOwnFile(String uploaderId,String fileName,String fileSort,String isShared,int pageIndex,int pageSize);
    int getMyOwnFileNum(String uploaderId,String fileName,String fileSort,String isShared);

    List<MyFile> getSharedFile(String fileName,String fileSort,int pageIndex,int pageSize);
    int getSharedFileNum(String fileName,String fileSort);

    int getFileNum();
    int addDownloadTimes(String uploaderId,String fileId);
    int shareFile(String uploaderId,String fileUrl,String isShared);
}
