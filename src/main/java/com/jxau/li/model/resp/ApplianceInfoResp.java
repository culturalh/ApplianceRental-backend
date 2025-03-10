package com.jxau.li.model.resp;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
@TableName("appliance_info")
public class ApplianceInfoResp {

    //以字符串的格式返回给前端,防止前端丢失精度
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;

    private String applianceName;

    private String deviceType;

    private String createTime;

    private String picture;

    private long price;

    //以字符串的格式返回给前端,防止前端丢失精度
    @JsonSerialize(using = ToStringSerializer.class)
    private long merchantId;

    private String status;

    private String description;

    private String isOrdered;
}
