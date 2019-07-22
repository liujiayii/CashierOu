package com.cashier.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.cashier.entity.Member;
import com.cashier.entity.Order;
import com.cashier.entity.Product;
import com.cashier.entityDTO.OrderDTO;
import com.cashier.entityDTO.TopTenProductDTO;
import com.cashier.entityVo.OrderVo;

/**
 *
 * @ClassName: OrderService
 * @description 订单表的方法
 *
 * @author dujiawei
 * @createDate 2019年6月19日
 */
public interface OrderService {

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
	 * 按条形码查询价格
	 * 
	 * @param barCode
	 * @param shopId 
	 * @return
	 */
	public Product querPreferences(String barCode, BigInteger shopId);

	/**
	 * 查询会员状态
	 * 
	 * @param number
	 * @return
	 */

	public Member Querymembershipstatus(String phone);

	/**
	 * 查询商品折扣的价格
	 * 
	 * @param shopId
	 * @param shopId2
	 * @return
	 */

	public Map<String,BigDecimal> querDiscount(String id, BigInteger shopId);

	/**
	 * 现金支付
	 * 
	 * @param i
	 * @param totalMoney
	 * @param orderNumber
	 * @return
	 */
	public int updatetotalMoney(int i, String authCode, String totalAmount, String out_trade_no,BigInteger shopId,String remark,int type,String nubber);

	/**
	 * 
	     * @Title: getPercentageOfProductType
	     * @description 获得每种商品种类下的商品今日销售百分比
	     * @param  店铺id
	     * @return   
	     * @author chenshuxian
	     * @createDate 2019年7月8日
	 */
	public Map<String, Object> getSumOrderByProductType(BigInteger shopId);

	/**
	 * 增加会员累计消费
	 * @param orderNumber
	 * @param totalMoney
	 * @return
	 */

	public int Increasecumulativeconsumptio(String orderNumber, BigDecimal totalMoney);
  /**
   * 修改订单状态
   * @param out_trade_no
   * @param total_fee
   * @return
   */
	public int updateOrderStates(String out_trade_no, String total_fee);




	
	
	/**
	 * 
	     * @Title: getMonthSumOrderByProductType
	     * @description  获得当月每种商品销售总订单数 及销售额
	     * @param  店铺id
	     * @return    
	     * @author chenshuxian
	     * @createDate 2019年7月9日
	 */
	public Map<String, Object> getMonthSumOrderByProductType(BigInteger shopId);
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
      * 存储支付宝支付的返回数据
      * @param result
      */
	public void severAlipayF2FPayResult(AlipayF2FPayResult result);
 /**
  * 
  * @param barCode
  * @param shopId
  * @return
  */
	
	public List<GoodsDetail> goodsDetailList(String barCode,BigInteger shopId);
}
