package cn.lhs.filesys.controller;

import cn.lhs.filesys.entity.User;
import cn.lhs.filesys.service.UserService;
import cn.lhs.filesys.util.GetMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public String createUser(@RequestParam(required = true,value = "userId")String userId,
                             @RequestParam(required = true,value = "userName")String userName,
                             @RequestParam(required = true,value = "password")String password){
        User user = new User ();
        user.setUserId ( userId );
        user.setUserName ( userName );
        user.setPassword ( GetMD5.encryptString ( password ) );
        user.setCreateTime ( new Date () );
        user.setAuthority ("1");
        System.out.println (user);
        //userService.createUser ( user );
        return "regSucceed";
    }

    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public String checkUser(HttpServletRequest request,HttpServletResponse response,
                             @RequestParam(required = true,value = "userId")String userId,
                             @RequestParam(required = true,value = "password")String password){
        //User user = userService.checkUser ( userId,GetMD5.encryptString ( password ) );
        User user = new User ();
        user.setUserId ( userId );
        user.setPassword ( password );
        System.out.println (user);
        HttpSession session = request.getSession ();
        session.setAttribute ( "user",user );
        return "uploadImage";
    }

    @RequestMapping("/userTest")
    public void test(HttpServletResponse response,
                            @RequestParam(required = true,value = "userId")String userId,
                            @RequestParam(required = true,value = "userName")String userName,
                            @RequestParam(required = true,value = "password")String password){
        System.out.println (userId+","+userName+","+password);
    }

}
