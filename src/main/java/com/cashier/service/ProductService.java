package com.cashier.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.ibatis.annotations.Param;

import com.cashier.entity.Product;
import com.cashier.entityVo.ProductVo;

public interface ProductService {

	/**
	 * 
	     * @Title:添加商品 
	     * @description 
	     * @param  商品实体类
	     * @return  成功添加条数
	     * @author chenshuxian
	     * @createDate 2019年6月18日 
	 */
	Integer insertProduct(Product product,BigInteger quantity,BigInteger inventoryWarning);
	/**
	 * 
	     * @Title:根据id修改商品 
	     * @description 
	     * @param  商品实体类
	     * @return  成功修改条数
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	Integer updateProductById(Product product);
	/**
	 * 
	     * @Title: 根据商品id删除商品（软删除）
	     * @description 
	     * @param  商品id
	     * @return  成功删除的条数
	     * @author chenshuxian
	     * @createDate 2019年6月18
	 */
	Integer delProductById(BigInteger productId);
	/**
	 * 
	     * @Title:根据商品分类id得到商品列表 （不去重，后台）
	     * @description 
	     * @param 商品分类id
	     * @return  商品集合
	     * @author chenshuxian
	     * @createDate 2019年6月18
	 */
	Map<String,Object> getProductByCondition(String productName,BigInteger productTypeId,BigInteger shopId,Integer page,Integer limit);
	/**
	 * 
	     * @Title:条件查询所有商品
	     * @description 
	     * @param 商品分类id
	     * @return  商品集合
	     * @author chenshuxian
	     * @createDate 2019年6月18
	 */
	Map<String,Object> productsByType(BigInteger productTypeId,BigInteger shopId,Integer page,Integer limit);
	/**
	 * 
	     * @Title: 根据id得到商品详情
	     * @description 
	     * @param  商品id
	     * @return 商品实体类
	     * @author chenshuxian
	     * @createDate 2019年6月18
	 */
	Product getProductById(BigInteger productId);
	/**
	 * 
	     * @Title: listProduct
	     * @description 分页查询所有商品（包含商品分类）
	     * @param  店铺id，页数，条数
	     * @return    
	     * @author 
	     * @createDate
	 *//*
	Map<String,Object> listProduct(@Param("shopId")BigInteger shopId,@Param("page")Integer page,@Param("limit")Integer limit);*/
	/**
	 * 
	     * @Title: groupByProductType
	     * @description 分组查询商品组 
	     * @param  店铺id
	     * @return  
	     * @author chenshuxian
	     * @createDate 2019年6月20日
	 */
	List<ProductVo> groupByProductType(BigInteger shopId);
	
	 /**
     * 获得商品条码最后一位验证码
     * 
     
     * @param barCode
     *            商品条码前12位 只传12位
    
     * @return 商品条码最后一位验证码
     */
	String barCode(String jbarCode);
	/**
	 * 
	     * @Title: getProductByBarCode
	     * @description 根据商品条码获得对应商品
	     * @param  商品条码
	     * @return   商品详情
	     * @author chenshuxian
	     * @createDate 2019年7月8日
	 */
	Product getProductByBarCode(Product product);
	/**
	 * 修改状态(上架/下架)
	 * 
	 * @author chenshuxian
	 * @createDate 2019年7月18日 下午2:00
	 */
	Integer updateProductState(Product product);
	
}
