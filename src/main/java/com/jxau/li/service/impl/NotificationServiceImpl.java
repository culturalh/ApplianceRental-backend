package com.jxau.li.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxau.li.mapper.NotificationMapper;
import com.jxau.li.model.Notification;
import com.jxau.li.model.req.NotificationReq;
import com.jxau.li.model.resp.NotificationResp;
import com.jxau.li.service.NotificationService;
import com.jxau.li.utils.TimeSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl  extends ServiceImpl<NotificationMapper, Notification> implements NotificationService {


    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 查询
     * @return
     */
    @Override
    public List<NotificationResp> get() {

        LambdaQueryWrapper<Notification> notificationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        notificationLambdaQueryWrapper.orderByDesc(Notification::getCreateTime);
        //查询所有
        List<Notification> notifications = notificationMapper.selectList(notificationLambdaQueryWrapper);
        List<NotificationResp> notificationResps = BeanUtil.copyToList(notifications, NotificationResp.class);
        //返回所有通知数据
        return notificationResps;
    }

    /**
     * 增加
     * @param notificationReq
     * @return
     */
    @Override
    public boolean add(NotificationReq notificationReq) {
        Notification notification = new Notification();
        BeanUtil.copyProperties(notificationReq,notification);
        notification.setCreateTime(TimeSimple.getTime());
        notification.setIsDelete("0");
        int row = notificationMapper.insert(notification);
        if (row>0){
            return true;
        }
        return false;
    }

    /**
     * 修改
     * @param notificationReq
     * @return
     */
    @Override
    public boolean update(NotificationReq notificationReq) {
        Notification notification = new Notification();
        BeanUtil.copyProperties(notificationReq,notification);
        notification.setCreateTime(TimeSimple.getTime());
//        notification.setIsDelete("0");
        int row = notificationMapper.updateById(notification);
        if (row>0){
            return true;
        }
        return false;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        int row = notificationMapper.deleteById(id);
        if (row>0){
            return true;
        }
        return false;
    }
}
