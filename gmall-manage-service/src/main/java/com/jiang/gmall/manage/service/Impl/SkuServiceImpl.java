package com.jiang.gmall.manage.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.jiang.gmall.beans.PmsSkuAttrValue;
import com.jiang.gmall.beans.PmsSkuImage;
import com.jiang.gmall.beans.PmsSkuInfo;
import com.jiang.gmall.beans.PmsSkuSaleAttrValue;
import com.jiang.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.jiang.gmall.manage.mapper.PmsSkuImageMapper;
import com.jiang.gmall.manage.mapper.PmsSkuInfoMapper;
import com.jiang.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.jiang.gmall.service.RedisUtil;
import com.jiang.gmall.service.SkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;


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

    @Autowired
    RedisUtil redisUtil;


    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {

        pmsSkuInfoMapper.insert(pmsSkuInfo);

        List<PmsSkuImage> pmsSkuImages = new ArrayList<>();
        pmsSkuImages = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : pmsSkuImages) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }

        List<PmsSkuAttrValue> pmsSkuAttrValues = new ArrayList<>();
        pmsSkuAttrValues = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : pmsSkuAttrValues) {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues = new ArrayList<>();
        pmsSkuSaleAttrValues = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : pmsSkuSaleAttrValues) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }

    }

    public PmsSkuInfo getSkuByIdFromDB(String skuId) {

        //SKU的商品对象
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo SkuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);
        //sku的商品图片集合
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);
        SkuInfo.setSkuImageList(pmsSkuImages);
        return SkuInfo;
    }


    @Override
    public PmsSkuInfo getSkuById(String skuId) {
        //SKU的商品对象
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        //链接缓存
        Jedis jedis = redisUtil.getJedis();
        //查询缓存
        String skuKey ="sku"+ skuId + ":info";
        String skuJson = jedis.get(skuKey);

        if(StringUtils.isNoneBlank(skuJson)){
            pmsSkuInfo =JSON.parseObject(skuJson,PmsSkuInfo.class);
        }else {
            //设置分布式锁
          String OK = jedis.set("sku"+ skuId + ":lock","1","nx","px",10);
            if(StringUtils.isNotBlank(OK)&& OK.equals("OK")){
                //设置成功 有权访问数据库
                //如果缓存中没有 查询Mysql
                pmsSkuInfo = getSkuByIdFromDB(skuId);
                //将结果录入redis
                if(pmsSkuInfo!=null){
                    jedis.set("sku:"+skuId+":info",JSON.toJSONString(pmsSkuInfo));
                }else {
                    //防止缓存穿透   就是说用户在不停的传入一个不存在的值，这个值缓存中没有，数据库中也没有，就会不停的访问数据库
                    //设置当数据库中也不存在时，在缓存设置一段时间为空值给redis
                    jedis.setex("sku:"+skuId+":info",60*3,JSON.toJSONString(""));
                }
            }else {
                //访问失败 ，自旋 （该线程在睡眠几秒后，重新尝试访问本方法）
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return getSkuById(skuId);
            }
        }
        jedis.close();
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {

        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);

        return pmsSkuInfos;
    }
}
