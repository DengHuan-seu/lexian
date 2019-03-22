package com.chinasofti.lexian.manager.category.constant;

public class CategoryConstant {
	private CategoryConstant() {
	}
	
	public static final String success = "操作成功！";
	public static final String duplicate_name = "已经存在同名类别";
	public static final String subcategories_exist = "存在下级类别，无法删除本类别";
	public static final String commodities_exist = "本类别下存在商品，无法删除";
	public static final String categoryname_null = "类别名称不能为空";
	public static final String categoryname_max_length = "类别名称长度超出最大限制";
	
}
