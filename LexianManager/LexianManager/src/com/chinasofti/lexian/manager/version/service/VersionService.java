package com.chinasofti.lexian.manager.version.service;

import java.io.UnsupportedEncodingException;

import com.chinasofti.lexian.manager.common.util.ResultHelper;
import com.chinasofti.lexian.manager.version.po.Version;

public interface VersionService {
	public ResultHelper addVersion(Version version) throws UnsupportedEncodingException;

	public ResultHelper deleteVersion(int versionId);

	public ResultHelper getVersionRecord(Version version);
	
	public ResultHelper getVersionMessage(int versionId);

}