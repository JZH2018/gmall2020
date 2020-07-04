package com.jiang.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiang.gmall.beans.PmsBaseCatalog1;
import com.jiang.gmall.beans.PmsBaseCatalog2;
import com.jiang.gmall.beans.PmsBaseCatalog3;
import com.jiang.gmall.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 查询类目
 */
@Controller
@CrossOrigin
public class CatalogController {

    @Reference
    CatalogService catalogService;

    /**
     * 查询类目1
     * @return
     */
    @RequestMapping("/getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1(){

        List<PmsBaseCatalog1>  pmsBaseCatalog1 = catalogService.getCatalog1();
        return pmsBaseCatalog1;
    }

    /**
     * 查询类目2
     * @return
     */
    @RequestMapping("/getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(@RequestParam("catalog1Id") String catalog1Id){
        List<PmsBaseCatalog2>  pmsBaseCatalog2 = catalogService.getCatalog2(catalog1Id);
        return pmsBaseCatalog2;
    }


    /**
     * 查询类目3
     * @return
     */
    @RequestMapping("/getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(@RequestParam("catalog2Id") String catalog2d){
        List<PmsBaseCatalog3>  pmsBaseCatalog3 = catalogService.getCatalog3(catalog2d);
        return pmsBaseCatalog3;
    }




}
