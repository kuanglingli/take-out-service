package com.itaem.crazy.shirodemo.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.project.DO.OrderDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itaem.crazy.shirodemo.project.bean.ItemVO;
import com.itaem.crazy.shirodemo.project.bean.LiPage;
import com.itaem.crazy.shirodemo.project.bean.OrderCreateVO;
import com.itaem.crazy.shirodemo.project.bean.OrderVO;
import com.itaem.crazy.shirodemo.project.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
public interface OrderService extends IService<OrderDO> {

    IPage<OrderVO> getOrders(LiPage liPage, String shopId,String custId) throws MyException;

    Boolean createOrder(User user, OrderCreateVO orderCreateVO) throws MyException;
}
