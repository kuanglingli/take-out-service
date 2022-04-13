package com.itaem.crazy.shirodemo.project.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.modules.shiro.entity.User;
import com.itaem.crazy.shirodemo.project.DO.UserDO;
import com.itaem.crazy.shirodemo.project.bean.PasswordUp;
import com.itaem.crazy.shirodemo.project.service.UserService;
import com.itaem.crazy.shirodemo.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/user-do")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/updatePw",name = "修改密码")
    public void updatePw(@RequestHeader("token") String token, @RequestBody PasswordUp passwordVO) throws MyException {
        User user = UserUtils.getCurrentUser(token);
        if (StringUtils.isBlank(passwordVO.getOldPassword())||!passwordVO.getOldPassword().equals(user.getPassword())){
            throw new MyException("-9999","请输入正确登录密码");
        }
        LambdaUpdateWrapper<UserDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserDO::getUsername,user.getUsername())
                .eq(UserDO::getPassword,passwordVO.getOldPassword())
                .set(UserDO::getPassword,passwordVO.getPassword());
        userService.update(updateWrapper);
    }

}

