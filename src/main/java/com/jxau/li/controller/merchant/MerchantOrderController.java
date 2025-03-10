package com.jxau.li.controller.merchant;

import com.jxau.li.common.result.CommonResp;
import com.jxau.li.model.resp.OrderResp;
import com.jxau.li.service.MerchantOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant/order")
public class MerchantOrderController {

    @Autowired
    private MerchantOrderService merchantOrderService;

    /**
     * 根据商家id，查询该商家的所有订单
     * @param id
     * @return
     */
    @GetMapping("/get")
    public CommonResp<List<OrderResp>> getMerchantOrder(@RequestParam Long id,@RequestParam(required = false) String searchQuery) {

        //根据id,查询用户订单
        List<OrderResp> orderRespList = merchantOrderService.getMerchantOrder(id,searchQuery);

        return new CommonResp<>(orderRespList);
    }
}
