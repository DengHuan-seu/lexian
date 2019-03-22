package com.chinasofti.lexian.mall.special.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.mall.common.controller.BaseController;
import com.chinasofti.lexian.mall.common.util.ParamValidateUtil;
import com.chinasofti.lexian.mall.common.util.ResultHelper;
import com.chinasofti.lexian.mall.common.util.ParamValidateUtil.ParamNotValidException;
import com.chinasofti.lexian.mall.special.constant.SpecialConstant;
import com.chinasofti.lexian.mall.special.po.SpecialCommodityPo;
import com.chinasofti.lexian.mall.special.service.SpecialService;

@Controller
@RequestMapping("/special")
public class SpecialController extends BaseController{

	private SpecialService specialService;
	
	@Autowired
	public void setSpecialService(SpecialService specialService) {
		this.specialService = specialService;
	}
	
	@RequestMapping("/findSpecialCommodityBySpecialId.do")
	@ResponseBody
	public Object findSpecialCommodityBySpecialId(SpecialCommodityPo specialCommodityPo){
		try {
			ParamValidateUtil.validateEmpty(specialCommodityPo.getSpecialId(), SpecialConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(specialCommodityPo.getCount(), SpecialConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return specialService.findSpecialCommodityBySpecialId(specialCommodityPo);
	}
}
