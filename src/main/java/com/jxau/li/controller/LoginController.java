package com.jxau.li.controller;

import cn.hutool.core.util.ObjectUtil;
import com.jxau.li.common.result.CommonResp;
import com.jxau.li.enums.ResultCodeEnum;
import com.jxau.li.exception.CustomException;
import com.jxau.li.model.dto.UserDTO;
import com.jxau.li.model.req.LoginReq;
import com.jxau.li.model.resp.LoginResp;
import com.jxau.li.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private LoginService loginService;


    @PostMapping("/login")
    public CommonResp<LoginResp> login(@RequestBody LoginReq loginReq){
        LoginResp loginResp = loginService.login(loginReq);
        return new CommonResp<>(loginResp);
    }


    @PostMapping("/register")
    @Transactional
    public CommonResp<String> register(@RequestBody UserDTO userDTO){

        try {
            if(ObjectUtil.isEmpty(userDTO.getUsername())
                    || ObjectUtil.isEmpty(userDTO.getPassword())
                    || ObjectUtil.isEmpty(userDTO.getRole())
                    || ObjectUtil.isEmpty(userDTO.getName())
                    ||ObjectUtil.isEmpty(userDTO.getGender())
                    ||ObjectUtil.isEmpty(userDTO.getPhone())){
                throw new CustomException(ResultCodeEnum.PARAM_LOST_ERROR);
            }
            boolean isRegister = loginService.register(userDTO);
            log.info("注册是否成功" + isRegister);
            if(isRegister){
                return new CommonResp<>();
            }else {
                throw new CustomException(ResultCodeEnum.USER_REGISTER_ERROR);
            }
        } catch (Exception e) {
            throw new CustomException(ResultCodeEnum.USER_REGISTER_ERROR);
        }


    }
}
