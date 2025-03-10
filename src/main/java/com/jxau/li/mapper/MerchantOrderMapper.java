package com.jxau.li.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxau.li.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MerchantOrderMapper extends BaseMapper<Order> {
}
