package com.chinasofti.lexian.mall.collection.service;

import com.chinasofti.lexian.mall.collection.vo.CollectionQueryVo;
import com.chinasofti.lexian.mall.common.util.ResultHelper;

public interface CollectionService {
	public ResultHelper deleteCollection(CollectionQueryVo vo);

	public ResultHelper findCollection(CollectionQueryVo queryVo);

	public ResultHelper addCollection(CollectionQueryVo vo);
	
	public ResultHelper findCount(String commodityNo);

	public ResultHelper findExist(CollectionQueryVo queryVo);
}
