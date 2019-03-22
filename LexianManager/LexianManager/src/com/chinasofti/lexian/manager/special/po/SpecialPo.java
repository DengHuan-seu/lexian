package com.chinasofti.lexian.manager.special.po;

import com.chinasofti.lexian.manager.common.util.PageHelper;

public class SpecialPo extends PageHelper<SpecialPo>{

	private int specialId;
	private String specialName;
	public int getSpecialId() {
		return specialId;
	}
	public void setSpecialId(int specialId) {
		this.specialId = specialId;
	}
	public String getSpecialName() {
		return specialName;
	}
	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}
}
