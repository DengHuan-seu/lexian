package com.chinasofti.lexian.mall.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.mall.store.vo.AreaVo;
import com.chinasofti.lexian.mall.common.controller.BaseController;
import com.chinasofti.lexian.mall.store.service.StoreService;
import com.chinasofti.lexian.mall.store.vo.StoreLocationVo;

@Controller
@RequestMapping("/store")
public class StoreController extends BaseController {
	private StoreService storeService;

	@Autowired
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	
	// 获取门店信息
	@RequestMapping(value = "/getLocation.do")
	@ResponseBody
	public Object getLocation(StoreLocationVo storeLocationVo) {
		return storeService.getLocation(storeLocationVo);
	}

	// 获取区域级别信息
	@RequestMapping( "/findArea.do")
	@ResponseBody
	public Object findArea(AreaVo areaVo){
		return storeService.findArea(areaVo);
	}
	
	// 获取门店信息
	@RequestMapping("/getStoreInfoByStoreNo.do")
	@ResponseBody
	public Object getStoreInfoByStoreNo(@RequestParam(value = "storeNo") String storeNo) {
		return storeService.getStoreInfoByStoreNo(storeNo);
	}
}
