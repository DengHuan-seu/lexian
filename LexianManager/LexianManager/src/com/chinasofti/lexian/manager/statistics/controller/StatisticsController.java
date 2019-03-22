package com.chinasofti.lexian.manager.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.manager.statistics.service.StatisticsService;
import com.chinasofti.lexian.manager.statistics.vo.TopQueryVo;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	private StatisticsService statisticsService;
	
	@Autowired
	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
	
	// 商品销售量和销售额统计
	@RequestMapping("/findSaleTop.do")
	@ResponseBody
	public Object findSaleTop(TopQueryVo topQueryVo){
		return statisticsService.findSaleTop(topQueryVo);
	}
    
	// 商品浏览数统计
	@RequestMapping("/findBrowseTop.do")
	@ResponseBody
	public Object findBrowseTop(TopQueryVo browseTopVo){
		return statisticsService.findBrowseTop(browseTopVo);
	}
}
