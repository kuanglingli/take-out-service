package com.itaem.crazy.shirodemo.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itaem.crazy.shirodemo.project.DO.CustDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itaem.crazy.shirodemo.project.bean.CustVO;

/**
 * <p>
 * 用户名 服务类
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
public interface CustService extends IService<CustDO> {

    IPage<CustDO> getCustPage(Page<CustDO> page);

    Boolean saveCust(CustVO custVO);

}
