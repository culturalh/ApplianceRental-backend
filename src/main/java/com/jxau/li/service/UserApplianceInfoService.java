package com.jxau.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.ApplianceInfo;
import com.jxau.li.model.req.IsOrderedReq;
import com.jxau.li.model.resp.ApplianceInfoResp;
import com.jxau.li.model.resp.UserInfoResp;

import java.util.List;

public interface UserApplianceInfoService extends IService<ApplianceInfo> {

    /**
     * 根据条件查询用户信息(查询上架已上架，用户未下单的加点信息)
     * @param searchQuery
     * @return
     */
    List<ApplianceInfoResp> get(String searchQuery);

    /**
     * 下单操作
     * @param isOrderedReq
     * @return
     */
    boolean orderedApplianceInfo(IsOrderedReq isOrderedReq);
}
