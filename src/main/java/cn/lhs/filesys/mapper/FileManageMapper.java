package cn.lhs.filesys.mapper;

import cn.lhs.filesys.entity.MyFile;

import java.util.List;
import java.util.Map;

public interface FileManageMapper {
    int saveMyFile(MyFile file);
    int delMyFile(String fileId);
    int modifyMyFile(String fileId);
    List<MyFile> getListMyFile(Map<String,Integer> param);
    int getFileNum();//获取总文件数
    int addDownloadTimes(Map<String,String> param);//递增上传次数
}
