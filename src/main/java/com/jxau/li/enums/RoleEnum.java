package com.jxau.li.enums;

public enum RoleEnum {

    ROLE_ADMIN("0","管理员"),
    ROLE_USER("1","用户"),
    ROLE_LEASOR("2","商家");


    private String CODE;
    private String MSG;




    RoleEnum( String CODE, String MSG) {
        this.CODE = CODE;
        this.MSG = MSG;
    }

    public String getCODE() {
        return CODE;
    }

    public String getMSG() {
        return MSG;
    }
}
