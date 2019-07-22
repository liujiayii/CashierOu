package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cashier.entity.OrderProduct;
import com.cashier.entityVo.OrderProductVo;

/**
 *
 * @ClassName: OrderProductMapper
 * @description 订单商品表的方法
 *
 * @author dujiawei
 * @createDate 2019年6月19日
 */
public interface OrderProductMapper {
	
	/**
	 * @Title: listOrderProduct
	 * @description 1.1、订单商品列表
	 * @param @param orderProductVo
	 * @return List<OrderProductVo>    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public List<OrderProductVo> listOrderProduct(OrderProductVo orderProductVo);
	
	/**
	 * @Title: countOrderProduct
	 * @description 1.2、订单商品列表的数量
	 * @param @param orderProductVo
	 * @return OrderProductVo    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public OrderProductVo countOrderProduct(OrderProductVo orderProductVo);
	
	/**
	 * @Title: saveOrderProduct
	 * @description 2、新增一条订单商品
	 * @param @param orderProduct
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public int saveOrderProduct(OrderProduct orderProduct);
	
	/**
	 * @Title: delOrderProduct
	 * @description 3、删除一条订单商品
	 * @param @param orderProduct
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public int delOrderProduct(OrderProduct orderProduct);
	
	/**
	 * @Title: listOrderProductByOrderNumber
	 * @description 4.1、通过订单号查询订单商品信息
	 * @param @param orderProductVo
	 * @return List<OrderProductVo>    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public List<OrderProductVo> listOrderProductByOrderNumber(OrderProductVo orderProductVo);
	
	/**
	 * @Title: countOrderProductByOrderNumber
	 * @description 4.2、通过订单号查询订单商品的数量
	 * @param @param orderProductVo
	 * @return OrderProductVo    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public OrderProductVo countOrderProductByOrderNumber(OrderProductVo orderProductVo);
      /**
       * 更新订单编号
       * @param string
       * @param number
       */
	public int upnumberbyid(@Param("product_id")BigInteger product_id, @Param("productCount")Integer productCount,@Param("shopId")BigInteger shopId);
     /**
      * 查询商品订单
      * @param orderNumber
      * @return
      */
	public List<OrderProduct> listorderNumberOrderProduct(String orderNumber);

}
