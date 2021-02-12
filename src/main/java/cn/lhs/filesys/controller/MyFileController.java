package cn.lhs.filesys.controller;


import cn.lhs.filesys.entity.MyFile;
import cn.lhs.filesys.entity.ResponseMsg;
import cn.lhs.filesys.service.MyFileService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class MyFileController extends BaseController{
    @Autowired
    private MyFileService myFileService;

    @Value("${image.url}")
    private String imageUrl;

    @Value("${video.url}")
    private String videoUrl;

    @PostMapping("/upload/image")
    public ResponseMsg uploadImages(HttpServletRequest request,
                                    @RequestParam(value = "uploaderId")Integer uploaderId,
                                    @RequestParam(value = "fileType")String fileType) {
        makeDir(imageUrl);
        List<MultipartFile> picFiles = ((MultipartHttpServletRequest) request).getFiles("file");
        if(picFiles.size() == 0){
            return new ResponseMsg(0,"没有任何文件");
        }
        for (int i = 0; i < picFiles.size (); i++) {
            if(!picFiles.get(i).isEmpty()) {
                MyFile myFile = getFileInfo(picFiles.get(i),uploaderId,imageUrl,fileType);
                log.info(myFile.toString());
                try {
                    picFiles.get(i).transferTo(new File(myFile.getFileUrl()));
                    if (!myFileService.save(myFile)) {
                        return new ResponseMsg(0,"上传失败");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ResponseMsg(0,"上传失败");
                }
            }
        }

        return new ResponseMsg(1,"上传成功");
    }


    @PostMapping("/upload/video")
    public ResponseMsg uploadVideo(@RequestParam("file")MultipartFile videoFile,
                                   @RequestParam(value = "uploaderId")Integer uploaderId,
                                   @RequestParam(value = "fileType")String fileType) {
        makeDir(videoUrl);
        MyFile myFile = getFileInfo(videoFile,uploaderId,videoUrl,fileType);
        log.info (myFile.toString ());

        try {
            long start = System.currentTimeMillis ();
            videoFile.transferTo ( new File ( myFile.getFileUrl() ) );
            log.info ( "用时="+(System.currentTimeMillis ()-start)+"ms" );

            if (myFileService.save (myFile)){
                return new ResponseMsg(1,"上传成功");
            }else {
                return new ResponseMsg(0,"上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace ();
            return new ResponseMsg(0,"上传失败");
        }
    }

    @GetMapping("/manage/listMyOwnFile")
    public ResponseMsg listMyOwnFile(@RequestParam(value = "uploaderId")String uploaderId,
                                     @RequestParam(required = false,value = "fileName")String fileName,
                                     @RequestParam(required = false,value = "fileType")String fileType,
                                     @RequestParam(required = false,value = "isShared")String isShared,
                                     @RequestParam(value = "pageIndex")Integer pageIndex,
                                     @RequestParam(value = "pageSize")Integer pageSize){

        log.info("fileName="+fileName+",fileType="+fileType+",isShared="+isShared);
        IPage<MyFile> myFileIPage = getMyFileList(uploaderId, fileName, fileType, isShared, pageIndex, pageSize);
        for(MyFile myFile:myFileIPage.getRecords()){
            log.info(myFile.toString());
        }
        return new ResponseMsg(1,"成功",(int)myFileIPage.getTotal(),myFileIPage.getRecords());
    }

    @GetMapping("/manage/listSharedFile")
    public ResponseMsg searchFile(@RequestParam(required = false,value = "fileName")String fileName,
                                  @RequestParam(required = false,value = "fileType")String fileType,
                                  @RequestParam(value = "pageIndex")Integer pageIndex,
                                  @RequestParam(value = "pageSize")Integer pageSize){

        log.info("fileName="+fileName+",fileType="+fileType);
        IPage<MyFile> myFileIPage = getMyFileList(null, null, fileType, "1", pageIndex, pageSize);
        for(MyFile myFile:myFileIPage.getRecords()){
            log.info(myFile.toString());
        }
        return new ResponseMsg(1,"成功",(int)myFileIPage.getTotal(),myFileIPage.getRecords());
    }


    @PostMapping("/manage/modifyFileName")
    public ResponseMsg modifyFileName(@RequestParam(value = "fileId")Integer fileId,
                                      @RequestParam(value = "newFileName")String newFileName){
        log.info("fileId="+fileId+",newFileName"+newFileName);
        MyFile myFile = myFileService.getById(fileId);
        myFile.setFileName(newFileName);
        boolean flag = myFileService.updateById(myFile);
        if(flag){
            return new ResponseMsg(1,"修改成功");
        }else {
            return new ResponseMsg(0,"修改失败");
        }
    }

    @PostMapping("/manage/delFile")
    public ResponseMsg delFile(@RequestParam(value = "fileId")String fileId){
        log.info("fileId="+fileId);
        MyFile myFile = myFileService.getById(fileId);
        myFile.setIsDel("1");
        boolean flag = myFileService.updateById(myFile);
        if(flag){
            return new ResponseMsg(1,"删除成功");
        }else {
            return new ResponseMsg(0,"删除失败");
        }
    }

    @PostMapping("/manage/addDownloadTimes")
    public ResponseMsg addDownloadTimes(@RequestParam(value = "fileId")String fileId){
        log.info("fileId="+fileId);
        MyFile myFile = myFileService.getById(fileId);
        myFile.setDownloadTimes(myFile.getDownloadTimes()+1);
        myFileService.updateById(myFile);
        return new ResponseMsg(1,"修改成功");
    }

    @PostMapping("/manage/shareFile")
    public ResponseMsg shareFile(@RequestParam(value = "fileId")String fileId,
                                 @RequestParam(value = "isShared")String isShared){
        log.info("fileId="+fileId+",isShared="+isShared);
        MyFile myFile = myFileService.getById(fileId);
        myFile.setIsShared(isShared);
        boolean flag = myFileService.updateById(myFile);
        String msg;
        int code;
        if(flag && isShared.equals("1")){
            code = 1;
            msg = "分享成功";
        }else if(flag && isShared.equals("0")){
            code = 1;
            msg = "已取消分享";
        }else {
            code = 0;
            msg = "操作失败";
        }
        return new ResponseMsg(code,msg);
    }


    //如果文件夹不存在，创建文件夹
    private void makeDir(String url){
        File file = new File(url);
        while (!file.exists()){
            file.mkdirs();
        }
    }

    private MyFile getFileInfo(MultipartFile file,int uploaderId,String url,String fileType){
        String originalFilename = file.getOriginalFilename();
        String[] str = originalFilename.split("\\.");
        String fileForMats = str[str.length-1];
        String fileName = originalFilename.substring(0,originalFilename.length()-1-fileForMats.length());

        return new MyFile()
                .setUploaderId(uploaderId)
                .setFileName(fileName)
                .setFileFormats(fileForMats)
                .setFileSize(file.getSize())
                .setFileUrl(url + file.getOriginalFilename())
                .setFileOriginName(originalFilename)
                .setFileType(fileType)
                .setUploadTime(new Date())
                .setDownloadTimes(0)//默认下载次数为0
                .setIsShared("0");//默认不分享
    }

    private IPage<MyFile> getMyFileList(String uploaderId, String fileName, String fileType, String isShared,
                                        int pageIndex, int pageSize){

        LambdaQueryWrapper<MyFile> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(uploaderId)){
            queryWrapper.eq(MyFile::getUploaderId,uploaderId);
        }
        if(StringUtils.isNotBlank(fileName)){
            queryWrapper.like(MyFile::getFileName,fileName);
        }
        if(StringUtils.isNotBlank(fileType)){
            queryWrapper.eq(MyFile::getFileType,fileType);
        }
        if (StringUtils.isNotBlank(isShared)){
            queryWrapper.eq(MyFile::getIsShared,isShared);
        }
        queryWrapper.eq(MyFile::getIsDel,0);
        if(pageIndex<1){
            pageIndex = 1;
        }
        if(pageSize<1){
            pageSize = 10;
        }
        Page<MyFile> page = new Page<>((pageIndex-1)*pageSize,pageSize);
        return myFileService.selectPage(page,queryWrapper);
    }

}
