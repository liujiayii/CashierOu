package com.cashier.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cashier.dao.UserOperationMapper;
import com.cashier.entity.Product;
import com.cashier.entity.User;
import com.cashier.entity.UserOperation;
import com.cashier.entityVo.ProductVo;
import com.cashier.service.ProductService;
import com.cashier.service.ex.ServiceException;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private UserOperationMapper userOperationMapper;
	/**
	 * 
	     * @Title: addProduct
	     * @description 添加商品
	     * @param  商品实体类，库存数量
	     * @return    
	     * @author chenshuxian
	     * @createDate
	 */
	@RequestMapping("/addProduct")
	@RequiresPermissions("/addProduct")
	@ResponseBody
	public Map<String,Object> addProduct(HttpServletRequest request,HttpSession session,Product product,BigInteger quantity,BigInteger inventoryWarning){
		Map<String, Object> map = new HashMap<>();
		//设置店铺id，
		product.setShopId(new BigInteger(session.getAttribute("shopId")+""));
		try {
			if(product.getBarCode()==null||product.getBarCode().equals("")){
				//自动生成商品条码
				String numStr = "";
				String trandStr = String.valueOf((Math.random() * 9 + 1) * 10000);
				String dataStr = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
				numStr = trandStr.toString().substring(0, 5);
				numStr = numStr + dataStr.substring(7, 14);
				String check = productService.barCode(numStr);
				//System.out.println(check+"check");
				product.setBarCode(numStr+check);
			}
			Integer row =productService.insertProduct(product,quantity,inventoryWarning);
			if(row!=0){
				 // 添加一条操作记录
	            User user = (User)session.getAttribute("user");
	            UserOperation userOperation = new UserOperation();
	            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
	            userOperation.setUserName(user.getUsername());
	            userOperation.setName(user.getName());
	            userOperation.setOperatingContent("添加商品");
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
     * @Title: createProduct
     * @description 修改商品信息
     * @param  
     * @return    
     * @author chenshuxian
     * @createDate
	 */
	@RequestMapping("/createProduct")
	@RequiresPermissions("/createProduct")
	@ResponseBody
	public Map<String,Object> updateProduct(Product product,HttpSession session){
		Map<String, Object> map = new HashMap<>();
		product.setShopId(new BigInteger(session.getAttribute("shopId")+""));
		try {
			Integer row = productService.updateProductById(product);
			if(row == 1){
				 // 添加一条操作记录
	            User user = (User)session.getAttribute("user");
	            UserOperation userOperation = new UserOperation();
	            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
	            userOperation.setUserName(user.getUsername());
	            userOperation.setName(user.getName());
	            userOperation.setOperatingContent("修改商品");
	            userOperationMapper.saveUserOperation(userOperation);
				map.put("code", 1);
				map.put("msg", "成功");
			}
		} catch (ServiceException e) {
			map.put("code", 0);
			map.put("msg", e.getMessage());
		}
		
		
		return map ;
	}
	/**
	 * 
	     * @Title: delProduct
	     * @description 根据商品id删除商品（软删除）
	     * @param  商品id
	     * @return 
	     * @author chenshuxian
	     * @createDate
	 */
	@RequestMapping("/delProduct")
	@RequiresPermissions("/delProduct")
	@ResponseBody
	public Map<String,Object> delProduct(BigInteger productId,Product product,HttpSession session){
		Map<String, Object> map = new HashMap<>();
		// 先判断分店铺或总店铺里是否还有库存，如果有则此分类不能删除---周嘉鑫20190729
		product.setId(productId);
        int count = productService.selectCountByProductId(product);
        if (count>0) {
            map.put("code", 0);
            map.put("msg", "失败,总店或分店下还有商品库存，不能删除");
            return map;
        }
		Integer row = productService.delProductById(productId);
		if(row == 1){
			 // 添加一条操作记录
            User user = (User)session.getAttribute("user");
            UserOperation userOperation = new UserOperation();
            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
            userOperation.setUserName(user.getUsername());
            userOperation.setName(user.getName());
            userOperation.setOperatingContent("删除商品");
            userOperationMapper.saveUserOperation(userOperation);
			map.put("code", 1);
			map.put("msg", "成功");
		}else{
			map.put("code", 0);
			map.put("msg", "失败");
		}
		return map ;
	}
	/**
     * @Title: 
     * @description 分页查询所有商品
     * @param   页数，条数
     * @return 商品列表
     * @author chenshuxian
     * @createDate 2019年6月18日
	 *//*
	@RequestMapping("/listProduct")
	@ResponseBody
	public Map<String,Object> listProduct(Integer page,Integer limit){
		BigInteger shopId = new BigInteger("1");
		Map<String, Object> map = productService.listProduct(shopId, page, limit);
		return map ;
	}*/
	/**
	 * 
	     * @Title: 
	     * @description 根据分类id得到所有商品 （前台用户 去重）
	     * @param  分类id 店铺id 页数，条数
	     * @return 商品列表
	     * @author chenshuxian
	     * @createDate 2019年6月18日
	 */
	@RequestMapping("/ProductsByType")
	@ResponseBody
	public Map<String,Object> ProductsByType(BigInteger productTypeId,Integer page,Integer limit){
		Map<String, Object> map = productService.productsByType(productTypeId,page, limit);
		return map ;
	}
	/**
	 * 
	     * @Title: 
	     * @description 根据商品id获得商品
	     * @param  商品id
	     * @return  
	     * @author chenshuxian
	     * @createDate
	 */
	@RequestMapping("/getProduct")
	@ResponseBody
	public Map<String,Object> getProductById(BigInteger productId){
		Map<String, Object> map = new HashMap<>();
		Product product = productService.getProductById(productId);
		map.put("code", 1);
		map.put("msg", "成功");
		map.put("product", product);
		return map ;
	}
	/**
	 * 
	     * @Title: groupByProductType
	     * @description 分组获得商品详情
	     * @param  
	     * @return   
	     * @author chenshuxian
	     * @createDate
	 */
	@RequestMapping("/groupByProductType")
	@ResponseBody
	public Map<String,Object> groupByProductType(HttpSession session){
		Map<String, Object> map = new HashMap<>();
		List<ProductVo> product= productService.groupByProductType();
		map.put("code", 1);
		map.put("msg", "成功");
		map.put("product", product);
		return map ;
	}
	/**
	 * 
	     * @Title: getProductByCondition
	     * @description 条件查询商品详情
	     * @param  模糊查询数据，店铺id，分类id，页数，条数
	     * @return   
	     * @author chenshuxian	
	     * @createDate 2019年7月3日
	 */
	@RequestMapping("/getProductByCondition")
	@RequiresPermissions("/getProductByCondition")
	@ResponseBody
	public Map<String,Object> getProductByCondition(String productName,BigInteger productTypeId,Integer page,Integer limit){
		Map<String,Object> map=productService.getProductByCondition(productName, productTypeId, page, limit);
		return map;
	}
	
	/**
	 * 
	     * @Title: 获得商品条码图片内容
	     * @description createCodeImage
	     * @param  number 商品条码
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月4日
	 */
	@RequestMapping("/missionCreateCodeImage")
	public String createCodeImage(String number,HttpServletResponse response) {
        try {
			BufferedImage bi = null;
			// 实例化JBarcode
			// 这里三个参数，必要填写
			JBarcode jbarcode13 = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(),
					EAN13TextPainter.getInstance());
			/*// 获取到校验位
			String checkCode = jbarcode13.calcCheckSum(number.substring(0,11));
            */
			/*
			 * 最重要的是这里的设置，如果明白了这里的设置就没有问题 如果是默认设置， 那么设置就是生成一般的条形码 如果不是默认
			 * 设置，那么就可以根据自己需要设置
			 */
			// 尺寸，面积，大小
			jbarcode13.setXDimension(Double.valueOf(0.7).doubleValue());
			// 条形码高度
			jbarcode13.setBarHeight(Double.valueOf(15).doubleValue());
			// 宽度率
			jbarcode13.setWideRatio(Double.valueOf(7).doubleValue());
			// 是否校验最后一位，默认是false
			jbarcode13.setShowCheckDigit(true);
			// 生成二维码
			//System.out.println(number.substring(0,12));
			if(number==null||number.equals("")||number.length()<12) {
				 
			}
			bi = jbarcode13.createBarcode(number.substring(0,12));
            response.reset();
            response.setContentType("image/jpeg");
            OutputStream ops =
                    response.getOutputStream();
            javax.imageio.ImageIO.write(bi, "jpeg", ops);
            ops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	/**
	 * 
	     * @Title: getProductByBarCode
	     * @description 根据商品条码获得商品
	     * @param  商品条码
	     * @return  
	     * @author chenshuxian
	     * @createDate 2019年7月8日
	 */
	@RequestMapping("/getProductByBarCode")
	@ResponseBody
	public Map<String,Object> getProductByBarCode(Product product){
		Map<String,Object> map = new HashMap<>();
		Product p = productService.getProductByBarCode(product);
		if(p != null && !p.equals("")){
			map.put("product", p);
			map.put("code", 1);
			map.put("msg", "成功");
		}else{
			map.put("code", 0);
			map.put("msg", "商品为空或不存在，请核实");
		}
		return map;
		
	}
	
	/**
	 * description 修改商品状态(上架/下架)
	 *
	 * @author chenshuxian
	 * @createDate 2019 年7月18日 下午2:00
	 */
	@RequestMapping("/updateProductState")
	@RequiresPermissions("/updateProductState")
	@ResponseBody
	public Map<String, Object> updateProductState(Product product,HttpSession session) {
		int row = productService.updateProductState(product);
		Map<String, Object> map = new HashMap<String, Object>();
		if (row == 1) {
			 // 添加一条操作记录
            User user = (User)session.getAttribute("user");
            UserOperation userOperation = new UserOperation();
            userOperation.setShopId(new BigInteger(session.getAttribute("shopId")+""));
            userOperation.setUserName(user.getUsername());
            userOperation.setName(user.getName());
            userOperation.setOperatingContent("修改商品状态");
            userOperationMapper.saveUserOperation(userOperation);
			map.put("code", 1);
			map.put("msg", "成功");
			
		} else {
			map.put("code", 0);
			map.put("msg", "失败");
		}
		return map;

	}
}
