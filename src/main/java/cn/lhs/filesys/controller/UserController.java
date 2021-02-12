package cn.lhs.filesys.controller;


import cn.lhs.filesys.entity.ResponseMsg;
import cn.lhs.filesys.entity.User;

import cn.lhs.filesys.service.UserService;
import cn.lhs.filesys.util.GetMD5;
import cn.lhs.filesys.util.JwtUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@Slf4j
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @Value("${jwt.token.name}")
    private String tokenName;

    @Value("${jwt.signing.key}")
    private String signingKey;

    @PostMapping(value = "/userRegister")
    public String createUser(@RequestParam(value = "mailAddress")String mailAddress,
                             @RequestParam(value = "userName")String userName,
                             @RequestParam(value = "password")String password){
        User user = new User ();
        user.setMailAddress ( mailAddress )
                .setUserName ( userName )
                .setPassword ( GetMD5.encryptString ( password ) )
                .setCreateTime ( new Date() )
                .setAuthority ("1");
        log.info (user.toString());
        userService.save ( user );
        return "regSucceed";
    }

    @PostMapping(value = "/userLogin")
    public String checkUser(HttpServletRequest request,
                            @RequestParam(value = "mailAddress")String mailAddress,
                            @RequestParam(value = "password")String password){

        log.info("mailAddress="+mailAddress+",password="+password);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getMailAddress,mailAddress).eq(User::getPassword,GetMD5.encryptString (password));
        User user = userService.getOne(wrapper);
        log.info(user.toString());
        HttpSession session = request.getSession ();
        session.setAttribute ( "user",user );
        session.setAttribute(tokenName,JwtUtil.generateToken(signingKey,user.getMailAddress()));
        return "redirect:/index";
    }


    @RequestMapping(value = "/userLogout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return "redirect:/login";
    }



    @GetMapping(value = "/isUserExist")
    @ResponseBody
    public ResponseMsg isUserExist(@RequestParam(value = "mailAddress")String mailAddress){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getMailAddress,mailAddress);
        int count = userService.count(wrapper);
        if(count == 0){
            return new ResponseMsg(1,"成功");
        }else {
            return new ResponseMsg(0, "失败");
        }
    }

    @GetMapping(value = "/userTest1")
    @ResponseBody
    public ResponseMsg test1(HttpServletRequest request,@RequestParam(value = "userId")String userId){
        String userFromToken = JwtUtil.getSubject(request,tokenName,signingKey);
        if(userId.equals(userFromToken)){
            return new ResponseMsg(1,"成功");
        }else {
            return new ResponseMsg(0, "失败");
        }
    }

    @GetMapping(value = "/checkPoint")
    @ResponseBody
    public ResponseMsg checkPoint(@RequestParam(value = "userId")Integer userId,
                                  @RequestParam(value = "uploaderId")Integer uploaderId,
                                  @RequestParam(value = "fileSort")String fileSort){

//        User downloadUser = userService.getById(userId);
//        User uploadUser = userService.getById(uploaderId);
//        int neededPoint;
//        if("1".equals(fileSort)){
//            neededPoint = 1;
//        }else {
//            neededPoint = 5;
//        }
//        if (downloadUser.getPoint() < neededPoint){
//            return new ResponseMsg(0,"积分不足");
//        }else {
//            uploadUser.setPoint(uploadUser.getPoint()+neededPoint);
//            downloadUser.setPoint(downloadUser.getPoint()-neededPoint);
//            boolean plus = userService.updateById(uploadUser);
//            boolean minus = userService.updateById(downloadUser);
//            if(plus && minus){
//                return new ResponseMsg(1,"积分扣除成功");
//            }else {
//                return new ResponseMsg(0,"积分扣除失败");
//            }
//        }
        return new ResponseMsg(1,"积分扣除成功");
    }

    @GetMapping(value = "/modifyUserName")
    public String modifyUserName(HttpServletRequest request,
                                 @RequestParam(value = "userId")String userId,
                                 @RequestParam(value = "newName")String newName){
        User user = userService.getById(userId);
        user.setUserName(newName);
        boolean flag = userService.updateById(user);
        if(!flag){
            return "redirect:/error";
        }else {
            //更新session的信息
            request.getSession().setAttribute("user", user);
            return "redirect:/setting";
        }
    }

    @PostMapping(value = "/modifyPassword")
    public String modifyPassword(HttpServletRequest request,
                                 @RequestParam(value = "userId")String userId,
                                 @RequestParam(value = "newPassword")String newPassword){
        log.info("userId="+userId+",newPassword="+newPassword);
        User user = userService.getById(userId);
        user.setPassword(GetMD5.encryptString(newPassword));
        boolean flag = userService.updateById(user);
        if(!flag){
            return "redirect:/error";
        }else {
            //删除原来session
            request.getSession().removeAttribute("user");
            request.getSession().invalidate();
            return "pwdModify";
        }
    }

    @GetMapping(value = "/checkOldPassword")
    @ResponseBody
    public ResponseMsg checkOldPassword(@RequestParam(value = "userId")Integer userId,
                                        @RequestParam(value = "oldPassword")String oldPassword){
        User user = new User();
        user.setPassword(GetMD5.encryptString(oldPassword)).setUserId(userId);
        int count = user.selectCount(new QueryWrapper<>(user));
        if (count > 0){
            return new ResponseMsg(1,"验证成功");
        }else {
            return new ResponseMsg(0,"验证失败");
        }
    }
}
