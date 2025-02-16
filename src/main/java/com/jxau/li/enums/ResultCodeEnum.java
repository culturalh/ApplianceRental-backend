package com.jxau.li.enums;

public enum ResultCodeEnum {
    SUCCESS("200", "成功"),

    PARAM_ERROR("400", "参数异常"),
    TOKEN_INVALID_ERROR("401", "无效的token"),
    TOKEN_CHECK_ERROR("401", "token验证失败，请重新登录"),
    PARAM_LOST_ERROR("4001", "参数缺失"),

    SYSTEM_ERROR("500", "系统异常"),
    USER_EXIST_ERROR("5001", "用户名已存在"),
    USER_NOT_LOGIN("5002", "用户未登录"),
    USER_REGISTER_ERROR("6001","用户注册失败"),
    USER_ACCOUNT_ERROR("5003", "账号或密码错误"),
    USER_NOT_EXIST_ERROR("5004", "用户不存在，请先注册"),
    PARAM_PASSWORD_ERROR("5005", "原密码输入错误"),
    CATEGORY_EXIST_ERROR("5006", "该分类已存在"),
    CATEGORY_NOT_DELETE_ERROR("5007", "该分类下有商品数据，禁止删除"),
    CIRCLES_EXIST_ERROR("5008", "该圈子已存在"),
    CIRCLES_NOT_DELETE_ERROR("5009", "该圈子下有帖子数据，禁止删除"),
    ;

    public String code;
    public String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
