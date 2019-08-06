package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cashier.entity.Product;
import com.cashier.entityVo.ProductOnDisplayVo;
import com.cashier.entityVo.ProductVo;

public interface ProductMapper {
	/**
	 * 
	     * @Title:添加商品 
	     * @description 
	     * @param  商品实体类
	     * @return  成功添加条数
	     * @author chenshuxian
	     * @createDate 2019年6月18日 
	 */
	Integer insertProduct(Product product);
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
	     * @Title: 根据商品分类id得到商品列表 (用户 去重)
	     * @description 
	     * @param  商品分类id 店铺id 页数条数
	     * @return  商品集合
	     * @author chenshuxian
	     * @createDate 2019年6月20
	 */
	List<ProductOnDisplayVo> productsByType(@Param("productTypeId")BigInteger productTypeId,@Param("page")Integer page,@Param("limit")Integer limit);
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
	     * @Title: getProductByCondition
	     * @description 条件查询商品详情
	     * @param  
	     * @return    
	     * @author 
	     * @createDate
	 */
	List<Product> getProductByCondition(@Param("productName")String productName,@Param("productTypeId")BigInteger productTypeId,@Param("page")Integer page,@Param("limit")Integer limit);
	/**
	 * 
	     * @Title: getProductByConditionCount
	     * @description 条件查询商品详情 数量
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月9日
	 */
	Integer getProductByConditionCount(@Param("productName")String productName,@Param("productTypeId")BigInteger productTypeId);
	/**
	 * 
	     * @Title: listProductCount
	     * @description 查询商品数量 如果商品分类不为null 则查询该种类商品数量
	     * @param  店铺id，商品种类id
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年6月20日
	 */
	Integer listProductCount(@Param("shopId")BigInteger shopId,@Param("productTypeId")BigInteger productTypeId);
	/**
	 * 
	     * @Title: groupByProductType
	     * @description 分组查询商品组 
	     * @param  店铺id
	     * @return  
	     * @author chenshuxian
	     * @createDate 2019年6月20日
	 */
	List<ProductVo> groupByProductType();
	/**
	 * 
	     * @Title: getProductByNameAndBarcode
	     * @description 根据商品码搜索商品
	     * @param  店铺id，名称，商品码
	     * @return  商品实体类
	     * @author chenshuxian
	     * @createDate 2019年6月20日
	 */
	Product getProductByNameAndBarcode(Product product);
	
	Product getProductByGoodstraffic(BigInteger id);
	/**
	 * 修改状态(上架/下架)
	 * @author chenshuxian
	 * @createDate 2019年7月18日 下午2:00
	 */
	public Integer updateProductState(Product product);
	
	/**
     * @Title: selectCountByProductId
     * @description 先判断分店铺或总店铺里是否还有库存，如果有则此分类不能删除---周嘉鑫20190729
     * @param @param product
     * @param @return  
     * @return int    
     * @author zhoujiaxin
     * @createDate 2019年7月29日
     */
    int selectCountByProductId(Product product);
    /**
     * @Title: delInventoryByPid
     * @description 删除商品时删除库存为零的库存数据
     * @param @param productId
     * @param @return  
     * @return int    
     * @author zhoujiaxin
     * @createDate 2019年8月2日
     */
    int delInventoryByPid(BigInteger productId);
}
