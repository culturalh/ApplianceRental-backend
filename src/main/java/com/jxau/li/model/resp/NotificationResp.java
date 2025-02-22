package com.jxau.li.model.resp;

import lombok.Data;

@Data
public class NotificationResp {

    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布时间
     */
    private String createTime;

    public NotificationResp() {
    }
}
