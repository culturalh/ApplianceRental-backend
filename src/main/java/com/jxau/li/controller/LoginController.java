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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private LoginService loginService;

    @Autowired
    private FileController fileController;


    @PostMapping("/login")
    public CommonResp<LoginResp> login(@RequestBody LoginReq loginReq){
        LoginResp loginResp = loginService.login(loginReq);
        return new CommonResp<>(loginResp);
    }


    @PostMapping("/register")
    @Transactional
//    public CommonResp<String> register(@RequestBody UserDTO userDTO){
    public CommonResp<String> register(
            @RequestParam(value = "avatar", required = false) MultipartFile avatar, // 允许 avatar 为空
            @RequestParam(value = "gender", required = false) String gender, // 原有头像地址
            @RequestParam(value = "age", required = false) String age,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("role") String role,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email
    ){
     try {
        UserDTO userDTO = new UserDTO();
        // 如果有新头像文件
        if (avatar != null && !avatar.isEmpty()) {
            String avatarUrl = fileController.uploadFile(avatar); // 上传新文件
            userDTO.setAvatar(avatarUrl);
        } else {
            // 如果未上传新文件但传递了原有地址，保留原有地址
            throw new CustomException(ResultCodeEnum.REPEAT_AVATAR_ERROR);
        }
        //验证注册参数
        if(ObjectUtil.isEmpty(username)
                    || ObjectUtil.isEmpty(password)
                    || ObjectUtil.isEmpty(role)
                    || ObjectUtil.isEmpty(name)
                    ||ObjectUtil.isEmpty(phone)
                    ||ObjectUtil.isEmpty(email)){
                throw new CustomException(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setName(name);
        if(ObjectUtil.isEmpty(age)){
            userDTO.setAge(-1L);
        }else{
            userDTO.setAge(Long.parseLong(age));
        }
         if(ObjectUtil.isEmpty(gender) || "undefined".equals(gender)){
             userDTO.setGender("");
         }else{
             userDTO.setGender(gender);
         }
        userDTO.setRole(role);
        userDTO.setPhone(phone);
        userDTO.setEmail(email);
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
