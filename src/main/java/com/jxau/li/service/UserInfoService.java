package com.jxau.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.User;
import com.jxau.li.model.resp.UserInfoResp;

public interface UserInfoService extends IService<User> {

    /**
     * 根据id 获取个人信息
     * @param id
     * @return
     */
    UserInfoResp get(Long id);

    /**
     * 更新个人信息
     * @param user
     * @return
     */
    boolean update(User user);
}
