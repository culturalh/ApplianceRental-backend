package com.jxau.li.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxau.li.model.Notification;
import com.jxau.li.model.User;
import com.jxau.li.model.req.NotificationReq;
import com.jxau.li.model.resp.NotificationResp;

import java.util.List;

public interface NotificationService extends IService<Notification> {

    List<NotificationResp> get();

    boolean add(NotificationReq notificationReq);

    boolean update(NotificationReq notificationReq);

    boolean delete(Integer id);
}
