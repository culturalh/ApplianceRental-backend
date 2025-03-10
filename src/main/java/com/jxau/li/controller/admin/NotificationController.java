package com.jxau.li.controller.admin;

import com.jxau.li.aspect.LogOperation;
import com.jxau.li.common.result.CommonResp;
import com.jxau.li.common.result.Constants;
import com.jxau.li.model.req.NotificationReq;
import com.jxau.li.model.resp.NotificationResp;
import com.jxau.li.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/notification")
public class NotificationController {


    //注入service
    @Autowired
    private NotificationService notificationService;

    /**
     * 查询通知
     * @return
     */
    @GetMapping("/get")
    public CommonResp<List<NotificationResp>> getNotification() {
        List<NotificationResp> notificationResp= notificationService.get();
        return new CommonResp<>(notificationResp);
    }

    /**
     * 增加通知
     */
    @LogOperation(type ="管理员增加系统通知操作")
    @PostMapping("/add")
    public CommonResp<String> addNotification(@RequestBody NotificationReq notificationReq) {

        boolean flag = notificationService.add(notificationReq);
        if(flag){
            return new CommonResp<>();
        }else{
            return new CommonResp<>(Constants.ADD);
        }
//        resp.setContent("欢迎使用");
    }

    /**
     * 修改通知
     */
    @LogOperation(type ="管理员更新系统通知操作")
    @RequestMapping("/update")
    public CommonResp<String> updateNotification(@RequestBody NotificationReq notificationReq) {

        boolean flag = notificationService.update(notificationReq);
        if(flag){
            return new CommonResp<>();
        }else{
            return new CommonResp<>(Constants.UPDATE);
        }
    }


    /**
     * 删除通知
     */
    @LogOperation(type ="管理员删除系统通知操作")
    @DeleteMapping("/delete/{id}")
    public CommonResp<String> deleteNotification(@PathVariable("id") Integer id) {

        boolean flag = notificationService.delete(id);
        if(flag){
            return new CommonResp<>();
        }else{
            return new CommonResp<>(Constants.DELETE);
        }

//        CommonResp<String> resp = new CommonResp<>();
////        resp.setContent("欢迎使用");
//        return resp;
    }
}
