package yogi.report.column.alias.condition;

import yogi.report.condition.Condition;

public class AliasCondition<C> {
	private String alias;
	private Condition<C> condition;
	
	public AliasCondition(String alias, Condition<C> condition) {
		super();
		this.alias = alias;
		this.condition = condition;
	}
	public String getAlias() {
		return alias;
	}
	public Condition<C> getCondition() {
		return condition;
	}
	
}
