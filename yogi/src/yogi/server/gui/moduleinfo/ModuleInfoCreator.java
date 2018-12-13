package yogi.server.gui.moduleinfo;

import yogi.base.Creator;
import yogi.server.gui.applicationinfo.ApplicationInfo;

public class ModuleInfoCreator implements Creator<ModuleInfo>
{
	private ApplicationInfo applicationName;
	private String moduleName;

	public ModuleInfo create()
	{
		return new ModuleInfoImpl(applicationName, moduleName);
	}
	
	public ApplicationInfo getApplicationName() {
		return applicationName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setApplicationName(ApplicationInfo applicationName) {
		this.applicationName = applicationName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Override
	public String toString() {
		return applicationName+"/"+moduleName;
	}
	
}