package yogi.server.gui.moduleinfo;

import yogi.base.Factory;

public class ModuleInfoFactory extends Factory<ModuleInfo>
{
	private static ModuleInfoFactory itsInstance = new ModuleInfoFactory(ModuleInfoManager.get());

	protected ModuleInfoFactory(ModuleInfoManager manager)
	{
		super(manager);
	}

	public static ModuleInfoFactory get()
	{
		return itsInstance;
	}
}
