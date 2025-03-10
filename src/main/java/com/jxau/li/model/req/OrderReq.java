package com.jxau.li.model.req;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("rental_orders")
@Data
public class OrderReq {

    /**
     * 订单id
     */
    private long orderId;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 商家名称
     */
    private String merchantName;

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
    private String description;

    /**
     * 租赁月数
     */
    private int monthCount;

}
