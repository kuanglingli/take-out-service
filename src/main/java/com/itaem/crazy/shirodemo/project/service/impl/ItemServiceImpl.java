package com.itaem.crazy.shirodemo.project.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.project.DO.ItemDO;
import com.itaem.crazy.shirodemo.project.bean.ItemVO;
import com.itaem.crazy.shirodemo.project.bean.LiPage;
import com.itaem.crazy.shirodemo.project.mapper.ItemMapper;
import com.itaem.crazy.shirodemo.project.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itaem.crazy.shirodemo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 储存商户上架的商品信息 服务实现类
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, ItemDO> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public IPage<ItemVO> getAllItems(LiPage liPage, String shopId) throws MyException {
        Page<ItemVO> page = new Page<>(liPage.getPage(),liPage.getSize(),itemMapper.getAllItemsCount(shopId),false);
        IPage<ItemVO> result = itemMapper.getAllItems(page,shopId);
        List<ItemVO> itemVOList = result.getRecords();
        if (CollectionUtils.isEmpty(itemVOList)){
            return result;
        }
        for (ItemVO itemVO:itemVOList){
            itemVO.setPrice(Utils.fen2Yuan(itemVO.getPrice()));
        }
        return result;
    }
}
