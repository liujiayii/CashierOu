package com.cashier.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.GoodstrafficOrdersProductMapper;
import com.cashier.entity.GoodstrafficOrdersProduct;
import com.cashier.service.GoodstrafficOrdersProductService;
@Service
public class GoodstrafficOrdersProductServiceImpl implements GoodstrafficOrdersProductService{
	@Autowired
	private GoodstrafficOrdersProductMapper goodstrafficOrdersProductMapper;
	 
	
	 /**
     * @Title: getGoodstrafficOrdersProductById
     * @description 获取货流商品表数据(数量,商品id,原材料id)
     * @param id
     * @return List<GoodstrafficOrdersProduct>    
     * @author chenshuxian
     * @createDate 2019年6月19日
     */
	@Override
	public List<GoodstrafficOrdersProduct> listGoodstrafficOrdersProduct(BigInteger id) {
		
		return goodstrafficOrdersProductMapper.listGoodstrafficOrdersProduct(id);
	}

}
