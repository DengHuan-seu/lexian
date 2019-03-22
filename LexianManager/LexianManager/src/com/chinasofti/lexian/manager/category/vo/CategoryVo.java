package com.chinasofti.lexian.manager.category.vo;

import com.chinasofti.lexian.manager.category.po.CategoryPo;
import com.chinasofti.lexian.manager.common.util.PageHelper;

public class CategoryVo extends PageHelper<CategoryVo>{
	private int id;
	private String categoryName;
	private int type;
	private int parentId;
	private String parentName;
	
	public CategoryVo(){
	}
	
	public CategoryVo(CategoryPo po){
		this.id = po.getId();
		this.categoryName = po.getCategoryName();
		this.type = po.getType();
		this.parentId = po.getParentId();
		this.parentName = po.getParentName();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
