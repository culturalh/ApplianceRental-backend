package com.jxau.li.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ApplianceCategories {

    /**
     * 家电分类编号
     */
    @TableId
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String deviceType;

    /**
     * 是否禁用，0-启用，1-禁用
     */
    private String isActive;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 是否删除，0-未删除，1-已删除
     */
    private String isDelete;
}
