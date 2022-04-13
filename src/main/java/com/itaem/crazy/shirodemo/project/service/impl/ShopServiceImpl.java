package com.itaem.crazy.shirodemo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.project.DO.ShopDO;
import com.itaem.crazy.shirodemo.project.DO.ShopDO;
import com.itaem.crazy.shirodemo.project.DO.UserDO;
import com.itaem.crazy.shirodemo.project.DO.UserRoleDO;
import com.itaem.crazy.shirodemo.project.bean.ShopVO;
import com.itaem.crazy.shirodemo.project.mapper.ShopMapper;
import com.itaem.crazy.shirodemo.project.mapper.UserMapper;
import com.itaem.crazy.shirodemo.project.mapper.UserRoleMapper;
import com.itaem.crazy.shirodemo.project.service.ShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itaem.crazy.shirodemo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 存储商户信息及管理员信息的表 服务实现类
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, ShopDO> implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Boolean saveShop(ShopVO shopVO) {
        try {
            LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserDO::getUsername,shopVO.getUserName());
            UserDO user = userMapper.selectOne(wrapper);
            if (user!=null){
                throw new MyException("-9999","用户名重复");
            }
            UserDO userDO = new UserDO();
            Integer userId = Integer.parseInt(Utils.getId());
            BeanUtils.copyProperties(shopVO,userDO);
            userDO.setUsername(shopVO.getUserName());
            userDO.setUserId(userId);
            userMapper.insert(userDO);
            UserRoleDO userRoleDO = new UserRoleDO();
            userRoleDO.setUserId(userId);
            userRoleDO.setRoleId(2);
            userRoleMapper.insert(userRoleDO);
            ShopDO shopDO = new ShopDO();
            BeanUtils.copyProperties(shopVO,shopDO);
            String shopId = System.currentTimeMillis()+"";
            shopDO.setShopId(shopId);
            shopDO.setUserType("1");
            shopDO.setDeleteYn("0");
            shopDO.setIsEffect("0");
            shopDO.setCreateId(userId+"");
            shopDO.setUpdateId(userId+"");
            shopDO.setCreateTime(LocalDateTime.now());
            shopDO.setUpdateTime(LocalDateTime.now());
            shopMapper.insert(shopDO);

            return true;
        }catch (Exception e){
            log.error("用户注册失败",e);
            return false;
        }
    }
}
