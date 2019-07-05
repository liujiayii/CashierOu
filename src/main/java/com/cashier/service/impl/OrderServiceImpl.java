package com.cashier.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cashier.dao.OrderMapper;
import com.cashier.entity.Member;
import com.cashier.entity.Order;
import com.cashier.entity.Product;
import com.cashier.entityVo.OrderVo;
import com.cashier.service.OrderService;

/**
 *
 * @ClassName: OrderServiceImpl
 * @description 订单表的Service实现层
 *
 * @author dujiawei
 * @createDate 2019年6月19日
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	/**
	 * @Title: listOrderByOption
	 * @description 条件查询订单信息
	 * @param @param orderVo
	 * @return List<OrderVo>    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public List<OrderVo> listOrderByOption(OrderVo orderVo) {
		// TODO Auto-generated method stub
		return orderMapper.listOrderByOption(orderVo);
	}

	/**
	 * @Title: countOrderByOption
	 * @description 条件查询订单数量
	 * @param @param orderVo
	 * @return OrderVo    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public OrderVo countOrderByOption(OrderVo orderVo) {
		// TODO Auto-generated method stub
		return orderMapper.countOrderByOption(orderVo);
	}

	/**
	 * @Title: saveOrder
	 * @description 新增一条订单
	 * @param @param order
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public int saveOrder(Order order) {
		// TODO Auto-generated method stub
		int num = 0;
		/*生成随机数会员卡号*/	
		//获取当前日期字符串
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddHHmmss");
		String timeNow = dateFormat.format(date).toString(); //当前日期字符串		
		//获取当前店铺id
		//BigInteger shopNum = order.getShopId();
		BigInteger shopNum = new BigInteger("1");
		//前标“云小讴”缩写
		String begin = "yxo";
		//随机生成一次后四位数字
		Random randomTwo = new Random();
		int x =randomTwo.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
		String endNum = String.format("%04d", x);
		//最终的订单号
		String orderNum = begin.concat(shopNum.toString()).concat(timeNow).concat(endNum);
		order.setNumber(orderNum);
		num = orderMapper.saveOrder(order); //执行新增订单方法
		
		return num;
	}

	/**
	 * @Title: getSumOrderAndSumOrderMoney
	 * @description 查询订单量和订单总金额
	 * @param @param orderVo
	 * @return OrderVo    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public OrderVo getSumOrderAndSumOrderMoney(OrderVo orderVo) {
		// TODO Auto-generated method stub
		return orderMapper.getSumOrderAndSumOrderMoney(orderVo);
	}

	/**
	 * @Title: updateOrderState
	 * @description 修改订单状态（退货）
	 * @param @param order
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public int updateOrderState(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrderState(order);
	}
  /*
   * 按条形码选择商品(non-Javadoc)
   * @see com.cashier.service.OrderService#querPreferences(java.lang.String)
   */
	@Override
	public Product querPreferences(String barCode) {
		
		return orderMapper.querPreferences(barCode);
	}
   /**
    * 按照会员卡号查询
    */
	@Override
	public Member Querymembershipstatus(String number,String phone) {
		// TODO Auto-generated method stub
		return orderMapper.Querymembershipstatus(number,phone);
	}

@Override
public BigDecimal querDiscount(BigInteger productId, BigInteger shopId,BigDecimal orderProduct) {
	/**
	 * 查询商品活动id
	 */
	BigInteger activity_id=orderMapper.queractivitybyid(productId,shopId);
	
  /**
   * 查询折扣
   */
	BigDecimal discount=orderMapper.querdiscountbyid(activity_id,orderProduct);
	/**
	 * 满减金额
	 */
	if(discount==null) {
	
	  discount=orderMapper.querdiscountsbyid(activity_id,orderProduct);
	  discount=orderProduct.subtract(discount);
	}
	
	if (discount==null) {
		discount=new BigDecimal(0);
	}
	return discount;
}

@Override
public int updatetotalMoney(int i, BigDecimal totalMoney, String orderNumber) {
	// TODO Auto-generated method stub
	return orderMapper.updatetotalMoney(i,totalMoney,orderNumber);
}

}
