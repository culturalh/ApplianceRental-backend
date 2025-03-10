package com.jxau.li.model.req;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("appliance_info")
public class ApplianceInfoReq {

    private long id;

    private String applianceName;

    private String deviceType;

    private String createTime;

    private String picture;

    private long price;

    private long merchantId;

    private String status;

    private String description;

    private String isOrdered;

}
