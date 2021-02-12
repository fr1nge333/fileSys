package cn.lhs.filesys.service.impl;

import cn.lhs.filesys.dao.UserMapper;
import cn.lhs.filesys.entity.User;
import cn.lhs.filesys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2020-11-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
