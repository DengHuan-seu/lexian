package com.chinasofti.lexian.mall.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.store.constant.StoreConstant;
import com.chinasofti.lexian.mall.store.dao.StoreDao;
import com.chinasofti.lexian.mall.store.po.AreaPo;
import com.chinasofti.lexian.mall.store.service.StoreService;
import com.chinasofti.lexian.mall.store.vo.AreaVo;
import com.chinasofti.lexian.mall.store.vo.StoreLocationVo;
import com.chinasofti.lexian.mall.store.vo.StoreVo;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
	private StoreDao storeDao;

	@Autowired
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@Override
	public ResultHelper getLocation(StoreLocationVo storeLocationVo) {
		List<StoreLocationVo> locations = storeDao.getLocation(storeLocationVo);
		return new ResultHelper(Constant.success_code, StoreConstant.success, locations);
	}

	@Override
	public ResultHelper findArea(AreaVo areaVo) {
		List<AreaPo> areas = storeDao.findArea(areaVo);
		return new ResultHelper(Constant.success_code, StoreConstant.success, areas);
	}
	
	@Override
	public ResultHelper getStoreInfoByStoreNo(String storeNo) {
		StoreVo storeVo = storeDao.getStoreInfoByStoreNo(storeNo);
		return new ResultHelper(Constant.success_code, StoreConstant.success, storeVo);
	}

}
