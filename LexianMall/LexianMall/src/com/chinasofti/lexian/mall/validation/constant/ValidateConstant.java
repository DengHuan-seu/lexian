package com.chinasofti.lexian.mall.validation.constant;

public final class ValidateConstant {
	// 注册
	public static final int type_register = 1;
	// 忘记密码
	public static final int type_forgetpassword = 2;
	// 修改密码
	public static final int type_changepassword = 3;
	// 充值
	public static final int type_charge = 4;
	// 转账
	public static final int type_transfer = 5;
	// 付款
	public static final int type_pay = 6;
	
	
	// 手机平台
	public static final int platform_phone = 1;
	// pc平台
	public static final int platform_pcweb = 2;
	// 微官网
	public static final int platform_microweb = 3;
	// 全平台
	public static final int platform_all = 4;
	
	public static final String[] contents = { 
			"您的手机注册验证码为", 
			"您的重置密码验证码为", 
			"您的修改密码验证码为",
			"您的充值的验证码为", 
			"您的转账验证码为",
			"您的钱包付款验证码为"
	};

	public static final String invalid_arguments = "参数错误";
	public static final String invalid_user = "无效的用户";
	public static final String duplicate_user = "用户已经存在";
	public static final String invalid_type = "无效的验证码类型";
	public static final String invalid_platform = "无效的平台类型";
	public static final String code_notexpired = "对不起，请一分钟后再获取验证码";
	public static final String code_outoflimit = "对不起，已超过发送短信条数";
	public static final String success = "获取验证码成功";
	public static final String failed = "验证码获取失败";
	public static final String wrong_code = "验证码不匹配";
}
