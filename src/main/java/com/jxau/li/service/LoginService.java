package com.jxau.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.User;
import com.jxau.li.model.dto.UserDTO;
import com.jxau.li.model.resp.UserResp;

public interface LoginService extends IService<User> {

    /**
     * 登录
     * @param user
     * @return
     */
    UserResp login(UserDTO user);

    /**
     * 注册
     * @param userDTO
     * @return
     */
    boolean register(UserDTO userDTO);
}
