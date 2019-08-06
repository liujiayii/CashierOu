package com.cashier.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.dao.UserOperationMapper;
import com.cashier.entity.Product;
import com.cashier.entity.ProductType;
import com.cashier.entity.User;
import com.cashier.entity.UserOperation;
import com.cashier.service.ProductTypeService;
import com.cashier.service.ex.ServiceException;

@Controller  // 商品分类和商品是通用的，由总店统一管理，总店和分店共用一份数据
public class ProductTypeController {
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private UserOperationMapper userOperationMapper;
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
	@RequiresPermissions("/addProductType")
	@ResponseBody
	public Map<String,Object> insertProductType(ProductType productType,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		productType.setShopId(new BigInteger(session.getAttribute("shopId")+""));
		try {
			Integer row = productTypeService.insertProductType(productType);
			if( row != 0 ) {
				 // 添加一条操作记录
	            User user = (User)session.getAttribute("user");
	            UserOperation userOperation = new UserOperation();
	            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
	            userOperation.setUserName(user.getUsername());
	            userOperation.setName(user.getName());
	            userOperation.setOperatingContent("添加商品类型");
	            userOperationMapper.saveUserOperation(userOperation);
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
	@RequiresPermissions("/createProductType")
	@ResponseBody
	public Map<String,Object> updateProductType(ProductType productType,HttpSession session){
		Map<String, Object> map = new HashMap<>();
		productType.setShopId(new BigInteger(session.getAttribute("shopId")+""));
		try {
			Integer row = productTypeService.updateProductType(productType);
			if(row == 1){
				 // 添加一条操作记录
	            User user = (User)session.getAttribute("user");
	            UserOperation userOperation = new UserOperation();
	            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
	            userOperation.setUserName(user.getUsername());
	            userOperation.setName(user.getName());
	            userOperation.setOperatingContent("修改商品类型");
	            userOperationMapper.saveUserOperation(userOperation);
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
	@RequiresPermissions("/delProductType")
	@ResponseBody
	public Map<String,Object> delProductType(BigInteger productTypeId,Product product,HttpSession session){
	    Map<String, Object> map = new HashMap<>();
	    // 先判断产品分类下是否还有商品，如果有则此分类不能删除---周嘉鑫20190729
	    int count = productTypeService.selectCountByTypeId(product);
	    if (count>0) {
	        map.put("code", 0);
            map.put("msg", "失败,产品分类下还有商品，不能删除");
            return map;
        }
		Integer row = productTypeService.delProductType(productTypeId);
		if(row == 1){
			 // 添加一条操作记录
            User user = (User)session.getAttribute("user");
            UserOperation userOperation = new UserOperation();
            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
            userOperation.setUserName(user.getUsername());
            userOperation.setName(user.getName());
            userOperation.setOperatingContent("删除商品类型");
            userOperationMapper.saveUserOperation(userOperation);
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
	     * @author chenshuxian   zhoujiaxin20190729查询时不考虑店铺ID
	     * @createDate
	 */
	@RequestMapping("/listProductType")
	@ResponseBody
	public Map<String,Object> listProductType(){
		Map<String, Object> map = new HashMap<>();
		List<ProductType> listProductType=productTypeService.listProductType();
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
	     * @author chenshuxian zhoujiaxin20190729查询时不考虑店铺ID
	     * @createDate 2019年7月3日
	 */
	@RequestMapping("/dimOrSelectAllproducts")
	@RequiresPermissions("/dimOrSelectAllproducts")
	@ResponseBody
	public Map<String,Object> allOrDimSelectProductType(String productTypeName,Integer page,Integer limit){
		Map<String,Object> map = new HashMap<>();
		List<ProductType> list=productTypeService.dimSelectProductType(productTypeName, page, limit);
		ProductType p = new ProductType();
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
	
	/**
     * 
     * @Title: selectByCanUse
     * @description 根据可用/可不用查询商品分类
     * @return ProductType    
     * @author liujunkai
     * @createDate 2019年7月12日
     */
	@RequestMapping("/selectByCanUse")
    @ResponseBody
    public Map<String,Object> selectByCanUse(HttpSession session) {
        Map<String,Object> map = new HashMap<>();
        //try {
           // BigInteger shopId=new BigInteger(session.getAttribute("shopId")+"");
            BigInteger shopId =null;
            List<ProductType> productType = productTypeService.selectByCanUse(shopId);
            map.put("code", 1);
            map.put("msg", "成功");
            map.put("data", productType);
        //} catch (Exception e) {
        //    e.printStackTrace();
         //   map.put("code", 0);
         //   map.put("msg", "查询失败");
       // }
       
        return map;
    }
}
