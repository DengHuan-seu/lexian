package com.chinasofti.lexian.manager.version.po;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import com.chinasofti.lexian.manager.common.util.PageHelper;

public class Version extends PageHelper<Version>{
	private Long id;
	private BigDecimal versionNumber;
	private String appUrl;
	private String appName;
	private Timestamp updateTime;
	private String log;
	private MultipartFile file;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(BigDecimal versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

}
