package cn.lhs.filesys.controller;

import cn.lhs.filesys.entity.FileMsg;
import cn.lhs.filesys.entity.MyFile;
import cn.lhs.filesys.entity.ResponseMsg;
import cn.lhs.filesys.service.FileManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manage")
public class FileManageController {

    @Autowired
    FileManageService fileManageService;

    @GetMapping("/listFile")
    public ResponseMsg listFiles(@RequestParam(required = true,value = "pageIndex")int pageIndex,
                                  @RequestParam(required = true,value = "pageSize")int pageSize){
        System.out.println("pageIndex="+pageIndex+",pageSize="+pageSize);
        List<MyFile> myFileList = fileManageService.getListMyFile(pageIndex,pageSize);
        List<FileMsg> fileMsgList = new ArrayList<>();
        for (int i = 0; i < myFileList.size(); i++) {
            fileMsgList.add(new FileMsg(myFileList.get(i)));
        }
        int count = fileManageService.getFileNum();
        System.out.println("count="+count);
        return new ResponseMsg(1,"成功",count,fileMsgList);
    }

    @GetMapping("/addDownloadTimes")
    public void addDownloadTimes(@RequestParam(required = true,value = "uploaderId")String uploaderId,
                                 @RequestParam(required = true,value = "fileId")String fileId){
        System.out.println("uploaderId="+uploaderId+",fileId="+fileId);
        fileManageService.addDownloadTimes(uploaderId,fileId);
    }


}
