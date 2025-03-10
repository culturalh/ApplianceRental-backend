package com.jxau.li.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.mapper.MerchantApplianceInfoMapper;
import com.jxau.li.mapper.UserApplianceInfoMapper;
import com.jxau.li.mapper.UserOrderMapper;
import com.jxau.li.model.ApplianceInfo;
import com.jxau.li.model.Order;
import com.jxau.li.model.req.ApplianceInfoReq;
import com.jxau.li.model.req.IsOrderedReq;
import com.jxau.li.model.resp.ApplianceInfoResp;
import com.jxau.li.model.resp.UserInfoResp;
import com.jxau.li.service.UserApplianceInfoService;
import com.jxau.li.utils.TimeSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserApplianceInfoServiceImpl extends ServiceImpl<UserApplianceInfoMapper, ApplianceInfo> implements UserApplianceInfoService {

    @Autowired
    private UserApplianceInfoMapper userApplianceInfoMapper;


    @Autowired
    private MerchantApplianceInfoMapper merchantApplianceInfoMapper;

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Override
    public List<ApplianceInfoResp> get(String searchQuery) {

        LambdaQueryWrapper<ApplianceInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(searchQuery != null){
            lambdaQueryWrapper.like(ApplianceInfo::getApplianceName,searchQuery);
        }
        //已上架
        lambdaQueryWrapper.eq(ApplianceInfo::getStatus,"1");
        //未下单
        lambdaQueryWrapper.eq(ApplianceInfo::getIsOrdered,"0");

        List<ApplianceInfo> applianceInfos = userApplianceInfoMapper.selectList(lambdaQueryWrapper);

        List<ApplianceInfoResp> applianceInfoResps = BeanUtil.copyToList(applianceInfos, ApplianceInfoResp.class);

        return applianceInfoResps;

    }

    @Override
    public boolean orderedApplianceInfo(IsOrderedReq isOrderedReq) {

        //1.更具id查询家电信息
        ApplianceInfo applianceInfo = merchantApplianceInfoMapper.selectById(isOrderedReq.getId());
        //2.生成订单
        Order order = new Order();
        order.setUserId(isOrderedReq.getUserId());//普通用户id
        order.setMerchantId(applianceInfo.getMerchantId());//商家id
        order.setOrderedNo(applianceInfo.getId());//家电信息id，作为订单号
        order.setApplianceName(applianceInfo.getApplianceName());//家电名称
        order.setCreateTime(TimeSimple.getTime());//创建时间
        order.setStartTime(TimeSimple.getTime());//开始时间
        order.setEndTime(TimeSimple.getTime(isOrderedReq.getMonthCount()));//结束时间
        //总金额
        order.setRentalPrice(applianceInfo.getPrice() * isOrderedReq.getMonthCount());

        order.setMonthCount(isOrderedReq.getMonthCount());//租赁月数
        if(isOrderedReq.getOrderRemark() == null || isOrderedReq.getOrderRemark().isEmpty()){
            order.setOrderRemark("无");
        }else{
            order.setOrderRemark(isOrderedReq.getOrderRemark());//备注
        }

        userOrderMapper.insert(order);

        //3.将家电信息中的isOrdered设置为1(已下单)
        String isOrdered = "1";
        boolean  is_success = merchantApplianceInfoMapper.updateIsOrdered(applianceInfo.getId(),isOrdered);

        //4.返回结果
        if(is_success){
            return true;
        }else{
            return false;
        }

    }
}
