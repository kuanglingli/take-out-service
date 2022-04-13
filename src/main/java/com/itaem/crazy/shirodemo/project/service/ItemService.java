package com.itaem.crazy.shirodemo.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.project.DO.ItemDO;
import com.itaem.crazy.shirodemo.project.bean.ItemVO;
import com.itaem.crazy.shirodemo.project.bean.LiPage;

/**
 * <p>
 * 储存商户上架的商品信息 服务类
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
public interface ItemService extends IService<ItemDO> {

    IPage<ItemVO> getAllItems(LiPage liPage, String shopId) throws MyException;

}
