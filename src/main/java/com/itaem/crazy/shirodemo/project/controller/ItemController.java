package com.itaem.crazy.shirodemo.project.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.project.DO.CustDO;
import com.itaem.crazy.shirodemo.project.DO.ItemDO;
import com.itaem.crazy.shirodemo.project.DO.ShopDO;
import com.itaem.crazy.shirodemo.project.DO.UserRoleDO;
import com.itaem.crazy.shirodemo.project.bean.*;
import com.itaem.crazy.shirodemo.project.service.ItemService;
import com.itaem.crazy.shirodemo.project.service.UserRoleService;
import com.itaem.crazy.shirodemo.utils.UserUtils;
import com.itaem.crazy.shirodemo.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 储存商户上架的商品信息 前端控制器
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/item-do")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping(value = "/save",name = "新增商品")
    public void saveItem(@RequestHeader("token") String token, @RequestBody ItemVO itemVO){
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemVO,itemDO);
        String itemId = System.currentTimeMillis()+"";
        itemDO.setItemId(itemId);
        itemDO.setPrice(Utils.yuan2Fen(itemVO.getPrice()));
        itemDO.setDeleteYn("0");
        itemDO.setCreateId(UserUtils.getUserId(token));
        itemDO.setUpdateId(UserUtils.getUserId(token));
        itemDO.setCreateTime(LocalDateTime.now());
        itemDO.setUpdateTime(LocalDateTime.now());
        itemService.save(itemDO);
    }

    @RequestMapping(value = "/update",name = "更新商品")
    public void updateItem(@RequestHeader("token") String token, @RequestBody ItemVO itemVO){
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemVO,itemDO);
        itemDO.setPrice(Utils.yuan2Fen(itemVO.getPrice()));
        itemDO.setUpdateId(UserUtils.getUserId(token));
        itemDO.setUpdateTime(LocalDateTime.now());
        itemService.updateById(itemDO);
    }

    @PostMapping(value = "/delete",name = "删除商品")
    public void deleteItem(@RequestHeader("token") String token,@RequestBody String id) throws MyException {
        JSONObject json = JSONObject.parseObject(id);
        id = json.getString("id");
        if (StringUtils.isBlank(id)){
            throw new MyException("-9999","删除失败");
        }
        ItemDO itemDO = new ItemDO();
        itemDO.setId(Long.parseLong(id));
        itemDO.setDeleteYn("1");
        itemDO.setUpdateId(UserUtils.getUserId(token));
        itemDO.setUpdateTime(LocalDateTime.now());
        itemService.updateById(itemDO);
    }

    @PostMapping(value = "/page",name = "查询所属当前商户的商品列表")
    public Result<IPage<ItemVO>> getItemPage(@RequestHeader("token") String token,@RequestBody @Validated LiPage liPage) throws MyException {
        User user = UserUtils.getCurrentUser(token);
        LambdaQueryWrapper<UserRoleDO> userRoleDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userRoleDOLambdaQueryWrapper.eq(UserRoleDO::getUserId,user.getUserId())
                .eq(UserRoleDO::getRoleId,"1");
        List<UserRoleDO> userRoleDOS = userRoleService.list(userRoleDOLambdaQueryWrapper);
        String shopId = null;
        if (CollectionUtils.isEmpty(userRoleDOS)){
            ShopDO currentShop = UserUtils.getCurrentShop(token);
            if (currentShop != null){
                shopId = currentShop.getShopId();
            }
        }
        IPage<ItemVO> reselt = itemService.getAllItems(liPage,shopId);
        return new Result<IPage<ItemVO>>("0","查询成功",reselt);
    }

    @PostMapping(value = "/hotList",name = "查询热门商品")
    public Result<List<ItemVO>> getItemPage(@RequestBody(required=false) String shopId) throws MyException {
        if (StringUtils.isNotBlank(shopId)){
            JSONObject json = JSONObject.parseObject(shopId);
            shopId = json.getString("shopId");
        }
        LambdaQueryWrapper<ItemDO> itemDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        itemDOLambdaQueryWrapper.eq(ItemDO::getDeleteYn,"0")
                .eq(StringUtils.isNotBlank(shopId),ItemDO::getShopId,shopId)
                .orderByDesc(ItemDO::getSellCount);
        List<ItemDO> itemDOlist = itemService.list(itemDOLambdaQueryWrapper);
        List<ItemVO> list = new ArrayList<>();

        if (CollectionUtils.isEmpty(itemDOlist)){
            return new Result<List<ItemVO>>("0","查询成功",list);
        }

        for (ItemDO item:itemDOlist){
            item.setPrice(Utils.fen2Yuan(item.getPrice()));
            ItemVO itemVO = new ItemVO();
            BeanUtils.copyProperties(item,itemVO);
            list.add(itemVO);
        }
        return new Result<List<ItemVO>>("0","查询成功",list);
    }

}

