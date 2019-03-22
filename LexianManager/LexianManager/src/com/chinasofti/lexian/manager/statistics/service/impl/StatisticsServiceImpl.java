package com.chinasofti.lexian.manager.statistics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.manager.common.service.BaseService;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.statistics.constant.StatisticsConstant;
import com.chinasofti.lexian.manager.statistics.dao.StatisticsDao;
import com.chinasofti.lexian.manager.statistics.service.StatisticsService;
import com.chinasofti.lexian.manager.statistics.vo.TopQueryVo;

@Service
@Transactional
public class StatisticsServiceImpl extends BaseService implements StatisticsService {
	private StatisticsDao statisticsDao;

	@Autowired
	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	@Override
	public ResultHelper findSaleTop(TopQueryVo topQueryVo) {
		return new ResultHelper(Constant.success_code, StatisticsConstant.success, 
				statisticsDao.findSaleTop(topQueryVo),
				topQueryVo.getTotal());
	}

	@Override
	public ResultHelper findBrowseTop(TopQueryVo browseTopVo) {
		return new ResultHelper(Constant.success_code, StatisticsConstant.success, 
				statisticsDao.findBrowseTop(browseTopVo),
				browseTopVo.getTotal());
	}
}
