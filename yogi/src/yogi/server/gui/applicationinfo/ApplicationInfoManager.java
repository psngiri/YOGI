package yogi.server.gui.applicationinfo;

import yogi.base.ObjectNotFoundException;
import yogi.base.relationship.index.IndexedManager;
import yogi.server.gui.partition.PartitionManager;

public class ApplicationInfoManager extends IndexedManager<ApplicationInfo, String>
{
	private static ApplicationInfoManager itsInstance = new ApplicationInfoManager();
	public static ApplicationInfo NULL = new ApplicationInfoImpl("", PartitionManager.ANY);
	public static ApplicationInfo ALL = new ApplicationInfoImpl("ALL", PartitionManager.ANY);

	protected ApplicationInfoManager()
	{
		super();
	}

	public static ApplicationInfoManager get()
	{
		return itsInstance;
	}

	public ApplicationInfo getApplicationInfo(String appName)
	{
		ApplicationInfo appInfo = NULL;
		if (null == appName || appName.trim().isEmpty()) {
			return appInfo;
		}
		appName = appName.trim();
		if(appName.equalsIgnoreCase("ALL")) return ALL;
		try {
			appInfo = this.getObject(appName);
		} catch (ObjectNotFoundException e) {
			throw new RuntimeException("ApplicationInfo for "+appName+" not Found");
		}
		return appInfo;
	}

}