package com.cashier.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.entity.ProductType;
import com.cashier.service.ProductTypeService;
import com.cashier.service.ex.ServiceException;

@Controller
public class ProductTypeController {
	@Autowired
	private ProductTypeService productTypeService;
	/**
	 * 
	     * @Title: 添加商品种类（以判重）
	     * @description 
	     * @param  商品种类名称
	     * @return    
	     * @author chenshuxian
	     * @createDate
	 */
	@RequestMapping("/addProductType")
	@ResponseBody
	public Map<String,Object> insertProductType(ProductType productType,HttpSession session){
		//System.out.println(productType);
		Map<String,Object> map = new HashMap<>();
		productType.setShopId(new BigInteger(session.getAttribute("shopId")+""));
		try {
			Integer row = productTypeService.insertProductType(productType);
			if( row != 0 ) {
				map.put("code", 1);
				map.put("msg", "添加成功");
			}
		} catch (ServiceException e) {
			map.put("code", 0);
			map.put("msg", e.getMessage());
		}
		
		return map ;
	}
	/**
	 * 
	     * @Title: 修改商品种类名称
	     * @description 
	     * @param  商品种类名称和id
	     * @return  
	     * @author chenshuxian
	     * @createDate
	 */
	@RequestMapping("/createProductType")
	@ResponseBody
	public Map<String,Object> updateProductType(ProductType productType,HttpSession session){
		Map<String, Object> map = new HashMap<>();
		productType.setShopId(new BigInteger(session.getAttribute("shopId")+""));
		try {
			Integer row = productTypeService.updateProductType(productType);
			if(row == 1){
				map.put("code", 1);
				map.put("msg", "成功");
			}
		} catch (ServiceException e) {
			map.put("code", 0);
			map.put("msg", e.getMessage());
		}
	 	
	 	
		return map;
	}
	/**
	 * 
	     * @Title: 软删除商品种类
	     * @description 
	     * @param  
	     * @return    
	     * @author 
	     * @createDate
	 */
	@RequestMapping("/delProductType")
	@ResponseBody
	public Map<String,Object> delProductType(BigInteger productTypeId){
		Map<String, Object> map = new HashMap<>();
		Integer row = productTypeService.delProductType(productTypeId);
		if(row == 1){
			map.put("code", 1);
			map.put("msg", "成功");
		}else{
			map.put("code", 0);
			map.put("msg", "失败");
		}
		return map;
	}
	/**
	 * 
	     * @Title: 
	     * @description 查询所有商品分类(不分页)
	     * @param  
	     * @return    
	     * @author chenshuxian
	     * @createDate
	 */
	@RequestMapping("/listProductType")
	@ResponseBody
	public Map<String,Object> listProductType(HttpSession session){
		Map<String, Object> map = new HashMap<>();
		BigInteger shopId=new BigInteger(session.getAttribute("shopId")+"");
		List<ProductType> listProductType=productTypeService.listProductType(shopId);
		map.put("code", 1);
		map.put("msg", "查询成功");
		map.put("listProductType", listProductType);
		return map;
	}
	/**
	 * 
	     * @Title: 
	     * @description 根据id获得商品分类
	     * @param  商品分类id
	     * @return  
	     * @author chenshuxian
	     * @createDate
	 */
	@RequestMapping("/getProductType")
	@ResponseBody
	public Map<String,Object> getProductTypeById(BigInteger productTypeId){
		Map<String, Object> map = new HashMap<>();
		ProductType productType = productTypeService.getProductTypeById(productTypeId);
		map.put("code", 1);
		map.put("msg", "查询成功");
		map.put("productType", productType);
		return map ;
	}
	/**
	 * 
	     * @Title: 模糊查询商品类型（也可分页查询所有商品 productType传null）
	     * @description 
	     * @param  productTypeName 查询文本框输入的数据
	     * @param  page 页数
	     * @param  limit 每页显示的条数
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月3日
	 */
	@RequestMapping("/dimOrSelectAllproducts")
	@ResponseBody
	public Map<String,Object> allOrDimSelectProductType(String productTypeName,Integer page,Integer limit,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		List<ProductType> list=productTypeService.dimSelectProductType(new BigInteger(session.getAttribute("shopId")+""), productTypeName, page, limit);
		ProductType p = new ProductType();
		p.setShopId(new BigInteger(session.getAttribute("shopId")+""));
		if(productTypeName!=null && !productTypeName.equals("")){
			p.setProductTypeName(productTypeName);
		}
		
		Integer count = productTypeService.dimSelectProductTypeCount(p);
		map.put("code", 1);
		map.put("msg", "成功");
		map.put("productType", list);
		map.put("count", count);
		return map;
	}
}
