package com.jxau.li.enums;

public enum RoleEnum {

    //管理员admin，用户user，商家merchant
    ROLE_ADMIN("0","admin"),
    ROLE_USER("1","user"),
    ROLE_MERCHANT("2","merchant");


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
