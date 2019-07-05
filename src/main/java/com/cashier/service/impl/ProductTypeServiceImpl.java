package com.cashier.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.ProductTypeMapper;
import com.cashier.entity.ProductType;
import com.cashier.service.ProductTypeService;
import com.cashier.service.ex.ProductTypeNameConflictException;
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	@Autowired
	private ProductTypeMapper productTypeMapper;
	/**
	 * 
	     * @Title:添加商品分类 
	     * @description 
	     * @param  商品分类
	     * @return  成功添加的条数
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	@Override
	public Integer insertProductType(ProductType productType) {
		//判断是否重复
		List<ProductType> listProductType=productTypeMapper.selectProductTypeListRemoveThis(productType);
		Integer row = 0;
		if(listProductType!=null&&listProductType.size()!=0){
			throw new ProductTypeNameConflictException("商品种类重复，添加失败");
		}else{
			productType.setCanUse(1); 
			row = productTypeMapper.insertProductType(productType);
		}
		
		return row;
	}

	/**
	 * 
	     * @Title: 修改商品分类名称
	     * @description 
	     * @param  商品分类
	     * @return  成功修改的条数
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	@Override
	public Integer updateProductType(ProductType productType) {
		//判断是否重复
				List<ProductType> listProductType=productTypeMapper.selectProductTypeListRemoveThis(productType);
				Integer row = 0;
				if(listProductType!=null&&listProductType.size()!=0){
					throw new ProductTypeNameConflictException("商品种类重复，修改失败");
				}else{
					productType.setCanUse(1); 
					row = productTypeMapper.updateProductType(productType);
				}
				
		return row;
	}

	/**
	 * 
	     * @Title: 删除商品分类名称（软删除）
	     * @description 
	     * @param  商品分类
	     * @return  成功修改的条数
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	@Override
	public Integer delProductType(BigInteger productTypeId) {
		
		return productTypeMapper.delProductType(productTypeId);
	}

	/**
	 * 
	     * @Title: 获取所有商品分类(不分页)
	     * @description 
	     * @param  
	     * @return    商品分类集合
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	@Override
	public List<ProductType> listProductType(BigInteger shopId) {
		
		return productTypeMapper.listProductType(shopId);
	}

	/**
	 * 
	     * @Title: 根据id得到商品分类详情
	     * @description 
	     * @param  商品分类id
	     * @return   商品分类实体类
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	@Override
	public ProductType getProductTypeById(BigInteger productTypeId) {
		
		return productTypeMapper.getProductTypeById(productTypeId);
	}

	/**
	 * 
	     * @Title: 查询除去当前商品类型所有的商品类型   不分页（用于判断商品分类是否重复
	     * @description 
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate
	 */
	@Override
	public List<ProductType> selectProductTypeListRemoveThis(ProductType productType) {
		
		return productTypeMapper.selectProductTypeListRemoveThis(productType);
	}

	/**
	 * 
	     * @Title: 模糊查询商品类型（也可分页查询所有商品 productType传null）
	     * @description 
	     * @param  
	     * @return    
	     * @author 
	     * @createDate
	 */
	@Override
	public List<ProductType> dimSelectProductType(BigInteger shopId, String productType, Integer page, Integer limit) {
		Integer beginPage = limit*(page-1);
		return productTypeMapper.dimSelectProductType(shopId, productType, beginPage, limit);
	}

}
