package cn.lhs.filesys.mapper;

import cn.lhs.filesys.entity.User;

import java.util.Map;

public interface UserMapper {
    int createUser(User user);//注册新用户
    User checkUser(Map<String, String> map);//用户登录
    int modifyUserName(Map<String, String> map);
    int modifyPassword(Map<String, String> map);
}
