package com.jxau.li.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.mapper.ApplianceClassifyMapper;
import com.jxau.li.model.ApplianceCategories;
import com.jxau.li.model.req.ApplianceClassifyReq;
import com.jxau.li.model.resp.ApplianceClassifyResp;
import com.jxau.li.service.ApplianceClassifyService;
import com.jxau.li.utils.TimeSimple;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ApplianceClassifyServiceImpl extends ServiceImpl<ApplianceClassifyMapper, ApplianceCategories> implements ApplianceClassifyService {

    @Autowired
    private ApplianceClassifyMapper applianceClassifyMapper;

    @Override
    public List<ApplianceClassifyResp> get(String searchQuery) {

        /**
         * 如果条件不为空，则根据条件查询，否则查询所有
         */
        LambdaQueryWrapper<ApplianceCategories> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(searchQuery != null && !searchQuery.equals("")){
            lambdaQueryWrapper.like(ApplianceCategories::getDeviceType,searchQuery);
        }

        List<ApplianceCategories> applianceCategories = applianceClassifyMapper.selectList(lambdaQueryWrapper);
        List<ApplianceClassifyResp> applianceClassifyResps = BeanUtil.copyToList(applianceCategories, ApplianceClassifyResp.class);
        return applianceClassifyResps;
    }

    @Override
    public boolean add(ApplianceClassifyReq applianceClassifyReq) {
        ApplianceCategories applianceCategories = new ApplianceCategories();
        BeanUtils.copyProperties(applianceClassifyReq,applianceCategories);
        applianceCategories.setCreatedTime(TimeSimple.getTime());
        applianceCategories.setIsDelete("0");
        int row = applianceClassifyMapper.insert(applianceCategories);
        if (row>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(ApplianceClassifyReq applianceClassifyReq) {

        ApplianceCategories applianceCategories = new ApplianceCategories();
        BeanUtils.copyProperties(applianceClassifyReq,applianceCategories);
        applianceCategories.setCreatedTime(TimeSimple.getTime());
        applianceCategories.setIsDelete("0");
        int row = applianceClassifyMapper.updateById(applianceCategories);
        if (row>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer categoryId) {
        //更具id删除
        int row = applianceClassifyMapper.deleteById(categoryId);
        if (row>0){
            return true;
        }
        return false;
    }
}
