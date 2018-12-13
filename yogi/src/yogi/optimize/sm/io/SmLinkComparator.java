package yogi.optimize.sm.io;

import yogi.base.io.link.LinkComparator;
import yogi.optimize.expr.Variable;

public class SmLinkComparator implements LinkComparator<SMVariableCreator, Variable> {

	public int compareFrom(SMVariableCreator from1, SMVariableCreator from2) {
		return from1.getVariableName().compareTo(from2.getVariableName());
	}

	public int compareTo(Variable to1, Variable to2) {
		return to1.getName().compareTo(to2.getName());
	}

	public int compareFromTo(SMVariableCreator from, Variable to) {
		return from.getVariableName().compareTo(to.getName());
	}

}
