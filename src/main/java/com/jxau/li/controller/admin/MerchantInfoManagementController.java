package com.jxau.li.controller.admin;

import com.jxau.li.aspect.LogOperation;
import com.jxau.li.common.result.CommonResp;
import com.jxau.li.model.req.UserInfoReq;
import com.jxau.li.model.resp.UserInfoResp;
import com.jxau.li.service.MerchantInfoManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/merchantInfoManagement")

public class MerchantInfoManagementController {

    @Autowired
    private MerchantInfoManagementService merchantInfoManagementService;

    /**
     * 获取所有商家信息
     * @return
     */
    @GetMapping("/get")
    public CommonResp<List<UserInfoResp>> get(@RequestParam(required = false) String searchQuery){


        List<UserInfoResp> userInfoResps = merchantInfoManagementService.get(searchQuery);

        return new CommonResp<>(userInfoResps);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/getUserInfo/{id}")
    public CommonResp<UserInfoResp> getUserInfo(@PathVariable("id") Long id){

        UserInfoResp userInfoResp = merchantInfoManagementService.getByMerchantInfoId(id);

        return new CommonResp<>(userInfoResp);
    }

    /**
     * 启用禁用商家
     */
    @PostMapping("/update")
    @LogOperation(type ="管理员禁用/启用商家操作")
    public CommonResp<String> updateUserInfo(@RequestBody UserInfoReq userInfoReq){
        /**
         * 更新用户状态
         */
        boolean is_success = merchantInfoManagementService.updatemerchantInfoStatus(userInfoReq.getId(),userInfoReq.getIsActived());
        if(is_success){
            return new CommonResp<>("更新成功");
        }else {
            return new CommonResp<>("更新失败");
        }
    }
}
