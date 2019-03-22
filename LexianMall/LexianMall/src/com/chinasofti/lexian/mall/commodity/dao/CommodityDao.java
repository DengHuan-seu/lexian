package com.chinasofti.lexian.mall.commodity.dao;

import java.util.List;

import com.chinasofti.lexian.mall.commodity.po.Cart;
import com.chinasofti.lexian.mall.commodity.po.CategoryCommodityPo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPicturePo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPricePo;
import com.chinasofti.lexian.mall.commodity.vo.CategoryCommodityQueryVo;
import com.chinasofti.lexian.mall.commodity.vo.KeywordCommodityQueryVo;

public interface CommodityDao {
public CommodityPo getCommodity(String commodityNo);
	
	public CommodityPricePo getMinMaxPrice(String commodityNo);

	public List<CommodityPicturePo> getCommodityPictures(String commodityNo);

	public CommodityPricePo getStorePrice(CommodityPricePo po);

	public List<CategoryCommodityPo> searchCategoryCommodities(CategoryCommodityQueryVo queryVo);
	
	public List<Cart> findTrolley(Cart cart);
	
	public Integer addTrolley(Cart cart);
	
	public void updateTrolley(Cart cart);
	
	public void deleteTrolley(String... trolleyIds);

	public Integer getTrolleyCount(String userId);
	
	public Cart selectTrolley(Cart cart);
	
	public String shakeCommodity(String storeNo);

	public List<CategoryCommodityPo> searchKeywordCommodities(KeywordCommodityQueryVo queryVo);
}
