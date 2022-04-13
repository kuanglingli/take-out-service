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
 * 存储商户信息及管理员信息的表
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("shop")
public class ShopDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商户编号
     */
    private String shopId;

    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 用户名
     */
    private String userName;

    private String userType;

    /**
     * 联系电话
     */
    private String shopTel;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 店铺图片
     */
    private String shopImgUrl;

    /**
     * 销售量
     */
    private Integer sellCount;

    /**
     * 店铺描述
     */
    private String shopDesc;

    /**
     * 删除标记
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

    /**
     * 是否生效
     */
    private String isEffect;

}
