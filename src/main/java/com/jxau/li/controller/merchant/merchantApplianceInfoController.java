package com.jxau.li.controller.merchant;

import com.jxau.li.aspect.LogOperation;
import com.jxau.li.common.result.CommonResp;
import com.jxau.li.common.result.Constants;
import com.jxau.li.controller.FileController;
import com.jxau.li.model.ApplianceInfo;
import com.jxau.li.model.User;
import com.jxau.li.model.req.ApplianceInfoReq;
import com.jxau.li.model.resp.ApplianceClassifyResp;
import com.jxau.li.model.resp.ApplianceInfoResp;
import com.jxau.li.service.ApplianceClassifyService;
import com.jxau.li.service.MerchantApplianceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/merchant/applianceInfo")
public class merchantApplianceInfoController {

    @Autowired
    private MerchantApplianceInfoService merchantApplianceInfoService;

    @Autowired
    private FileController fileController;

    @Autowired
    private ApplianceClassifyService applianceClassifyService;

    /**
     * 查询通知
     * @return
     */
    @GetMapping("/get")
    public CommonResp<List<ApplianceInfoResp>> getApplianceInfo(@RequestParam(required = false) String searchQuery,@RequestParam long merchantId) {
        //条件查询，如果条件为“”或者为null，则查询所有通知
        List<ApplianceInfoResp> applianceInfoResps = merchantApplianceInfoService.get(searchQuery,merchantId);
        return new CommonResp<>(applianceInfoResps);
    }

    /**
     * 增加
     * @param picture
     * @param existingPicture
     * @param applianceName
     * @param deviceType
     * @param price
     * @return
     */
    @LogOperation(type ="商家增加家电信息操作")
    @PostMapping("/add")
    public CommonResp<String> addApplianceInfo(
            @RequestParam(value = "picture", required = false) MultipartFile picture, // 允许 avatar 为空
            @RequestParam(value = "existingPicture", required = false) String existingPicture, // 原有头像地址
            @RequestParam("applianceName") String applianceName,
            @RequestParam("deviceType") String deviceType,
            @RequestParam("price") long price,
            @RequestParam("description") String description,
            @RequestParam("merchantId") long merchantId
    ) {

        ApplianceInfoReq applianceInfoReq = new ApplianceInfoReq();
        // 如果有新头像文件
        if (picture != null && !picture.isEmpty()) {
            String avatarUrl = fileController.uploadFile(picture); // 上传新文件
            applianceInfoReq.setPicture(avatarUrl);
        } else if (existingPicture != null) {
            // 如果未上传新文件但传递了原有地址，保留原有地址
            applianceInfoReq.setPicture(existingPicture);
        }

        applianceInfoReq.setApplianceName(applianceName);
        applianceInfoReq.setDeviceType(deviceType);
        applianceInfoReq.setPrice(price);
        applianceInfoReq.setDescription(description);
        applianceInfoReq.setMerchantId(merchantId);

        boolean is_success = merchantApplianceInfoService.add(applianceInfoReq);
        if(is_success){
            return new CommonResp<String>();
        }else{
            return new CommonResp<String>(Constants.UPDATE);
        }
    }


    /**
     * 修改
     * @param picture
     * @param existingPicture
     * @param applianceName
     * @param deviceType
     * @param price
     * @return
     */
    @LogOperation(type ="商家修改家电信息操作")
    @PostMapping("/update")
    public CommonResp<String> updateApplianceInfo(
            @RequestParam(value = "picture", required = false) MultipartFile picture, // 允许 avatar 为空
            @RequestParam(value = "existingPicture", required = false) String existingPicture, // 原有头像地址
            @RequestParam("id") long id,
            @RequestParam("applianceName") String applianceName,
            @RequestParam("deviceType") String deviceType,
            @RequestParam("status") String status,
            @RequestParam("price") long price,
            @RequestParam("description") String description,
            @RequestParam("merchantId") long merchantId
    ) {

        ApplianceInfoReq applianceInfoReq = new ApplianceInfoReq();
        // 如果有新头像文件
        if (picture != null && !picture.isEmpty()) {
            String avatarUrl = fileController.uploadFile(picture); // 上传新文件
            applianceInfoReq.setPicture(avatarUrl);
        } else if (existingPicture != null) {
            // 如果未上传新文件但传递了原有地址，保留原有地址
            applianceInfoReq.setPicture(existingPicture);
        }
        applianceInfoReq.setId(id);
        applianceInfoReq.setApplianceName(applianceName);
        applianceInfoReq.setStatus(status);
        applianceInfoReq.setDeviceType(deviceType);
        applianceInfoReq.setPrice(price);
        applianceInfoReq.setDescription(description);
        applianceInfoReq.setMerchantId(merchantId);

        boolean is_success = merchantApplianceInfoService.update(applianceInfoReq);
        if(is_success){
            return new CommonResp<String>();
        }else{
            return new CommonResp<String>(Constants.UPDATE);
        }
    }


    /**
     * 删除通知
     */
    @LogOperation(type ="商家删除家电信息操作")
    @DeleteMapping("/delete/{id}")
    public CommonResp<String> deleteApplianceInfo(@PathVariable("id") long id) {

        boolean flag = merchantApplianceInfoService.delete(id);
        if(flag){
            return new CommonResp<>();
        }else{
            return new CommonResp<>(Constants.DELETE);
        }
    }

    /**
     * 更新状态,前端使用json格式传过来的参数，需要使用@RequestBody接收
     */
    @LogOperation(type ="商家上架/下架家电信息操作")
    @PostMapping("/updateStatus")
    public CommonResp<String> updateStatus(@RequestBody ApplianceInfoReq applianceInfoReq) {

        boolean flag = merchantApplianceInfoService.updateStatus(applianceInfoReq.getId(), applianceInfoReq.getStatus());
        if(flag){
            return new CommonResp<>();
        }else{
            return new CommonResp<>(Constants.UPDATE);
        }
    }

    /**
     * 查询通知
     * @return
     */
    @GetMapping("/getClassify")
    public CommonResp<List<ApplianceClassifyResp>> getApplianceClassify() {
        //条件查询，如果条件为“”或者为null，则查询所有通知
        List<ApplianceClassifyResp> applianceClassifyResp= applianceClassifyService.getByStatus();
        return new CommonResp<>(applianceClassifyResp);
    }

}
