package cn.lhs.filesys.mapper;

import cn.lhs.filesys.entity.MyFile;

import java.util.List;
import java.util.Map;

public interface FileManageMapper {
    int saveMyFile(MyFile file);
    int delMyFile(Map<String,String> param);
    int modifyMyFile(Map<String,String> param);

    List<MyFile> getMyOwnFile(Map<String,Object> param);
    int getMyOwnFileNum(Map<String,Object> param);

    List<MyFile> getSharedFile(Map<String,Object> param);
    int getSharedFileNum(Map<String,Object> param);

    int getFileNum();//获取总文件数

    int addDownloadTimes(Map<String,String> param);//递增下载次数
    int shareFile(Map<String,String> param);//分享自己上传的文件
}
