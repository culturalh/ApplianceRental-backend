package com.jxau.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.User;
import com.jxau.li.model.resp.UserInfoResp;

import java.util.List;

public interface MerchantInfoManagementService extends IService<User> {

    /**
     * 获取所有商户信息
     * @return
     */
    List<UserInfoResp> get(String searchQuery);

    /**
     * 根据商户id获取商户详细信息
     * @param id
     * @return
     */
    UserInfoResp getByMerchantInfoId(Long id);


    /**
     * 更新商家状态
     * @param id
     * @param status
     * @return
     */
    boolean updatemerchantInfoStatus(Long id, String status);


}
