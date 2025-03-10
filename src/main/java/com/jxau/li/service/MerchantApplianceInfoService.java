package com.jxau.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.ApplianceInfo;
import com.jxau.li.model.req.ApplianceInfoReq;
import com.jxau.li.model.resp.ApplianceInfoResp;

import java.util.List;

public interface MerchantApplianceInfoService extends IService<ApplianceInfo> {

    /**
     * 根据条件查询,条件为null，查询所有
     * @param searchQuery
     * @return
     */
    List<ApplianceInfoResp> get(String searchQuery,long merchantId);

    /**
     * 添加
     * @param applianceInfoReq
     * @return
     */
    boolean add(ApplianceInfoReq applianceInfoReq);

    /**
     * 修改
     * @param applianceInfoReq
     * @return
     */
    boolean update(ApplianceInfoReq applianceInfoReq);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(long id, String status);
}
