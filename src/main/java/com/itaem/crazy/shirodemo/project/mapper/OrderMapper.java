package com.itaem.crazy.shirodemo.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itaem.crazy.shirodemo.project.DO.OrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itaem.crazy.shirodemo.project.bean.ItemVO;
import com.itaem.crazy.shirodemo.project.bean.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Repository
public interface OrderMapper extends BaseMapper<OrderDO> {

    public IPage<OrderVO> getOrders(Page<OrderVO> page, @Param("shopId") String shopId,@Param("custId") String custId);

    public Integer getOrdersCount(@Param("shopId") String shopId,@Param("custId") String custId);

}
