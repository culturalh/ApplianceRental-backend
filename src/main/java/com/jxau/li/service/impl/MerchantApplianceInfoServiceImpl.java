package com.jxau.li.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.enums.RoleEnum;
import com.jxau.li.mapper.MerchantApplianceInfoMapper;
import com.jxau.li.model.ApplianceInfo;
import com.jxau.li.model.User;
import com.jxau.li.model.req.ApplianceInfoReq;
import com.jxau.li.model.resp.ApplianceInfoResp;
import com.jxau.li.model.resp.UserInfoResp;
import com.jxau.li.service.MerchantApplianceInfoService;
import com.jxau.li.utils.SnowUtil;
import com.jxau.li.utils.TimeSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantApplianceInfoServiceImpl extends ServiceImpl<MerchantApplianceInfoMapper, ApplianceInfo> implements MerchantApplianceInfoService {

    @Autowired
    private MerchantApplianceInfoMapper merchantApplianceInfoMapper;

    @Override
    public List<ApplianceInfoResp> get(String searchQuery,long merchantId) {
        //查询所有商家信息
        LambdaQueryWrapper<ApplianceInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(User::getRole, RoleEnum.ROLE_MERCHANT.getCODE());
//        lambdaQueryWrapper.like(ApplianceInfo::getAppliance_name, searchQuery);
        if(searchQuery != null){
            lambdaQueryWrapper.like(ApplianceInfo::getApplianceName,searchQuery);
        }
        lambdaQueryWrapper.eq(ApplianceInfo::getMerchantId,merchantId);
        List<ApplianceInfo> applianceInfos = merchantApplianceInfoMapper.selectList(lambdaQueryWrapper);
        List<ApplianceInfoResp> applianceInfoResps = BeanUtil.copyToList(applianceInfos, ApplianceInfoResp.class);
        return applianceInfoResps;
    }

    @Override
    public boolean add(ApplianceInfoReq applianceInfoReq) {

        ApplianceInfo applianceInfo = new ApplianceInfo();
        BeanUtil.copyProperties(applianceInfoReq,applianceInfo);
        applianceInfo.setId(SnowUtil.getSnowflakeNextId());//唯一主键，可以当作家电编号使用
        applianceInfo.setCreateTime(TimeSimple.getTime());
        applianceInfo.setStatus("1");//默认上架状态
        applianceInfo.setIsOrdered("0");
        int row = merchantApplianceInfoMapper.insert(applianceInfo);
        if (row>0){
            return true;
        }else {
         throw new RuntimeException("添加失败");
        }
    }

    @Override
    public boolean update(ApplianceInfoReq applianceInfoReq) {
        ApplianceInfo applianceInfo = new ApplianceInfo();
        BeanUtil.copyProperties(applianceInfoReq,applianceInfo);
        applianceInfo.setCreateTime(TimeSimple.getTime());
        int row = merchantApplianceInfoMapper.updateById(applianceInfo);
        if (row>0){
            return true;
        }else {
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public boolean delete(long id) {
        int row = merchantApplianceInfoMapper.deleteById(id);
        if (row>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStatus(long id, String status) {

       int row =  merchantApplianceInfoMapper.updateStatus(id,status);
        if (row>0){
            return true;
        }else{
            return false;
        }
    }
}
