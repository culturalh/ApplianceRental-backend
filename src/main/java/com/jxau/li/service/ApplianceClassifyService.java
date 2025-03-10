package com.jxau.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.ApplianceCategories;
import com.jxau.li.model.req.ApplianceClassifyReq;
import com.jxau.li.model.resp.ApplianceClassifyResp;

import java.util.List;

public interface ApplianceClassifyService extends IService<ApplianceCategories> {

    /**
     * 获取所有分类
     * @param searchQuery
     * @return
     */
    List<ApplianceClassifyResp> get(String searchQuery);


    /**
     * 添加分类
     * @param applianceClassifyReq
     * @return
     */
    boolean add(ApplianceClassifyReq applianceClassifyReq);

    /**
     * 修改分类
     * @param applianceClassifyReq
     * @return
     */
    boolean update(ApplianceClassifyReq applianceClassifyReq);

    /**
     * 删除分类
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据状态查询分类
     * @return
     */
    List<ApplianceClassifyResp> getByStatus();
}
