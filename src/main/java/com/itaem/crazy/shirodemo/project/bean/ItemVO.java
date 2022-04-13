package com.itaem.crazy.shirodemo.project.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ItemVO implements Serializable {

    private static final long serialVersionUID = 7482866738932034658L;

    /**
     * id
     */
    private Long id;

    /**
     * 商户编号
     */
    @NotBlank(message = "商户编码不能为空")
    private String shopId;

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
     * 商品编号
     */
    private String itemId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String itemName;

    /**
     * 商品图片
     */
    private String itemImgUrl;

    /**
     * 销售量
     */
    private Integer sellCount;

    /**
     * 商品价格（分）
     */
    @NotBlank(message = "商品价格不能为空")
    private String price;

    /**
     * 制作时长（分）
     */
    @NotBlank(message = "制作时长不能为空")
    private String makeTime;

    /**
     * 商品类型：1 - 实物商品，2 - 配送费
     */
    @NotBlank(message = "商品类型不能为空")
    private String itemType;

    private String itemTypeName;

    /**
     * 商品描述
     */
    private String itemDesc;

    private Integer shoppingCount = 0;

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
}
