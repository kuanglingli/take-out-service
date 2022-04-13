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
 * 售后退款表
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("refund")
public class RefundDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 售后单编号
     */
    private String reversalId;

    /**
     * 订单状态：0 - 待退款，1 - 已退款
     */
    private String refundStatus;

    /**
     * 退款金额（分）
     */
    private String refundFee;

    /**
     * 退款时间
     */
    private LocalDateTime refundTime;

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
