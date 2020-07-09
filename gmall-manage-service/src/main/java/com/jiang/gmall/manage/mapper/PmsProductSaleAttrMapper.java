package com.jiang.gmall.manage.mapper;

import com.jiang.gmall.beans.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {
    @Select("SELECT\n" +
            "\tsa.*,\n" +
            "\tsav.*,\n" +
            "IF\n" +
            "\t( ssav.sku_id, 1, 0 ) AS isChecked \n" +
            "FROM\n" +
            "\tpms_product_sale_attr sa\n" +
            "\tINNER JOIN pms_product_sale_attr_value sav ON sa.product_id = sav.product_id \n" +
            "\tAND sa.sale_attr_id = sav.sale_attr_id \n" +
            "\tAND sa.product_id = #{productId}\n" +
            "\tLEFT JOIN pms_sku_sale_attr_value ssav ON sav.id = ssav.sale_attr_value_id \n" +
            "\tAND ssav.sku_id = #{skuId}")
    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") String productId, @Param("skuId")String skuId);
}
