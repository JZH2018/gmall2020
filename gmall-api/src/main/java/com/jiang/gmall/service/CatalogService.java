package com.jiang.gmall.service;

import com.jiang.gmall.beans.PmsBaseCatalog1;
import com.jiang.gmall.beans.PmsBaseCatalog2;
import com.jiang.gmall.beans.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {

    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2d);
}
