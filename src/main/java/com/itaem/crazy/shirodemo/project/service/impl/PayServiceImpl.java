package com.itaem.crazy.shirodemo.project.service.impl;

import com.itaem.crazy.shirodemo.project.DO.PayDO;
import com.itaem.crazy.shirodemo.project.mapper.PayMapper;
import com.itaem.crazy.shirodemo.project.service.PayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付表 服务实现类
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, PayDO> implements PayService {

}
