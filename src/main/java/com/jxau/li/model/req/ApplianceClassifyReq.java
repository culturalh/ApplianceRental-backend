package com.jxau.li.model.req;

import lombok.Data;

@Data
public class ApplianceClassifyReq {

    /**
     * 家电分类id
     */
    private Integer categoryId;

    /**
     * 家电分类名称
     */
    private String deviceType;

    /**
     * 是否禁用
     */
    private String isActive;

}
