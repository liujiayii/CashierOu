package com.cashier.service;

import java.math.BigInteger;
import java.util.List;

import com.cashier.entity.GoodstrafficOrdersProduct;

public interface GoodstrafficOrdersProductService {


    /**
     * @Title: listGoodstrafficOrdersProduct
     * @description 获取货流商品表数据(数量,商品id,原材料id)
     * @param id
     * @return List<GoodstrafficOrdersProduct>
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    List<GoodstrafficOrdersProduct> listGoodstrafficOrdersProduct(BigInteger id);
}
