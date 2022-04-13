package com.itaem.crazy.shirodemo.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.project.DO.*;
import com.itaem.crazy.shirodemo.project.bean.*;
import com.itaem.crazy.shirodemo.project.bean.OrderVO;
import com.itaem.crazy.shirodemo.project.service.DeliveryService;
import com.itaem.crazy.shirodemo.project.service.OrderService;
import com.itaem.crazy.shirodemo.project.service.UserRoleService;
import com.itaem.crazy.shirodemo.utils.UserUtils;
import com.itaem.crazy.shirodemo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/order-do")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping(value = "/page",name = "查询所属当前用户的订单列表，管理员返回全部订单")
    public Result<IPage<OrderVO>> getOrders(@RequestHeader("token") String token, @RequestBody @Validated LiPage liPage) throws MyException {
        User user = UserUtils.getCurrentUser(token);
        LambdaQueryWrapper<UserRoleDO> userRoleDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleDOLambdaQueryWrapper.eq(UserRoleDO::getUserId,user.getUserId())
                .eq(UserRoleDO::getRoleId,"1");
        List<UserRoleDO> userRoleDOS = userRoleService.list(userRoleDOLambdaQueryWrapper);
        String shopId = null;
        String custId = null;
        if (CollectionUtils.isEmpty(userRoleDOS)){
            ShopDO currentShop = UserUtils.getCurrentShop(token);
            if (currentShop != null){
                shopId = currentShop.getShopId();
            }
            CustDO custDO = UserUtils.getCurrentCust(token);
            if (custDO != null){
                custId = custDO.getCustId();
            }
        }
        IPage<OrderVO> reselt = orderService.getOrders(liPage,shopId,custId);
        List<OrderVO> list = reselt.getRecords();
        if (!CollectionUtils.isEmpty(list)){
            for (OrderVO orderVO:list){
                orderVO.setTotalFee(Utils.fen2Yuan(orderVO.getTotalFee()));
                orderVO.setImgList(Arrays.asList(orderVO.getOrderImgs().split(",")));

            }
        }

        return new Result<IPage<OrderVO>>("0","查询成功",reselt);
    }

    @PostMapping(value = "/pay",name = "下单")
    public Result<Boolean> createOrder(@RequestHeader("token") String token,@RequestBody OrderCreateVO orderCreateVO) throws MyException {
        User user = UserUtils.getCurrentUser(token);
        return new Result<Boolean>("0","支付成功",orderService.createOrder(user,orderCreateVO));
    }

    @PostMapping(value = "/update",name = "更新订单")
    public Result<Boolean> updateOrder(@RequestHeader("token") String token,@RequestBody OrderVO orderVO) throws MyException {
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderVO,orderDO);
        orderDO = orderService.getById(orderDO);
        LambdaQueryWrapper<OrderDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderDO::getDeleteYn,"0")
                .eq(OrderDO::getPrimaryOrderId,orderDO.getPrimaryOrderId());

        List<OrderDO> list = orderService.list(wrapper);
        for (OrderDO order:list){
            order.setOrderStatus(orderVO.getOrderStatus());
        }

        return new Result<Boolean>("0","操作成功",orderService.updateBatchById(list));
    }

    @PostMapping(value = "/updateType",name = "更新订单类型")
    public Result<Boolean> updateOrderType(@RequestHeader("token") String token,@RequestBody OrderVO orderVO) throws MyException {
        User user = UserUtils.getCurrentUser(token);
        OrderDO orderDO = new OrderDO();
        String meg = "";
        BeanUtils.copyProperties(orderVO,orderDO);
        if ("1".equals(orderDO.getOrderType())){
            meg = "已退3元配送费";
            orderDO.setTotalFee((Integer.parseInt(Utils.yuan2Fen(orderDO.getTotalFee()))-300)+"");
        }else {
            meg = "已补3元配送费";
            orderDO.setTotalFee((Integer.parseInt(Utils.yuan2Fen(orderDO.getTotalFee()))+300)+"");
        }
        orderService.updateById(orderDO);
        orderDO = orderService.getById(orderDO);
        if ("1".equals(orderDO.getOrderType())){
            LambdaUpdateWrapper<DeliveryDO> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(DeliveryDO::getDeleteYn,"0")
                    .eq(DeliveryDO::getPrimaryOrderId,orderDO.getPrimaryOrderId())
                    .set(DeliveryDO::getDeleteYn,"1");
            deliveryService.update(updateWrapper);
        }else {
            DeliveryDO deliveryDO = new DeliveryDO();
            BeanUtils.copyProperties(orderVO,deliveryDO);
            deliveryDO.setOrderId(orderDO.getOrderId());
            deliveryDO.setPrimaryOrderId(orderDO.getPrimaryOrderId());
            deliveryDO.setTel(orderVO.getDelyTel());
            deliveryDO.setDeleteYn("0");
            deliveryDO.setCreateId(user.getUserId()+"");
            deliveryDO.setUpdateId(user.getUserId()+"");
            deliveryDO.setCreateTime(LocalDateTime.now());
            deliveryDO.setUpdateTime(LocalDateTime.now());
            deliveryService.save(deliveryDO);
        }
        LambdaQueryWrapper<OrderDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderDO::getDeleteYn,"0")
                .eq(OrderDO::getPrimaryOrderFlag,"0")
                .eq(OrderDO::getPrimaryOrderId,orderDO.getPrimaryOrderId());

        List<OrderDO> list = orderService.list(wrapper);
        for (OrderDO order:list){
            order.setOrderType(orderVO.getOrderType());
        }

        return new Result<Boolean>("0","操作成功",orderService.updateBatchById(list));
    }
}

