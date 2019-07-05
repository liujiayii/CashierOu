package com.cashier.entityVo;

import java.math.BigDecimal;

import com.cashier.entity.Product;



public class Productvos extends   Product{
	/**商品总价价 */
	private BigDecimal memberPrice;

	/**商品价折扣 */
	private BigDecimal memberPricediscount;
	/**商品数量*/
	private Integer productCount;

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	@Override
	public String toString() {
		return "Productvos [memberPrice=" + memberPrice + ", memberPricediscount=" + memberPricediscount
				+ ", productCount=" + productCount + "]";
	}

	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

	public BigDecimal getMemberPricediscount() {
		return memberPricediscount;
	}

	public void setMemberPricediscount(BigDecimal memberPricediscount) {
		this.memberPricediscount = memberPricediscount;
	}
}
