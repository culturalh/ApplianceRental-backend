package com.jxau.li.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@TableName("operation_logs")
public class OperationLog implements Serializable {
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    private String role;

    private String username;

    private String operationType;

    private String operationTime;

}