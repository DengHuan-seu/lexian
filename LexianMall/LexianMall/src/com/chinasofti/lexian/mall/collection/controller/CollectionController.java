package com.chinasofti.lexian.mall.collection.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.mall.collection.service.CollectionService;
import com.chinasofti.lexian.mall.collection.vo.CollectionQueryVo;
import com.chinasofti.lexian.mall.common.controller.BaseController;

@Controller
@RequestMapping("/collection")
public class CollectionController extends BaseController {
	private CollectionService collectionService;

	@Autowired
	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}

	// 删除收藏品
	@RequestMapping(value = "/deleteCollection.do")
	@ResponseBody
	public Object deleteCollection(CollectionQueryVo queryVo) {
		return collectionService.deleteCollection(queryVo);
	}

	// 查找收藏品
	@RequestMapping(value = "/findCollection.do")
	@ResponseBody
	public Object findCollection(CollectionQueryVo queryVo) {
		return collectionService.findCollection(queryVo);

	}

	// 添加收藏品
	@RequestMapping("/addCollection.do")
	@ResponseBody
	public Object addCollection(CollectionQueryVo queryVo) {
		return collectionService.addCollection(queryVo);
	}
	
	// 返回某商品被收藏的次数
	@RequestMapping("/findCount.do")
	@ResponseBody
	public Object findCount(String commodityNo) {
		return collectionService.findCount(commodityNo);
	}
	
	// 查找该用户是否收藏过该商品
	@RequestMapping("/findExist.do")
	@ResponseBody
	public Object findExist(CollectionQueryVo queryVo) {
		return collectionService.findExist(queryVo);
	}
}
