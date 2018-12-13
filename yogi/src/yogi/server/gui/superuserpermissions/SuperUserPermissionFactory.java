package yogi.server.gui.superuserpermissions;

import yogi.base.Factory;

public class SuperUserPermissionFactory extends Factory<SuperUserPermission> {
	
	private static SuperUserPermissionFactory itsInstance = new SuperUserPermissionFactory(SuperUserPermissionManager.get());

	protected SuperUserPermissionFactory(SuperUserPermissionManager manager) {
		super(manager);
	}

	public static SuperUserPermissionFactory get() {
		return itsInstance;
	}
}
