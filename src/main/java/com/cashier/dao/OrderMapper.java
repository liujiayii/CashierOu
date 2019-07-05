package com.cashier.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cashier.entity.Member;
import com.cashier.entity.Order;
import com.cashier.entity.Product;
import com.cashier.entityVo.OrderVo;

/**
 *
 * @ClassName: OrderMapper
 * @description 订单表的方法
 *
 * @author dujiawei
 * @createDate 2019年6月19日
 */
public interface OrderMapper {
	
	/**
	 * @Title: listOrderByOption
	 * @description 条件查询订单信息
	 * @param @param orderVo
	 * @return List<OrderVo>    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public List<OrderVo> listOrderByOption(OrderVo orderVo);
	
	/**
	 * @Title: countOrderByOption
	 * @description 条件查询订单数量
	 * @param @param orderVo
	 * @return OrderVo    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public OrderVo countOrderByOption(OrderVo orderVo);
	
	/**
	 * @Title: saveOrder
	 * @description 新增一条订单
	 * @param @param order
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public int saveOrder(Order order);
	
	/**
	 * @Title: getSumOrderAndSumOrderMoney
	 * @description 查询订单量和订单总金额
	 * @param @param orderVo
	 * @return OrderVo    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public OrderVo getSumOrderAndSumOrderMoney(OrderVo orderVo);
	
	/**
	 * @Title: updateOrderState
	 * @description 修改订单状态（退货）
	 * @param @param order
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public int updateOrderState(Order order);
     /**
      * 按条形码选择商品
      * @param barCode
      * @return
      */
	public Product querPreferences(String barCode);
	/**
	 * 按照手机号会员卡号查询会员信息
	 * @param number
	 * @param phone
	 * @return
	 */

	

	public Member Querymembershipstatus(@Param("number")String number, @Param("phone")String phone);
       /**
        * 查询活动id
        * @param productId
        * @param shopId
        * @return
        */
	public BigInteger queractivitybyid(@Param("productId")BigInteger productId,@Param("shopId") BigInteger shopId);
    /**
     * 查询折扣
     * @param activity_id
     * @return
     */
	public BigDecimal querdiscountbyid(@Param("activity_id")BigInteger activity_id,@Param("orderProduct")BigDecimal orderProduct);
  /**
          * 查询满减
   * @param activity_id
   * @param orderProduct
   * @return
   */
	public BigDecimal querdiscountsbyid(@Param("activity_id")BigInteger activity_id,@Param("orderProduct")BigDecimal orderProduct);
/**
 * 查询订单
 * @param i
 * @param totalMoney
 * @param orderNumber 
 * @return
 */
public int updatetotalMoney(@Param("i")int i,@Param("totalMoney") BigDecimal totalMoney, @Param("orderNumber")String orderNumber);
	
	



}
