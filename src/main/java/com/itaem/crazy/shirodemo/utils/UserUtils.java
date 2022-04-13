package com.itaem.crazy.shirodemo.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itaem.crazy.shirodemo.modules.shiro.entity.SysToken;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.modules.shiro.service.ShiroService;
import com.itaem.crazy.shirodemo.project.DO.CustDO;
import com.itaem.crazy.shirodemo.project.DO.ShopDO;
import com.itaem.crazy.shirodemo.project.service.CustService;
import com.itaem.crazy.shirodemo.project.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserUtils {

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CustService custService;

    private static UserUtils userUtils;

    @PostConstruct
    public void init(){
        userUtils = this;
        userUtils.shiroService = this.shiroService;
        userUtils.custService = this.custService;
        userUtils.shopService = this.shopService;
    }

    public static String getUserId(String token){
        SysToken sysToken = userUtils.shiroService.findByToken(token);
        return sysToken.getUserId()+"";
    }

    public static User getCurrentUser(String token){
        SysToken sysToken = userUtils.shiroService.findByToken(token);
        if (sysToken == null){
            return null;
        }
        User user = userUtils.shiroService.findByUserId(sysToken.getUserId());
        return user;
    }

    public static ShopDO getCurrentShop(String token){
        SysToken sysToken = userUtils.shiroService.findByToken(token);
        if (sysToken == null){
            return null;
        }
        User user = userUtils.shiroService.findByUserId(sysToken.getUserId());
        if (user == null){
            return null;
        }
        LambdaQueryWrapper<ShopDO> shopDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shopDOLambdaQueryWrapper.eq(ShopDO::getUserName,user.getUsername())
                .eq(ShopDO::getIsEffect,"1");
        return userUtils.shopService.getOne(shopDOLambdaQueryWrapper);
    }

    public static CustDO getCurrentCust(String token){
        SysToken sysToken = userUtils.shiroService.findByToken(token);
        if (sysToken == null){
            return null;
        }
        User user = userUtils.shiroService.findByUserId(sysToken.getUserId());
        if (user == null){
            return null;
        }
        LambdaQueryWrapper<CustDO> custDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        custDOLambdaQueryWrapper.eq(CustDO::getUserName,user.getUsername())
                .eq(CustDO::getDeleteYn,"0");
        return userUtils.custService.getOne(custDOLambdaQueryWrapper);
    }
}
