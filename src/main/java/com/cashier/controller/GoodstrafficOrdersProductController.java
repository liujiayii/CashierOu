package com.cashier.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.entity.GoodstrafficOrdersProduct;
import com.cashier.service.GoodstrafficOrdersProductService;

@Controller
public class GoodstrafficOrdersProductController {

    @Autowired
    private GoodstrafficOrdersProductService goodstrafficOrdersProductService;

    
    /**
     * @Title: listGoodstrafficOrdersProduct
     * @description 获取货流商品表数据(数量,商品id,原材料id)
     * @param id
     * @return List<GoodstrafficOrdersProduct>
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
    @ResponseBody
    @RequestMapping("/listGoodstrafficOrdersProduct")
    public Map<String, Object> listGoodstrafficOrdersProduct(BigInteger id) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<GoodstrafficOrdersProduct> listGoodstrafficOrdersProduct = goodstrafficOrdersProductService
                    .listGoodstrafficOrdersProduct(id);
            map.put("code", 1);
            map.put("msg", "显示成功");
            map.put("listGoodstrafficOrdersProduct", listGoodstrafficOrdersProduct);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", "显示失败，方法错误");
        }

        return map;
    }
}
