package com.chinasofti.lexian.mall.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

// 订单编号生成器
public class OrderNumberGenerator {
	public static String generateOrderNo(){
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(now);
	}
}
