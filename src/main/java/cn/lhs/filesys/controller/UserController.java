package cn.lhs.filesys.controller;

import cn.lhs.filesys.entity.FileMsg;
import cn.lhs.filesys.entity.MyFile;
import cn.lhs.filesys.entity.ResponseMsg;
import cn.lhs.filesys.entity.User;
import cn.lhs.filesys.service.FileManageService;
import cn.lhs.filesys.service.UserService;
import cn.lhs.filesys.util.GetMD5;
import cn.lhs.filesys.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${jwt.token.name}")
    private String tokenName;

    @Value("${jwt.signing.key}")
    private String signingKey;

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
    public ResponseMsg test(HttpServletRequest request,@RequestParam(required = true,value = "userId")String userId){
        String token = JwtUtil.generateToken("fr1ng3",userId);
        HttpSession session = request.getSession ();
        session.setAttribute ( tokenName,token );
        return new ResponseMsg(1,"成功");
    }

    @RequestMapping(value = "/userTest1",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMsg test1(HttpServletRequest request,@RequestParam(required = true,value = "userId")String userId){
        String userFromToken = JwtUtil.getSubject(request,tokenName,signingKey);
        if(userId.equals(userFromToken)){
            return new ResponseMsg(1,"成功");
        }else {
            return new ResponseMsg(0, "失败");
        }
    }

    @RequestMapping(value = "/checkPoint",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMsg checkPoint(@RequestParam(value = "userId")String userId,
                                  @RequestParam(value = "uploaderId")String uploaderId,
                                  @RequestParam(value = "fileSort")String fileSort){

        int usePoint = 0,neededPoint = 0,point = 0;
        if(fileSort.equals("1")){
            neededPoint = 1;
            point = -1;
        }else {
            neededPoint = 5;
            point = -5;
        }
        usePoint = userService.getUserPoint(userId);
        if (usePoint < neededPoint){
            return new ResponseMsg(0,"积分不足");
        }else {
            int flag1 = userService.pointPlusAndMinus(userId,point);
            int flag2 = userService.pointPlusAndMinus(uploaderId,neededPoint);
            if(flag1 == 1 && flag2 == 1){
                return new ResponseMsg(1,"积分扣除成功");
            }else {
                return new ResponseMsg(0,"积分扣除失败");
            }

        }
    }

    @RequestMapping(value = "/modifyUserName",method = RequestMethod.GET)
    public String modifyUserName(HttpServletRequest request,
                                  @RequestParam(value = "userId")String userId,
                                  @RequestParam(value = "newName")String newName){
        int code = userService.modifyUserName(userId, newName);
        if(code == 0){
            return "redirect:/error";
        }else {
            //更新session中user的信息
            User user = (User) request.getSession().getAttribute("user");
            user.setUserName(newName);
            //删除原来session
            request.getSession().removeAttribute("user");
            request.getSession().invalidate();
            //传给前台新的session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            return "redirect:/setting";
        }
    }

    @RequestMapping(value = "/modifyPassword",method = RequestMethod.POST)
    public String modifyPassword(HttpServletRequest request,
                             @RequestParam(value = "userId")String userId,
                             @RequestParam(value = "newPassword")String newPassword){
        logger.info("userId="+userId+",newPassword="+newPassword);

        int code = userService.modifyPassword(userId,GetMD5.encryptString(newPassword));
        if(code == 0){
            return "redirect:/error";
        }else {
            //删除原来session
            request.getSession().removeAttribute("user");
            request.getSession().invalidate();
            return "pwdModify";
        }
    }

    @RequestMapping(value = "/checkOldPassword",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMsg checkOldPassword(HttpServletRequest request,
                                 @RequestParam(value = "userId")String userId,
                                 @RequestParam(value = "oldPassword")String oldPassword){

        User user = userService.checkUser(userId,GetMD5.encryptString(oldPassword));
        if (user != null){
            return new ResponseMsg(1,"验证成功");
        }else {
            return new ResponseMsg(0,"验证失败");
        }
    }



}
