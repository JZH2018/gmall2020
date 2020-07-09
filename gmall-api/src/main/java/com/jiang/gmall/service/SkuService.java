package com.jiang.gmall.service;

import com.jiang.gmall.beans.PmsSkuInfo;

public interface SkuService {

    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuById(String skuId);
}
