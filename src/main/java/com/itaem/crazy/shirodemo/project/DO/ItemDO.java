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
 * 储存商户上架的商品信息
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("item")
public class ItemDO implements Serializable {

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
     * 商品编号
     */
    private String itemId;

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

    /**
     * 销售量
     */
    private Integer sellCount;

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


}
