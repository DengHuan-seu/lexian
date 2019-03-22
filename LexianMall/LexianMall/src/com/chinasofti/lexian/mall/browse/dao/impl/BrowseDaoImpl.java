package com.chinasofti.lexian.mall.browse.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.mall.browse.dao.BrowseDao;
import com.chinasofti.lexian.mall.browse.po.BrowsePo;
import com.chinasofti.lexian.mall.common.dao.BaseDao;

@Repository
public class BrowseDaoImpl extends BaseDao implements BrowseDao {
	@Override
	public List<BrowsePo> findBrowse(BrowsePo browsePo) {
		return selectList("findBrowse", browsePo);
	}

	@Override
	public int addBrowse(BrowsePo browsePo) {
		return insert("addBrowse", browsePo);
	}
}
