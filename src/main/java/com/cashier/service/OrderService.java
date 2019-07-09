package com.cashier.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cashier.entity.Member;
import com.cashier.entity.Order;
import com.cashier.entity.Product;
import com.cashier.entityDTO.OrderDTO;
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
	public List<OrderVo> listOrderByOption(OrderVo orderVo);

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
	 * @return
	 */
	public Product querPreferences(String barCode);

	/**
	 * 查询会员状态
	 * 
	 * @param number
	 * @return
	 */

	public Member Querymembershipstatus(String number, String phone);

	/**
	 * 查询商品折扣的价格
	 * 
	 * @param shopId
	 * @param shopId2
	 * @return
	 */

	public BigDecimal querDiscount(BigInteger id, BigInteger shopId, BigDecimal memberPrice);

	/**
	 * 现金支付
	 * 
	 * @param i
	 * @param totalMoney
	 * @param orderNumber
	 * @return
	 */
	public int updatetotalMoney(int i, BigDecimal totalMoney, String orderNumber);

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
}
