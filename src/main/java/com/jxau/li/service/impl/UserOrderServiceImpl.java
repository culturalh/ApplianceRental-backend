package com.jxau.li.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.mapper.UserOrderMapper;
import com.jxau.li.model.Order;
import com.jxau.li.model.req.OrderReq;
import com.jxau.li.model.resp.OrderResp;
import com.jxau.li.model.resp.UserInfoResp;
import com.jxau.li.service.UserInfoService;
import com.jxau.li.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, Order> implements UserOrderService {

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public List<OrderResp> getUserOrder(Long id,String searchQuery) {

        Order order = new Order();

        order.setUserId(id);

        //查询用户订单信息
        LambdaQueryWrapper<Order> orderLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderLambdaQueryWrapper.eq(Order::getUserId, id);

        //如果搜索条件不为空，则根据搜索条件查询订单信息
        if (searchQuery != null && !searchQuery.isEmpty()) {
            orderLambdaQueryWrapper.like(Order::getOrderedNo, searchQuery);
        }
        List<Order> orders = userOrderMapper.selectList(orderLambdaQueryWrapper);
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
//        返回
//        List<OrderResp> orderResps = BeanUtil.copyToList(orders, OrderResp.class);

        return orderResps;
    }
}
