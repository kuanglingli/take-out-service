package com.itaem.crazy.shirodemo.project.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CustVO  implements Serializable {

    private static final long serialVersionUID = -267633539581392772L;

    /**
     * id
     */
    private Long id;

    /**
     * 客户编号
     */
    private String custId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String tel;

    /**
     * 注册时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String registTime;

    /**
     * 默认：0（未删除），删除后改为：1（已删除）
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
}
