package yogi.optimize.expr;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.RelationshipObject;
import yogi.base.util.logging.Logging;

public class ConstraintAssistant extends RelationshipAssistant<RelationshipObject> {
	private static ConstraintAssistant itsInstance = new ConstraintAssistant();
	private static Logger logger = Logging.getLogger(ConstraintAssistant.class);
	
	public static ConstraintAssistant get() {
		return itsInstance;
	}
	
	public void setDualValue(Constraint constraint, double dualValue)
	{
		constraint.setValue(dualValue);
		if (logger.isLoggable(Level.FINE))
		{
			logger.fine("Variable.setSolutionValue: "+constraint.getName()+" : "+ dualValue);
		}
	}
	
	public void setObjectiveValue(double objectiveValue)
	{
		Objective.objectiveFunction.setValue(objectiveValue);
	}
}
