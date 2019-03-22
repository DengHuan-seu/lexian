package com.chinasofti.lexian.mall.store.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.mall.common.dao.BaseDao;
import com.chinasofti.lexian.mall.store.po.AreaPo;
import com.chinasofti.lexian.mall.store.vo.AreaVo;
import com.chinasofti.lexian.mall.store.dao.StoreDao;
import com.chinasofti.lexian.mall.store.vo.StoreLocationVo;
import com.chinasofti.lexian.mall.store.vo.StoreVo;

@Repository
public class StoreDaoImpl extends BaseDao implements StoreDao {
	@Override
	public List<StoreLocationVo> getLocation(StoreLocationVo storeLocationVo) {
		return selectList("getLocation", storeLocationVo);
	}

	@Override
	public StoreVo getStoreInfoByStoreNo(String storeNo) {
		return selectOne("getStoreInfoByStoreNo",storeNo);
	}
	
	@Override
	public List<AreaPo> findArea(AreaVo areaVo){
		return selectList("findArea", areaVo);
	}
}
