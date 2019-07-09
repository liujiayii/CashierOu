package com.cashier.entity;

import java.math.BigInteger;
import java.util.Date;

public class alipaylist {
	/**
	 * 主键
	 */
private  BigInteger id;

/**
 * 订单编号
 */
private  String  number;
/**
 *支付宝交易号 
 */
private  String  trade_no;
/**
 * 商户订单号
 */
private  String  out_trade_no;
/**
 * 买家支付宝账号
 */
private  String  buyer_logon_id;
/**
 * 结算币种订单金额
 */
private  String  settle_amount;
/**
 * 支付币种
 */
private  String  pay_currency;
/**
 * 支付币种订单金额
 */
private  String  pay_amount;
/**
 * 交易支付时间
 */
private  Date  gmt_payment;
/**
 * 结果
 */
private  Date  msg;
public BigInteger getId() {
	return id;
}
public void setId(BigInteger id) {
	this.id = id;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getTrade_no() {
	return trade_no;
}
public void setTrade_no(String trade_no) {
	this.trade_no = trade_no;
}
public String getOut_trade_no() {
	return out_trade_no;
}
public void setOut_trade_no(String out_trade_no) {
	this.out_trade_no = out_trade_no;
}
public String getBuyer_logon_id() {
	return buyer_logon_id;
}
public void setBuyer_logon_id(String buyer_logon_id) {
	this.buyer_logon_id = buyer_logon_id;
}
public String getSettle_amount() {
	return settle_amount;
}
public void setSettle_amount(String settle_amount) {
	this.settle_amount = settle_amount;
}
public String getPay_currency() {
	return pay_currency;
}
public void setPay_currency(String pay_currency) {
	this.pay_currency = pay_currency;
}
public String getPay_amount() {
	return pay_amount;
}
public void setPay_amount(String pay_amount) {
	this.pay_amount = pay_amount;
}
public Date getGmt_payment() {
	return gmt_payment;
}
public void setGmt_payment(Date gmt_payment) {
	this.gmt_payment = gmt_payment;
}
public Date getMsg() {
	return msg;
}
public void setMsg(Date msg) {
	this.msg = msg;
}
@Override
public String toString() {
	return "alipaylist [id=" + id + ", number=" + number + ", trade_no=" + trade_no + ", out_trade_no=" + out_trade_no
			+ ", buyer_logon_id=" + buyer_logon_id + ", settle_amount=" + settle_amount + ", pay_currency="
			+ pay_currency + ", pay_amount=" + pay_amount + ", gmt_payment=" + gmt_payment + ", msg=" + msg + "]";
}



}
