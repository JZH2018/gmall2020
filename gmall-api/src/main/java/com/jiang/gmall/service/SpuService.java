package com.jiang.gmall.service;

import com.jiang.gmall.beans.PmsProductImage;
import com.jiang.gmall.beans.PmsProductInfo;
import com.jiang.gmall.beans.PmsProductSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpuService{

    List<PmsProductInfo> spuList(String catalog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);


    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);
}
