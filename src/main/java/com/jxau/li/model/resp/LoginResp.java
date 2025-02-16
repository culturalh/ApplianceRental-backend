package com.jxau.li.model.resp;

public class LoginResp {

    private String username;

//    private String password;

    private String role;

    private String token;

    public LoginResp() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResp(String username, String role, String token) {
        this.username = username;
        this.role = role;
        this.token = token;
    }
}
