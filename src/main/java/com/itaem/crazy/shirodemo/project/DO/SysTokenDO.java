package com.itaem.crazy.shirodemo.project.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_token")
public class SysTokenDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private LocalDateTime expireTime;

    private String token;

    private LocalDateTime updateTime;


}
