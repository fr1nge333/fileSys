package cn.lhs.filesys.controller;

import cn.lhs.filesys.entity.User;
import cn.lhs.filesys.service.UserService;
import cn.lhs.filesys.util.GetMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("/register")
    public String createUser(@RequestParam(required = true,value = "userId")String userId,
                             @RequestParam(required = true,value = "userName")String userName,
                             @RequestParam(required = true,value = "password")String password){
        User user = new User ();
        user.setUserId ( userId );
        user.setUserName ( userName );
        user.setPassword ( GetMD5.encryptString ( password ) );
        user.setCreateTime ( new Date () );
        user.setAuthority ("1");
        userService.createUser ( user );
        return "succeed";
    }

    @RequestMapping("/login")
    public String checkUser(HttpServletRequest request,HttpServletResponse response,
                             @RequestParam(required = true,value = "userId")String userId,
                             @RequestParam(required = true,value = "password")String password){
        User user = userService.checkUser ( userId,GetMD5.encryptString ( password ) );
        System.out.println (user);
        HttpSession session = request.getSession ();
        session.setAttribute ( "user",user );
        return "login";
    }

    @RequestMapping("/test")
    public User test(HttpServletRequest request,
                            @RequestParam(required = true,value = "userId")String userId,
                            @RequestParam(required = true,value = "password")String password){
        User user = userService.checkUser ( userId,GetMD5.encryptString ( password ) );
        System.out.println (user);
        return user;
    }

}
