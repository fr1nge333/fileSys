package cn.lhs.filesys.mapper;

import cn.lhs.filesys.entity.User;

import java.util.Map;

public interface UserMapper {
    int createUser(User user);//注册新用户
    int isUserExist(String userId);
    User checkUser(Map<String, String> param);//用户登录
    int modifyUserName(Map<String, String> param);
    int modifyPassword(Map<String, String> param);

    int getUserPoint(String userId);
    int modifyUserPoint(Map<String, Object> param);
    int pointPlusAndMinus(Map<String, Object> param);
}
