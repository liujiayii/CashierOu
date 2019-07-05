package com.cashier.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cashier.entity.ProductType;

public interface ProductTypeService {

	/**
	 * 
	     * @Title:添加商品分类 
	     * @description 
	     * @param  商品分类
	     * @return  成功添加的条数
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	Integer insertProductType(ProductType productType);
	/**
	 * 
	     * @Title: 修改商品分类名称
	     * @description 
	     * @param  商品分类
	     * @return  成功修改的条数
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	Integer updateProductType(ProductType productType);
	/**
	 * 
	     * @Title: 删除商品分类名称（软删除）
	     * @description 
	     * @param  商品分类
	     * @return  成功修改的条数
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	Integer delProductType(BigInteger productTypeId);
	/**
	 * 
	     * @Title: 获取所有商品分类(不分页)
	     * @description 
	     * @param  
	     * @return    商品分类集合
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	List<ProductType> listProductType(BigInteger shopId);
	/**
	 * 
	     * @Title: 根据id得到商品分类详情
	     * @description 
	     * @param  商品分类id
	     * @return   商品分类实体类
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	ProductType getProductTypeById(BigInteger productTypeId);
	/**
	 * 
	     * @Title: 查询除去当前商品类型所有的商品类型   不分页（用于判断商品分类是否重复
	     * @description 
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate
	 */
	List<ProductType> selectProductTypeListRemoveThis(ProductType  productType);
	/**
	 * 
	     * @Title: 模糊查询商品类型（也可分页查询所有商品 shopid和productType传null）
	     * @description 
	     * @param  
	     * @return    
	     * @author 
	     * @createDate
	 */
	List<ProductType> dimSelectProductType(BigInteger shopId,String productType,Integer page,Integer limit);
}
