package com.cashier.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.cashier.entity.Member;
import com.cashier.entity.Order;
import com.cashier.entity.Product;
import com.cashier.entityDTO.OrderDTO;
import com.cashier.entityDTO.TopTenProductDTO;
import com.cashier.entityVo.OrderVo;
import com.cashier.entityVo.unsteady;

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
	 * @param @param
	 *            orderVo
	 * @return List<OrderVo>
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public List<Order> listOrderByOption(OrderVo orderVo);

	/**
	 * @Title: countOrderByOption
	 * @description 条件查询订单数量
	 * @param @param
	 *            orderVo
	 * @return OrderVo
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public OrderVo countOrderByOption(OrderVo orderVo);

	/**
	 * @Title: saveOrder
	 * @description 新增一条订单
	 * @param @param
	 *            order
	 * @return int
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public int saveOrder(Order order);

	/**
	 * @Title: getSumOrderAndSumOrderMoney
	 * @description 查询订单量和订单总金额
	 * @param @param
	 *            orderVo
	 * @return OrderVo
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public OrderVo getSumOrderAndSumOrderMoney(OrderVo orderVo);

	/**
	 * @Title: updateOrderState
	 * @description 修改订单状态（退货）
	 * @param @param
	 *            order
	 * @return int
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	public int updateOrderState(Order order);

	/**
	 * 按条形码选择商品
	 * 
	 * @param barCode
	 * @return
	 */
	public Product querPreferences(@Param("barCode")String barCode);

	/**
	 * 按照手机号会员卡号查询会员信息
	 * 
	 * @param number
	 * @param phone
	 * @return
	 */

	public Member Querymembershipstatus(String phone);

	/**
	 * 查询活动id
	 * 
	 * @param productId
	 * @param shopId
	 * @return
	 * 
	 * 
	 */
	public unsteady queractivitybyid(@Param("productId") String productId, @Param("shopId") BigInteger shopId,@Param("orderProduct")String orderProduct);
	//public List<unsteady> queractivitybyid(@Param("productId") String productId, @Param("shopId") BigInteger shopId,@Param("orderProduct")BigDecimal orderProduct);
	



	/**
	 * 查询折扣
	 * 
	 * @param activity_id
	 * @return
	 */
	public BigDecimal querdiscountbyid(@Param("activity_id") BigInteger activity_id,
			@Param("orderProduct") BigDecimal orderProduct);

	/**
	 * 查询满减
	 * 
	 * @param activity_id
	 * @param orderProduct
	 * @return
	 */
	public BigDecimal querdiscountsbyid(@Param("activity_id") BigInteger activity_id,
			@Param("orderProduct") BigDecimal orderProduct);

	/**
	 * 修改实际付款金额
	 * 
	 * @param i
	 * @param totalMoney
	 * @param orderNumber
	 * @return
	 */
	public int updatetotalMoney(@Param("i") int i, @Param("totalMoney") BigDecimal totalMoney,
			@Param("orderNumber") String orderNumber,@Param("out_trade_no")String out_trade_no,@Param("payAdvance")BigDecimal payAdvance);
	/**
	 * 
	 * @Title: getSumOrderByProductType
	 * @description 查询当天每种商品种类 销售数量
	 * @param 店铺id
	 * @return
	 * @author chenshuxian
	 * @createDate 2019年7月8日
	 */
	public List<OrderDTO> getSumOrderByProductType(BigInteger shopId);

   /*
    * 修改累计积分
    */
	public int Increasecumulativeconsumptio(@Param("memberNumber")String memberNumber, @Param("totalMoney")BigDecimal totalMoney);
    /**
     * 查询订单的会员信息
     * @param orderNumber
     * @return
     */
	public Order OrderByOption(String orderNumber);
  /**
   * 修改退货
   * @param out_trade_no
   * @param total_fee
   * @return
   */
	public int updateOrderStates(@Param("out_trade_no")String out_trade_no, @Param("total_fee")String total_fee);
	/**
	 * 
	     * @Title: getMonthSumOrderByProductType
	     * @description  获得当月每种商品销售总订单数 及销售额
	     * @param  店铺id
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月9日
	 */
	public List<OrderDTO> getMonthSumOrderByProductType(BigInteger shopId);
	/**
	 * 
	     * @Title: getTopTenProduct
	     * @description 当月销售前十 商品名称及数量
	     * @param  店铺id
	     * @return  
	     * @author chenshuxian
	     * @createDate  2019年7月9日
	 */
	public List<TopTenProductDTO> getTopTenProduct(BigInteger shopId);

	
	
	/**
	 * 
	     * @Title: getTopTenProduct
	     * @description 插入订单定时表
	     * @param  店铺id
	     * @return  
	     * @author chenshuxian
	     * @createDate  2019年7月9日
	 */
	
	public void islist(unsteady unsteady);
	/**
	 * 查询价格
	 * @param no
	 * @return
	 */

	public List<unsteady> queractivitybyids(String no);
  /*
   * 
   * 查询商品信息
   */
	public Product querproductbyid(String id);
 /**
  *   没有参加活动的情况
  * @param productId
  * @param shopId
  * @param orderProduct
  * @return
  */
	public unsteady queractivitybyidde(@Param("productId") String productId, @Param("shopId") BigInteger shopId,@Param("orderProduct")String orderProduct);
/**
 * 存储返回的数据
 * @param result
 */
public void severAlipayF2FPayResult(AlipayF2FPayResult result);



	
	
         
}
