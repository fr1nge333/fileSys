package cn.lhs.filesys.service;

import cn.lhs.filesys.entity.MyFile;

import java.util.List;

public interface FileManageService {
    int saveMyFile(MyFile file);
    int delMyFile(String uploaderId,String fileUrl);
    int modifyMyFile(String uploaderId,String fileUrl,String fileName);
    List<MyFile> getListMyFile(int pageIndex,int pageSize);
    List<MyFile> getListMyFileByUserId(String uploaderId,int pageIndex,int pageSize);
    List<MyFile> searchMyFile(String uploaderId,String fileName,String fileSort,int pageIndex,int pageSize);
    int getFileNum();
    int getFileNumByUserId(String uploaderId);
    int addDownloadTimes(String uploaderId,String fileId);
    int shareFile(String uploaderId,String fileUrl,String isShared);
}
