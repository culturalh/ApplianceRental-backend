package com.jxau.li.model.resp;

import com.baomidou.mybatisplus.annotation.TableField;

public class UserResp {
    /**
     * 主键
     */
    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private long age;

    /**
     * 角色
     */
    private String role;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * token验证
     */
    @TableField(exist = false)
    private String token;


    public UserResp() {
    }

    public UserResp(long id, String username, String name, String gender, long age, String role, String phone, String avatar, String email, String token) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.role = role;
        this.phone = phone;
        this.avatar = avatar;
        this.email = email;
        this.token = token;
    }

    /**
     * 获取
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return age
     */
    public long getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(long age) {
        this.age = age;
    }

    /**
     * 获取
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 获取
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String toString() {
        return "UserResp{id = " + id + ", username = " + username + ", name = " + name + ", gender = " + gender + ", age = " + age + ", role = " + role + ", phone = " + phone + ", avatar = " + avatar + ", email = " + email + ", token = " + token + "}";
    }
}
