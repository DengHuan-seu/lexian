
package com.chinasofti.lexian.mall.wallet.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinasofti.lexian.mall.common.dao.BaseDao;
import com.chinasofti.lexian.mall.wallet.dao.WalletDao;
import com.chinasofti.lexian.mall.wallet.po.WalletPo;
import com.chinasofti.lexian.mall.wallet.po.WalletRecordPo;

@Repository
public class WalletDaoImpl extends BaseDao implements WalletDao {

	@Override
	public int updateWallet(WalletPo po) {
		return update("updateWallet", po);
	}

	@Override
	public WalletPo findWallet(String user_id) {
		return selectOne("findWallet", user_id);
	}

	@Override
	public void addWalletRecord(WalletRecordPo po) {
		insert("addWalletRecord", po);
	}

	@Override
	public List<WalletRecordPo> findWalletRecords(String user_id) {
		return selectList("findWalletRecords", user_id);
	}

	@Override
	public Boolean isValidPassword(WalletPo po) {
		return selectOne("isValidPassword", po);
	}

	@Override
	public void addWallet(WalletPo wallet) {
		insert("addWallet", wallet);
	}

	@Override
	public void updateWalletPassword(WalletPo wallet) {
		update("updateWalletPassword", wallet);
	}
}
