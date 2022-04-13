package com.itaem.crazy.shirodemo.project.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.project.DO.*;
import com.itaem.crazy.shirodemo.project.bean.ItemVO;
import com.itaem.crazy.shirodemo.project.bean.LiPage;
import com.itaem.crazy.shirodemo.project.bean.Result;
import com.itaem.crazy.shirodemo.project.service.AddressService;
import com.itaem.crazy.shirodemo.utils.UserUtils;
import com.itaem.crazy.shirodemo.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 客户地址表 前端控制器
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/address-do")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(value = "/page",name = "查询所属当前客户的地址列表")
    public Result<IPage<AddressDO>> getAddressPage(@RequestHeader("token") String token, @RequestBody @Validated LiPage liPage) throws MyException {
        CustDO custDO = UserUtils.getCurrentCust(token);
        LambdaQueryWrapper<AddressDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressDO::getDeleteYn,"0")
                .eq(AddressDO::getCustId,custDO.getCustId());
        Page<AddressDO> page = new Page<>(liPage.getPage(),liPage.getSize());
        return new Result<IPage<AddressDO>>("0","查询成功",addressService.page(page,queryWrapper));
    }

    @PostMapping(value = "/list",name = "查询所属当前客户的地址列表")
    public Result<List<AddressDO>> getAddressList(@RequestHeader("token") String token) throws MyException {
        CustDO custDO = UserUtils.getCurrentCust(token);
        LambdaQueryWrapper<AddressDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressDO::getDeleteYn,"0")
                .eq(AddressDO::getCustId,custDO.getCustId());
        return new Result<List<AddressDO>>("0","查询成功",addressService.list(queryWrapper));
    }

    @RequestMapping(value = "/save",name = "新增地址")
    public void saveItem(@RequestHeader("token") String token, @RequestBody AddressDO addressDO){
        CustDO custDO = UserUtils.getCurrentCust(token);
        addressDO.setCustId(custDO.getCustId());
        addressDO.setDeleteYn("0");
        addressDO.setCreateId(UserUtils.getUserId(token));
        addressDO.setUpdateId(UserUtils.getUserId(token));
        addressDO.setCreateTime(LocalDateTime.now());
        addressDO.setUpdateTime(LocalDateTime.now());
        addressService.save(addressDO);
    }

    @RequestMapping(value = "/update",name = "更新地址")
    public void updateItem(@RequestHeader("token") String token, @RequestBody AddressDO addressDO){

        addressDO.setUpdateId(UserUtils.getUserId(token));
        addressDO.setUpdateTime(LocalDateTime.now());
        addressService.updateById(addressDO);
    }

    @PostMapping(value = "/delete",name = "删除地址")
    public void deleteItem(@RequestHeader("token") String token,@RequestBody String id) throws MyException {
        JSONObject json = JSONObject.parseObject(id);
        id = json.getString("id");
        if (StringUtils.isBlank(id)){
            throw new MyException("-9999","删除失败");
        }
        AddressDO addressDO = new AddressDO();
        addressDO.setId(Long.parseLong(id));
        addressDO.setDeleteYn("1");
        addressDO.setUpdateId(UserUtils.getUserId(token));
        addressDO.setUpdateTime(LocalDateTime.now());
        addressService.updateById(addressDO);
    }

}

