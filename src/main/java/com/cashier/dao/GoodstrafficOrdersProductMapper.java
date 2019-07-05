package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;

import com.cashier.entity.GoodstrafficOrdersProduct;

public interface GoodstrafficOrdersProductMapper {


    /**
     * @Title: insertSubscribePurchase
     * @description 增加一条货流订单产品表商品信息
     * @param godstrafficOrdersProduct 
     * @return void    
     * @author chenshuxian
     * @createDate 2019年6月19日
     */   
    void insertSubscribePurchase(GoodstrafficOrdersProduct goodstrafficOrdersProduct);
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
