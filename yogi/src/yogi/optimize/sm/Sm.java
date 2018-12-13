package yogi.optimize.sm;

import yogi.base.relationship.RelationshipObject;
import yogi.optimize.expr.Variable;

public interface Sm extends RelationshipObject {
	String getVariableName();
	float getSolutionValue();
	Variable getVariable();
}
