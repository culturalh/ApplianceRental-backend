package com.jxau.li.controller.admin;

import com.jxau.li.aspect.LogOperation;
import com.jxau.li.common.result.CommonResp;
import com.jxau.li.model.resp.OrderResp;
import com.jxau.li.service.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")

public class AdminOrderController {


    @Autowired
    private AdminOrderService adminOrderService;

    /**
     * 根据商家id，查询所有订单详情
     * @param
     * @return
     */
    @GetMapping("/get")
    public CommonResp<List<OrderResp>> getOrder(@RequestParam(required = false) String searchQuery) {

        //更具id,查询订单
        List<OrderResp> orderRespList = adminOrderService.getOrder(searchQuery);

        return new CommonResp<>(orderRespList);
    }

}
