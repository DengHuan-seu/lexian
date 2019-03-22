package com.chinasofti.lexian.manager.statistics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.manager.common.dao.BaseDao;
import com.chinasofti.lexian.manager.statistics.dao.StatisticsDao;
import com.chinasofti.lexian.manager.statistics.po.BrowseTop;
import com.chinasofti.lexian.manager.statistics.po.SaleTop;
import com.chinasofti.lexian.manager.statistics.vo.TopQueryVo;

@Repository
public class StatisticsDaoImpl extends BaseDao implements StatisticsDao {
	@Override
	public List<SaleTop> findSaleTop(TopQueryVo topQueryVo) {
		return selectList("findSaleTop",topQueryVo);
	}
	
	@Override
	public List<BrowseTop> findBrowseTop(TopQueryVo browseTopVo) {
		return selectList("findBrowseTop",browseTopVo);
	}
}
