package com.chinasofti.lexian.manager.store.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.manager.common.service.BaseService;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.store.constant.StoreConstant;
import com.chinasofti.lexian.manager.store.dao.StoreDao;
import com.chinasofti.lexian.manager.store.po.AreaPo;
import com.chinasofti.lexian.manager.store.po.StoreCommodityPo;
import com.chinasofti.lexian.manager.store.po.StorePo;
import com.chinasofti.lexian.manager.store.service.StoreService;
import com.chinasofti.lexian.manager.store.vo.AreaVo;
import com.chinasofti.lexian.manager.store.vo.StoreCommodityQueryVo;
import com.chinasofti.lexian.manager.store.vo.StoreCommodityVo;
import com.chinasofti.lexian.manager.store.vo.StoreVo;

@Service
@Transactional
public class StoreServiceImpl extends BaseService implements StoreService {
	private StoreDao storeDao;

	@Autowired
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}
	
	@Override
	public ResultHelper addStore(StoreVo storeVo) {
		try {
			StorePo storePo = new StorePo();
			BeanUtils.copyProperties(storeVo, storePo);
			storePo.setStatus(1);
			DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			if(storeVo.getStarttime()!=null){
				storePo.setStarttime(sdf.parse(storeVo.getStarttime()));
			}
			if(storeVo.getClosetime()!=null){
				storePo.setClosetime(sdf.parse(storeVo.getClosetime()));
			}
			storeDao.addStore(storePo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultHelper(Constant.success_code, StoreConstant.success);
	}

	@Override
	public ResultHelper updateStore(StoreVo storeVo) {
		try {
			StorePo storePo = new StorePo();
			BeanUtils.copyProperties(storeVo, storePo);
			DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			if(storeVo.getStarttime()!=null){
				storePo.setStarttime(sdf.parse(storeVo.getStarttime()));
			}
			if(storeVo.getClosetime()!=null){
				storePo.setClosetime(sdf.parse(storeVo.getClosetime()));
			}
			storeDao.updateStore(storePo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultHelper(Constant.success_code, StoreConstant.success);
	}

	@Override
	public ResultHelper findStore(StoreVo storeVo) {
		StorePo storePo = new StorePo();
		BeanUtils.copyProperties(storeVo, storePo);
		List<StorePo> localStoresInfo = storeDao.findStore(storePo);
		return new ResultHelper(Constant.success_code, StoreConstant.success, 
				localStoresInfo, storePo.getTotal());
	}

	@Override
	public ResultHelper deleteStore(String id) {
		String[] ids = id.split(",");
		storeDao.deleteStore(ids);
		return new ResultHelper(Constant.success_code, StoreConstant.success);
	}

	@Override
	public ResultHelper addchangeStore(StoreVo storeVo) {
		StorePo storePo = new StorePo();
		BeanUtils.copyProperties(storeVo, storePo);
		storeDao.addchangeStore(storePo);
		return null;
	}

	@Override
	public List<StorePo> findlocalStore() {
		return storeDao.findlocalStore();
	}

	@Override
	public List<AreaPo> findArea(AreaVo areaVo) {
		return storeDao.findArea(areaVo);
	}

	@Override
	public Object getCommodities(StoreCommodityQueryVo queryVo) {
		List<StoreCommodityVo> scv = new ArrayList<StoreCommodityVo>();
		List<StoreCommodityPo> scp = storeDao.getCommodities(queryVo);
		for(StoreCommodityPo po : scp){
			StoreCommodityVo vo = new StoreCommodityVo(po);
			scv.add(vo);
		}
		
		return new ResultHelper(Constant.success_code, StoreConstant.success,
				scv, queryVo.getTotal());
	}

	@Override
	public Object changeCommodityPrice(StoreCommodityVo vo) {
		StoreCommodityPo po = new StoreCommodityPo();
		po.setId(vo.getId());
		po.setCommodity_price(vo.getListPrice());
		po.setReal_price(vo.getRealPrice());
		storeDao.changeCommodityPrice(po);
		
		return new ResultHelper(Constant.success_code, StoreConstant.success);
	}

	@Override
	public Object updateShelfStatus(StoreCommodityVo vo) {
		StoreCommodityPo po = new StoreCommodityPo();
		po.setId(vo.getId());
		po.setType(vo.getActive() ? 1 : 0);
		storeDao.updateShelfStatus(po);
		
		return new ResultHelper(Constant.success_code, StoreConstant.success);
	}

	@Override
	public Object updateCommodityStock(StoreCommodityVo vo) {
		StoreCommodityPo po = new StoreCommodityPo();
		po.setId(vo.getId());
		po.setCommodity_amont(vo.getStock());
		storeDao.updateCommodityStock(po);
		
		return new ResultHelper(Constant.success_code, StoreConstant.success);
	}

	@Override
	public Object registerCommodities(String storeNo, String[] commodityNos) {
		List<String> existNos = storeDao.getAllCommodityNos(storeNo);
		for(String commodityNo : commodityNos){
			if(existNos.contains(commodityNo)){
				continue;
			} else{
				StoreCommodityPo po = new StoreCommodityPo();
				po.setStore_no(storeNo);
				po.setCommodity_no(commodityNo);
				storeDao.registerCommodity(po);
			}
		}
		return new ResultHelper(Constant.success_code, StoreConstant.success);
	}
}
