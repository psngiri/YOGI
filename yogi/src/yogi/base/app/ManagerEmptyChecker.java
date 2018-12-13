package yogi.base.app;

import yogi.base.Manager;


public class ManagerEmptyChecker implements Checker{
	
	private Manager<?> manager;

	public ManagerEmptyChecker(Manager<?> manager) {
		super();
		this.manager = manager;
	}

	public boolean check() {
		if(manager.isEmpty())
		{
			return false;
		}
		return true;
	}

}
