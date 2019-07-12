package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cashier.entity.ProductType;

public interface ProductTypeMapper {
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
	     * @Title: 模糊查询商品类型（也可分页查询所有商品 productType传null）
	     * @description 
	     * @param  
	     * @return    
	     * @author 
	     * @createDate
	 */
	List<ProductType> dimSelectProductType(@Param("shopId")BigInteger shopId,@Param("productTypeName")String productTypeName,@Param("page")Integer page,@Param("limit")Integer limit);
	/**
	 * 
	     * @Title: 软删除商品类型
	     * @description 
	     * @param  商品类型id
	     * @return    成功执行条数
	     * @author chenshuxian
	     * @createDate
	 */
	Integer delProductType(BigInteger productType);
	Integer dimSelectProductTypeCount(ProductType p);
	
	/**
	 * 
	 * @Title: selectByCanUse
	 * @description 根据可用/可不用查询商品分类
	 * @return ProductType    
	 * @author liujunkai
	 * @createDate 2019年7月12日
	 */
	ProductType selectByCanUse();
}
