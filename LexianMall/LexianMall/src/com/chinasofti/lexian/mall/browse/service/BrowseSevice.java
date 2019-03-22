package com.chinasofti.lexian.mall.browse.service;

import com.chinasofti.lexian.mall.browse.service.vo.FindBrowseVo;
import com.chinasofti.lexian.mall.common.util.ResultHelper;

public interface BrowseSevice {
	public ResultHelper findBrowse(FindBrowseVo findBrowseVo);
	public ResultHelper addBrowse(String commodityNo);

}
