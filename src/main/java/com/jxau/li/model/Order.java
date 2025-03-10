package com.jxau.li.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("rental_orders")
@Data
public class Order {

    /**
     * 订单id
     */
    private long orderId;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 商家id
     */
    private long merchantId;

    /**
     * 订单编号
     */
    private long orderedNo;

    /**
     * 家电名称
     */
    private String applianceName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 租赁价格
     */
    private long rentalPrice;

    /**
     * 订单创建时间
     */
    private String createTime;

    /**
     * 备注
     */
    private String orderRemark;

    /**
     * 租赁月数
     */
    private int monthCount;

}
