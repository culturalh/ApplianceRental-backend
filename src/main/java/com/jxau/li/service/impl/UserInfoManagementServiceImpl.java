package com.jxau.li.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.enums.RoleEnum;
import com.jxau.li.mapper.UserInfoManagementMapper;
import com.jxau.li.model.User;
import com.jxau.li.model.resp.UserInfoResp;
import com.jxau.li.service.UserInfoManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoManagementServiceImpl extends ServiceImpl<UserInfoManagementMapper, User> implements UserInfoManagementService {

    @Autowired
    private UserInfoManagementMapper userInfoManagementMapper;

    @Override
    public List<UserInfoResp> get(String searchQuery) {
        //查询所有商家信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getRole, RoleEnum.ROLE_USER.getCODE());
        if(searchQuery != null){
            //根据名称查询
            lambdaQueryWrapper.like(User::getName,searchQuery);
        }
        List<User> users = userInfoManagementMapper.selectList(lambdaQueryWrapper);
        List<UserInfoResp> userInfoResps = BeanUtil.copyToList(users, UserInfoResp.class);
        return userInfoResps;

    }

    @Override
    public UserInfoResp getByUserInfoId(Long id) {
        User user = userInfoManagementMapper.selectById(id);
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        UserInfoResp userInfoResp = new UserInfoResp();
        BeanUtil.copyProperties(user,userInfoResp);
        return userInfoResp;
    }

    @Override
    public boolean updateUserInfoStatus(Long id, String status) {

        int row = userInfoManagementMapper.updateById(id,status);
        if(row > 0){
            return true;
        }else {
            return false;
        }
    }
}
