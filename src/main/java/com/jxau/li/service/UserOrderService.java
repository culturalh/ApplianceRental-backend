package com.jxau.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.Order;
import com.jxau.li.model.resp.OrderResp;

import java.util.List;


public interface UserOrderService extends IService<Order> {
    /**
     * 根据id 查询用户订单
     * @param id
     * @return
     */
    List<OrderResp> getUserOrder(Long id,String searchQuery);
}
