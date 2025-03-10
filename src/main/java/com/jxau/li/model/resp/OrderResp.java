package com.jxau.li.model.resp;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@TableName("rental_orders")
@Data
public class OrderResp {

    /**
     * 订单id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private long orderId;

    /**
     * 用户名称
     */

    private String usersName;

    /**
     * 商家名称
     */
    private String merchantsName;

    /**
     * 订单编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
