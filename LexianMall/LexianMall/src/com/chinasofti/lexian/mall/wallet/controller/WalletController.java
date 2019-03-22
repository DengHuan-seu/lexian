package com.chinasofti.lexian.mall.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.mall.common.controller.BaseController;
import com.chinasofti.lexian.mall.wallet.service.WalletService;
import com.chinasofti.lexian.mall.wallet.vo.WalletRecordVo;
import com.chinasofti.lexian.mall.wallet.vo.WalletTransferVo;

@Controller
@RequestMapping("/wallet")
public class WalletController extends BaseController {

	private WalletService walletService;

	@Autowired
	public void setWalletService(WalletService walletService) {
		this.walletService = walletService;
	}
	
	// 查询钱包余额
	@RequestMapping("/findWallet.do")
	@ResponseBody
	public Object findWallet(){
		return walletService.findWallet();
	}
	
	@RequestMapping("/findWalletRecords.do")
	@ResponseBody
	public Object findWalletRecords(){
		return walletService.findWalletRecords();
	}
	
	@RequestMapping("/chargeWallet.do")
	@ResponseBody
	public Object chargeWallet(WalletRecordVo vo){
		return walletService.chargeWallet(vo);
	}
	
	@RequestMapping("/transferToWallet.do")
	@ResponseBody
	public Object transferToWallet(WalletTransferVo vo){
		return walletService.transferToWallet(vo);
	}
}
