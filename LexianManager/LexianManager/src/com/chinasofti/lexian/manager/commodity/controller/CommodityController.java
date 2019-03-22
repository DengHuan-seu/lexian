package com.chinasofti.lexian.manager.commodity.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.manager.commodity.constant.CommodityConstant;
import com.chinasofti.lexian.manager.commodity.service.CommodityService;
import com.chinasofti.lexian.manager.commodity.vo.CommodityInfoVo;
import com.chinasofti.lexian.manager.commodity.vo.CommodityPictureVo;
import com.chinasofti.lexian.manager.commodity.vo.CommodityQueryVo;
import com.chinasofti.lexian.manager.commodity.vo.CommoditySpecVo;
import com.chinasofti.lexian.manager.commodity.vo.Fckeditor;
import com.chinasofti.lexian.manager.common.controller.BaseController;
import com.chinasofti.lexian.manager.common.util.ParamValidateUtil;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.common.util.ParamValidateUtil.ParamNotValidException;

@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController {
	private CommodityService commodityService;

	@Autowired
	public void setCommodityService(CommodityService commodityService) {
		this.commodityService = commodityService;
	}
	
	@RequestMapping("/getCommodityList.do")
	@ResponseBody
	public Object getCommodityList(CommodityQueryVo commodityQueryVo){
		return commodityService.getCommodityList(commodityQueryVo);
	}
	
	@RequestMapping("/addCommodityInfo.do")
	@ResponseBody
	public Object addCommodityInfo(CommodityInfoVo vo){
		return commodityService.addCommodityInfo(vo);
	}
	
	@RequestMapping("/getCommodityInfo.do")
	@ResponseBody
	public Object getCommodity(String commodityNo){
		return commodityService.getCommodityInfo(commodityNo);
	}
	
	@RequestMapping("/uploadFckPicture.do")
	@ResponseBody
	public Object uploadFckPicture(Fckeditor fckeditor,HttpServletResponse response){
		String result =	 commodityService.uploadFckPicture(fckeditor);	
		PrintWriter out = null;
		try{
			out = response.getWriter();
			out.print(result);
			out.flush();
		}
		catch(Exception e){
			logger.error(e.getMessage(),e);
		}
		finally{
		   if(null!=out){
			   out.close();
			   out=null;
		   }
		}
		return null;
	}
	
	@RequestMapping("/updateCommodityInfo.do")
	@ResponseBody
	public Object updateCommodityInfo(CommodityInfoVo civ){
		try{
			ParamValidateUtil.validateEmpty(civ.getCategoryId(), CommodityConstant.category_empty);
			ParamValidateUtil.validateEmpty(civ.getIntroduce(), CommodityConstant.introduce_empty);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return commodityService.updateCommodityInfo(civ);
	}
	
	@RequestMapping("/uploadMainPicture.do")
	@ResponseBody
	public Object updateMainPicture(CommodityPictureVo cpv){
		return commodityService.updateMainPicture(cpv);
	}
	
	@RequestMapping("/uploadSubPicture.do")
	@ResponseBody
	public Object updateSubPicture(CommodityPictureVo cpv){
		return commodityService.updateSubPicture(cpv);
	}
	
	@RequestMapping("/deleteSubPicture.do")
	@ResponseBody
	public Object deleteSubPicture(CommodityPictureVo cpv){
		return commodityService.deleteSubPicture(cpv);
	}
	
	@RequestMapping("/deleteSpec.do")
	@ResponseBody
	public Object deleteSpec(int specId){
		return commodityService.deleteSpec(specId);
	}
	
	@RequestMapping("/addSpec.do")
	@ResponseBody
	public Object addSpec(CommoditySpecVo specVo){
		return commodityService.addSpec(specVo);
	}
}
