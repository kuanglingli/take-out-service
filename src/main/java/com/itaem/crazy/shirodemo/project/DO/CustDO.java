package com.itaem.crazy.shirodemo.project.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户名
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cust")
public class CustDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户编号
     */
    private String custId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 注册时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:MM:SS",timezone="GMT+8")
    private LocalDateTime registTime;

    /**
     * 默认：0（未删除），删除后改为：1（已删除）
     */
    private String deleteYn;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:MM:SS",timezone="GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:MM:SS",timezone="GMT+8")
    private LocalDateTime updateTime;

    /**
     * 创建人编号
     */
    private String createId;

    /**
     * 更新人编号
     */
    private String updateId;


}
