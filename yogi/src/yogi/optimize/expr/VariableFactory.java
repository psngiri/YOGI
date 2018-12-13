package yogi.optimize.expr;

import yogi.base.SynchronizedFactory;

public class VariableFactory extends SynchronizedFactory<Variable> {
	private static VariableFactory variableFactory = new VariableFactory(VariableManager.get());

	public VariableFactory(VariableManager manager) {
		super(manager);
	}

	public static VariableFactory get() {
		return variableFactory;
	}

	void addVariable(Variable object) {
		int id = this.size();
		object.setId(id);
		super.add(object);
	}

}
