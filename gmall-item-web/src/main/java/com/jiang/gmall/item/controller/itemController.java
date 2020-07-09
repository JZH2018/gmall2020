package com.jiang.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.gmall.beans.PmsProductSaleAttr;
import com.jiang.gmall.beans.PmsSkuInfo;
import com.jiang.gmall.service.SkuService;
import com.jiang.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class itemController {

    @Reference
    SkuService skuService;
    @Reference
    SpuService spuService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId,ModelMap modelMap) {

       PmsSkuInfo pmsSkuInfo = skuService.getSkuById(skuId);
       modelMap.put("skuInfo",pmsSkuInfo);
       //销售属性列表
       List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),pmsSkuInfo.getId());

        modelMap.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);
        return "item";
    }

    @RequestMapping("/test")
    public String index(ModelMap modelMap) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add("循环了"+i+"次");
        }
        modelMap.put("list",list);
        modelMap.put("check","1");
        modelMap.put("hello","Hello thymeleaf");
        return "index";
    }

}
