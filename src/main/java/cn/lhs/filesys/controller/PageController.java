package cn.lhs.filesys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
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

    @GetMapping("/setting")
    public String setting(){
        return "setting";
    }

}
