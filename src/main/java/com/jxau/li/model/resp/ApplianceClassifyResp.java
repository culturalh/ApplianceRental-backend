package com.jxau.li.model.resp;

import lombok.Data;

@Data
public class ApplianceClassifyResp {

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

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 是否删除
     */
    private String isDelete;
}
