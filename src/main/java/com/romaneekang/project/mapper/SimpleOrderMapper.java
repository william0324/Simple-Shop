package com.romaneekang.project.mapper;

import com.romaneekang.project.pojo.SimpleOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author weika
* @description 针对表【simple_order】的数据库操作Mapper
* @createDate 2024-06-15 08:59:20
* @Entity com.romaneekang.mp.pojo.SimpleOrder
*/
public interface SimpleOrderMapper extends BaseMapper<SimpleOrder> {
    /**
     * 根据订单id查询订单，在执行查询时锁定行,以防止其他事务对这些行进行更改
     * @param orderId   订单id
     * @return 订单数据
     */
    SimpleOrder selectByOrderIdForUpdate(String orderId);
}




