package com.jiang.gmall.item.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.gmall.beans.PmsProductSaleAttr;
import com.jiang.gmall.beans.PmsSkuInfo;
import com.jiang.gmall.beans.PmsSkuSaleAttrValue;
import com.jiang.gmall.service.SkuService;
import com.jiang.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class itemController {

    @Reference
    SkuService skuService;
    @Reference
    SpuService spuService;

    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId, ModelMap modelMap, HttpServletRequest request) {

        String remoteAddr = request.getRemoteAddr();


        PmsSkuInfo pmsSkuInfo = skuService.getSkuById(skuId,remoteAddr);
       modelMap.put("skuInfo",pmsSkuInfo);
       //销售属性列表
       List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),pmsSkuInfo.getId());

        modelMap.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);


        //查询当前sku的spu的其他sku的集合的hash表

        Map<String,String> skuSaleAttrHash = new HashMap<>();
        List<PmsSkuInfo> pmsSkuInfos= skuService.getSkuSaleAttrValueListBySpu(pmsSkuInfo.getProductId());
        for (PmsSkuInfo skuInfo : pmsSkuInfos) {
            String v = skuInfo.getId();
            String k = "";

            List<PmsSkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                k+=pmsSkuSaleAttrValue.getSaleAttrValueId() + "|";
            }
            skuSaleAttrHash.put(k,v);
        }
        //将sku的销售属性hash表放到页面
        String skuSaleAttrHashJsonstr = JSON.toJSONString(skuSaleAttrHash);
        modelMap.put("skuSaleAttrHashJsonstr",skuSaleAttrHashJsonstr);

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
