package yogi.optimize.expr;

import yogi.base.relationship.RelationshipObject;

public interface VariableCreator<T extends RelationshipObject>{
	Variable create(T variableObject);
}
