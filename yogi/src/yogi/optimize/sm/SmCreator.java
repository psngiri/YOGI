package yogi.optimize.sm;

import yogi.base.Creator;
import yogi.optimize.expr.Variable;

public class SmCreator implements Creator<Sm> {
	private String variableName;
	private float solutionValue;
	private Variable variable;

	public Sm create() {
		return new SmImpl(variableName, solutionValue, variable);
	}

	public Variable getVariable() {
		return variable;
	}

	public float getSolutionValue() {
		return solutionValue;
	}

	public void setSolutionValue(float solutionValue) {
		this.solutionValue = solutionValue;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	@Override
	public String toString() {
		return variableName;
	}
}
