package com.chinasofti.lexian.manager.statistics.dao;

import java.util.List;

import com.chinasofti.lexian.manager.statistics.po.BrowseTop;
import com.chinasofti.lexian.manager.statistics.po.SaleTop;
import com.chinasofti.lexian.manager.statistics.vo.TopQueryVo;

public interface StatisticsDao {
   public List<SaleTop>  findSaleTop(TopQueryVo topQueryVo);
   
   public List<BrowseTop> findBrowseTop(TopQueryVo browseTopVo);
}
