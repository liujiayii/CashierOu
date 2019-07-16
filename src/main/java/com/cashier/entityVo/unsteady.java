package com.cashier.entityVo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class unsteady {
	public BigInteger   activity_id;
	public BigDecimal member_price;
	public BigDecimal sale_price;
	public int  type;
	public String  productId;
	public String  no;
	
	public BigInteger getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(BigInteger activity_id) {
		this.activity_id = activity_id;
	}
	public BigDecimal getMember_price() {
		return member_price;
	}
	public void setMember_price(BigDecimal member_price) {
		this.member_price = member_price;
	}
	public BigDecimal getSale_price() {
		return sale_price;
	}
	public void setSale_price(BigDecimal sale_price) {
		this.sale_price = sale_price;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "unsteady [activity_id=" + activity_id + ", member_price=" + member_price + ", sale_price=" + sale_price
				+ ", type=" + type + ", productId=" + productId + ", no=" + no + "]";
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
	
	
}
