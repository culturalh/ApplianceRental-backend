package com.jxau.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.OperationLog;

import java.util.List;

public interface LogService extends IService<OperationLog> {

    /**
     * 查询日志操作
     * @return
     */
    List<OperationLog> get();
}
