package com.chinasofti.lexian.manager.category.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.manager.category.dao.CategoryDao;
import com.chinasofti.lexian.manager.category.po.CategoryPo;
import com.chinasofti.lexian.manager.category.vo.CategoryVo;
import com.chinasofti.lexian.manager.commodity.po.CommodityPo;
import com.chinasofti.lexian.manager.common.dao.BaseDao;

@Repository
public class CategoryDaoImpl extends BaseDao implements CategoryDao {
	@Override
	public List<CategoryPo> findCategories(CategoryVo categoryVo) {
		return selectList("findCategories", categoryVo);
	}
	
	@Override
	public List<CommodityPo> findCategoryCommodities(int categoryId) {
		return selectList("findCategoryCommodities", categoryId);
	}
	
	@Override
	public CategoryPo findCategoryByName(String categoryName) {
		return selectOne("findCategoryByName", categoryName);
	}
	
	@Override
	public void updateCategory(CategoryPo categoryPo) {
		update("updateCategory", categoryPo);
	}

	@Override
	public void addCategory(CategoryPo categoryPo) {
		insert("addCategory", categoryPo);
	}
	
	@Override
	public void deleteCategory(int categoryId) {
		delete("deleteCategory", categoryId);
	}

	@Override
	public CategoryPo findCategoryById(int categoryId) {
		return selectOne("findCategoryById", categoryId);
	}

	@Override
	public boolean existsCommodities(int categoryId) {
		return selectOne("existsCommodities", categoryId);
	}

	@Override
	public boolean existsSubcategories(int categoryId) {
		return selectOne("existsSubcategories", categoryId);
	}
}
