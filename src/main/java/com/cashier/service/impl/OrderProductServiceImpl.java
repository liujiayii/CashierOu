package com.cashier.service.impl;

import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cashier.dao.OrderProductMapper;
import com.cashier.entity.OrderProduct;
import com.cashier.entityVo.OrderProductVo;
import com.cashier.service.OrderProductService;

/**
 *
 * @ClassName: OrderProductServiceImpl
 * @description 订单商品表的service实现层
 *
 * @author dujiawei
 * @createDate 2019年6月19日
 */
@Service
public class OrderProductServiceImpl implements OrderProductService {
	
	@Autowired
	private OrderProductMapper orderProductMapper;

	/**
	 * @Title: listOrderProduct
	 * @description 1.1、订单商品列表
	 * @param @param orderProductVo
	 * @return List<OrderProductVo>    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public List<OrderProductVo> listOrderProduct(OrderProductVo orderProductVo) {
		// TODO Auto-generated method stub
		return orderProductMapper.listOrderProduct(orderProductVo);
	}

	/**
	 * @Title: countOrderProduct
	 * @description 1.2、订单商品列表的数量
	 * @param @param orderProductVo
	 * @return OrderProductVo    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public OrderProductVo countOrderProduct(OrderProductVo orderProductVo) {
		// TODO Auto-generated method stub
		return orderProductMapper.countOrderProduct(orderProductVo);
	}

	/**
	 * @Title: saveOrderProduct
	 * @description 2、新增一条订单商品
	 * @param @param orderProduct
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public int saveOrderProduct(OrderProduct orderProduct) {
		// TODO Auto-generated method stub
		return orderProductMapper.saveOrderProduct(orderProduct);
	}

	/**
	 * @Title: delOrderProduct
	 * @description 3、删除一条订单商品
	 * @param @param orderProduct
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public int delOrderProduct(OrderProduct orderProduct) {
		// TODO Auto-generated method stub
		return orderProductMapper.delOrderProduct(orderProduct);
	}

	/**
	 * @Title: listOrderProductByOrderNumber
	 * @description 4.1、通过订单号查询订单商品信息
	 * @param @param orderProductVo
	 * @return List<OrderProductVo>    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public List<OrderProductVo> listOrderProductByOrderNumber(OrderProductVo orderProductVo) {
		// TODO Auto-generated method stub
		return orderProductMapper.listOrderProductByOrderNumber(orderProductVo);
	}

	/**
	 * @Title: countOrderProductByOrderNumber
	 * @description 4.2、通过订单号查询订单商品的数量
	 * @param @param orderProductVo
	 * @return OrderProductVo    
	 * @author dujiawei
	 * @createDate 2019年6月19日
	 */
	@Override
	public OrderProductVo countOrderProductByOrderNumber(OrderProductVo orderProductVo) {
		// TODO Auto-generated method stub
		return orderProductMapper.countOrderProductByOrderNumber(orderProductVo);
	}



	@Override
	public List<OrderProduct> listorderNumberOrderProduct(String orderNumber) {
		// TODO Auto-generated method stub
		return orderProductMapper.listorderNumberOrderProduct(orderNumber);
	}
      /**
       * 减少库存
       */
	@Override
	public int upnumberbyid(BigInteger id, Integer productCount,BigInteger shopId) {
		// TODO Auto-generated method stub
		return orderProductMapper.upnumberbyid(id,productCount,shopId);
	}

}
