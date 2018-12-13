package yogi.optimize.expr.slack;

import yogi.base.relationship.RelationshipObject;
import yogi.optimize.expr.Constraint;

public interface SlackVariableObject extends RelationshipObject {
	Constraint getConstraint();
	SlackType getSlackType();
}
