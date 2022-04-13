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
 * 订单表
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_order")
public class OrderDO implements Serializable {

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
     * 订单名称
     */
    private String orderName;

    /**
     * 订单图片
     */
    private String orderImgs;

    /**
     * 订单类型：1-到店自取，2-商家配送
     */
    private String orderType;

    /**
     * 订单状态：0 - 待支付，1 - 已支付，2 - 制作中，3 - 制作完成，4 - 已取餐，5 - 配送中，6 - 订单完成，7 - 申请撤单，8 - 订单取消
     */
    private String orderStatus;

    /**
     * 客户编号
     */
    private String custId;

    /**
     * 商户编号
     */
    private String shopId;

    /**
     * 商品编号
     */
    private String itemId;

    /**
     * 商品数量
     */
    private Integer itemQuantity;

    /**
     * 订单总金额（分）
     */
    private String totalFee;

    /**
     * 配送地址
     */
    private String address;

    /**
     * 订单备注
     */
    private String remark;

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

    /**
     * 倒计时时间
     */
    private Long doTime;


}
