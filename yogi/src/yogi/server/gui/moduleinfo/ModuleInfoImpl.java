package yogi.server.gui.moduleinfo;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.server.gui.applicationinfo.ApplicationInfo;

public class ModuleInfoImpl extends RelationshipObjectImpl<ModuleInfo> implements ModuleInfo
{
	private ApplicationInfo applicationName;
	private String moduleName;
	
	protected ModuleInfoImpl(ApplicationInfo applicationName, String moduleName)
	{
		super();
		this.applicationName = applicationName;
		this.moduleName = moduleName;
	}
	
	public ApplicationInfo getAppName()
	{
		return applicationName;
	}
	
	@Override
	public String getModuleName() {
		return moduleName;
	}

	public String getName()
	{
		return applicationName.getAppName()+"/"+moduleName;
	}

	@Override
	public String toString()
	{
		return applicationName.getAppName()+"/"+moduleName;
	}
	
}
