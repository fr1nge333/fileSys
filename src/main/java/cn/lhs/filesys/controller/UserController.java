package cn.lhs.filesys.controller;

import cn.lhs.filesys.entity.FileMsg;
import cn.lhs.filesys.entity.MyFile;
import cn.lhs.filesys.entity.ResponseMsg;
import cn.lhs.filesys.entity.User;
import cn.lhs.filesys.service.FileManageService;
import cn.lhs.filesys.service.UserService;
import cn.lhs.filesys.util.GetMD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
        logger.info (user.toString());
        userService.createUser ( user );
        return "regSucceed";
    }

    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public String checkUser(HttpServletRequest request,
                             @RequestParam(required = true,value = "userId")String userId,
                             @RequestParam(required = true,value = "password")String password){
        User user = userService.checkUser ( userId,GetMD5.encryptString ( password ) );
        HttpSession session = request.getSession ();
        session.setAttribute ( "user",user );
        return "redirect:/index";
    }

    @RequestMapping(value = "/userLogout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return "redirect:/login";
    }



    @RequestMapping(value = "/userTest",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMsg test(@RequestParam(required = true,value = "userId")String userId){

        int code = userService.isUserExist(userId);
        return new ResponseMsg(code,"查询成功");
    }

}
