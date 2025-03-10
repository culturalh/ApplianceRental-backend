package com.jxau.li.controller.merchant;

import com.jxau.li.aspect.LogOperation;
import com.jxau.li.common.result.CommonResp;
import com.jxau.li.common.result.Constants;
import com.jxau.li.controller.FileController;
import com.jxau.li.model.User;
import com.jxau.li.model.resp.UserInfoResp;
import com.jxau.li.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/merchant/userInfo")
public class merchantInfoController {

    @Autowired
    private UserInfoService userInfoService;


    @Autowired
    private FileController fileController;

    /**
     * 获取用户信息
     */
    @GetMapping("/getUserInfo/{id}")
    public CommonResp<UserInfoResp> getUserInfo(@PathVariable("id") Long id){

        UserInfoResp userInfoResp = userInfoService.get(id);

        return new CommonResp<>(userInfoResp);
    }

    /*
     headers: {
        'Content-Type': 'multipart/form-data'//设置请求头
      }
      后端接口参数需要单独分开写
     */
    /**
     *
     * @param avatar
     * @param id
     * @param username
     * @param name
     * @param gender
     * @param role
     * @param age
     * @param phone
     * @param email
     * @return
     */
    @LogOperation(type ="商家更新信息操作")
    @PostMapping("/update")
    public CommonResp<String> updateUserInfo(
            @RequestParam(value = "avatar", required = false) MultipartFile avatar, // 允许 avatar 为空
            @RequestParam(value = "existingAvatar", required = false) String existingAvatar, // 原有头像地址
            @RequestParam("id") Long id,
            @RequestParam("username") String username,
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("role") String role,
            @RequestParam("age") Integer age,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email
    ) {

        User user = new User();
        // 如果有新头像文件
        if (avatar != null && !avatar.isEmpty()) {
            String avatarUrl = fileController.uploadFile(avatar); // 上传新文件
            user.setAvatar(avatarUrl);
        } else if (existingAvatar != null) {
            // 如果未上传新文件但传递了原有地址，保留原有地址
            user.setAvatar(existingAvatar);
        }

        user.setId(id);
        user.setUsername(username);
        user.setName(name);
        user.setGender(gender);
        user.setRole(role);
        user.setAge(age);
        user.setPhone(phone);
        user.setEmail(email);

        boolean is_success = userInfoService.update(user);
        if(is_success){
            return new CommonResp<String>();
        }else{
            return new CommonResp<String>(Constants.UPDATE);
        }
    }
}
