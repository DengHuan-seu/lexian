package com.chinasofti.lexian.mall.commodity.service;

import com.chinasofti.lexian.mall.commodity.po.Cart;
import com.chinasofti.lexian.mall.commodity.vo.CategoryCommodityQueryVo;
import com.chinasofti.lexian.mall.commodity.vo.KeywordCommodityQueryVo;
import com.chinasofti.lexian.mall.common.util.ResultHelper;

public interface CommodityService {
	public ResultHelper findTrolley(Cart cart);
	
	public ResultHelper saveCommomdityToTrolley(Cart cart);
	
	public ResultHelper shakeCommodity(String storeNo);
	
	public ResultHelper getTrolleyCount();
	
	public ResultHelper searchCategoryCommodities(CategoryCommodityQueryVo queryVo);
	
	public ResultHelper searchKeywordCommodities(KeywordCommodityQueryVo queryVo);
	
	public ResultHelper getCommodityInfo(String commodityNo);

	public ResultHelper getStorePrice(String commodityNo, String storeNo);
	
	public ResultHelper updateTrolleyCount(Cart cart);
	
	public ResultHelper deleteTrolley(String trolleyIds);

	public ResultHelper getCommodityDetail(String commodityNo, String storeNo);

	public ResultHelper findTrolleyForApp();
}
