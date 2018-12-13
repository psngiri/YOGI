package yogi.report.group;

import java.util.HashSet;
import java.util.Set;

import yogi.report.split.SplitGroupCreator;


public abstract class BaseGroupBy<T> implements GroupBy<T> {
	private GroupCreator<T> groupCreator;
	private GroupMultiplierCalculator<T> multiplierCalculator;
	private Set<String> keys;
	public BaseGroupBy() {
		super();
		groupCreator = new SplitGroupCreator<T>(this);
		keys = new HashSet<String>();
	}

	public boolean hasKey(String keyName) {
		return keys.contains(keyName);
	}

	protected void addKey(String keyName)
	{
		keys.add(keyName);
	}
	
	public GroupMultiplierCalculator<T> getMultiplierCalculator() {
		return multiplierCalculator;
	}

	public GroupCreator<T> getGroupCreator() {
		return groupCreator;
	}

	public void setGroupCreator(GroupCreator<T> groupCreator) {
		this.groupCreator = groupCreator;
	}

	public void setMultiplierCalculator(GroupMultiplierCalculator<T> multiplierCalculator) {
		this.multiplierCalculator = multiplierCalculator;
	}
}
