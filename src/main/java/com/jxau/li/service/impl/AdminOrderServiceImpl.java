package com.jxau.li.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.mapper.AdminOrderMapper;
import com.jxau.li.model.Order;
import com.jxau.li.model.resp.OrderResp;
import com.jxau.li.model.resp.UserInfoResp;
import com.jxau.li.service.AdminOrderService;
import com.jxau.li.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminOrderServiceImpl extends ServiceImpl<AdminOrderMapper, Order> implements AdminOrderService {

    @Autowired
    private AdminOrderMapper adminOrderMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public List<OrderResp> getOrder(String searchQuery) {
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Order> orders = null;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            orderLambdaQueryWrapper.like(Order::getOrderedNo, searchQuery);
            orders = adminOrderMapper.selectList(orderLambdaQueryWrapper);
        }else{
            orders = adminOrderMapper.selectList(null);
        }

        //返回
//        List<OrderResp> orderResps = BeanUtil.copyToList(orders, OrderResp.class);
        List<OrderResp> orderResps = new ArrayList<>();
        for (Order o: orders) {
            long userId = o.getUserId();
            long merchantId = o.getMerchantId();
            //根据用户id和商家id查询用户信息和商家信息
            UserInfoResp userInfoResp = userInfoService.get(userId);
            UserInfoResp merchantInfoResp = userInfoService.get(merchantId);
            OrderResp orderResp = new OrderResp();
            BeanUtil.copyProperties(o,orderResp,"usersName","merchantsName");
            orderResp.setUsersName(userInfoResp.getName());
            orderResp.setMerchantsName(merchantInfoResp.getName());
            orderResps.add(orderResp);
        }
        return orderResps;

    }
}
