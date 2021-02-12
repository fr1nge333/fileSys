package cn.lhs.filesys.service.impl;

import cn.lhs.filesys.entity.MyFile;
import cn.lhs.filesys.dao.MyFileMapper;
import cn.lhs.filesys.service.MyFileService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
public class MyFileServiceImpl extends ServiceImpl<MyFileMapper, MyFile> implements MyFileService {

    @Override
    public IPage<MyFile> selectPage(IPage<MyFile> page, Wrapper<MyFile> wrapper) {
        return baseMapper.selectPage(page,wrapper);
    }
}
