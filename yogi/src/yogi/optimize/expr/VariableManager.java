package yogi.optimize.expr;

import yogi.base.Manager;

public class VariableManager extends Manager<Variable> {
	private static VariableManager variableManager = new VariableManager();

	public static VariableManager get() {
		return variableManager;
	}

	protected VariableManager() {
		super();
	}
}
