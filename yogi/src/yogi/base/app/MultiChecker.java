package yogi.base.app;

import java.util.ArrayList;
import java.util.List;

import yogi.base.Manager;


public class MultiChecker implements Checker{
	
	private List<Checker> checkers;

	public MultiChecker() {
		super();
		this.checkers = new ArrayList<Checker>();
	}

	public void addManager(Manager<?> manager)
	{
		addChecker(new ManagerChecker(manager));
	}
	
	public void addChecker(Checker checker)
	{
		checkers.add(checker);
	}
	
	public boolean check() {
		for(Checker checker: checkers)
		{
			if(!checker.check()) return false;
		}
		return true;
	}

}
