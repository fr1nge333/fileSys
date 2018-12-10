package cn.lhs.filesys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/uploadImage")
    public String uploadImage(){
        return "uploadImage";
    }

    @RequestMapping("/uploadVideo")
    public String uploadVideo(){
        return "uploadVideo";
    }

    @RequestMapping("/download")
    public String download(){
        return "showAndDownload";
    }

    @RequestMapping("/test")
    public String test(){
        return "test2";
    }

}
