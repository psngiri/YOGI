package yogi.optimize.expr.mps;

import yogi.optimize.expr.LinearExpressionTerm;

public class MpsColumn  extends MpsBase{
	private static MpsColumn mpsColumn = new MpsColumn();
	private String rowName;
	
	public static MpsColumn getMpsColumn() {
		return mpsColumn;
	}

	public MpsColumn initialize(LinearExpressionTerm term)
	{
		name = term.getVariable().getName();
		rowName = term.getExpression().getConstraint().getName();
		value = term.getCoefficient();
		return this;
	}
	
	@Override
	public boolean isValid() {
		return (value != 0f);
	}

	public String format() {
		return new StringBuilder(SEPARATOR).append(name).append(SEPARATOR).append(rowName).append(SEPARATOR).append(value).toString();
	}

}
