package yogi.optimize.expr;

import yogi.base.Selector;
import yogi.base.relationship.RelationshipObject;

public class AssignedVariableObjectSelector implements Selector<RelationshipObject> {

	public boolean select(RelationshipObject item) {
		return VariableAssistant.get().getVariable(item).isAssigned();
	}

}
