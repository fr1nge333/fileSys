package cn.lhs.filesys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "reg";
    }

    @GetMapping("/uploadImage")
    public String uploadImage(){
        return "uploadImage";
    }

    @GetMapping("/uploadVideo")
    public String uploadVideo(){
        return "uploadVideo";
    }

    @GetMapping("/download")
    public String download(){
        return "showAndDownload";
    }

    @GetMapping("/test")
    public String test(){
        return "test2";
    }

}
