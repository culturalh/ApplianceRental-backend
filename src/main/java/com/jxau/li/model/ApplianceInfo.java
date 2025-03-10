package com.jxau.li.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("appliance_info")
public class ApplianceInfo {

    /**
     * 主键,13位雪花算法
     */
    private long id;

    /**
     * 家电名称
     */
    private String applianceName;

//    /**
//     * 商家名称
//     */
//    private String merchantName;

    /**
     * 家电类型
     */
    private String deviceType;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 图片
     */
    private String picture;

    /**
     * 价格
     */
    private long price;

    /**
     * 商家id
     */
    private long merchantId;

    /**
     * 上架状态
     */
    private String status;

    /**
     * 备注
     */
    private String description;

    /**
     * 是否被下单
     */
    private String isOrdered;

}
