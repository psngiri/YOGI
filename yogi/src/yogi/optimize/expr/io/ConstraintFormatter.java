package yogi.optimize.expr.io;

import yogi.base.io.Formatter;
import yogi.optimize.expr.Constraint;
import yogi.optimize.expr.Expression;
import yogi.optimize.expr.LinearExpressionTerm;
import yogi.optimize.expr.Variable;

public class ConstraintFormatter implements Formatter<Constraint> {
	public String format(Constraint constraint) {
		StringBuilder sb = new StringBuilder();
		Expression expression = constraint.getExpression();
		for(LinearExpressionTerm term: expression.getLinearExpressionTerms())
		{
			float coefficient = term.getCoefficient();
			if(coefficient >= 0) sb.append('+');
			sb.append(coefficient);
			Variable myVariable = term.getVariable();
			sb.append(myVariable.getName());
			sb.append('(').append(myVariable.getSolutionValue()).append(')');
		}
		sb.append(" ");
		sb.append(constraint.getLowerBound()).append(" ");
		sb.append(constraint.getUpperBound());
		return sb.toString();
	}

}
