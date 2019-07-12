package com.cashier.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.dao.LevelMapper;
import com.cashier.dao.MemberMapper;
import com.cashier.dao.OrderMapper;
import com.cashier.dao.OrderProductMapper;
import com.cashier.entity.Level;
import com.cashier.entity.Member;
import com.cashier.entity.Order;
import com.cashier.entity.OrderProduct;
import com.cashier.entity.Product;
import com.cashier.entityDTO.OrderDTO;
import com.cashier.entityDTO.TopTenProductDTO;
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
	@Autowired
	private OrderProductMapper orderProductMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private LevelMapper levelMapper;

	/**
	 * @Title: listOrderByOption
	 * @description 条件查询订单信息
	 * @param @param
	 *            orderVo
	 * @return List<OrderVo>
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public List<Order> listOrderByOption(OrderVo orderVo) {
		// TODO Auto-generated method stub
		return orderMapper.listOrderByOption(orderVo);
	}

	/**
	 * @Title: countOrderByOption
	 * @description 条件查询订单数量
	 * @param @param
	 *            orderVo
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
	 * @param @param
	 *            order
	 * @return int
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public int saveOrder(Order order) {
		// TODO Auto-generated method stub
		int num = 0;
		/* 生成随机数会员卡号 */
		// 获取当前日期字符串
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeNow = dateFormat.format(date).toString(); // 当前日期字符串
		// 获取当前店铺id
		// BigInteger shopNum = order.getShopId();
		BigInteger shopNum = new BigInteger("1");
		// 前标“云小讴”缩写
		String begin = "yxo";
		// 随机生成一次后四位数字
		Random randomTwo = new Random();
		int x = randomTwo.nextInt(9999 - 1000 + 1) + 1000;// 为变量赋随机值1000-9999
		String endNum = String.format("%04d", x);
		// 最终的订单号
		String orderNum = begin.concat(shopNum.toString()).concat(timeNow).concat(endNum);
		order.setNumber(orderNum);
		num = orderMapper.saveOrder(order); // 执行新增订单方法

		return num;
	}

	/**
	 * @Title: getSumOrderAndSumOrderMoney
	 * @description 查询订单量和订单总金额
	 * @param @param
	 *            orderVo
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
	 * @param @param
	 *            order
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
	 * 
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
	public Member Querymembershipstatus(String number, String phone) {
		// TODO Auto-generated method stub
		return orderMapper.Querymembershipstatus(number, phone);
	}

	@Override
	public BigDecimal querDiscount(BigInteger productId, BigInteger shopId, BigDecimal orderProduct) {
		/**
		 * 查询商品活动id
		 */
		BigInteger activity_id = orderMapper.queractivitybyid(productId, shopId);
         
		/**
		 * 查询折扣
		 */
		BigDecimal discount = orderMapper.querdiscountbyid(activity_id, orderProduct);
		/**
		 * 满减金额
		 */
		if (discount == null) {

			discount = orderMapper.querdiscountsbyid(activity_id, orderProduct);
			discount = orderProduct.subtract(discount);
		}

		if (discount == null) {
			discount = orderProduct;
		}
		return discount;
	}

	@Override
	public int updatetotalMoney(int i, String orderNumber,String out_trade_no) {
		/**订单应收金额*/
		BigDecimal payAdvance=new BigDecimal(0);
		
		 BigDecimal customDiscount=new BigDecimal(0);
		/**实付总金额*/
		BigDecimal totalMoney=new BigDecimal(0);
	
		BigDecimal Advance=new BigDecimal(0);
		List<OrderProduct> OrderProduct=orderProductMapper.listorderNumberOrderProduct(orderNumber);
		for(int t=0;t<OrderProduct.size();t++) {
			customDiscount=OrderProduct.get(t).getMemberPricediscount();
			totalMoney=totalMoney.add(customDiscount);
			Advance=OrderProduct.get(t).getMemberPrice();
			payAdvance=payAdvance.add(Advance);
		}
		orderMapper.Increasecumulativeconsumptio(orderNumber,totalMoney);
		Order Order=orderMapper.OrderByOption(orderNumber);
		/**
		 * 更新会员累计金额
		 */
        if(Order.getMemberNumber()!=null) {
	         int fig= orderMapper.Increasecumulativeconsumptio(Order.getMemberNumber(),totalMoney);
        }

		  /**
		   * 更新订单的实际付款金额
		   */

		 
        //查询会员的累计消费金额，并更新会员等级
        Member member = new Member();
        Level level = new Level();
        member.setNumber(Order.getMemberNumber());
        Member message = memberMapper.getMemberByNumber(member);  //通过会员卡号获取会员信息---message
        Level levelMSG = levelMapper.getMaxMoney(level);  //获取会员等级表中最高级别的消费上限金额
        //累计消费金额如果等于或者超过最高会员等级的最高金额，那么当前会员的等级就是最高级别
        if(message.getTotalMoney().compareTo(levelMSG.getMaximum()) == 1 || message.getTotalMoney().compareTo(levelMSG.getMaximum()) == 0){
        	member.setLdLevelId(levelMSG.getId());
        	member.setId(message.getId());
        	memberMapper.updateMemberMoneyAndLevel(member);
        	//System.out.println("最高级别*****");
        } else {  //若没有超过，根据累计消费金额查询对应的会员等级
        	level.setTotalMoney(message.getTotalMoney());
        	Level nowLevel = levelMapper.getLevelByMoney(level);
        	member.setLdLevelId(nowLevel.getId());
        	member.setId(message.getId());
        	memberMapper.updateMemberMoneyAndLevel(member);
        	//System.out.println("其他级别#####");
        }
        

		return orderMapper.updatetotalMoney(i, totalMoney, orderNumber,out_trade_no,payAdvance);
	}

	/**
	 * 
	     * @Title: getPercentageOfProductType
	     * @description 获得每种商品种类下的商品今日销售百分比
	     * @param  店铺id
	     * @return   
	     * @author chenshuxian
	     * @createDate 2019年7月8日
	 */
	@Override
	public Map<String,Object> getSumOrderByProductType(BigInteger shopId) {
		Map<String,Object> map = new HashMap<>();
		try {
			Integer count = 0;
			List<OrderDTO> listOrder= orderMapper.getSumOrderByProductType(shopId);
			NumberFormat numberFormat = NumberFormat.getInstance();  
			numberFormat.setMaximumFractionDigits(2);
			
			for(OrderDTO o : listOrder){
				count+=o.getCount();
				/*//将每种商品种类的商品订单量除以总量计算百分比
				String result = numberFormat.format((float)o.getCount()/(float)count * 100);
				System.out.println((float)o.getCount()/(float)count);
				map.put(o.getProductType(), result+"%");*/
			}
			for(OrderDTO o : listOrder){
				//将每种商品种类的商品订单量除以总量计算百分比
				String result = numberFormat.format((float)o.getCount()/(float)count * 100);
				map.put(o.getProductType(), result+"%");
			}
			map.put("code", 1);
			map.put("msg", "成功");
			map.put("listOrder", listOrder);
		} catch (Exception e) {
			
			e.printStackTrace();
			map.put("code", 0);
			map.put("msg", "失败");
		}
		
		System.out.println(map);
		return map;
	}

	/**
	 * 增加会员累计消费
	 */
	public int Increasecumulativeconsumptio(String orderNumber, BigDecimal totalMoney) {
		OrderVo	orderVo =new OrderVo();
		orderVo.setNumber(orderNumber);
	         Order Order=orderMapper.OrderByOption(orderNumber);
	         if(Order.getMemberNumber()!=null) {
		         int fig= orderMapper.Increasecumulativeconsumptio(Order.getMemberNumber(),totalMoney);
		         return fig;
	         }
	          
		
		return 1;
	}
  /**
   * 修改订单状态
   */
	@Override
	public int updateOrderStates(String out_trade_no, String total_fee) {
		// TODO Auto-generated method stub
		return orderMapper.updateOrderStates(out_trade_no,total_fee);
	}

	/**
	 * 
	     * @Title: getMonthSumOrderByProductType
	     * @description 获得当月每种商品销售总订单数 及销售额
	     * @param  店铺id
	     * @return   
	     * @author chenshuxian
	     * @createDate 2019年7月9日
	 */
	@Override
	public Map<String, Object> getMonthSumOrderByProductType(BigInteger shopId) {
		
		Map<String,Object> map = new HashMap<>();
		try {
			Integer count = 0;
			List<OrderDTO> listOrder= orderMapper.getMonthSumOrderByProductType(shopId);
			NumberFormat numberFormat = NumberFormat.getInstance();  
			numberFormat.setMaximumFractionDigits(2);
			for(OrderDTO o : listOrder){
				count+=o.getCount();
			}
			for(OrderDTO o : listOrder){
				//将每种商品种类的商品订单量除以总量计算百分比
				String result = numberFormat.format((float)o.getCount()/(float)count * 100);
				map.put(o.getProductType(), result+"%");
			}
			map.put("code", 1);
			map.put("msg", "成功");
			map.put("listOrder", listOrder);
		} catch (Exception e) {
			
			e.printStackTrace();
			map.put("code", 0);
			map.put("msg", "失败");
		}
		
		System.out.println(map);
		return map;
	}
	/**
	 * 
	     * @Title: getTopTenProduct
	     * @description 当月销售前十 商品名称及数量
	     * @param  店铺id
	     * @return  
	     * @author chenshuxian
	     * @createDate  2019年7月9日
	 */
	@Override
	public List<TopTenProductDTO> getTopTenProduct(BigInteger shopId) {
		
		return orderMapper.getTopTenProduct(shopId);
	}


}
