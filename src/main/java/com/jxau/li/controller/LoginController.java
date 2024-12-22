package com.jxau.li.controller;

import cn.hutool.core.util.ObjectUtil;
import com.jxau.li.common.result.Result;
import com.jxau.li.enums.ResultCodeEnum;
import com.jxau.li.exception.CustomException;
import com.jxau.li.model.User;
import com.jxau.li.model.dto.UserDTO;
import com.jxau.li.model.resp.UserResp;
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
    public Result login(@RequestBody UserDTO userDTO){


        try {
            if(ObjectUtil.isEmpty(userDTO.getUsername())
                    || ObjectUtil.isEmpty(userDTO.getPassword())
                    || ObjectUtil.isEmpty(userDTO.getRole())){
                return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
            }
            UserResp loginUser = loginService.login(userDTO);
            log.info("登录成功");
            return Result.success(loginUser);
        } catch (Exception e) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

    }

    @PostMapping("/register")
    @Transactional
    public Result register(@RequestBody UserDTO userDTO){

        try {
            if(ObjectUtil.isEmpty(userDTO.getUsername())
                    || ObjectUtil.isEmpty(userDTO.getPassword())
                    || ObjectUtil.isEmpty(userDTO.getRole())
                    || ObjectUtil.isEmpty(userDTO.getName())
                    ||ObjectUtil.isEmpty(userDTO.getGender())
                    ||ObjectUtil.isEmpty(userDTO.getPhone())){
                return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
            }
            boolean isRegister = loginService.register(userDTO);
            log.info("注册是否成功" + isRegister);
            if(isRegister){
                return  Result.success();
            }else {
                throw new CustomException(ResultCodeEnum.USER_REGISTER_ERROR);
            }
        } catch (Exception e) {
            throw new CustomException(ResultCodeEnum.USER_REGISTER_ERROR);
        }


    }
}
