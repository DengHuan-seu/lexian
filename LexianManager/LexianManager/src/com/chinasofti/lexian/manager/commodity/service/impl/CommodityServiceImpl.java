package com.chinasofti.lexian.manager.commodity.service.impl;

import com.chinasofti.lexian.manager.commodity.service.CommodityService;
import com.chinasofti.lexian.manager.commodity.vo.CommodityQueryVo;
import com.chinasofti.lexian.manager.commodity.vo.CommoditySpecVo;
import com.chinasofti.lexian.manager.commodity.vo.CommodityVo;
import com.chinasofti.lexian.manager.commodity.constant.CommodityConstant;
import com.chinasofti.lexian.manager.commodity.vo.Fckeditor;
import com.chinasofti.lexian.manager.commodity.vo.CommodityInfoVo;
import com.chinasofti.lexian.manager.commodity.vo.CommodityPictureVo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.manager.commodity.dao.CommodityDao;
import com.chinasofti.lexian.manager.commodity.po.CommodityCategoryPo;
import com.chinasofti.lexian.manager.commodity.po.CommodityPicturePo;
import com.chinasofti.lexian.manager.commodity.po.CommodityPo;
import com.chinasofti.lexian.manager.commodity.po.CommoditySpecPo;
import com.chinasofti.lexian.manager.common.service.BaseService;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ResultHelper;

@Service
@Transactional
public class CommodityServiceImpl extends BaseService implements CommodityService{
	private CommodityDao commodityDao;
	
	private String picServerPhysicalPath;		// 所有图片上传物理路径
	private String commodityPhysicalPath;		// 商品图片上传物理路径
	private String commodityVirtualPath;		// 商品图片上传虚拟路径
	
	@Value("${picServerPhysicalPath}")
	public void setPicServerPhysicalPath(String picServerPhysicalPath) {
		this.picServerPhysicalPath = picServerPhysicalPath;
	}
	
	@Value("${commodityPhysicalPath}")
	public void setCommodityPhysicalPath(String commodityPhysicalPath) {
		this.commodityPhysicalPath = commodityPhysicalPath;
	}

	@Value("${commodityVirtualPath}")
	public void setCommoditywebPath(String commodityVirtualPath) {
		this.commodityVirtualPath = commodityVirtualPath;
	}

	@Autowired
	public void setCommodityDao(CommodityDao commodityDao) {
		this.commodityDao = commodityDao;
	}
	
	@Override
	public ResultHelper getCommodityList(CommodityQueryVo commodityQueryVo) {
		List<CommodityVo> cvs = new ArrayList<CommodityVo>();
		List<CommodityPo> cps = commodityDao.getCommodityList(commodityQueryVo);
		for(CommodityPo cp : cps){
			CommodityVo cv = new CommodityVo(cp);
			cvs.add(cv);
		}
		
		return new ResultHelper(Constant.success_code, CommodityConstant.success,
				cvs, commodityQueryVo.getTotal());
	}
	

	@Override
	public Object addCommodityInfo(CommodityInfoVo vo) {
		Boolean result = commodityDao.hasExistedCommodityNo(vo.getCommodityNo());
		if(result){
			return new ResultHelper(Constant.failed_code, CommodityConstant.duplicate_commodityno);
		}
		
		CommodityPo po = new CommodityPo();
		po.setCommodity_no(vo.getCommodityNo());
		po.setName(vo.getName());
		po.setCategory_id(vo.getCategoryId());
		po.setIntroduce(vo.getIntroduce());
		
		commodityDao.addCommodityInfo(po);
		
		return new ResultHelper(Constant.success_code, CommodityConstant.success);
	}

	@Override
	public ResultHelper getCommodityInfo(String commodityNo) {
		CommodityPo cp = commodityDao.getCommodity(commodityNo);
		List<CommodityPicturePo> cpp = commodityDao.getCommodityPictures(commodityNo);
		CommodityCategoryPo cat = commodityDao.getCommodityCategories(cp.getCategory_id());
		List<CommoditySpecPo> specs = commodityDao.getCommoditySpecs(commodityNo);
		
		CommodityInfoVo cv = new CommodityInfoVo(cp, cpp, cat, specs);
		
		return new ResultHelper(Constant.success_code, CommodityConstant.success, cv);
	}

	@Override
	public String uploadFckPicture(Fckeditor fckeditor) {
		String picUrl = null;
		if (fckeditor.getUpload() != null) {
			picUrl = saveImage(commodityVirtualPath, commodityPhysicalPath, fckeditor.getUpload());
			if (picUrl == null) {
				return CommodityConstant.file_Error;
			}
			picUrl = com.chinasofti.lexian.manager.common.util.Config.PicServerVirtualPath + picUrl;
		}

		StringBuilder resultUrl = new StringBuilder();
		resultUrl.append("<script type=\"text/javascript\">").append("window.parent.CKEDITOR.tools.callFunction("
				+ fckeditor.getCKEditorFuncNum() + ",'" + picUrl + "','');").append("</script>");
		return resultUrl.toString();
	}

	@Override
	public Object updateCommodityInfo(CommodityInfoVo civ) {
		String detailed = civ.getDetailed().replace(com.chinasofti.lexian.manager.common.util.Config.PicServerVirtualPath, "");
		civ.setDetailed(detailed);
		commodityDao.updateCommodityInfo(civ);
		return new ResultHelper(Constant.success_code, CommodityConstant.success);
	}

	@Override
	public Object updateMainPicture(CommodityPictureVo cpv) {
		if(cpv.getFileMainPicture().getSize() != 0){
			String picUrl = saveImage(commodityVirtualPath, commodityPhysicalPath, 
					cpv.getFileMainPicture());
			if (picUrl == null) {
				return new ResultHelper(Constant.failed_code, CommodityConstant.file_Error);
			}
			CommodityPicturePo cpp = new CommodityPicturePo();
			cpp.setCommodity_no(cpv.getCommodityNo());
			cpp.setPicture_url(picUrl);
			commodityDao.updateMainPicture(cpp);
			return new ResultHelper(Constant.success_code, 
					CommodityConstant.success, picUrl);
		}
		return new ResultHelper(Constant.failed_code, CommodityConstant.file_Error);
	}

	@Override
	public Object updateSubPicture(CommodityPictureVo cpv) {
		if(cpv.getFileSubPicture().getSize() != 0){
			String picUrl = saveImage(commodityVirtualPath, commodityPhysicalPath, 
					cpv.getFileSubPicture());
			if (picUrl == null) {
				return new ResultHelper(Constant.failed_code, CommodityConstant.file_Error);
			}
			CommodityPicturePo cpp = new CommodityPicturePo();
			cpp.setCommodity_no(cpv.getCommodityNo());
			cpp.setPicture_url(picUrl);
			commodityDao.addSubPicture(cpp);
			
			CommodityPictureVo result = new CommodityPictureVo();
			result.setId(cpp.getId());
			result.setPictureUrl(cpp.getPicture_url());
			result.setCommodityNo(cpp.getCommodity_no());
			
			return new ResultHelper(Constant.success_code, CommodityConstant.success, result);
		}
		return new ResultHelper(Constant.failed_code, CommodityConstant.file_Error);
	}

	@Override
	public Object deleteSubPicture(CommodityPictureVo cpv) {
		deleteImageFile(picServerPhysicalPath, cpv.getPictureUrl());
		commodityDao.deleteSubPicture(cpv.getId());
		return new ResultHelper(Constant.success_code, CommodityConstant.success);
	}

	@Override
	public Object deleteSpec(int specId) {
		commodityDao.deleteSpec(specId);
		return new ResultHelper(Constant.success_code, CommodityConstant.success);
	}

	@Override
	public Object addSpec(CommoditySpecVo specVo) {
		CommoditySpecPo specPo = new CommoditySpecPo();
		specPo.setCommodity_no(specVo.getCommodityNo());
		specPo.setSpec_group(specVo.getSpecGroup());
		specPo.setSpec_name(specVo.getSpecName());
		commodityDao.addSpec(specPo);
		
		specVo.setSpecId(specPo.getSpecid());
		
		return new ResultHelper(Constant.success_code, CommodityConstant.success, specVo);
	}
}
