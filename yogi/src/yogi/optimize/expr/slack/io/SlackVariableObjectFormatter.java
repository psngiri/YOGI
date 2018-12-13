package yogi.optimize.expr.slack.io;

import yogi.base.io.Formatter;
import yogi.optimize.expr.Constraint;
import yogi.optimize.expr.VariableAssistant;
import yogi.optimize.expr.io.ConstraintFormatter;
import yogi.optimize.expr.slack.SlackType;
import yogi.optimize.expr.slack.SlackVariableObject;

public class SlackVariableObjectFormatter implements Formatter<SlackVariableObject> {
	public static boolean WriteConstraint = false;
	private ConstraintFormatter constraintFormatter = new ConstraintFormatter();
	public String format(SlackVariableObject slackVariableObject) {
		Constraint constraint = slackVariableObject.getConstraint();
		if(slackVariableObject.getSlackType() == SlackType.Single_Slack && constraint.getName().startsWith("SlkRow")) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(constraint.getName()).append(" ");
		
		//Check to see if this was a negative slack variable, if so include the sign in the display
		if(slackVariableObject.getSlackType() == SlackType.Negative_Slack)
		{
			sb.append("-");
		}
		sb.append(VariableAssistant.get().getVariable(slackVariableObject).getSolutionValue());
		if(WriteConstraint) sb.append(" ").append(constraintFormatter.format(constraint));
		return sb.toString();
	}
}
