package com.jiang.gmall.service;

import com.jiang.gmall.beans.PmsBaseAttrInfo;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);
}
