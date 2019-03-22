package com.chinasofti.lexian.mall.commodity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.mall.commodity.dao.CommodityDao;
import com.chinasofti.lexian.mall.commodity.po.Cart;
import com.chinasofti.lexian.mall.commodity.po.CategoryCommodityPo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPicturePo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPricePo;
import com.chinasofti.lexian.mall.commodity.vo.CategoryCommodityQueryVo;
import com.chinasofti.lexian.mall.commodity.vo.KeywordCommodityQueryVo;
import com.chinasofti.lexian.mall.common.dao.BaseDao;

@Repository
public class CommodityDaoImpl extends BaseDao implements CommodityDao {
	@Override
	public List<CategoryCommodityPo> searchCategoryCommodities(CategoryCommodityQueryVo queryVo) {
		return selectList("searchCategoryCommodities", queryVo);
	}
	
	@Override
	public List<CategoryCommodityPo> searchKeywordCommodities(KeywordCommodityQueryVo queryVo) {
		return selectList("searchKeywordCommodities", queryVo);
	}

	@Override
	public CommodityPo getCommodity(String commodityNo) {
		return selectOne("getCommodityInfo", commodityNo);
	}

	@Override
	public CommodityPricePo getMinMaxPrice(String commodityNo) {
		return selectOne("getMinMaxPrice", commodityNo);
	}

	@Override
	public List<CommodityPicturePo> getCommodityPictures(String commodityNo) {
		return selectList("getCommodityPictures", commodityNo);
	}

	@Override
	public CommodityPricePo getStorePrice(CommodityPricePo po) {
		return selectOne("getStorePrice", po);
	}
	
	@Override
	public List<Cart> findTrolley(Cart trolley) {
		return selectList("findAlltrolley", trolley);
	}
	
	@Override
	public Integer addTrolley(Cart cart) {
		return insert("saveCommomdityToTrolley", cart);
	}

	@Override
	public void updateTrolley(Cart cart) {
		update("updateTrolley", cart);
	}

	@Override
	public void deleteTrolley(String... trolleyIds) {
	  delete("deleteTrolley", trolleyIds);
	}
	
	@Override
	public Cart selectTrolley(Cart cart) {
		return selectOne("selectTrolley", cart);
	}
	
	@Override
	public Integer getTrolleyCount(String userId) {
		
		return selectOne("getTrolleyCount", userId);
	}

	@Override
	public String shakeCommodity(String storeNo) {
		return selectOne("shakeCommodity",storeNo);
	}
}
