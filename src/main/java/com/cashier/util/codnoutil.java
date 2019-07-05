package com.cashier.util;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class codnoutil {
	/**
	 * 
	 * 生成订单编号
	 * @return
	 */
public  static String cood(BigInteger shopNum) {
	Date date = new Date();
	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddHHmmss");
	String timeNow = dateFormat.format(date).toString(); //当前日期字符串	
	//前标“云小讴”缩写
			String begin = "yxo";
			//随机生成一次后四位数字
			Random randomTwo = new Random();
			int x =randomTwo.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
			String endNum = String.format("%04d", x);
			//最终的订单号
			String orderNum = begin.concat(shopNum.toString()).concat(timeNow).concat(endNum);
	return orderNum;
	
}
}
