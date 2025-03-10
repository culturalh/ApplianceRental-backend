package com.jxau.li.controller.user;

import com.jxau.li.aspect.LogOperation;
import com.jxau.li.common.result.CommonResp;
import com.jxau.li.model.req.ApplianceInfoReq;
import com.jxau.li.model.req.IsOrderedReq;
import com.jxau.li.model.resp.ApplianceInfoResp;
import com.jxau.li.model.resp.UserInfoResp;
import com.jxau.li.service.UserApplianceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/applianceInfo")
public class UserApplianceInfoController {


    @Autowired
    private UserApplianceInfoService userApplianceInfoService;

    /**
     * 查看家电信息
     * @param searchQuery
     * @return
     */
    @GetMapping("/get")
    public CommonResp<List<ApplianceInfoResp>> getUserApplianceInfo(@RequestParam(required = false) String searchQuery){

        List<ApplianceInfoResp> userInfoResps = userApplianceInfoService.get(searchQuery);
        return new CommonResp<>(userInfoResps);
    }

    @LogOperation(type ="用户下单操作")
    @PostMapping("/ordered")
    public CommonResp<String> orderedApplianceInfo(@RequestBody IsOrderedReq isOrderedReq){

        boolean is_success = userApplianceInfoService.orderedApplianceInfo(isOrderedReq);
        if(is_success){
            return new CommonResp<String>("下单成功");
        } else{
            return new CommonResp<>("下单失败");
        }
    }


}
