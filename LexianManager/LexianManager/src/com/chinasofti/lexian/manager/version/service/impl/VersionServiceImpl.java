package com.chinasofti.lexian.manager.version.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.lexian.manager.common.service.BaseService;
import com.chinasofti.lexian.manager.common.util.CommonUtil;
import com.chinasofti.lexian.manager.common.util.Constant;
import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.version.constant.VersionConstant;
import com.chinasofti.lexian.manager.version.dao.VersionDao;
import com.chinasofti.lexian.manager.version.po.Version;
import com.chinasofti.lexian.manager.version.service.VersionService;

@Service
@Transactional
public class VersionServiceImpl extends BaseService implements VersionService {
	private String apkPhysicalPath;
	private String apkVirtualPath;

	@Value("${apkPhysicalPath}")
	public void setApkPhysicalPath(String apkPhysicalPath) {
		this.apkPhysicalPath = apkPhysicalPath;
	}

	public String getApkPhysicalPath() {
		return apkPhysicalPath;
	}

	@Value("${apkVirtualPath}")
	public void setApkVirtualPath(String apkVirtualPath) {
		this.apkVirtualPath = apkVirtualPath;
	}

	public String getApkVirtualPath() {
		return apkVirtualPath;
	}
	
	private VersionDao versionDao;
	
	@Autowired
	public void setVersionDao(VersionDao versionDao) {
		this.versionDao = versionDao;
	}

	@Override
	public ResultHelper addVersion(Version version) throws UnsupportedEncodingException {
		String  appName = URLDecoder.decode(version.getAppName(),"UTF-8");
		String   log = URLDecoder.decode(version.getLog(),"UTF-8");
		version.setAppName(appName);
		version.setLog(log);
		String webPath = null;
		String uploadPath=null;
		webPath = apkVirtualPath;
		uploadPath=apkPhysicalPath;
		
		Version lastUpdatedVersion = versionDao.getNewVersion();
		if (null != lastUpdatedVersion
				&& lastUpdatedVersion.getVersionNumber().compareTo(
						version.getVersionNumber()) >= 0) {
			return new ResultHelper(Constant.failed_code, VersionConstant.invalid_version);
		}
		version.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		String appUrl = saveFile(webPath, uploadPath, version.getFile());
		if (null == appUrl)
			return new ResultHelper(Constant.failed_code, VersionConstant.failed_save);
		version.setAppUrl(appUrl);
		version.setAppName(version.getAppName() + version.getVersionNumber());
		versionDao.addVersion(version);
		return new ResultHelper(Constant.success_code, VersionConstant.success);
	}

	@Override
	public ResultHelper deleteVersion(int versionId) {
		Version version=versionDao.getVersionMessage(versionId);
		if (!CommonUtil.delFile(apkPhysicalPath,version.getAppUrl())) {
			return new ResultHelper(Constant.failed_code, VersionConstant.failed_delete);
		}
		versionDao.deleteVersion(versionId);
		return new ResultHelper(Constant.success_code, VersionConstant.success);
	}

	@Override
	public ResultHelper getVersionRecord(Version version) {
		return new ResultHelper(Constant.success_code,VersionConstant.success , 
				versionDao.getVersionRecord(version),version.getTotal());
	}

	@Override
	public ResultHelper getVersionMessage(int versionId) {
		return new ResultHelper(Constant.success_code, 
				VersionConstant.success,
				versionDao.getVersionMessage(versionId));
	}
}
