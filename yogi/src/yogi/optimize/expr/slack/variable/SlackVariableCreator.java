package yogi.optimize.expr.slack.variable;

import yogi.optimize.expr.Variable;
import yogi.optimize.expr.VariableCreator;
import yogi.optimize.expr.slack.SlackType;
import yogi.optimize.expr.slack.SlackVariableObject;

public class SlackVariableCreator implements VariableCreator<SlackVariableObject> {

	public Variable create(SlackVariableObject slackVariableObject) {
		if(slackVariableObject.getSlackType() == SlackType.Single_Slack)
			return new Variable(slackVariableObject,-Float.MAX_VALUE,Float.MAX_VALUE,slackVariableObject.getName());
		return new Variable(slackVariableObject, slackVariableObject.getName());
	}

}
