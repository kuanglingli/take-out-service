package com.itaem.crazy.shirodemo.project.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统参数表
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_code")
public class SysCodeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 参数编码类型：标识参数的唯一业务编码
     */
    private String parCodeTypeId;

    /**
     * 参数编码类型名称
     */
    private String parCodeTypeName;

    /**
     * 参数业务编码
     */
    private String parBizCode;

    /**
     * 参数名称
     */
    private String parBizName;

    /**
     * 上级参数业务编码
     */
    private String parSuperBizCode;

    /**
     * 有效性标志：0-无效，1-有效
     */
    private Boolean deleteYn;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 记录最后更新时间，记录初次创建时等同于create_time
     */
    private LocalDateTime updateTime;

    /**
     * 记录创建人id
     */
    private String createId;

    /**
     * 记录最后更新人id，记录初次创建等于create_id
     */
    private String updateId;


}
