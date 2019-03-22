package com.chinasofti.lexian.mall.browse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.mall.browse.constant.BrowseConstant;
import com.chinasofti.lexian.mall.browse.dao.BrowseDao;
import com.chinasofti.lexian.mall.browse.po.BrowsePo;
import com.chinasofti.lexian.mall.browse.service.BrowseSevice;
import com.chinasofti.lexian.mall.browse.service.vo.FindBrowseVo;
import com.chinasofti.lexian.mall.common.service.BaseService;
import com.chinasofti.lexian.mall.common.util.Constant;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.user.po.User;

@Service
@Transactional
public class BrowseServiceImpl extends BaseService implements BrowseSevice {

	private BrowseDao browseDao;
	
	@Autowired
	public void setBrowseDao(BrowseDao browseDao) {
		this.browseDao = browseDao;
	}

	@Override
	public ResultHelper findBrowse(FindBrowseVo findBrowseVo) {
		BrowsePo browsePo = new BrowsePo();
		
		if(getUser()!=null){
			browsePo.setUserId(getUser().getId());
		}
		browsePo.setMaxnum(findBrowseVo.getMaxnum());
		
		List<BrowsePo> browsePos = browseDao.findBrowse(browsePo);
		return new ResultHelper(Constant.success_code, BrowseConstant.success, browsePos);
	}

	@Override
	public ResultHelper addBrowse(String commodityNo) {
		BrowsePo browsePo = new BrowsePo();
		User user = getUser();
		if(user != null){
			browsePo.setUserId(getUser().getId());
		}
		browsePo.setCommodity_no(commodityNo);
		browseDao.addBrowse(browsePo);
		return new ResultHelper(Constant.success_code, BrowseConstant.success);
	}
}
