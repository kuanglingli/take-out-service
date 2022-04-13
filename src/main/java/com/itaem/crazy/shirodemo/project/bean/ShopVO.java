package com.itaem.crazy.shirodemo.project.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ShopVO implements Serializable {

    private static final long serialVersionUID = 433655394341297475L;

    /**
     * id
     */
    private Long id;

    /**
     * 商户编号
     */
    private String shopId;

    /**
     * 商户名称
     */
    @NotBlank(message = "商户名称不能为空")
    private String shopName;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 用户名
     */
    @NotBlank(message = "密码不能为空")
    private String password;


    private String userType;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空")
    private String shopTel;

    /**
     * 店铺地址
     */
    @NotBlank(message = "商户地址不能为空")
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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String updateTime;

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

    /**
     * 是否生效
     */
    private String isEffectName;
}
