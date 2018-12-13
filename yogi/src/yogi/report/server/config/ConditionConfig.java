package yogi.report.server.config;

import java.io.Serializable;

import yogi.report.condition.Condition;


public abstract class ConditionConfig<C> implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Validator validator;
	
	public ConditionConfig(String conditionName, Validator validator) {
		super();
		this.name = conditionName;
		this.validator = validator;
	}
	
	public String getName() {
		return name;
	}

	public Validator getValidator() {
		return validator;
	}
	@Override
	public String toString() {
		return name;
	}

	public abstract Condition<C> getCondition(String value);
}