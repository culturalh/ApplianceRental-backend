package com.jxau.li.model.req;

import lombok.Data;

@Data
public class NotificationReq {

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

    public NotificationReq() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NotificationReq{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
