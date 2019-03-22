package com.chinasofti.lexian.mall.browse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.mall.browse.service.BrowseSevice;
import com.chinasofti.lexian.mall.browse.service.vo.FindBrowseVo;
import com.chinasofti.lexian.mall.common.controller.BaseController;

@Controller
@RequestMapping("/browse")
public class BrowseController extends BaseController {

	private BrowseSevice browseSevice;

	@Autowired
	public void setBrowseSevice(BrowseSevice browseSevice) {
		this.browseSevice = browseSevice;
	}

	// 查找浏览记录
	@RequestMapping("/findBrowse.do")
	@ResponseBody
	public Object findBrowse(FindBrowseVo findBrowseVo) {
		findBrowseVo.setIsgetTotal(true);
		return browseSevice.findBrowse(findBrowseVo);
	}

	// 添加浏览记录
	@RequestMapping("/addBrowse.do")
	@ResponseBody
	public Object addBrowse(String commodityNo) {
		return browseSevice.addBrowse(commodityNo);
	}
}
