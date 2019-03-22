package com.chinasofti.lexian.manager.store.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.manager.common.dao.BaseDao;
import com.chinasofti.lexian.manager.store.dao.StoreDao;
import com.chinasofti.lexian.manager.store.po.AreaPo;
import com.chinasofti.lexian.manager.store.po.StoreCommodityPo;
import com.chinasofti.lexian.manager.store.po.StorePo;
import com.chinasofti.lexian.manager.store.vo.AreaVo;
import com.chinasofti.lexian.manager.store.vo.StoreCommodityQueryVo;

@Repository
public class StoreDaoImpl extends BaseDao implements StoreDao {
	@Override
	public int addStore(StorePo storePo) {
		return insert("addStore", storePo);
	}

	@Override
	public List<StorePo> findStore(StorePo storePo) {
		return selectList("findStore", storePo);
	}
	
	@Override
	public int deleteStore(String... id) {
		return delete("deleteStore", id);
	}

	@Override
	public int updateStore(StorePo storePo) {
		return update("updateStore", storePo);
	}

	@Override
	public int addchangeStore(StorePo storepo) {

		return update("addchangeStore", storepo);
	}

	@Override
	public List<StorePo> findlocalStore() {
		return selectList("findStore");
	}

	@Override
	public List<AreaPo> findArea(AreaVo areaVo) {
		return selectList("findArea", areaVo);
	}

	@Override
	public List<StoreCommodityPo> getCommodities(StoreCommodityQueryVo vo) {
		return selectList("getCommodities", vo);
	}

	@Override
	public void changeCommodityPrice(StoreCommodityPo po) {
		update("changeCommodityPrice", po);
	}

	@Override
	public void updateShelfStatus(StoreCommodityPo po) {
		update("updateShelfStatus", po);
	}

	@Override
	public void updateCommodityStock(StoreCommodityPo po) {
		update("updateCommodityStock", po);
	}

	@Override
	public List<String> getAllCommodityNos(String storeNo) {
		return selectList("getAllCommodityNos", storeNo);
	}

	@Override
	public void registerCommodity(StoreCommodityPo po) {
		insert("registerCommodity", po);
	}
}
