package com.jiang.gmall.manage.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jiang.gmall.beans.PmsBaseAttrInfo;
import com.jiang.gmall.beans.PmsBaseAttrValue;
import com.jiang.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.jiang.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.jiang.gmall.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        return pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
    }
}
