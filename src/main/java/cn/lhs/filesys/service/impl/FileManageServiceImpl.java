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
    public List<MyFile> getMyOwnFile(String uploaderId,String fileName,String fileSort,String isShared, int pageIndex, int pageSize) {
        Map<String,Object> map = new HashMap<>(  );
        map.put ( "offset",(pageIndex-1)*pageSize );
        map.put ( "rows",pageSize );
        map.put("uploaderId",uploaderId);
        if (fileName != null){
            map.put("fileName",fileName);
        }
        if (fileSort != null){
            map.put("fileSort",fileSort);
        }
        if (isShared != null){
            map.put("isShared",isShared);
        }
        return fileManageMapper.getMyOwnFile(map);
    }

    @Override
    public int getMyOwnFileNum(String uploaderId,String fileName,String fileSort,String isShared) {
        Map<String,Object> map = new HashMap<>(  );
        map.put("uploaderId",uploaderId);
        if (fileName != null){
            map.put("fileName",fileName);
        }
        if (fileSort != null){
            map.put("fileSort",fileSort);
        }
        if (isShared != null){
            map.put("isShared",isShared);
        }
        return fileManageMapper.getMyOwnFileNum(map);
    }

    @Override
    public List<MyFile> getSharedFile(String fileName, String fileSort, int pageIndex, int pageSize) {
        Map<String,Object> map = new HashMap<>(  );
        map.put ( "offset",(pageIndex-1)*pageSize );
        map.put ( "rows",pageSize );
        if(fileSort != null){
            map.put("fileSort",fileSort);
        }
        if (fileName != null){
            map.put("fileName",fileName);
        }
        return fileManageMapper.getSharedFile(map);
    }

    @Override
    public int getSharedFileNum(String fileName,String fileSort) {
        Map<String,Object> map = new HashMap<>(  );
        if(fileSort != null){
            map.put("fileSort",fileSort);
        }
        if (fileName != null){
            map.put("fileName",fileName);
        }
        return fileManageMapper.getSharedFileNum(map);
    }

    @Override
    public int getFileNum() {
        return fileManageMapper.getFileNum();
    }

    @Override
    public int addDownloadTimes(String uploaderId, String fileId) {
        Map<String,String> map = new HashMap<>(  );
        map.put ( "uploaderId",uploaderId );
        map.put ( "fileId",fileId );
        return fileManageMapper.addDownloadTimes(map);
    }

    @Override
    public int shareFile(String uploaderId, String fileUrl, String isShared) {
        Map<String,String> map = new HashMap<>(  );
        map.put ( "uploaderId",uploaderId );
        map.put ( "fileUrl",fileUrl );
        map.put("isShared",isShared);
        return fileManageMapper.shareFile(map);
    }

}
