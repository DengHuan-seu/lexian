package com.chinasofti.lexian.mall.collection.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.mall.collection.dao.CollectionDao;
import com.chinasofti.lexian.mall.collection.po.CollectionPo;
import com.chinasofti.lexian.mall.collection.vo.CollectionQueryVo;
import com.chinasofti.lexian.mall.common.dao.BaseDao;

@Repository
public class CollectionDaoImpl extends BaseDao implements CollectionDao {
	@Override
	public void deleteCollection(CollectionPo po) {
		delete("deleteCollection", po);
	}

	@Override
	public int addCollection(CollectionPo collectionPo) {
		return insert("addCollection", collectionPo);
	}

	@Override
	public Integer findCount(String commodityNo) {
		return selectOne("findCount1", commodityNo);
	}

	@Override
	public List<CollectionPo> findCollection(CollectionQueryVo queryVo) {
		return selectList("findCollection", queryVo);
	}

	@Override
	public Boolean findExist(CollectionPo po) {
		return selectOne("findExist", po);
	}

}
