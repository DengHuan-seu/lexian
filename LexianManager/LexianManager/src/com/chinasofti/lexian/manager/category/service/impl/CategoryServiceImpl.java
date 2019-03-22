package com.chinasofti.lexian.manager.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.lexian.manager.category.constant.CategoryConstant;
import com.chinasofti.lexian.manager.category.dao.CategoryDao;
import com.chinasofti.lexian.manager.category.po.CategoryPo;
import com.chinasofti.lexian.manager.category.service.CategoryService;
import com.chinasofti.lexian.manager.category.vo.CategoryVo;
import com.chinasofti.lexian.manager.commodity.po.CommodityPo;
import com.chinasofti.lexian.manager.common.service.BaseService;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ResultHelper;

@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {

	private CategoryDao categoryDao;

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@Override
	public ResultHelper findCategories(CategoryVo categoryVo) {
		List<CategoryPo> pos = categoryDao.findCategories(categoryVo);
		List<CategoryVo> vos = new ArrayList<CategoryVo>();
		for(CategoryPo po : pos){
			CategoryVo vo = new CategoryVo(po);
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, CategoryConstant.success,
				vos, categoryVo.getTotal());
	}
	
	@Override
	public ResultHelper findCategoryCommodities(int categoryId) {
		List<CommodityPo> list = categoryDao.findCategoryCommodities(categoryId);
		return new ResultHelper(Constant.success_code, CategoryConstant.success, list);
	}
	
	@Override
	public ResultHelper updateCategory(CategoryVo categoryVo) {
		CategoryPo po = new CategoryPo();
		po.setId(categoryVo.getId());
		po.setCategoryName(categoryVo.getCategoryName());
		
		CategoryPo existedCategory = categoryDao.findCategoryByName(po.getCategoryName());
		
		if(existedCategory == null){	// 没有同名类别，直接更新类别名称
			categoryDao.updateCategory(po);
			return new ResultHelper(Constant.success_code, CategoryConstant.success);
		} else if(existedCategory.getId() == po.getId()){	// 修改为与自身类别名称相同
			return new ResultHelper(Constant.success_code, CategoryConstant.success);
		} else {	// 存在同名的其它类别
			return new ResultHelper(Constant.failed_code, CategoryConstant.duplicate_name);
		}
	}

	@Override
	public ResultHelper addCategory(CategoryVo categoryVo) {
		CategoryPo po = new CategoryPo();
		po.setCategoryName(categoryVo.getCategoryName());
		po.setParentId(categoryVo.getParentId());
		po.setType(categoryVo.getType());
		
		CategoryPo existedCategory = categoryDao.findCategoryByName(po.getCategoryName());
		if(existedCategory == null){	// 没有同名类别，可添加新类别
			categoryDao.addCategory(po);
			return new ResultHelper(Constant.success_code, CategoryConstant.success);
		} else {	// 存在同名的其它类别
			return new ResultHelper(Constant.failed_code, CategoryConstant.duplicate_name);
		}
	}

	@Override
	public ResultHelper deleteCategory(int categoryId) {
		CategoryPo category = categoryDao.findCategoryById(categoryId);
		if(category == null){
			return new ResultHelper(Constant.success_code, CategoryConstant.success);
		}
		if(category.getType() == 3){		// 删除三级类别
			if(categoryDao.existsCommodities(categoryId)){	// 类别下存在商品
				return new ResultHelper(Constant.failed_code,
						CategoryConstant.commodities_exist);
			}
		} else{
			if(categoryDao.existsSubcategories(categoryId)){	// 存在子类别
				return new ResultHelper(Constant.failed_code,
						CategoryConstant.subcategories_exist);
			}
		}
		
		// 执行删除操作
		categoryDao.deleteCategory(categoryId);
		return new ResultHelper(Constant.success_code, CategoryConstant.success);
	}

}
