package com.chinasofti.lexian.mall.commodity.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.mall.commodity.constant.CommodityConstant;
import com.chinasofti.lexian.mall.commodity.dao.CommodityDao;
import com.chinasofti.lexian.mall.commodity.po.Cart;
import com.chinasofti.lexian.mall.commodity.po.CategoryCommodityPo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPo;
import com.chinasofti.lexian.mall.commodity.po.CommodityPricePo;
import com.chinasofti.lexian.mall.commodity.service.CommodityService;
import com.chinasofti.lexian.mall.commodity.vo.CategoryCommodityQueryVo;
import com.chinasofti.lexian.mall.commodity.vo.CategoryCommodityVo;
import com.chinasofti.lexian.mall.commodity.vo.CommodityDetail;
import com.chinasofti.lexian.mall.common.service.BaseService;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.commodity.po.CommodityPicturePo;
import com.chinasofti.lexian.mall.commodity.vo.CommodityInfoVo;
import com.chinasofti.lexian.mall.commodity.vo.KeywordCommodityQueryVo;

@Service
@Transactional
public class CommodityServiceImpl extends BaseService implements CommodityService {

	private CommodityDao commodityDao;
	private String commodityPhysicalPath;
	private String commodityVirtualPath;
	private int thumbnailCount;

	@Value("${thumbnailCount}")
	public void setThumbnailCount(int thumbnailCount) {
		this.thumbnailCount = thumbnailCount;
	}

	public int getThumbnailCount() {
		return thumbnailCount;
	}

	@Value("${commodityVirtualPath}")
	public void setCommodityVirtualPath(String commodityVirtualPath) {
		this.commodityVirtualPath = commodityVirtualPath;
	}

	public String getCommodityVirtualPath() {
		return commodityVirtualPath;
	}

	@Value("${commodityPhysicalPath}")
	public void setCommodityPhysicalPath(String commodityPhysicalPath) {
		this.commodityPhysicalPath = commodityPhysicalPath;
	}

	public String getCommodityPhysicalPath() {
		return commodityPhysicalPath;
	}

	@Autowired
	public void setCommodityDao(CommodityDao commodityDao) {
		this.commodityDao = commodityDao;
	}

	@Override
	public ResultHelper updateTrolleyCount(Cart cart) {
		CommodityPricePo price = new CommodityPricePo();
		price.setStore_no(cart.getStoreNo());
		price.setCommodity_no(cart.getCommodityNo());
		price = commodityDao.getStorePrice(price);
		if (price == null) {
			return new ResultHelper(Constant.failed_code, CommodityConstant.commodity_notfound);
		}
		
		double totalPrice = price.getCommodity_price() * cart.getAmont();
		cart.setTotalPrice(totalPrice);
		commodityDao.updateTrolley(cart);

		return new ResultHelper(Constant.success_code, CommodityConstant.success, cart.getTotalPrice());
	}
	
	@Override
	public ResultHelper deleteTrolley(String ids) {
		commodityDao.deleteTrolley(ids.split(","));
		return new ResultHelper(Constant.success_code, CommodityConstant.success);
	}
	
	// 加入购物车
	@Override
	public ResultHelper saveCommomdityToTrolley(Cart cart) {
		cart.setUserId(getUser().getId());
		
		// 获取商品价格信息
		CommodityPricePo price = new CommodityPricePo();
		price.setStore_no(cart.getStoreNo());
		price.setCommodity_no(cart.getCommodityNo());
		price = commodityDao.getStorePrice(price);
		if (price == null) {
			return new ResultHelper(Constant.failed_code, CommodityConstant.commodity_notfound);
		}
		// 根据userId, commodityNo和storeNo找到购物车中的对应项
		Cart cartInfo = commodityDao.selectTrolley(cart);
		
		if (cartInfo != null) {		// 该购物项已存在，只需更新购物数量
			int amount = cart.getAmont() + cartInfo.getAmont();
			cart.setCommodityPrice(price.getCommodity_price());
			cart.setAmont(amount);
			cart.setTotalPrice(amount* price.getCommodity_price());
			commodityDao.updateTrolley(cart);
		} else {					// 该购物项不存在，需添加新项
			cart.setCommodityPrice(price.getCommodity_price());
			cart.setTotalPrice(price.getCommodity_price() * cart.getAmont());
			commodityDao.addTrolley(cart);
		}
		return new ResultHelper(Constant.success_code, CommodityConstant.success);
	}

	@Override
	public ResultHelper findTrolley(Cart cart) {
		cart.setUserId(getUser().getId());
		List<Cart> carts = commodityDao.findTrolley(cart);
		
		// 根据店铺进行分组
		Map<String, List<Cart>> map = new HashMap<String, List<Cart>>();
		for (final Cart c : carts) {
			String storeNo = c.getStoreNo();
			if (!map.containsKey(storeNo)) {
				map.put(storeNo, new ArrayList<Cart>() {
					{
						add(c);
					}
				});
			} else {
				map.get(storeNo).add(c);
			}
		}
		return new ResultHelper(Constant.success_code, CommodityConstant.success, map);
	}
	
	@Override
	public ResultHelper findTrolleyForApp() {
		Cart cart = new Cart();
		cart.setUserId(getUser().getId());
		List<Cart> carts = commodityDao.findTrolley(cart);
		
		// 根据店铺进行分组
		Map<String, List<Cart>> map = new HashMap<String, List<Cart>>();
		for (final Cart c : carts) {
			String storeNo = c.getStoreNo();
			if (!map.containsKey(storeNo)) {
				map.put(storeNo, new ArrayList<Cart>() {
					{
						add(c);
					}
				});
			} else {
				map.get(storeNo).add(c);
			}
		}
		return new ResultHelper(Constant.success_code, CommodityConstant.success, map);
	}

	@Override
	public ResultHelper shakeCommodity(String storeNo) {
		String commodityNo = commodityDao.shakeCommodity(storeNo);
		return new ResultHelper(Constant.success_code, CommodityConstant.success,commodityNo);
	}

	@Override
	public ResultHelper getTrolleyCount() {
		Integer count = commodityDao.getTrolleyCount(getUser().getId());
		if (count == null) {
			count = 0;
		}
		return new ResultHelper(Constant.success_code, CommodityConstant.success, count);
	}

	@Override
	public ResultHelper searchCategoryCommodities(CategoryCommodityQueryVo queryVo) {
		List<CategoryCommodityVo> vos = new ArrayList<CategoryCommodityVo>();
		List<CategoryCommodityPo> pos = commodityDao.searchCategoryCommodities(queryVo);
		for(CategoryCommodityPo po : pos){
			CategoryCommodityVo vo = new CategoryCommodityVo(po);
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, CommodityConstant.success,
				vos, queryVo.getTotalSize());
	}
	
	@Override
	public ResultHelper searchKeywordCommodities(KeywordCommodityQueryVo queryVo) {
		List<CategoryCommodityVo> vos = new ArrayList<CategoryCommodityVo>();
		List<CategoryCommodityPo> pos = commodityDao.searchKeywordCommodities(queryVo);
		for(CategoryCommodityPo po : pos){
			CategoryCommodityVo vo = new CategoryCommodityVo(po);
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, CommodityConstant.success,
				vos, queryVo.getTotalSize());
	}

	@Override
	public ResultHelper getCommodityInfo(String commodityNo) {
		CommodityPo commodity = commodityDao.getCommodity(commodityNo);
		List<CommodityPicturePo> picture = commodityDao.getCommodityPictures(commodityNo);
		CommodityPricePo price = commodityDao.getMinMaxPrice(commodityNo);
		CommodityInfoVo cv = new CommodityInfoVo(commodity, picture, price);
		
		return new ResultHelper(Constant.success_code, CommodityConstant.success, cv);
	}
	
	@Override
	public ResultHelper getStorePrice(String commodityNo, String storeNo) {
		CommodityPricePo price = new CommodityPricePo();
		price.setCommodity_no(commodityNo);
		price.setStore_no(storeNo);
		CommodityPricePo result = commodityDao.getStorePrice(price);
		if(result == null){
			result = price;
		}
		
		return new ResultHelper(Constant.success_code, CommodityConstant.success, result);
	}

	@Override
	public ResultHelper getCommodityDetail(String commodityNo, String storeNo) {
		CommodityPo commodity = commodityDao.getCommodity(commodityNo);
		
		List<CommodityPicturePo> pictures = commodityDao.getCommodityPictures(commodityNo);
		
		CommodityPricePo price = new CommodityPricePo();
		price.setCommodity_no(commodityNo);
		price.setStore_no(storeNo);
		CommodityPricePo commodityPrice = commodityDao.getStorePrice(price);
		if(commodityPrice == null){
			commodityPrice = price;
		}
		
		CommodityDetail detail = new CommodityDetail(commodity, pictures, commodityPrice);
		
		return new ResultHelper(Constant.success_code, CommodityConstant.success, detail);
	}
}
