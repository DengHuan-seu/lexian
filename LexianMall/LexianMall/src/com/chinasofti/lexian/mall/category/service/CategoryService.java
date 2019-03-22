package com.chinasofti.lexian.mall.category.service;

import com.chinasofti.lexian.mall.common.util.ResultHelper;

public interface CategoryService {
	public ResultHelper getCategories();

	public ResultHelper getSubcategories(int parentId);

	public Object getCategoryPathForCategory(int categoryId);

	public Object getCategoryPathForCommodity(String commodityNo);
}
