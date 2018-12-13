package yogi.server.gui.applicationinfo;

import yogi.base.Factory;

public class ApplicationInfoFactory extends Factory<ApplicationInfo>
{
	private static ApplicationInfoFactory itsInstance = new ApplicationInfoFactory(ApplicationInfoManager.get());

	protected ApplicationInfoFactory(ApplicationInfoManager manager)
	{
		super(manager);
	}

	public static ApplicationInfoFactory get()
	{
		return itsInstance;
	}
}
