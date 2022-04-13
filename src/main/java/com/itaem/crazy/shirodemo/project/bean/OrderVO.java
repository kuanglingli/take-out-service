package com.itaem.crazy.shirodemo.project.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderVO implements Serializable {

    private static final long serialVersionUID = 5839451370556280668L;

    /**
     * id
     */
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

    private List<String> imgList;

    /**
     * 订单类型：1-到店自取，2-商家配送
     */
    private String orderType;

    private String orderTypeName;

    /**
     * 订单状态：0 - 待支付，1 - 已支付，2 - 制作中，3 - 制作完成，4 - 已取餐，5 - 配送中，6 - 订单完成，7 - 申请撤单，8 - 订单取消
     */
    private String orderStatus;

    private String orderStatusName;

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
     * 订单总金额
     */
    private String totalFee;

    /**
     * 配送联系人
     */
    private String contact;

    /**
     * 配送电话
     */
    private String delyTel;

    /**
     * 配送地址
     */
    private String address;

    /**
     * 订单备注
     */
    private String remark;

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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String registTime;

    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 联系电话
     */
    private String shopTel;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 店铺描述
     */
    private String shopDesc;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品图片
     */
    private String itemImgUrl;

    /**
     * 商品价格（分）
     */
    private String price;

    /**
     * 制作时长（分）
     */
    private String makeTime;

    /**
     * 商品类型：1 - 实物商品，2 - 配送费
     */
    private String itemType;

    private String itemTypeName;

    /**
     * 商品描述
     */
    private String itemDesc;

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
