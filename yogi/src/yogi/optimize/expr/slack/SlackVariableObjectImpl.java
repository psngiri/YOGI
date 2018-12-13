package yogi.optimize.expr.slack;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.optimize.expr.Constraint;


public class SlackVariableObjectImpl extends RelationshipObjectImpl<SlackVariableObject> implements SlackVariableObject {
	private Constraint constraint;
	private SlackType slackType;
	
	protected SlackVariableObjectImpl(Constraint constraint, SlackType slackType) {
		super();
		this.constraint = constraint;
		this.slackType = slackType;
	}

	public String getName() {
		String rtnValue = null;
		switch(slackType)
		{	
			case Positive_Slack: 
				rtnValue = SlackVariableObjectProperties.SlackPrefix + SlackVariableObjectProperties.NegativePrefixPart + constraint.getName();
				break;
			case Negative_Slack:  
				rtnValue = SlackVariableObjectProperties.SlackPrefix + SlackVariableObjectProperties.PositivePrefixPart + constraint.getName();
				break;
			case Single_Slack:
				rtnValue = SlackVariableObjectProperties.SlackPrefix + constraint.getName();
		}
		return rtnValue;
	}

	public Constraint getConstraint() {
		return constraint;
	}

	public SlackType getSlackType() {
		return slackType;
	}


}
