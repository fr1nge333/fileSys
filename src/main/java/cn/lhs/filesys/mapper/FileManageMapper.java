package cn.lhs.filesys.mapper;

import cn.lhs.filesys.entity.MyFile;

import java.util.List;
import java.util.Map;

public interface FileManageMapper {
    int saveMyFile(MyFile file);
    int delMyFile(Map<String,String> param);
    int modifyMyFile(Map<String,String> param);
    List<MyFile> getListMyFile(Map<String,Integer> param);
    List<MyFile> getListMyFileByUserId(Map<String,Object> param);
    int getFileNum();//获取总文件数
    int getFileNumByUserId(String uploaderId);
    int addDownloadTimes(Map<String,String> param);//递增上传次数
}
