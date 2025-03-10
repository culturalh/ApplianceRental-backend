package com.jxau.li.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.mapper.LogMapper;
import com.jxau.li.model.OperationLog;
import com.jxau.li.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, OperationLog> implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public List<OperationLog> get() {
        List<OperationLog> operationLogs = logMapper.selectList(null);
        return operationLogs;
    }
}
