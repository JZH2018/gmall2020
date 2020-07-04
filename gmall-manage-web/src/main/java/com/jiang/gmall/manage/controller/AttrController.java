package com.jiang.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.gmall.beans.PmsBaseAttrInfo;
import com.jiang.gmall.service.AttrService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 属性
 */
@RestController
@CrossOrigin
public class AttrController {

    @Reference
    AttrService attrService;

    @RequestMapping("/attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(@RequestParam("catalog3Id") String catalog3Id) {
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.attrInfoList(catalog3Id);
        return pmsBaseAttrInfos;
    }

    @RequestMapping("/saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {

        return "success";
    }

}
