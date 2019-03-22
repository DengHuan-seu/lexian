package com.chinasofti.lexian.mall.store.service;

import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.store.vo.AreaVo;
import com.chinasofti.lexian.mall.store.vo.StoreLocationVo;

public interface StoreService {
	public ResultHelper getLocation(StoreLocationVo storeLocationVo);

	public ResultHelper findArea(AreaVo areaVo);
	
	public ResultHelper getStoreInfoByStoreNo (String storeNo);
}
