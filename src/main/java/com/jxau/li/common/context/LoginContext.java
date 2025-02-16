package com.jxau.li.common.context;

import com.jxau.li.model.resp.LoginResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginContext {
    private static final Logger LOG = LoggerFactory.getLogger(LoginContext.class);

    private static ThreadLocal<LoginResp> member = new ThreadLocal<>();

    public static LoginResp getMember() {
        return member.get();
    }

    public static void setMember(LoginResp member) {
        LoginContext.member.set(member);
    }

    public static String getUsername() {
        try {
            return member.get().getUsername();
        } catch (Exception e) {
            LOG.error("获取登录会员信息异常", e);
            throw e;
        }
    }

}
