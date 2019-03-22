package com.chinasofti.lexian.mall.browse.dao;

import java.util.List;

import com.chinasofti.lexian.mall.browse.po.BrowsePo;

public interface BrowseDao {
	public List<BrowsePo> findBrowse(BrowsePo browsePo);
	public int addBrowse(BrowsePo browsePo);
}
