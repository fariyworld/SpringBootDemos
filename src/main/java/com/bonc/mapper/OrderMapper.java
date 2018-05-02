package com.bonc.mapper;

import com.bonc.common.BaseMapper;
import com.bonc.domain.Order;

public interface OrderMapper extends BaseMapper<Order> {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(Order record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Order record);

    /**
     *
     * @mbggenerated
     */
    Order selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Order record);
}