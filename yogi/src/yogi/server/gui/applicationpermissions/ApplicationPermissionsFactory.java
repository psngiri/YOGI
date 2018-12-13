package yogi.server.gui.applicationpermissions;

import yogi.base.Factory;

public class ApplicationPermissionsFactory extends Factory<ApplicationPermissions> {
	private static ApplicationPermissionsFactory itsInstance = new ApplicationPermissionsFactory(ApplicationPermissionsManager.get());

	protected ApplicationPermissionsFactory(ApplicationPermissionsManager manager) {
		super(manager);
	}

	public static ApplicationPermissionsFactory get() {
		return itsInstance;
	}
}
