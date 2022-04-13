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
 * 支付表
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pay")
public class PayDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 主订单标记：0 - 子单，1 - 主单
     */
    private String primaryOrderFlag;

    /**
     * 主订单编号
     */
    private String primaryOrderId;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 支付状态：0 - 待支付，1 - 已支付
     */
    private String payStatus;

    /**
     * 支付方式: 1 - 支付宝，2 - 微信
     */
    private String payMethod;

    /**
     * 支付金额（分）
     */
    private String payAmount;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

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
