package com.chinasofti.lexian.manager.version.dao;

import java.util.List;

import com.chinasofti.lexian.manager.version.po.Version;

public interface VersionDao {
	public void addVersion(Version version);

	public void deleteVersion(int versionId);

	public Version getNewVersion();
	
	public List<Version> getVersionRecord(Version version);

	public Version getVersionMessage(int versionId);
}
