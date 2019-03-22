package com.chinasofti.lexian.mall.browse.service.vo;

import com.chinasofti.lexian.mall.common.util.PageHelper;

public class FindBrowseVo extends PageHelper<FindBrowseVo> {
	private String userId;
	private int maxnum;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getMaxnum() {
		return maxnum;
	}
	public void setMaxnum(int maxnum) {
		this.maxnum = maxnum;
	}
}
