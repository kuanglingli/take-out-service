package com.itaem.crazy.shirodemo.project.bean;

import com.itaem.crazy.shirodemo.project.DO.AddressDO;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreateVO {

    private Integer orderType;

    private List<ItemVO> itemList;

    private AddressDO address;

    private String totalMoney;

}
