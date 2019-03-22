package com.chinasofti.lexian.mall.category.vo;

import java.util.ArrayList;
import java.util.List;

import com.chinasofti.lexian.mall.category.po.CategoryPo;

public class CategoryVo {
	private int categoryId;
	private String categoryName;
	private List<CategoryVo> subCategories;
	
	public CategoryVo(){
	}
	
	public CategoryVo(CategoryPo po){
		this.categoryId = po.getId();
		this.categoryName = po.getCategoryname();
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<CategoryVo> getSubCategories() {
		if(this.subCategories == null){
			this.subCategories = new ArrayList<CategoryVo>();
		}
		return subCategories;
	}
	public void setSubCategories(List<CategoryVo> subCategories) {
		this.subCategories = subCategories;
	}
}
