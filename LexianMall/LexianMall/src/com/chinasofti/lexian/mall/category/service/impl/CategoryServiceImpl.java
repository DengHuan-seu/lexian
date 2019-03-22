package com.chinasofti.lexian.mall.category.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinasofti.lexian.mall.category.constant.CategoryConstant;
import com.chinasofti.lexian.mall.category.dao.CategoryDao;
import com.chinasofti.lexian.mall.category.po.CategoryPathInfoPo;
import com.chinasofti.lexian.mall.category.po.CategoryPo;
import com.chinasofti.lexian.mall.category.service.CategoryService;
import com.chinasofti.lexian.mall.category.vo.CategoryVo;
import com.chinasofti.lexian.mall.common.service.BaseService;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.ResultHelper;

@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {

	private CategoryDao categoryDao;

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public ResultHelper getCategories() {
		List<CategoryVo> vos = new ArrayList<CategoryVo>();
		List<CategoryPo> pos = categoryDao.getCategories();
		for(CategoryPo po : pos){
			vos.add(new CategoryVo(po));
		}
		return new ResultHelper(Constant.success_code, CategoryConstant.success,
				vos);
	}

	@Override
	public ResultHelper getSubcategories(int parentId) {
		List<CategoryPo> pos = categoryDao.getSubCategories(parentId);
		List<CategoryVo> vos = new ArrayList<CategoryVo>();
		Map<Integer, CategoryVo> map = new HashMap<Integer, CategoryVo>();
		for(CategoryPo po : pos){
			if(po.getType() == 2){		// 二级目录
				CategoryVo secondVo = new CategoryVo(po);
				vos.add(secondVo);
				map.put(po.getId(), secondVo);
			} else{						// 三级目录
				int pId = po.getParentid();
				CategoryVo thirdVo = new CategoryVo(po);
				map.get(pId).getSubCategories().add(thirdVo);
			}
		}
		return new ResultHelper(Constant.success_code, CategoryConstant.success, vos);
	}

	@Override
	public Object getCategoryPathForCategory(int categoryId) {
		CategoryPathInfoPo pif = categoryDao.getCategoryPathForCategory(categoryId);
		return new ResultHelper(Constant.success_code, CategoryConstant.success, pif);
	}

	@Override
	public Object getCategoryPathForCommodity(String commodityNo) {
		CategoryPathInfoPo pif = categoryDao.getCategoryPathForCommodity(commodityNo);
		return new ResultHelper(Constant.success_code, CategoryConstant.success, pif);
	}

	
}
