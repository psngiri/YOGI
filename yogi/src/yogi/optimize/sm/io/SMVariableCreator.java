package yogi.optimize.sm.io;

import yogi.optimize.expr.Variable;
import yogi.optimize.expr.VariableAssistant;
import yogi.optimize.sm.SmCreator;

public class SMVariableCreator extends SmCreator {
	public void setVariable(Variable variable) {
		super.setVariable(variable);
		VariableAssistant.get().setSolutionValue(variable, this.getSolutionValue());
	}
}
