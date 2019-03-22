package com.chinasofti.lexian.mall.wallet.service;

import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.wallet.vo.WalletRecordVo;
import com.chinasofti.lexian.mall.wallet.vo.WalletTransferVo;

public interface WalletService {
	ResultHelper findWallet();

	ResultHelper findWalletRecords();

	ResultHelper chargeWallet(WalletRecordVo vo);

	ResultHelper transferToWallet(WalletTransferVo vo);

}
