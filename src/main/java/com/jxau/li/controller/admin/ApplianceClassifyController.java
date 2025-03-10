package com.jxau.li.controller.admin;

import com.jxau.li.aspect.LogOperation;
import com.jxau.li.common.result.CommonResp;
import com.jxau.li.common.result.Constants;
import com.jxau.li.model.req.ApplianceClassifyReq;
import com.jxau.li.model.resp.ApplianceClassifyResp;
import com.jxau.li.service.ApplianceClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/applianceClassify")

public class ApplianceClassifyController {

    //注入service
    @Autowired
    private ApplianceClassifyService applianceClassifyService;

    /**
     * 查询通知
     * @return
     */
    @GetMapping("/get")
    public CommonResp<List<ApplianceClassifyResp>> getApplianceClassify(@RequestParam(required = false) String searchQuery) {
        //条件查询，如果条件为“”或者为null，则查询所有通知
        List<ApplianceClassifyResp> applianceClassifyResp= applianceClassifyService.get(searchQuery);
        return new CommonResp<>(applianceClassifyResp);
    }

    /**
     * 增加通知
     */
    @PostMapping("/add")
    @LogOperation(type ="管理员发布家电分类操作")
    public CommonResp<String> addApplianceClassify(@RequestBody ApplianceClassifyReq applianceClassifyReq) {

        boolean flag = applianceClassifyService.add(applianceClassifyReq);
        if(flag){
            return new CommonResp<>();
        }else{
            return new CommonResp<>(Constants.ADD);
        }
    }

    /**
     * 修改通知
     */
    @RequestMapping("/update")
    @LogOperation(type ="管理员修改家电分类操作")
    public CommonResp<String> updateApplianceClassify(@RequestBody ApplianceClassifyReq applianceClassifyReq) {

        boolean flag = applianceClassifyService.update(applianceClassifyReq);
        if(flag){
            return new CommonResp<>();
        }else{
            return new CommonResp<>(Constants.UPDATE);
        }
    }

    /**
     * 删除通知
     */
    @DeleteMapping("/delete/{categoryId}")
    @LogOperation(type ="管理员删除家电分类操作")
    public CommonResp<String> deleteApplianceClassify(@PathVariable("categoryId") Integer id) {

        boolean flag = applianceClassifyService.delete(id);
        if(flag){
            return new CommonResp<>();
        }else{
            return new CommonResp<>(Constants.DELETE);
        }
    }

}
