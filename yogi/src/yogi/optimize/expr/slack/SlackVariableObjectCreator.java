package yogi.optimize.expr.slack;

import yogi.base.Creator;
import yogi.optimize.expr.Constraint;

public class SlackVariableObjectCreator implements Creator<SlackVariableObject> {
	private Constraint constraint;
	private SlackType slackType;
	
	public SlackVariableObjectCreator(Constraint constraint, SlackType slackType) {
		super();
		this.constraint = constraint;
		this.slackType = slackType;
	}
	
	public SlackVariableObject create() {
		return new SlackVariableObjectImpl(constraint, slackType);
	}
}
