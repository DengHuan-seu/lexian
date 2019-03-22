package com.chinasofti.lexian.mall.store.dao;

import java.util.List;

import com.chinasofti.lexian.mall.store.po.AreaPo;
import com.chinasofti.lexian.mall.store.vo.AreaVo;
import com.chinasofti.lexian.mall.store.vo.StoreLocationVo;
import com.chinasofti.lexian.mall.store.vo.StoreVo;

public interface StoreDao {
	public List<StoreLocationVo> getLocation(StoreLocationVo storeLocationVo);
	
	public StoreVo getStoreInfoByStoreNo(String storeNo);

	public List<AreaPo> findArea(AreaVo areaVo);
}
