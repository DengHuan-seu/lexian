package com.chinasofti.lexian.mall.category.dao;

import java.util.List;

import com.chinasofti.lexian.mall.category.po.CategoryPathInfoPo;
import com.chinasofti.lexian.mall.category.po.CategoryPo;

public interface CategoryDao {
	public List<CategoryPo> getCategories();

	public List<CategoryPo> getSubCategories(int parentId);

	public CategoryPathInfoPo getCategoryPathForCategory(int categoryId);

	public CategoryPathInfoPo getCategoryPathForCommodity(String commodityNo);
}
