package com.jxau.li.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.Order;
import com.jxau.li.model.resp.OrderResp;

import java.util.List;

public interface AdminOrderService extends IService<Order> {
    /**
     * 查询所有订单
     * @return
     */
    List<OrderResp> getOrder(String searchQuery);
}
