package com.chinasofti.lexian.manager.store.service;

import java.util.List;

import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.store.po.AreaPo;
import com.chinasofti.lexian.manager.store.po.StorePo;
import com.chinasofti.lexian.manager.store.vo.AreaVo;
import com.chinasofti.lexian.manager.store.vo.StoreCommodityQueryVo;
import com.chinasofti.lexian.manager.store.vo.StoreCommodityVo;
import com.chinasofti.lexian.manager.store.vo.StoreVo;

public interface StoreService {
	public ResultHelper addStore(StoreVo storeVo);

	public ResultHelper addchangeStore(StoreVo storeVo);

	public ResultHelper updateStore(StoreVo storeVo);

	public ResultHelper findStore(StoreVo StoreVo);

	public ResultHelper deleteStore(String id);

	public List<StorePo> findlocalStore();

	public List<AreaPo> findArea(AreaVo areaVo);

	public Object getCommodities(StoreCommodityQueryVo queryVo);

	public Object changeCommodityPrice(StoreCommodityVo vo);

	public Object updateShelfStatus(StoreCommodityVo vo);

	public Object updateCommodityStock(StoreCommodityVo vo);

	public Object registerCommodities(String storeNo, String[] commodityNos);
}
