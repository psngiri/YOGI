package yogi.server.gui.applicationinfo;

import yogi.base.validation.ValidatorAdapter;

public class ApplicationInfoValidator extends ValidatorAdapter<ApplicationInfo>
{
	@Override
	public boolean validate(ApplicationInfo appInfo)
	{
		if(appInfo == ApplicationInfoManager.NULL) return false;
		return true;
	}
}
