package com.chinasofti.lexian.manager.systeminfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chinasofti.lexian.manager.common.controller.BaseController;
import com.chinasofti.lexian.manager.systeminfo.service.impl.SystemInfoServiceImpl;

@Controller
@RequestMapping("/systemInfo")
public class SystemInfoController extends BaseController{

	private SystemInfoServiceImpl systemInfoServiceImpl;
	
	public void setSystemInfoServiceImpl(SystemInfoServiceImpl systemInfoServiceImpl) {
		this.systemInfoServiceImpl = systemInfoServiceImpl;
	}
	
	@RequestMapping("/getSystemInfo.do")
	public Object getSystemInfo(@RequestParam("keys") String keys)
	{
		return systemInfoServiceImpl.getSystemInfo(keys);
	}
}
