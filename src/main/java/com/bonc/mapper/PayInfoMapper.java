package com.bonc.mapper;

import com.bonc.common.BaseMapper;
import com.bonc.domain.PayInfo;

public interface PayInfoMapper extends BaseMapper<PayInfo> {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(PayInfo record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(PayInfo record);

    /**
     *
     * @mbggenerated
     */
    PayInfo selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PayInfo record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PayInfo record);
}