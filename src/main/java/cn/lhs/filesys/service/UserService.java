package cn.lhs.filesys.service;

import cn.lhs.filesys.entity.User;

public interface UserService {
    int createUser(User user);
    int isUserExist(String userId);
    User checkUser(String userId, String password);
    int modifyUserName(String userId, String userName);
    int modifyPassword(String userId, String password);

    int getUserPoint(String userId);
    int modifyUserPoint(String userId,int point);
    int pointPlusAndMinus(String userId,int point);
}
