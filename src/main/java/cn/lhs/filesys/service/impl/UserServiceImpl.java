package cn.lhs.filesys.service.impl;

import cn.lhs.filesys.entity.User;
import cn.lhs.filesys.mapper.UserMapper;
import cn.lhs.filesys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int createUser(User user) {
        return userMapper.createUser ( user );
    }

    @Override
    public int isUserExist(String userId) {
        return userMapper.isUserExist(userId);
    }

    @Override
    public User checkUser(String userId, String password) {
        Map<String,String> map = new HashMap<> ();
        map.put ( "userId",userId );
        map.put ( "password",password );
        return userMapper.checkUser ( map );
    }

    @Override
    public int modifyUserName(String userId, String userName) {
        Map<String,String> map = new HashMap<> ();
        map.put ( "userId",userId );
        map.put ( "userName",userName );
        return userMapper.modifyUserName ( map );
    }

    @Override
    public int modifyPassword(String userId, String password) {
        Map<String,String> map = new HashMap<> ();
        map.put ( "userId",userId );
        map.put ( "password",password );
        return userMapper.modifyPassword ( map );
    }

    @Override
    public int getUserPoint(String userId) {
        return userMapper.getUserPoint(userId);
    }

    @Override
    public int modifyUserPoint(String userId, int point) {
        Map<String,Object> map = new HashMap<> ();
        map.put ( "userId",userId );
        map.put ( "point",point );
        return userMapper.modifyUserPoint(map);
    }

    @Override
    public int pointPlusAndMinus(String userId, int points) {
        Map<String,Object> map = new HashMap<> ();
        map.put ( "userId",userId );
        map.put ( "points",points );
        return userMapper.pointPlusAndMinus(map);
    }

}
