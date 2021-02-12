package cn.lhs.filesys.controller;

import cn.lhs.filesys.entity.ResponseMsg;
import cn.lhs.filesys.entity.SysConstant;
import cn.lhs.filesys.service.SysConstantService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2021-02-11
 */
@RestController
@Slf4j
public class SysConstantController {
    @Autowired
    private SysConstantService sysConstantService;

    @GetMapping("/getConstantList")
    public ResponseMsg getConstantList(@RequestParam(value = "constantKey")String constantKey){
        log.info("constantKey="+constantKey);
        LambdaQueryWrapper<SysConstant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysConstant::getConstantKey,constantKey).orderByAsc(SysConstant::getConstantOrder);
        List<SysConstant> sysConstantList = sysConstantService.list(queryWrapper);
        return new ResponseMsg(0,"成功",sysConstantList.size(),sysConstantList);
    }
}
