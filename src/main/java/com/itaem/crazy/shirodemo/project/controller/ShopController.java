package com.itaem.crazy.shirodemo.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.modules.shiro.entity.SysToken;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.modules.shiro.service.ShiroService;
import com.itaem.crazy.shirodemo.project.DO.ShopDO;
import com.itaem.crazy.shirodemo.project.DO.ShopDO;
import com.itaem.crazy.shirodemo.project.bean.CustVO;
import com.itaem.crazy.shirodemo.project.bean.LiPage;
import com.itaem.crazy.shirodemo.project.bean.Result;
import com.itaem.crazy.shirodemo.project.bean.ShopVO;
import com.itaem.crazy.shirodemo.project.service.ShopService;
import com.itaem.crazy.shirodemo.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 存储商户信息及管理员信息的表 前端控制器
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/shop-do")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShiroService shiroService;

    @PostMapping(value = "shopList" ,name = "商户列表查询")
    public Result<IPage<ShopVO>> getShopList(@RequestBody @Validated LiPage liPage){
        Page<ShopDO> page = new Page<>(liPage.getPage(),liPage.getSize());
        LambdaQueryWrapper<ShopDO> shopDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shopDOLambdaQueryWrapper.eq(ShopDO::getDeleteYn,"0")
                .orderByDesc(ShopDO::getCreateTime);
        IPage<ShopDO> shopDOIPage = shopService.page(page,shopDOLambdaQueryWrapper);
        IPage<ShopVO> result = new Page<>();
        BeanUtils.copyProperties(shopDOIPage,result);
        List<ShopVO> shopVOS = new ArrayList<>();
        List<ShopDO> list = shopDOIPage.getRecords();
        for (ShopDO shopDO:list){
            ShopVO shopVO = new ShopVO();
            BeanUtils.copyProperties(shopDO,shopVO);
            if (shopVO.getIsEffect().equals("0")){
                shopVO.setIsEffectName("失效");
            }else{
                shopVO.setIsEffectName("生效");
            }
            shopVOS.add(shopVO);
        }
        result.setRecords(shopVOS);
        return new Result<IPage<ShopVO>>("0","查询成功",result);
    }

    @PostMapping(value = "update",name = "商户信息更新")
    public Result<ShopVO> updateShop(@RequestBody ShopVO shopVO){
        ShopDO shopDO = new ShopDO();
        BeanUtils.copyProperties(shopVO,shopDO);
        if (shopService.updateById(shopDO)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PostMapping(value = "setEffect",name = "商户信息更新")
    public Result<ShopVO> setEffect(@RequestBody ShopVO shopVO){
        ShopDO shopDO = new ShopDO();
        BeanUtils.copyProperties(shopVO,shopDO);
        if (shopService.updateById(shopDO)){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    @PostMapping(value = "currentShop",name = "当前登录商户查询")
    public Result getCurrentShop(@RequestHeader("token") String token){
        if (StringUtils.isBlank(token)){
            return Result.fail();
        }
        SysToken sysToken = shiroService.findByToken(token);
        if (sysToken==null){
            return Result.fail();
        }
        User user = shiroService.findByUserId(sysToken.getUserId());
        if (user==null){
            return Result.fail();
        }
        LambdaQueryWrapper<ShopDO> shopDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shopDOLambdaQueryWrapper.eq(ShopDO::getUserName,user.getUsername())
                .eq(ShopDO::getIsEffect,"1");
        ShopVO shopVO = new ShopVO();
        ShopDO shopDO = shopService.getOne(shopDOLambdaQueryWrapper);
        if (shopDO==null){
            return Result.successAndData(null);
        }
        BeanUtils.copyProperties(shopDO,shopVO);
        return Result.successAndData(shopVO);
    }

    @PostMapping(value = "/hotList",name = "查询热门商品")
    public Result<List<ShopDO>> getItemPage() throws MyException {
        LambdaQueryWrapper<ShopDO> ShopDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ShopDOLambdaQueryWrapper.eq(ShopDO::getIsEffect,"1")
                .orderByDesc(ShopDO::getSellCount);
        List<ShopDO> list = shopService.list(ShopDOLambdaQueryWrapper);
        return new Result<List<ShopDO>>("0","查询成功",list);
    }

    @PostMapping(value = "register" ,name = "商户注册")
    public void register(@RequestBody @Validated ShopVO shopVO){
        shopService.saveShop(shopVO);
    }
}

