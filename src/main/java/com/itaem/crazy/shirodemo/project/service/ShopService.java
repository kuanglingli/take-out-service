package com.itaem.crazy.shirodemo.project.service;

import com.itaem.crazy.shirodemo.project.DO.ShopDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itaem.crazy.shirodemo.project.bean.ShopVO;

/**
 * <p>
 * 存储商户信息及管理员信息的表 服务类
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
public interface ShopService extends IService<ShopDO> {

    Boolean saveShop(ShopVO shopVO);
}
