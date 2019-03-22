package com.chinasofti.lexian.mall.collection.dao;

import java.util.List;

import com.chinasofti.lexian.mall.collection.po.CollectionPo;
import com.chinasofti.lexian.mall.collection.vo.CollectionQueryVo;

public interface CollectionDao {
	public List<CollectionPo> findCollection(CollectionQueryVo queryVo);

	public void deleteCollection(CollectionPo vo);

	public int addCollection(CollectionPo collectionPo);
	
	public Integer findCount(String commodityNo);

	public Boolean findExist(CollectionPo po);
}
