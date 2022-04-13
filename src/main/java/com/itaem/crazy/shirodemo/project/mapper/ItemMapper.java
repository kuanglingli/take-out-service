package com.itaem.crazy.shirodemo.project.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itaem.crazy.shirodemo.project.DO.ItemDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itaem.crazy.shirodemo.project.bean.ItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 储存商户上架的商品信息 Mapper 接口
 * </p>
 *
 * @author Li Chaozhu
 * @since 2022-03-28
 */
@Repository
public interface ItemMapper extends BaseMapper<ItemDO> {

    IPage<ItemVO> getAllItems(Page<ItemVO> page,@Param("shopId") String shopId);

    Integer getAllItemsCount(@Param("shopId") String shopId);
}
