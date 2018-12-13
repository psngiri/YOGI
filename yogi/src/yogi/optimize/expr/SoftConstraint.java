package yogi.optimize.expr;

import yogi.base.relationship.RelationshipObject;
import yogi.optimize.expr.slack.SlackType;
import yogi.optimize.expr.slack.SlackVariableObject;
import yogi.optimize.expr.slack.SlackVariableObjectCreator;
import yogi.optimize.expr.slack.SlackVariableObjectFactory;

public class SoftConstraint extends Constraint {
	private Variable positiveSlackVariable;
	private Variable negativeSlackVariable;
	private Variable singleSlackVariable;
	
	public SoftConstraint(String prefix, RelationshipObject constraintObject, Expression expression, float lowerBound, float upperBound, float positiveSlackCoefficient, float negativeSlackCoefficient) {
		this(prefix, "", constraintObject, expression , lowerBound, upperBound, positiveSlackCoefficient, negativeSlackCoefficient);
	}
	
	public SoftConstraint(String prefix, String suffix, RelationshipObject constraintObject, Expression expression, float lowerBound, float upperBound, float positiveSlackCoefficient, float negativeSlackCoefficient) {
		super(prefix, suffix, constraintObject, expression, lowerBound, upperBound);
		addPossitiveSlack(positiveSlackCoefficient, true);
		addNegativeSlack(negativeSlackCoefficient, true);
	}
	
	public SoftConstraint(String prefix, String suffix, RelationshipObject constraintObject, Expression expression, float lowerBound, float upperBound, float slackCoefficient) {
		super(prefix, suffix, constraintObject, expression, lowerBound, upperBound);
		addSingleSlack(slackCoefficient, true, this);
	}

	
	public SoftConstraint(String prefix, String suffix, RelationshipObject constraintObject, Expression expression, float lowerBound, float upperBound, float slackCoefficient, boolean doSpecial) {
		super(prefix, suffix, constraintObject, expression, lowerBound, upperBound);
		Expression exp = new Expression();
	    Constraint slackConstraint = new Constraint("SlkRow" + prefix, suffix, constraintObject, exp, 0, 0);
		addSingleSlack(slackCoefficient, false, slackConstraint);
		addPossitiveSlack(-slackCoefficient, false);
		addNegativeSlack(slackCoefficient, false);
		exp.plus(new LinearExpressionTerm(singleSlackVariable));
		exp.minus(new LinearExpressionTerm(positiveSlackVariable));
		exp.plus(new LinearExpressionTerm(negativeSlackVariable));
	}

	private void addSingleSlack(float slackCoefficient, boolean addToObjective, Constraint constraint) {
		if(slackCoefficient == 0) return;
		SlackVariableObject slackVariableObject = SlackVariableObjectFactory.get().create(new SlackVariableObjectCreator(constraint, SlackType.Single_Slack));
		singleSlackVariable = VariableAssistant.get().getVariable(slackVariableObject);
	    plus(new LinearExpressionTerm(singleSlackVariable));
	    if(addToObjective) Objective.objectiveFunction.plus(new LinearExpressionTerm(slackCoefficient, singleSlackVariable));
	}
	
	private void addPossitiveSlack(float positiveSlackCoefficient, boolean addToConstraint){
		if(positiveSlackCoefficient == 0) return;
		
		if(this.getEqualityType() != EqualityType.EQUAL_TO && 
		   this.getEqualityType() != EqualityType.GREATER_THAN)
				throw new RuntimeException("A positive slack is not valid for a constraint with equality type " + this.getEqualityType().getDescription());
		
		SlackVariableObject slackVariableObject = SlackVariableObjectFactory.get().create(new SlackVariableObjectCreator(this, SlackType.Positive_Slack));
	    positiveSlackVariable = VariableAssistant.get().getVariable(slackVariableObject);
	    if(addToConstraint) plus(new LinearExpressionTerm(positiveSlackVariable));
	    Objective.objectiveFunction.plus(new LinearExpressionTerm(positiveSlackCoefficient, positiveSlackVariable));
	}
	
	private void addNegativeSlack(float negativeSlackCoefficient, boolean addToConstraint){
		if(negativeSlackCoefficient == 0) return;
		
		if(this.getEqualityType() != EqualityType.EQUAL_TO &&
		   this.getEqualityType() != EqualityType.LESS_THAN)
				throw new RuntimeException("A negative slack is not valid for a constraint with equality type " + this.getEqualityType().getDescription());
		
		SlackVariableObject slackVariableObject = SlackVariableObjectFactory.get().create(new SlackVariableObjectCreator(this, SlackType.Negative_Slack));
		negativeSlackVariable = VariableAssistant.get().getVariable(slackVariableObject);
		if(addToConstraint) minus(new LinearExpressionTerm(negativeSlackVariable));
		Objective.objectiveFunction.minus(new LinearExpressionTerm(negativeSlackCoefficient, negativeSlackVariable));
	}

	public double getSlack()
	{
		if(singleSlackVariable != null) return singleSlackVariable.getSolutionValue();
		double rtnValue = 0;
		if(positiveSlackVariable != null) rtnValue = positiveSlackVariable.getSolutionValue();
		if(negativeSlackVariable != null) rtnValue = rtnValue - negativeSlackVariable.getSolutionValue();
		return rtnValue;
	}
}
