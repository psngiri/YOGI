package yogi.server.gui.moduleinfo;

import yogi.base.validation.ValidatorAdapter;

public class ModuleInfoValidator extends ValidatorAdapter<ModuleInfo>
{
	@Override
	public boolean validate(ModuleInfo moduleInfo)
	{
		if(moduleInfo==ModuleInfoManager.NULL) return false;
		return true;
	}
}
