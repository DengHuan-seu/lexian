package com.chinasofti.lexian.mall.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.mall.category.service.CategoryService;
import com.chinasofti.lexian.mall.common.controller.BaseController;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

	private CategoryService categoryService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 返回商品一级目录
	@RequestMapping(value = "/getCategories.do")
	@ResponseBody
	public Object getCategories() {
		return categoryService.getCategories();
	}

	// 返回指定一级目录下的商品二级目录
	@RequestMapping(value = "/getSubCategories.do")
	@ResponseBody
	public Object getSubCategories(int parentId) {
		return categoryService.getSubcategories(parentId);
	}
	
	// 根据三级类别ID获得类别名称路径
	@RequestMapping("/getCategoryPathForCategory.do")
	@ResponseBody
	public Object getCategoryPathForCategory(int categoryId){
		return categoryService.getCategoryPathForCategory(categoryId);
	}
		
	// 根据商品ID获取类别名称路径
	@RequestMapping("/getCategoryPathForCommodity.do")
	@ResponseBody
	public Object getCategoryPathForCommodity(String commodityNo){
		return categoryService.getCategoryPathForCommodity(commodityNo);
	}
}
