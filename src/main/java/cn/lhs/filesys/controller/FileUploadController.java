package cn.lhs.filesys.controller;

import cn.lhs.filesys.entity.MyFile;
import cn.lhs.filesys.entity.ResponseMsg;
import cn.lhs.filesys.service.FileManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private FileManageService fileManageService;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);


    @Value("${image.url}")
    private String imageUrl;

    @Value("${video.url}")
    private String videoUrl;


    @PostMapping("/image")
    @ResponseBody
    public ResponseMsg uploadImages(HttpServletRequest request,
                                    @RequestParam(required = true,value = "uploaderId")String uploaderId,
                                    @RequestParam(required = true,value = "fileSort")String fileSort) {
        makeDir(imageUrl);
        List<MultipartFile> picFiles = ((MultipartHttpServletRequest) request).getFiles("file");
        if(picFiles.size() == 0){
            return new ResponseMsg(0,"没有任何文件");
        }
        for (int i = 0; i < picFiles.size (); i++) {
            if(!picFiles.get(i).isEmpty()) {
                String originalFilename = picFiles.get(i).getOriginalFilename();
                String[] str = originalFilename.split("\\.");
                String fileForMats = str[str.length-1];
                String fileName = originalFilename.substring(0,originalFilename.length()-1-fileForMats.length());

                MyFile myFile = new MyFile();
                myFile.setUploaderId(uploaderId);
                myFile.setFileId(System.currentTimeMillis() + "-" + fileName);
                myFile.setFileName(fileName);
                myFile.setFileFormats(fileForMats);
                myFile.setFileSize(picFiles.get(i).getSize());
                myFile.setFileUrl(imageUrl + picFiles.get(i).getOriginalFilename());
                myFile.setFileOriginName(originalFilename);
                myFile.setFileSort(fileSort);
                myFile.setUploadTime(new Date());
                myFile.setDownloadTimes(0);//默认下载次数为0
                myFile.setIsShared("0");//默认不分享
                logger.info(myFile.toString());
                try {
                    picFiles.get(i).transferTo(new File(myFile.getFileUrl()));
                    if (fileManageService.saveMyFile(myFile) <= 0) {
                        return new ResponseMsg(0,"插入数据错误");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ResponseMsg(0,"保存文件错误");
                }
            }
        }

        return new ResponseMsg(1,"上传成功");
    }


    @PostMapping("/video")
    @ResponseBody
    public ResponseMsg uploadVideo(@RequestParam("file")MultipartFile videoFile,
                                     @RequestParam(required = true,value = "uploaderId")String uploaderId,
                                     @RequestParam(required = true,value = "fileSort")String fileSort) {
        makeDir(videoUrl);

        String originalFilename = videoFile.getOriginalFilename ();
        String[] str = originalFilename.split("\\.");
        String fileForMats = str[str.length-1];
        String fileName = originalFilename.substring(0,originalFilename.length()-1-fileForMats.length());
        MyFile myFile = new MyFile ();
        myFile.setUploaderId ( uploaderId );
        myFile.setFileId ( System.currentTimeMillis ()+"-"+ fileName );
        myFile.setFileName ( fileName );
        myFile.setFileFormats ( fileForMats );
        myFile.setFileSize ( videoFile.getSize () );
        myFile.setFileUrl ( videoUrl+videoFile.getOriginalFilename () );
        myFile.setFileOriginName(originalFilename);
        myFile.setFileSort ( fileSort );
        myFile.setUploadTime ( new Date () );
        myFile.setDownloadTimes ( 0 );
        myFile.setIsShared("0");//默认不分享
        logger.info ( myFile.toString () );

        try {
            long start = System.currentTimeMillis ();
            videoFile.transferTo ( new File ( myFile.getFileUrl() ) );
            logger.info ( "用时="+(System.currentTimeMillis ()-start)+"ms" );

            if (fileManageService.saveMyFile ( myFile )>0){
                return new ResponseMsg(1,"上传成功");
            }else {
                return new ResponseMsg(0,"插入数据错误");
            }
        } catch (IOException e) {
            e.printStackTrace ();
            return new ResponseMsg(0,"保存文件错误");
        }



    }

    //如果文件夹不存在，创建文件夹
    private void makeDir(String url){
        File file = new File(url);
        while (!file.exists()){
            file.mkdirs();
        }
    }
}

