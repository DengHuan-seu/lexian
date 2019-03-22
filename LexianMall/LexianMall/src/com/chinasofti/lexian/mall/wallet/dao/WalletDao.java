package com.chinasofti.lexian.mall.wallet.dao;

import java.util.List;

import com.chinasofti.lexian.mall.wallet.po.WalletPo;
import com.chinasofti.lexian.mall.wallet.po.WalletRecordPo;

public interface WalletDao {
	WalletPo findWallet(String user_id);
	
	int updateWallet(WalletPo po);
	
	void addWalletRecord(WalletRecordPo po);

	List<WalletRecordPo> findWalletRecords(String user_id);
	
	Boolean isValidPassword(WalletPo po);

	void addWallet(WalletPo wallet);

	void updateWalletPassword(WalletPo wallet);
}
