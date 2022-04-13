package com.itaem.crazy.shirodemo.modules.shiro.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itaem.crazy.shirodemo.common.utils.TokenUtil;
import com.itaem.crazy.shirodemo.modules.shiro.dto.LoginDTO;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.modules.shiro.service.ShiroService;
import com.itaem.crazy.shirodemo.project.DO.CustDO;
import com.itaem.crazy.shirodemo.project.DO.ShopDO;
import com.itaem.crazy.shirodemo.project.DO.UserRoleDO;
import com.itaem.crazy.shirodemo.project.service.UserRoleService;
import com.itaem.crazy.shirodemo.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author 大誌
 * @Date 2019/3/30 22:04
 * @Version 1.0
 */
@Api(tags = "Shiro权限管理")
@RestController
public class ShiroController {

    @Autowired
    private UserRoleService userRoleService;

    private final ShiroService shiroService;

    public ShiroController(ShiroService shiroService) {
        this.shiroService = shiroService;
    }


    /**
     * 登录
     */
    @ApiOperation(value = "登陆", notes = "参数:用户名 密码")
    @PostMapping("/sys/login")
    public Map<String, Object> login(@RequestBody @Validated LoginDTO loginDTO, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        if (bindingResult.hasErrors()) {
            result.put("status", 400);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        }

        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        //用户信息
        User user = shiroService.findByUsername(username);
        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(password)) {
            result.put("status", 400);
            result.put("msg", "账号或密码有误");
        } else {
            //生成token，并保存到数据库
            result = shiroService.createToken(user.getUserId());
            result.put("status", 200);
            result.put("msg", "登陆成功");
            result.put("userName",username);
            CustDO custDO = UserUtils.getCurrentCust(result.get("token").toString());
            ShopDO shopDO = UserUtils.getCurrentShop(result.get("token").toString());
            LambdaQueryWrapper<UserRoleDO> userRoleDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userRoleDOLambdaQueryWrapper.eq(UserRoleDO::getUserId,user.getUserId())
                    .eq(UserRoleDO::getRoleId,"1");
            List<UserRoleDO> userRoleDOS = userRoleService.list(userRoleDOLambdaQueryWrapper);
            if (custDO!=null){
                result.put("type","3");
            }else if (shopDO!=null){
                result.put("type","2");
            }else if (!CollectionUtils.isEmpty(userRoleDOS)){
                result.put("type","1");
            }else {
                result = new HashMap<>();
                result.put("status", -9999);
                result.put("msg", "登录失败，账号未生效");
            }
        }
        return result;
    }

    /**
     * 退出
     */
    @ApiOperation(value = "登出", notes = "参数:token")
    @PostMapping("/sys/logout")
    public Map<String, Object> logout(@RequestHeader("token")String token) {
        Map<String, Object> result = new HashMap<>();
        shiroService.logout(token);
        result.put("status", 200);
        result.put("msg", "您已安全退出系统");
        return result;
    }
}


