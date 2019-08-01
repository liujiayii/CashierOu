package com.cashier.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpRequest;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cashier.dao.InventoryMapper;
import com.cashier.dao.ProductMapper;
import com.cashier.dao.ShopMapper;
import com.cashier.entity.Inventory;
import com.cashier.entity.Product;
import com.cashier.entity.Shop;
import com.cashier.entityVo.ProductOnDisplayVo;
import com.cashier.entityVo.ProductVo;
import com.cashier.service.ProductService;
import com.cashier.service.ex.DataNotExistsException;
import com.cashier.service.ex.ProductBarCodeConflictException;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private InventoryMapper inventoryMapper;

	/**
	 * 
	 * @Title:添加商品（判断商品码是否重复） 添加商品对应的库存
	 * @description
	 * @param 商品实体类
	 * @return 成功添加条数
	 * @author chenshuxian
	 * @createDate 2019年6月18日
	 */
	@Override
	@Transactional
	public Integer insertProduct(Product product, BigInteger quantity,BigInteger inventoryWarning) {
		Integer row;
		// 判断商品码是否重复，如果重复自定义商品码
		Product p = productMapper.getProductByNameAndBarcode(product);
		if (p == null) {
			product.setCanUse(1);
			// 计入库存
			product.setInventory(1);
			// 执行添加操作
			row = productMapper.insertProduct(product);
			if (row == 0) {
				throw new DataNotExistsException("添加失败，请联系管理员");
			}
		} else {
			// 商品码重复，自定义商品码
			throw new ProductBarCodeConflictException("商品码重复");
		}

		// 在库存表中添加相应的商品及库存数量
		Inventory inventory = new Inventory();
		inventory.setProductId(product.getId());
		inventory.setQuantity(quantity);
		inventory.setShopId(product.getShopId());
		inventory.setInventoryWarning(inventoryWarning);
		// 判断此店铺是否是总店
		Shop shop = new Shop();
		shop.setId(product.getShopId());
		Shop s = shopMapper.getId(shop);
		if (s.getType() == 1) {
			// 总店 采购
			inventory.setPurchaseState(2);
		} else {
			// 分店 申购
			inventory.setPurchaseState(1);
		}
		// 执行添加库存操作
		row = inventoryMapper.insertInventory(inventory);
		if (row == 0) {
			throw new DataNotExistsException("添加失败，请联系管理员");
		}

		return row;
	}
	/**
	 * 
	     * @Title: getProductByBarCode
	     * @description 根据商品条码获得对应商品
	     * @param  商品条码
	     * @return   商品详情
	     * @author chenshuxian
	     * @createDate 2019年7月8日
	 */
	@Override
	public Product getProductByBarCode(Product product){
		return productMapper.getProductByNameAndBarcode(product);
	}
	/**
	 * 
	 * @Title:根据id修改商品
	 * @description
	 * @param 商品实体类
	 * @return 成功修改条数
	 * @author chenshuxian
	 * @createDate 2019年6月18日
	 */
	@Override
	@Transactional
	public Integer updateProductById(Product product) {
		Integer row;
		// 判断商品码是否重复，如果重复自定义商品码
		Product p = productMapper.getProductByNameAndBarcode(product);
		if (p == null) {

			// 执行操作
			row = productMapper.updateProductById(product);
			if (row == 0) {
				throw new DataNotExistsException("添加失败，请联系管理员");
			}
		} else {
			// 商品码重复，自定义商品码
			throw new ProductBarCodeConflictException("商品码重复，请自定义商品码");
		}
		return row;
	}

	/**
	 * 
	 * @Title: 根据商品id删除商品（软删除）
	 * @description
	 * @param 商品id
	 * @return 成功删除的条数
	 * @author chenshuxian
	 * @createDate 2019年6月18
	 */
	@Override
	public Integer delProductById(BigInteger productId) {

		return productMapper.delProductById(productId);
	}

	/**
	 * 
	 * @Title:条件查询所有商品
	 * @description
	 * @param 商品分类id
	 * @return 商品集合
	 * @author chenshuxian
	 * @createDate 2019年6月18
	 */
	@Override
	public Map<String, Object> getProductByCondition(String productName, BigInteger productTypeId,
			Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		Integer beginPage = limit * (page - 1);
		List<Product> listProduct = productMapper.getProductByCondition(productName, productTypeId,  beginPage,
				limit);
		Integer count = productMapper.getProductByConditionCount(productName, productTypeId);
		
		map.put("code", 1);
		map.put("count", count);
		map.put("listProduct", listProduct);
		return map;
	}

	/**
	 * 
	 * @Title:根据商品分类id得到商品列表 (用户 去重)
	 * @description
	 * @param 商品分类id
	 * @return 商品集合
	 * @author chenshuxian
	 * @createDate 2019年6月20日
	 */
	@Override
	public Map<String, Object> productsByType(BigInteger productTypeId,Integer page,
			Integer limit) {
		Map<String, Object> map = new HashMap<>();
		Integer beginPage = limit * (page - 1);
		List<ProductOnDisplayVo> listProduct = productMapper.productsByType(productTypeId,beginPage, limit);
		Map<String, ProductOnDisplayVo> m = new HashMap<>();
		for (ProductOnDisplayVo p : listProduct) {
			if (m.containsKey(p.getName())) {
				// 合并属性
				ProductOnDisplayVo pd = (ProductOnDisplayVo) m.get(p.getName());
				pd.getListProperty().add(p.getListProperty().get(0));
			} else {
				m.put(p.getName(), p);
			}
		}

		List<ProductOnDisplayVo> listProductOnDisplay = new ArrayList<>();

		for (String key : m.keySet()) {

			listProductOnDisplay.add(m.get(key));
		}
		map.put("code", 1);
		map.put("listProduct", listProductOnDisplay);
		return map;
	}

	/**
	 * 
	 * @Title: 根据id得到商品详情
	 * @description
	 * @param 商品id
	 * @return 商品实体类
	 * @author chenshuxian
	 * @createDate 2019年6月18
	 */
	@Override
	public Product getProductById(BigInteger productId) {

		return productMapper.getProductById(productId);
	}

	/**
	 * 
	 * @Title: groupByProductType
	 * @description 分组查询商品组
	 * @param 店铺id
	 * @return
	 * @author chenshuxian
	 * @createDate 2019年6月20日
	 */
	@Override
	public List<ProductVo> groupByProductType() {
		List<ProductVo> listProducts = productMapper.groupByProductType();
		Map<String, ProductOnDisplayVo> m = new HashMap<>();
		for (ProductVo pv : listProducts) {
			for (ProductOnDisplayVo p : pv.getListProduct()) {
				if (m.containsKey(p.getName())) {
					// 合并属性
					ProductOnDisplayVo pd = (ProductOnDisplayVo) m.get(p.getName());
					pd.getListProperty().add(p.getListProperty().get(0));
				} else {
					m.put(p.getName(), p);
				}
			}
			List<ProductOnDisplayVo> listProductOnDisplay = new ArrayList<>();

			for (String key : m.keySet()) {

				listProductOnDisplay.add(m.get(key));
			}
			pv.setListProduct(listProductOnDisplay);
		}

		return listProducts;
	}

	
	/**
	 * 生成商品条形码
	 * 
	 * @param filePath
	 *            商品条形码图片存放路径：C://barcode//images//
	 * 
	 * @param barCode
	 *            商品条形码：13位
	 * @param imgFormat
	 *            图片格式
	 * 
	 * @return 图片存放路径+图片名称+图片文件类型
	 */
	private static String barCode(String savePath, String jbarCode, String imgFormat, String imgName) {

		try {

			BufferedImage bi = null;

			// 实例化JBarcode
			// 这里三个参数，必要填写
			JBarcode jbarcode13 = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(),
					EAN13TextPainter.getInstance());

			// 获取到校验位
			String checkCode = jbarcode13.calcCheckSum(jbarCode);

			/*
			 * 最重要的是这里的设置，如果明白了这里的设置就没有问题 如果是默认设置， 那么设置就是生成一般的条形码 如果不是默认
			 * 设置，那么就可以根据自己需要设置
			 */
			// 尺寸，面积，大小
			jbarcode13.setXDimension(Double.valueOf(0.8).doubleValue());
			// 条形码高度
			jbarcode13.setBarHeight(Double.valueOf(20).doubleValue());
			// 宽度率
			jbarcode13.setWideRatio(Double.valueOf(20).doubleValue());
			// 是否校验最后一位，默认是false
			jbarcode13.setShowCheckDigit(true);
			// 生成二维码
			bi = jbarcode13.createBarcode(jbarCode);
			// 保存二维码图片

			File dirFile = new File(savePath);

			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			ImageIO.write(bi, imgFormat, dirFile);

			// 返回校验码
			return checkCode;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * 获得商品条码最后一位验证码
	 * 
	 * 
	 * @param barCode
	 *            商品条码前12位 只传12位
	 * 
	 * @return 商品条码最后一位验证码
	 */
	@Override
	public String barCode(String jbarCode) {
		// 实例化JBarcode
		// 这里三个参数，必要填写
		JBarcode jbarcode13 = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(),
				EAN13TextPainter.getInstance());

		// 获取到校验位
		String checkCode = null;
		try {
			checkCode = jbarcode13.calcCheckSum(jbarCode);
			// System.out.println(checkCode+"checkCode");
		} catch (InvalidAtributeException e) {

			e.printStackTrace();
		}
		return checkCode;
	}
	/**
	 * 修改状态(上架/下架)
	 * 
	 * @author chenshuxian
	 * @createDate 2019年7月18日 下午2:00
	 */
	@Override
	public Integer updateProductState(Product product) {

		return productMapper.updateProductState(product);
	}
	
	/**
     * @Title: selectCountByProductId
     * @description 先判断分店铺或总店铺里是否还有库存，如果有则此分类不能删除---周嘉鑫20190729
     * @param @param product
     * @param @return  
     * @return int    
     * @author zhoujiaxin
     * @createDate 2019年7月29日
     */
    @Override
    public int selectCountByProductId(Product product) {
        return productMapper.selectCountByProductId(product);
    }
	
}
