package com.jxau.li.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.mapper.UserInfoMapper;
import com.jxau.li.model.User;
import com.jxau.li.model.resp.UserInfoResp;
import com.jxau.li.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, User> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfoResp get(Long id) {

        User user = userInfoMapper.selectById(id);
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        UserInfoResp userInfoResp = new UserInfoResp();
        BeanUtil.copyProperties(user,userInfoResp);
        return userInfoResp;
    }

    @Override
    public boolean update(User user) {
        int row = userInfoMapper.updateById(user);
        if(row > 0){
            throw new RuntimeException("修改失败");
        }
        return false;
    }
}
