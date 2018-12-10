package cn.lhs.filesys.service;

import cn.lhs.filesys.entity.MyFile;

import java.util.List;

public interface FileManageService {
    int saveMyFile(MyFile file);
    int delMyFile(String fileId);
    int modifyMyFile(String fileId);
    List<MyFile> getListMyFile(int pageIndex,int pageSize);
    int getFileNum();
    int addDownloadTimes(String uploaderId,String fileId);
}
