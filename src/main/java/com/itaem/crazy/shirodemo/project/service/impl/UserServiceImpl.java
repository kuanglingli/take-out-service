package com.itaem.crazy.shirodemo.project.service.impl;

import com.itaem.crazy.shirodemo.project.DO.UserDO;
import com.itaem.crazy.shirodemo.project.mapper.UserMapper;
import com.itaem.crazy.shirodemo.project.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}
