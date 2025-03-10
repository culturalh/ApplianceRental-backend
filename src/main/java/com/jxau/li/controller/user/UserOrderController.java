package com.jxau.li.controller.user;

import cn.hutool.system.UserInfo;
import com.jxau.li.aspect.LogOperation;
import com.jxau.li.common.result.CommonResp;
import com.jxau.li.model.resp.OrderResp;
import com.jxau.li.service.MerchantInfoManagementService;
import com.jxau.li.service.UserInfoService;
import com.jxau.li.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/order")
public class UserOrderController {

    @Autowired
    private UserOrderService userOrderService;

    /**
     * 更具用户id，查询该用户的所有订单
     * @param id
     * @return
     */
    @GetMapping("/get")
    public CommonResp<List<OrderResp>> getUserOrder(@RequestParam Long id,@RequestParam(required =false) String searchQuery) {

        //更具id,查询用户订单
        List<OrderResp> orderRespList = userOrderService.getUserOrder(id, searchQuery);

        return new CommonResp<>(orderRespList);
    }
}
