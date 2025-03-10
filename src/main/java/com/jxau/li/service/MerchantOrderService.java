package com.jxau.li.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.Order;
import com.jxau.li.model.resp.OrderResp;

import java.util.List;

public interface MerchantOrderService extends IService<Order> {

    /**
     * 获取商户订单
     * @param id
     * @return
     */
    List<OrderResp> getMerchantOrder(Long id,String searchQuery);
}
