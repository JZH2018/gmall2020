package com.jiang.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.gmall.beans.PmsProductImage;
import com.jiang.gmall.beans.PmsProductInfo;
import com.jiang.gmall.beans.PmsProductSaleAttr;
import com.jiang.gmall.manage.util.PmsUploadUtil;
import com.jiang.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping("/spuImageList")
    public List<PmsProductImage> spuImageList(@RequestParam("spuId") String spuId){
        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);
        return pmsProductImages;
    }

    @RequestMapping("/spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(@RequestParam("spuId") String spuId){
        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }


    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        //将文件或视频上传到分布式文件存储系统
        //将文件存储路径返回给页面
        String imgurl = PmsUploadUtil.uploadImage(multipartFile);
        return imgurl;
    }

    @RequestMapping("/saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {
        spuService.saveSpuInfo(pmsProductInfo);

        return "success";
    }

    @RequestMapping("/spuList")
    public List<PmsProductInfo> spuList(@RequestParam("catalog3Id") String catalog3Id) {
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }


}
