package com.jxau.li.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.enums.RoleEnum;
import com.jxau.li.mapper.MerchantInfoManagementMapper;
import com.jxau.li.model.User;
import com.jxau.li.model.resp.UserInfoResp;
import com.jxau.li.service.MerchantInfoManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantInfoManagementServiceImpl extends ServiceImpl<MerchantInfoManagementMapper, User> implements MerchantInfoManagementService {


    @Autowired
    private MerchantInfoManagementMapper merchantInfoManagementMapper;

    @Override
    public List<UserInfoResp> get(String searchQuery) {
        //查询所有商家信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getRole, RoleEnum.ROLE_MERCHANT.getCODE());
        if(searchQuery != null){
            //根据名称查询商家信息
            lambdaQueryWrapper.like(User::getName,searchQuery);
        }
        List<User> users = merchantInfoManagementMapper.selectList(lambdaQueryWrapper);
        List<UserInfoResp> userInfoResps = BeanUtil.copyToList(users, UserInfoResp.class);
        return userInfoResps;
    }

    /**
     * 根据商家id查询商家信息
     * @param id
     * @return
     */
    @Override
    public UserInfoResp getByMerchantInfoId(Long id) {
        User user = merchantInfoManagementMapper.selectById(id);
        if(user == null){
            throw new RuntimeException("商家不存在");
        }
        UserInfoResp userInfoResp = new UserInfoResp();
        BeanUtil.copyProperties(user,userInfoResp);
        return userInfoResp;
    }

    @Override
    public boolean updatemerchantInfoStatus(Long id, String status) {

        int row = merchantInfoManagementMapper.updateById(id,status);
        if(row > 0){
            return true;
        }else {
            return false;
        }
    }
}
