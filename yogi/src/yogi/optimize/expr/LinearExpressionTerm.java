package yogi.optimize.expr;

import yogi.base.util.merge.Mergable;

public class LinearExpressionTerm implements Mergable<LinearExpressionTerm> {
   private float coefficient;
   private Variable variable;
   private Expression expression;
   
	public LinearExpressionTerm() {
		super();
	}   
	
	public LinearExpressionTerm(float coefficient, Variable variable) {
		super();
		this.coefficient = coefficient;
		this.variable = variable;
		variable.add(this);
	}
	public LinearExpressionTerm(Variable variable) {
		this(1, variable);
	}
	public float getCoefficient() {
		return coefficient;
	}
	public Variable getVariable() {
		return variable;
	}
	
	public LinearExpressionTerm multiply(float f) {
		coefficient *= f;
		return this;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	void delete()
	{
		variable.remove(this);
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(coefficient).append(variable).toString();
	}
	
	public boolean isMergable(LinearExpressionTerm term) {
		if(term == null) return false;
		return getVariable().equals(term.getVariable());
	}

	public void merge(LinearExpressionTerm term) {
		coefficient += term.getCoefficient();
		term.delete();
	}

}
