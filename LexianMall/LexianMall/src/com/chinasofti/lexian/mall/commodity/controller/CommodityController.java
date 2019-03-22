package com.chinasofti.lexian.mall.commodity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.mall.commodity.constant.CommodityConstant;
import com.chinasofti.lexian.mall.commodity.po.Cart;
import com.chinasofti.lexian.mall.commodity.service.CommodityService;
import com.chinasofti.lexian.mall.commodity.vo.CategoryCommodityQueryVo;
import com.chinasofti.lexian.mall.commodity.vo.KeywordCommodityQueryVo;
import com.chinasofti.lexian.mall.common.controller.BaseController;
import com.chinasofti.lexian.mall.common.util.ParamValidateUtil;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.common.util.ParamValidateUtil.ParamNotValidException;

@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController {

	private CommodityService commodityService;

	@Autowired
	public void setCommodityService(CommodityService commodityService) {
		this.commodityService = commodityService;
	}
	
	// 返回第三级类别下的所有商品信息列表
	@RequestMapping(value = "/searchCategoryCommodities.do")
	@ResponseBody
	public Object searchCategoryCommodities(CategoryCommodityQueryVo queryVo){
		return commodityService.searchCategoryCommodities(queryVo);
	}
	
	// 根据关键字检索商品，返回商品信息列表
	@RequestMapping(value = "/searchKeywordCommodities.do")
	@ResponseBody
	public Object searchKeywordCommodities(KeywordCommodityQueryVo queryVo){
		return commodityService.searchKeywordCommodities(queryVo);
	}
	
	// 获取指定商品编号的商品详细信息
	@RequestMapping(value = "/getCommodityInfo.do")
	@ResponseBody
	public Object getCommodityInfo(String commodityNo) {
		return commodityService.getCommodityInfo(commodityNo);
	}
	
	// 获取指定编号的商品在某个店铺中的价格
	@RequestMapping(value = "/getStorePrice.do")
	@ResponseBody
	public Object getStorePrice(String commodityNo, String storeNo) {
		return commodityService.getStorePrice(commodityNo, storeNo);
	}

	// 商品加入购物车
	@RequestMapping(value = "/saveCommomdityToTrolley.do")
	@ResponseBody
	public Object saveCommomdityToTrolley(Cart cart) {
		try {
			ParamValidateUtil.validateEmpty(cart.getCommodityNo(), CommodityConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(cart.getStoreNo(),CommodityConstant.invalid_arguments);
			ParamValidateUtil.validateNull(cart.getAmont(), CommodityConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return commodityService.saveCommomdityToTrolley(cart);
	}

	// 更新购物车中的商品数量
	@RequestMapping(value = "/updateTrolleyCount.do")
	@ResponseBody
	public Object updateTrolleyCount(Cart cart) {
		cart.setUserId(getUser().getId());
		return commodityService.updateTrolleyCount(cart);
	}
	
	// 查找当前User下的所有购物车项
	@RequestMapping(value = "/findAlltrolley.do")
	@ResponseBody
	public Object findAlltrolley(Cart cart) {
		return commodityService.findTrolley(cart);
	}
	
	// 提供给Android App的接口，查找当前User下的所有购物车项
	@RequestMapping(value = "/findTrolleyForApp.do")
	@ResponseBody
	public Object findTrolleyForApp() {
		return commodityService.findTrolleyForApp();
	}

	// 批量删除购物车中的商品
	@RequestMapping(value = "/deleteTrolley.do")
	@ResponseBody
	public Object deleteTrolley(String ids) {
		return commodityService.deleteTrolley(ids);
	}

	// 查找指定用户的购物车中的项数
	@RequestMapping("/getTrolleyCount.do")
	@ResponseBody
	public Object getTrolleyCount(){
		return commodityService.getTrolleyCount();
	}
	
	// 提供给Android App，获取商品在某个门店中的详细信息
	@RequestMapping("/getCommodityDetail.do")
	@ResponseBody
	public Object getCommodityDetail(String commodityNo, String storeNo){
		return commodityService.getCommodityDetail(commodityNo, storeNo);
	}
}
