package cn.lhs.filesys.service.impl;


import cn.lhs.filesys.entity.MyFile;
import cn.lhs.filesys.mapper.FileManageMapper;
import cn.lhs.filesys.service.FileManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileManageServiceImpl implements FileManageService {

    @Autowired
    private FileManageMapper fileManageMapper;

    @Override
    public int saveMyFile(MyFile file) {
        return fileManageMapper.saveMyFile ( file );
    }

    @Override
    public int delMyFile(String uploaderId, String fileUrl) {
        Map<String,String> map = new HashMap<>();
        map.put("uploaderId",uploaderId);
        map.put("fileUrl",fileUrl);
        return fileManageMapper.delMyFile(map);
    }

    @Override
    public int modifyMyFile(String uploaderId, String fileUrl, String fileName) {
        Map<String,String> map = new HashMap<>();
        map.put("uploaderId",uploaderId);
        map.put("fileUrl",fileUrl);
        map.put("fileName",fileName);
        return fileManageMapper.modifyMyFile(map);
    }

    @Override
    public List<MyFile> getListMyFile(int pageIndex, int pageSize) {
        Map<String,Integer> map = new HashMap<>(  );
        map.put ( "offset",(pageIndex-1)*pageSize );
        map.put ( "rows",pageSize );
        return fileManageMapper.getListMyFile(map);
    }

    @Override
    public List<MyFile> getListMyFileByUserId(String uploaderId, int pageIndex, int pageSize) {
        Map<String,Object> map = new HashMap<>(  );
        map.put ( "offset",(pageIndex-1)*pageSize );
        map.put ( "rows",pageSize );
        map.put("uploaderId",uploaderId);
        return fileManageMapper.getListMyFileByUserId(map);
    }

    @Override
    public int getFileNum() {
        return fileManageMapper.getFileNum();
    }

    @Override
    public int getFileNumByUserId(String uploaderId) {
        return fileManageMapper.getFileNumByUserId(uploaderId);
    }

    @Override
    public int addDownloadTimes(String uploaderId, String fileId) {
        Map<String,String> map = new HashMap<>(  );
        map.put ( "uploaderId",uploaderId );
        map.put ( "fileId",fileId );
        return fileManageMapper.addDownloadTimes(map);
    }

}
