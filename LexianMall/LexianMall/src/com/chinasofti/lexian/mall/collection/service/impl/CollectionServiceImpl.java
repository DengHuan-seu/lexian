package com.chinasofti.lexian.mall.collection.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.mall.collection.constant.CollectionConstant;
import com.chinasofti.lexian.mall.collection.dao.CollectionDao;
import com.chinasofti.lexian.mall.collection.po.CollectionPo;
import com.chinasofti.lexian.mall.collection.service.CollectionService;
import com.chinasofti.lexian.mall.collection.vo.CollectionQueryVo;
import com.chinasofti.lexian.mall.collection.vo.CollectionVo;
import com.chinasofti.lexian.mall.common.service.BaseService;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.ResultHelper;

@Service
@Transactional
public class CollectionServiceImpl extends BaseService implements CollectionService {

	private CollectionDao collectionDao;

	@Autowired
	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
	}

	// 分页查找指定用户的收藏品列表
	@Override
	public ResultHelper findCollection(CollectionQueryVo queryVo) {
		String userId = getUser().getId();
		queryVo.setUserId(userId);
		List<CollectionPo> pos = collectionDao.findCollection(queryVo);
		List<CollectionVo> vos = new ArrayList<CollectionVo>();
		for(CollectionPo po : pos){
			CollectionVo vo = new CollectionVo(po);
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, CollectionConstant.success, 
				vos, queryVo.getTotalSize());
	}

	//删除收藏商品
	@Override
	public ResultHelper deleteCollection(CollectionQueryVo queryVo) {
		CollectionPo po = new CollectionPo();
		po.setCommodity_no(queryVo.getCommodityNo());
		po.setUser_id(getUser().getId());
		po.setStore_no(queryVo.getStoreNo());
		collectionDao.deleteCollection(po);
		
		return new ResultHelper(Constant.success_code, CollectionConstant.success);
	}

	// 添加收藏商品
	@Override
	public ResultHelper addCollection(CollectionQueryVo queryVo) {
		CollectionPo po = new CollectionPo();
		po.setStore_no(queryVo.getStoreNo());
		po.setUser_id(getUser().getId());
		po.setCommodity_no(queryVo.getCommodityNo());
		
		Boolean exists = collectionDao.findExist(po);
		if (exists) {
			return new ResultHelper(Constant.success_code, CollectionConstant.success, 1);
		} else{
			collectionDao.addCollection(po);
			return new ResultHelper(Constant.success_code, CollectionConstant.success, 0);
		}
	}

	@Override
	public ResultHelper findCount(String commodityNo) {
		return new ResultHelper(Constant.success_code, CollectionConstant.success,
				collectionDao.findCount(commodityNo));
	}

	@Override
	public ResultHelper findExist(CollectionQueryVo queryVo) {
		CollectionPo po = new CollectionPo();
		po.setStore_no(queryVo.getStoreNo());
		po.setUser_id(getUser().getId());
		po.setCommodity_no(queryVo.getCommodityNo());
		return new ResultHelper(Constant.success_code, CollectionConstant.success,
				collectionDao.findExist(po));
	}
}
