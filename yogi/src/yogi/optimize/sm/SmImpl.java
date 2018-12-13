package yogi.optimize.sm;

import yogi.base.relationship.RelationshipObjectImpl;
import yogi.optimize.expr.Variable;


public class SmImpl extends RelationshipObjectImpl<Sm> implements Sm {
	private String variableName;
	private float solutionValue;
	private Variable variable;

	protected SmImpl(String variableName, float solutionValue, Variable variable) {
		super();
		this.variableName = variableName;
		this.solutionValue = solutionValue;
		this.variable = variable;
	}

	public String getName() {
		return getVariableName();
	}

	public float getSolutionValue() {
		return solutionValue;
	}

	public Variable getVariable() {
		return variable;
	}

	public String getVariableName() {
		return variableName;
	}

}
