package com.chinasofti.lexian.manager.version.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.lexian.manager.common.controller.BaseController;
import com.chinasofti.lexian.manager.common.util.ParamValidateUtil;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.common.util.ParamValidateUtil.ParamNotValidException;
import com.chinasofti.lexian.manager.version.constant.VersionConstant;
import com.chinasofti.lexian.manager.version.po.Version;
import com.chinasofti.lexian.manager.version.service.VersionService;

@Controller
@RequestMapping("/version")
public class VersionController extends BaseController {

	private VersionService versionService;

	@Autowired
	public void setVersionService(VersionService versionService) {
		this.versionService = versionService;
	}

	// 添加新版本
	@RequestMapping("/addVersion.do")
	@ResponseBody
	public Object addVersion(Version version) throws UnsupportedEncodingException {
		try {
			ParamValidateUtil.validateNull(version, VersionConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(version.getVersionNumber(), VersionConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(version.getAppName(), VersionConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(version.getLog(), VersionConstant.invalid_arguments);
			ParamValidateUtil.validateEmpty(version.getFile(), VersionConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return versionService.addVersion(version);
	}

	// 删除版本
	@RequestMapping("/deleteVersion.do")
	@ResponseBody
	public Object deleteVersion(int versionId) {
		try {
			ParamValidateUtil.validatePositive(versionId, VersionConstant.invalid_arguments);
		} catch (ParamNotValidException e) {
			return new ResultHelper(e.getCode(), e.getMessage());
		}
		return versionService.deleteVersion(versionId);
	}

	// 获取版本记录
	@RequestMapping("/getVersionRecord.do")
	@ResponseBody
	public Object getVersionRecord(Version version) {
		return versionService.getVersionRecord(version);
	}
	
	// 获取版本详情
	@RequestMapping("/getVersionMessage.do")
	@ResponseBody
	public Object getVersionMessage(int versionId) {

		return versionService.getVersionMessage(versionId);
	}
}
