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
 * 客户地址表
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("address")
public class AddressDO implements Serializable {

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
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 配送地址
     */
    private String address;

    /**
     * 是否默认地址
     */
    private String isDefault;

    /**
     * 删除标记
     */
    private String deleteYn;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
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
