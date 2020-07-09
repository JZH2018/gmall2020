package com.jiang.gmall.manage.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jiang.gmall.beans.PmsSkuAttrValue;
import com.jiang.gmall.beans.PmsSkuImage;
import com.jiang.gmall.beans.PmsSkuInfo;
import com.jiang.gmall.beans.PmsSkuSaleAttrValue;
import com.jiang.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.jiang.gmall.manage.mapper.PmsSkuImageMapper;
import com.jiang.gmall.manage.mapper.PmsSkuInfoMapper;
import com.jiang.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.jiang.gmall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;




    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {

        pmsSkuInfoMapper.insert(pmsSkuInfo);

        List<PmsSkuImage> pmsSkuImages = new ArrayList<>();
        pmsSkuImages=pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : pmsSkuImages) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }

        List<PmsSkuAttrValue> pmsSkuAttrValues = new ArrayList<>();
        pmsSkuAttrValues=pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : pmsSkuAttrValues) {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues = new ArrayList<>();
        pmsSkuSaleAttrValues=pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : pmsSkuSaleAttrValues) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }

    }
}
