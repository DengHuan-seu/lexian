package com.chinasofti.lexian.mall.category.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.mall.category.dao.CategoryDao;
import com.chinasofti.lexian.mall.category.po.CategoryPathInfoPo;
import com.chinasofti.lexian.mall.category.po.CategoryPo;
import com.chinasofti.lexian.mall.common.dao.BaseDao;

@Repository
public class CategoryDaoImpl extends BaseDao implements CategoryDao {
	@Override
	public List<CategoryPo> getCategories(){
		return selectList("getCategories");
	}

	@Override
	public List<CategoryPo> getSubCategories(int parentId) {
		return selectList("getSubcategories", parentId);
	}
	
	@Override
	public CategoryPathInfoPo getCategoryPathForCategory(int categoryId) {
		return selectOne("getCategoryPathForCategory", categoryId);
	}

	@Override
	public CategoryPathInfoPo getCategoryPathForCommodity(String commodityNo) {
		return	selectOne("getCategoryPathForCommodity", commodityNo);
	}
}
