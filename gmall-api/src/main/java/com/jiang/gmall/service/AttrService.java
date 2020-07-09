package com.jiang.gmall.service;

import com.jiang.gmall.beans.PmsBaseAttrInfo;
import com.jiang.gmall.beans.PmsBaseAttrValue;
import com.jiang.gmall.beans.PmsBaseSaleAttr;
import com.jiang.gmall.beans.PmsProductSaleAttr;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
