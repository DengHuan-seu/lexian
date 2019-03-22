package com.chinasofti.lexian.mall.wallet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.mall.common.service.BaseService;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.OrderNumberGenerator;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.user.dao.UserDao;
import com.chinasofti.lexian.mall.user.po.User;
import com.chinasofti.lexian.mall.validation.constant.ValidateConstant;
import com.chinasofti.lexian.mall.validation.dao.ValidateCodeDao;
import com.chinasofti.lexian.mall.validation.po.ValidationCodePo;
import com.chinasofti.lexian.mall.wallet.constant.WalletConstant;
import com.chinasofti.lexian.mall.wallet.dao.WalletDao;
import com.chinasofti.lexian.mall.wallet.po.WalletPo;
import com.chinasofti.lexian.mall.wallet.po.WalletRecordPo;
import com.chinasofti.lexian.mall.wallet.service.WalletService;
import com.chinasofti.lexian.mall.wallet.vo.WalletRecordVo;
import com.chinasofti.lexian.mall.wallet.vo.WalletTransferVo;
import com.chinasofti.lexian.mall.wallet.vo.WalletVo;


@Service
@Transactional

public class WalletServiceImpl extends BaseService implements WalletService {
	private WalletDao walletDao;
	private ValidateCodeDao validateCodeDao;
	private UserDao userDao;
	
	@Autowired
	public void setWalletDao(WalletDao walletDao){
		this.walletDao = walletDao;
	}
	
	@Autowired
	public void setValidateCodeDao(ValidateCodeDao validateCodeDao){
		this.validateCodeDao = validateCodeDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}

	@Override
	public ResultHelper findWallet() {
		String userId = getUser().getId();
		WalletPo po = walletDao.findWallet(userId);
		WalletVo vo = new WalletVo(po);
		return new ResultHelper(Constant.success_code, WalletConstant.success, vo);
	}

	@Override
	public ResultHelper findWalletRecords() {
		String userId = getUser().getId();
		List<WalletRecordPo> pos = walletDao.findWalletRecords(userId);
		List<WalletRecordVo> vos = new ArrayList<WalletRecordVo>();
		for(WalletRecordPo po : pos){
			WalletRecordVo vo = new WalletRecordVo(po);
			vos.add(vo);
		}
		return new ResultHelper(Constant.success_code, WalletConstant.success, vos);
	}

	@Override
	public ResultHelper chargeWallet(WalletRecordVo vo) {
		String userId = getUser().getId();
		
		// 本操作特意删除了手机验证码的检查。如果要查看手机验证码如何检查，请看transferToWallet函数中的代码
		
		WalletPo wallet = walletDao.findWallet(userId);
		// 增加钱包余额
		wallet.setBalance(wallet.getBalance() + vo.getAmount());
		walletDao.updateWallet(wallet);
		
		// 添加充值记录
		WalletRecordPo record = new WalletRecordPo();
		record.setWallet_id(wallet.getId());
		record.setAmount(vo.getAmount());
		record.setOrder_no(OrderNumberGenerator.generateOrderNo());
		record.setType(1);			// 充值
		record.setResulttype(1);
		record.setDescription("在线充值");
		
		walletDao.addWalletRecord(record);
		
		
		return new ResultHelper(Constant.success_code, WalletConstant.success);
	}

	@Override
	public ResultHelper transferToWallet(WalletTransferVo vo) {
		String userId = getUser().getId();
		String userPhone = getUser().getPhone();
		String validationCode = vo.getValidationCode();
		String password = vo.getPassword();
		double amount = vo.getAmount();
		
		if(amount <= 0){
			return new ResultHelper(Constant.failed_code, WalletConstant.invalid_amount);
		}
		
		WalletPo wallet = new WalletPo();
		wallet.setPassword(password);
		wallet.setUser_id(userId);
		
		// 检查用户提供的钱包密码是否正确
		Boolean exists = walletDao.isValidPassword(wallet);
		if (!exists) {
			return new ResultHelper(Constant.failed_code, WalletConstant.invalid_password);
		}
		
		// 获取钱包信息
		wallet = walletDao.findWallet(userId);
		
		// 检查余额是否足够
		if(wallet.getBalance() < amount){
			return new ResultHelper(Constant.failed_code, WalletConstant.inadequate_balance);
		}
		
		// 检查验证码是否正确
		ValidationCodePo validationCodePo = new ValidationCodePo();
		validationCodePo.setCode(validationCode);
		validationCodePo.setPhone(userPhone);
		validationCodePo.setPlatformCode(ValidateConstant.platform_pcweb);
		validationCodePo.setType(ValidateConstant.type_transfer);
		
		if (!validateCodeDao.isExistValidateCode(validationCodePo))
			return new ResultHelper(Constant.failed_code, ValidateConstant.wrong_code);
		validateCodeDao.expireValidateCode(validationCodePo);
		
		// 查找到目标用户信息
		User userQueryPo = new User();
		userQueryPo.setPhone(vo.getTargetUserPhone());
		userQueryPo.setUsername(vo.getTargetUserName());
		User targetUser = userDao.getUser(userQueryPo);
		if (null == targetUser) {
			return new ResultHelper(Constant.failed_code, WalletConstant.invalid_target);
		}
		if (targetUser.getId().equals(userQueryPo.getId())) {
			return new ResultHelper(Constant.failed_code, WalletConstant.invalid_target);
		}
		
		String orderNo = OrderNumberGenerator.generateOrderNo();
		
		// 转入目标钱包
		WalletPo targetWallet = walletDao.findWallet(targetUser.getId());
		targetWallet.setBalance(targetWallet.getBalance() + amount);
		walletDao.updateWallet(targetWallet);
		// 向目标钱包追加转账记录
		WalletRecordPo targetRecord = new WalletRecordPo();
		targetRecord.setWallet_id(targetWallet.getId());
		targetRecord.setAmount(amount);
		targetRecord.setOrder_no(orderNo);
		targetRecord.setType(2);			// 转入
		targetRecord.setResulttype(1);
		targetRecord.setDescription("他人钱包转入资金");
		walletDao.addWalletRecord(targetRecord);
		
		// 转出本人钱包
		wallet = walletDao.findWallet(userId);
		wallet.setBalance(wallet.getBalance() - amount);
		walletDao.updateWallet(wallet);
		// 追加转账记录
		WalletRecordPo record = new WalletRecordPo();
		record.setWallet_id(wallet.getId());
		record.setAmount(amount);
		record.setOrder_no(orderNo);
		record.setType(3);			// 转出
		record.setResulttype(1);
		record.setDescription("转出资金到他人钱包");
		walletDao.addWalletRecord(record);
		
		return new ResultHelper(Constant.success_code, WalletConstant.success);
	}

}
