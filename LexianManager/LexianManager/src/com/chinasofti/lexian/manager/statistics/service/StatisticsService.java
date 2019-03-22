package com.chinasofti.lexian.manager.statistics.service;

import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.statistics.vo.TopQueryVo;

public interface StatisticsService {
   public ResultHelper  findSaleTop(TopQueryVo topQueryVo);
   
   public ResultHelper findBrowseTop(TopQueryVo browseTopVo);
}
