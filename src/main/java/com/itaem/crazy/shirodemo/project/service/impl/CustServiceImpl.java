package com.itaem.crazy.shirodemo.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itaem.crazy.shirodemo.Exception.MyException;
import com.itaem.crazy.shirodemo.project.DO.CustDO;
import com.itaem.crazy.shirodemo.project.DO.UserDO;
import com.itaem.crazy.shirodemo.project.DO.UserRoleDO;
import com.itaem.crazy.shirodemo.project.bean.CustVO;
import com.itaem.crazy.shirodemo.project.mapper.CustMapper;
import com.itaem.crazy.shirodemo.project.mapper.UserMapper;
import com.itaem.crazy.shirodemo.project.mapper.UserRoleMapper;
import com.itaem.crazy.shirodemo.project.service.CustService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itaem.crazy.shirodemo.project.service.UserRoleService;
import com.itaem.crazy.shirodemo.project.service.UserService;
import com.itaem.crazy.shirodemo.utils.Utils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户名 服务实现类
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Transactional
@Service
public class CustServiceImpl extends ServiceImpl<CustMapper, CustDO> implements CustService {

    @Autowired
    private CustMapper custMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public IPage<CustDO> getCustPage(Page<CustDO> page) {
        return null;
    }

    @SneakyThrows
    @Override
    public Boolean saveCust(CustVO custVO) {
        try {
            LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserDO::getUsername,custVO.getUserName());
            UserDO user = userMapper.selectOne(wrapper);
            if (user!=null){
                throw new MyException("-9999","用户名重复");
            }
            UserDO userDO = new UserDO();
            Integer userId = Integer.parseInt(Utils.getId());
            BeanUtils.copyProperties(custVO,userDO);
            userDO.setUsername(custVO.getUserName());
            userDO.setUserId(userId);
            userMapper.insert(userDO);
            UserRoleDO userRoleDO = new UserRoleDO();
            userRoleDO.setUserId(userId);
            userRoleDO.setRoleId(3);
            userRoleMapper.insert(userRoleDO);
            CustDO custDO = new CustDO();
            BeanUtils.copyProperties(custVO,custDO);
            String custId = System.currentTimeMillis()+"";
            custDO.setCustId(userId+"");
            custDO.setRegistTime(LocalDateTime.now());
            custDO.setDeleteYn("0");
            custDO.setCreateId(userId+"");
            custDO.setUpdateId(userId+"");
            custDO.setCreateTime(LocalDateTime.now());
            custDO.setUpdateTime(LocalDateTime.now());
            custMapper.insert(custDO);
            return true;
        }catch (Exception e){
            log.error("用户注册失败",e);
            throw new MyException("-9999","注册失败");
        }

    }
}
