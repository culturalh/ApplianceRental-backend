package com.jxau.li.controller.admin;

import com.jxau.li.common.result.CommonResp;
import com.jxau.li.model.OperationLog;
import com.jxau.li.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/get")
    public CommonResp<List<OperationLog>> getLog(){

        List<OperationLog> operationLogs = logService.get();
        return new CommonResp<>(operationLogs);
    }

}
