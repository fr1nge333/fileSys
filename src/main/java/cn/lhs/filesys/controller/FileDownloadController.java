package cn.lhs.filesys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/download")
public class FileDownloadController {
    private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);
    @RequestMapping(value = "/getFile",method = RequestMethod.GET)
    public void downloadFile(@RequestParam(required = true, value = "fileUrl") String fileUrl, HttpServletResponse response){
        logger.info (fileUrl);
        try {
            int len = 0;
            byte[] temp = new byte[10240];

            FileInputStream fileInputStream = new FileInputStream ( fileUrl );
            BufferedInputStream bufferedInputStream = new BufferedInputStream ( fileInputStream );
            ServletOutputStream outputStream = response.getOutputStream ();
            while ((len = bufferedInputStream.read ( temp )) != -1){
                outputStream.write ( temp,0,len );
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
