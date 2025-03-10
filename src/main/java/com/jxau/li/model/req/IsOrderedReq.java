package com.jxau.li.model.req;

import lombok.Data;

@Data
public class IsOrderedReq {

    /**
     * 家电信息id
     */
    private long id;

    /**
     * 用户id
     */
    private long userId;


    /**
     * 租赁月数
     */
    private int monthCount;

    /**
     * 描述
     */
    private String orderRemark;

}
