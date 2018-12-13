package yogi.optimize.expr.slack;

import yogi.base.Factory;

public class SlackVariableObjectFactory extends Factory<SlackVariableObject> {
	private static SlackVariableObjectFactory itsInstance = new SlackVariableObjectFactory(SlackVariableObjectManager.get());

	protected SlackVariableObjectFactory(SlackVariableObjectManager manager) {
		super(manager);
	}

	public static SlackVariableObjectFactory get() {
		return itsInstance;
	}
}
