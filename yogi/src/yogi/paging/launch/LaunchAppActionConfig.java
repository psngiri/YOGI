package yogi.paging.launch;

import yogi.paging.TableActionConfig;
import yogi.paging.launch.command.LaunchAppCommand;

public class LaunchAppActionConfig extends TableActionConfig {
	
	public static String LaunchApp = "LaunchApp";

	private static final long serialVersionUID = 1089791718252684446L;

	public LaunchAppActionConfig(String name, Class<? extends LaunchAppCommand> klass, String iconCls) {
		super(name, klass, LaunchApp, iconCls);
	}
	
	public LaunchAppActionConfig(String name, Class<? extends LaunchAppCommand> klass, String iconCls, int shortcutKeyCode) {
		super(name, klass, LaunchApp, iconCls);
	}
}
