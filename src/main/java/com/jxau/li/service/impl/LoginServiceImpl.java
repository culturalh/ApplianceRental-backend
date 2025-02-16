package com.jxau.li.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.common.result.Result;
import com.jxau.li.enums.ResultCodeEnum;
import com.jxau.li.enums.RoleEnum;
import com.jxau.li.exception.CustomException;
import com.jxau.li.mapper.LoginMapper;
import com.jxau.li.model.User;
import com.jxau.li.model.dto.UserDTO;
import com.jxau.li.model.req.LoginReq;
import com.jxau.li.model.resp.LoginResp;
import com.jxau.li.model.resp.UserResp;
import com.jxau.li.service.LoginService;
import com.jxau.li.utils.JwtUtil;
import com.jxau.li.utils.MD5Util;
import com.jxau.li.utils.SnowUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper,User> implements LoginService {

    //密码加密盐值，可以配置在配置文件中
    private final String saltValue = "Cultural";


    @Resource
    private LoginMapper loginMapper;


//    @Override
//    public UserResp login(UserDTO userDTO) {
//
//        //根据用户名和角色查询用户
//        User dbUser =  loginMapper.selectByUsernameAndRole(userDTO);
//
//        if(ObjectUtil.isNull(dbUser)){
//            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
//        }
//
//        //使用md5加密比对
//        if(!dbUser.getPassword().equals(MD5Util.MD5Upper(userDTO.getPassword(),saltValue))){
//            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
//        }
//
//        //将前端传过来的role转换，0-管理员，1-用户，2-商家
//        if(RoleEnum.ROLE_ADMIN.getCODE().equals(dbUser.getRole())){
//            dbUser.setRole(RoleEnum.ROLE_ADMIN.getMSG());
//        }else if(RoleEnum.ROLE_USER.getMSG().equals(dbUser.getRole())){
//            dbUser.setRole(RoleEnum.ROLE_USER.getMSG());
//        }else if(RoleEnum.ROLE_LEASOR.getMSG().equals(dbUser.getRole())){
//            dbUser.setRole(RoleEnum.ROLE_LEASOR.getMSG());
//        }
//
//        String token = JwtUtil.createToken(dbUser.getId(), dbUser.getRole());
//        dbUser.setToken(token);
//        UserResp userResp = new UserResp();
//        BeanUtils.copyProperties(dbUser,userResp);
//        return userResp;
//    }

    @Override
    public boolean register(UserDTO userDTO) {

        //查询数据库，该用户是否存在
        User user = loginMapper.selectByUsernameAndRole(userDTO);
        //如果不存在，则存入数据库
        if(ObjectUtil.isNull(user)){
            User newUser = new User();
            BeanUtils.copyProperties(userDTO,newUser);
            //使用雪花算法生成一个id
            long snowflakeNextId = SnowUtil.getSnowflakeNextId();
            newUser.setId(snowflakeNextId);
            //使用md5加密
            String password = MD5Util.MD5Upper(userDTO.getPassword(), saltValue);
            newUser.setPassword(password);
            //将前端传过来的role转换，0-管理员，1-用户，2-商家
            if(RoleEnum.ROLE_ADMIN.getMSG().equals(newUser.getRole())){
                newUser.setRole(RoleEnum.ROLE_ADMIN.getCODE());
            }else if(RoleEnum.ROLE_USER.getMSG().equals(newUser.getRole())){
                newUser.setRole(RoleEnum.ROLE_USER.getCODE());
            }else if(RoleEnum.ROLE_LEASOR.getMSG().equals(newUser.getRole())){
                newUser.setRole(RoleEnum.ROLE_LEASOR.getCODE());
            }
            loginMapper.insert(newUser);
        }else {
            //如果存在，则抛出异常
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        return true;
    }

    @Override
    public LoginResp login(LoginReq loginReq) {

        //根据用户名和角色查询用户
        User dbUser =  loginMapper.selectByRoleAndUsername(loginReq);

        //如果数据库查询用户不存在，则提示请先注册
        if(ObjectUtil.isNull(dbUser)){
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        //使用md5加密比对
        //todo 测试先注释
//        if(!dbUser.getPassword().equals(MD5Util.MD5Upper(loginReq.getPassword(),saltValue))){
//            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
//        }

        //将前端传过来的role转换，0-管理员，1-用户，2-商家
        if(RoleEnum.ROLE_ADMIN.getCODE().equals(dbUser.getRole())){
            dbUser.setRole(RoleEnum.ROLE_ADMIN.getMSG());
        }else if(RoleEnum.ROLE_USER.getMSG().equals(dbUser.getRole())){
            dbUser.setRole(RoleEnum.ROLE_USER.getMSG());
        }else if(RoleEnum.ROLE_LEASOR.getMSG().equals(dbUser.getRole())){
            dbUser.setRole(RoleEnum.ROLE_LEASOR.getMSG());
        }

        //根据主键和角色创建token
        String token = JwtUtil.createToken(dbUser.getId(), dbUser.getRole());

        LoginResp loginResp = new LoginResp();
        loginResp.setUsername(dbUser.getUsername());
        loginResp.setRole(dbUser.getRole());
        loginResp.setToken(token);
        return loginResp;
    }
}
