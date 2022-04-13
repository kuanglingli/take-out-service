package com.itaem.crazy.shirodemo.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itaem.crazy.shirodemo.project.DO.CustDO;
import com.itaem.crazy.shirodemo.project.DO.ItemDO;
import com.itaem.crazy.shirodemo.project.DO.UserDO;
import com.itaem.crazy.shirodemo.project.DO.UserRoleDO;
import com.itaem.crazy.shirodemo.project.bean.CustVO;
import com.itaem.crazy.shirodemo.project.bean.LiPage;
import com.itaem.crazy.shirodemo.project.bean.Result;
import com.itaem.crazy.shirodemo.project.service.CustService;
import com.itaem.crazy.shirodemo.project.service.UserService;
import com.itaem.crazy.shirodemo.utils.UserUtils;
import com.itaem.crazy.shirodemo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户名 前端控制器
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/cust-do")
public class CustController {

    @Autowired
    private CustService custService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "custList" ,name = "用户列表查询")
    public Result<IPage<CustVO>> getCustList(@RequestBody @Validated LiPage liPage){
        Page<CustDO> page = new Page<>(liPage.getPage(),liPage.getSize());
        LambdaQueryWrapper<CustDO> custDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        custDOLambdaQueryWrapper.eq(CustDO::getDeleteYn,"0")
                .orderByDesc(CustDO::getCreateTime);
        IPage<CustDO> custDOIPage = custService.page(page,custDOLambdaQueryWrapper);
        IPage<CustVO> reselt = new Page<>();
        BeanUtils.copyProperties(custDOIPage,reselt);
        return new Result<IPage<CustVO>>("0","查询成功",reselt);
    }

    @PostMapping(value = "currentCust" ,name = "查询当前登录客户")
    public CustDO getCurrentCust(@RequestHeader("token") String token){
        return UserUtils.getCurrentCust(token);
    }

    @PostMapping(value = "register" ,name = "用户注册")
    public void register(@RequestBody @Validated CustVO custVO){
        custService.saveCust(custVO);
    }
}

