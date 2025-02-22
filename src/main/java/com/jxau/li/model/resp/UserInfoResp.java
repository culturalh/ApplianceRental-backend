package com.jxau.li.model.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class UserInfoResp {

    /**
     * 主键
     */
    //以字符串的格式返回给前端
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 名称
     */
    private String name;

    /**
     * 角色
     */
    private String role;

    /**
     * 年龄
     */
    private long age;

    /**
     * 性别
     */
    private String gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    private String isActived;
}
