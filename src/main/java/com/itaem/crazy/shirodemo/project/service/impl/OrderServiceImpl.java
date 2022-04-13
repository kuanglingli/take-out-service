package com.itaem.crazy.shirodemo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.project.DO.*;
import com.itaem.crazy.shirodemo.project.bean.*;
import com.itaem.crazy.shirodemo.project.bean.OrderVO;
import com.itaem.crazy.shirodemo.project.mapper.*;
import com.itaem.crazy.shirodemo.project.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itaem.crazy.shirodemo.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Transactional
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderService {
    
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustMapper custMapper;

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public IPage<OrderVO> getOrders(LiPage liPage, String shopId,String custId) throws MyException {
        Page<OrderVO> page = new Page<>(liPage.getPage(),liPage.getSize(),orderMapper.getOrdersCount(shopId,custId),false);
        IPage<OrderVO> result = orderMapper.getOrders(page,shopId,custId);
        List<OrderVO> OrderVOList = result.getRecords();
        if (CollectionUtils.isEmpty(OrderVOList)){
            return result;
        }
        for (OrderVO OrderVO:OrderVOList){
            OrderVO.setPrice(Utils.fen2Yuan(OrderVO.getPrice()));
        }
        return result;
    }

    @Override
    public Boolean createOrder(User user, OrderCreateVO orderCreateVO) throws MyException {
        LambdaQueryWrapper<CustDO> custDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        custDOLambdaQueryWrapper.eq(CustDO::getDeleteYn,"0")
                .eq(CustDO::getUserName,user.getUsername());
        CustDO custDO = custMapper.selectOne(custDOLambdaQueryWrapper);
        OrderDO primaryOrderDO = new OrderDO();
        String primaryOrderId = System.currentTimeMillis()+"";
        String primaryOrderFee = Utils.yuan2Fen(orderCreateVO.getTotalMoney());
        Integer count = 0;
        List<String> imgs = new ArrayList<>();
        Integer m = 0;
        for (ItemVO item:orderCreateVO.getItemList()){
            if (Integer.parseInt(item.getMakeTime())>m){
                m = Integer.parseInt(item.getMakeTime());
            }
            String OrderId = System.currentTimeMillis()+1+"";
            OrderDO orderDO = new OrderDO();
            orderDO.setOrderId(OrderId);
            orderDO.setItemId(item.getItemId());
            orderDO.setOrderName(item.getItemName());
            orderDO.setShopId(item.getShopId());
            orderDO.setItemQuantity(item.getShoppingCount());
            orderDO.setCustId(custDO.getCustId());
            orderDO.setPrimaryOrderId(primaryOrderId);
            orderDO.setPrimaryOrderFlag("0");
            orderDO.setTotalFee((Integer.parseInt(Utils.yuan2Fen(item.getPrice()))*item.getShoppingCount())+"");
            orderDO.setOrderImgs(item.getItemImgUrl());
            orderDO.setOrderStatus("1");
            orderDO.setOrderType("1");
            if (orderCreateVO.getOrderType()==2){
                orderDO.setOrderType("2");
                orderDO.setAddress(orderCreateVO.getAddress().getAddress());
            }
            orderDO.setDeleteYn("0");
            orderDO.setCreateId(user.getUserId()+"");
            orderDO.setUpdateId(user.getUserId()+"");
            orderDO.setCreateTime(LocalDateTime.now());
            orderDO.setUpdateTime(LocalDateTime.now());
            orderDO.setDoTime(orderDO.getCreateTime().plusMinutes(Long.valueOf(item.getMakeTime())).toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
            imgs.add(item.getItemImgUrl());
            count += orderDO.getItemQuantity();
            BeanUtils.copyProperties(orderDO,primaryOrderDO);
            orderMapper.insert(orderDO);
            LambdaUpdateWrapper<ItemDO> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(ItemDO::getDeleteYn,"0")
                    .eq(ItemDO::getItemId,item.getItemId())
                    .set(ItemDO::getSellCount,item.getSellCount()+item.getShoppingCount());
            itemMapper.update(null,updateWrapper);
            LambdaUpdateWrapper<ShopDO> Wrapper = new LambdaUpdateWrapper<>();
            Wrapper.eq(ShopDO::getIsEffect,"1")
                    .eq(ShopDO::getShopId,item.getShopId())
                    .set(ShopDO::getSellCount,item.getSellCount()+item.getShoppingCount());
            shopMapper.update(null,Wrapper);
        }
        primaryOrderDO.setPrimaryOrderFlag("1");
        primaryOrderDO.setPrimaryOrderId(primaryOrderId);
        primaryOrderDO.setOrderId(primaryOrderId);
        primaryOrderDO.setTotalFee(primaryOrderFee);
        primaryOrderDO.setOrderImgs(StringUtils.join(imgs.toArray(new String[imgs.size()]),","));
        primaryOrderDO.setItemQuantity(count);
        primaryOrderDO.setOrderName(orderCreateVO.getItemList().get(0).getItemName());
        if (orderCreateVO.getItemList().size()>1){
            primaryOrderDO.setOrderName(orderCreateVO.getItemList().get(0).getItemName()+"等"+orderCreateVO.getItemList().size()+"种商品");
        }
        if (orderCreateVO.getOrderType()==2){
            DeliveryDO deliveryDO = new DeliveryDO();
            BeanUtils.copyProperties(orderCreateVO.getAddress(),deliveryDO);
            deliveryDO.setPrimaryOrderId(primaryOrderId);
            deliveryDO.setOrderId(primaryOrderId);
            deliveryDO.setDeleteYn("0");
            deliveryDO.setCreateId(user.getUserId()+"");
            deliveryDO.setUpdateId(user.getUserId()+"");
            deliveryDO.setCreateTime(LocalDateTime.now());
            deliveryDO.setUpdateTime(LocalDateTime.now());
            deliveryMapper.insert(deliveryDO);
        }
        primaryOrderDO.setDoTime(primaryOrderDO.getCreateTime().plusMinutes(Long.valueOf(m)).toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        orderMapper.insert(primaryOrderDO);
        return true;
    }
}
