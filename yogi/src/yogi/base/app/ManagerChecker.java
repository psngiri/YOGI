package yogi.base.app;

import yogi.base.Manager;


public class ManagerChecker implements Checker{
	
	private Manager<?> manager;

	public ManagerChecker(Manager<?> manager) {
		super();
		this.manager = manager;
	}

	public boolean check() {
		if(manager.isEmpty())
		{
			String managerName = manager.getClass().getSimpleName();
			int indexOf = managerName.indexOf("Manager");
			String type = managerName;
			if(indexOf > 0) type = managerName.substring(0, indexOf);
			ErrorReporter.get().info(managerName + " is not loaded, the coresponding factory needs to be populated before executing this module.");
			throw new RuntimeException("No " + type + "'s found");
		}
		return true;
	}

}
