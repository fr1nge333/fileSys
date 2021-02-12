package cn.lhs.filesys.service;

import cn.lhs.filesys.entity.MyFile;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-11-18
 */
public interface MyFileService extends IService<MyFile> {
    IPage<MyFile> selectPage(IPage<MyFile> page, Wrapper<MyFile> wrapper);
}
