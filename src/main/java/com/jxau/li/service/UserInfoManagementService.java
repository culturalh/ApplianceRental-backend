package com.jxau.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.User;
import com.jxau.li.model.resp.UserInfoResp;

import java.util.List;

public interface UserInfoManagementService extends IService<User> {

    /**
     * 获取所有用户信息
     * @return
     */
    List<UserInfoResp> get(String searchQuery);

    /**
     * 根据id获取用户详细信息
     * @param id
     * @return
     */
    UserInfoResp getByUserInfoId(Long id);

    /**
     * 更新用户状态
     * @param id
     * @param status
     * @return
     */
    boolean updateUserInfoStatus(Long id, String status);


}
