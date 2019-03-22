package com.chinasofti.lexian.manager.category.service;

import com.chinasofti.lexian.manager.category.vo.CategoryVo;
import com.chinasofti.lexian.manager.common.util.ResultHelper;

public interface CategoryService {
	public ResultHelper findCategories(CategoryVo categoryVo);
	
	public ResultHelper findCategoryCommodities(int categoryId);
	
	public ResultHelper updateCategory(CategoryVo categoryVo);
	
	public ResultHelper addCategory(CategoryVo categoryVo);
	
	public ResultHelper deleteCategory(int categoryId);
}
