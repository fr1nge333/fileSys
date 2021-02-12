package cn.lhs.filesys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.alibaba.fastjson.JSON;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2021-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysConstant extends Model<SysConstant> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "constant_id", type = IdType.AUTO)
    private Integer constantId;

    /**
     * 排序
     */
    private Integer constantOrder;

    /**
     * 常量key
     */
    private String constantKey;

    /**
     * 常量名
     */
    private String constantName;

    /**
     * 常量值
     */
    private String constantValue;

    /**
     * 常量说明
     */
    private String constantComment;


    @Override
    protected Serializable pkVal() {
        return this.constantId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
