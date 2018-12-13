package yogi.optimize.expr.mps;

import yogi.optimize.expr.Constraint;

public class MpsRhs  extends MpsBase{
	private static MpsRhs mpsRhs = new MpsRhs();
	private String rowName;
	private float value;
	private boolean valid;
	
	public static MpsRhs getMpsRhs() {
		return mpsRhs;
	}

	public MpsRhs initialize(Constraint constraint)
	{
		name = "VALRHS";
		rowName = constraint.getName();
		value = getValue(constraint);
		
		valid = isValid(constraint);

		return this;
	}

	private boolean isValid(Constraint constraint) {
		float lowerBound = (constraint.getLowerBound() == Float.MIN_VALUE)? Float.MIN_VALUE : constraint.getLowerBound() - constraint.getExpression().getConstant();
		float upperBound = (constraint.getUpperBound() == Float.MAX_VALUE)? Float.MAX_VALUE : constraint.getUpperBound() - constraint.getExpression().getConstant();
		return (lowerBound > Float.MIN_VALUE && lowerBound != 0f) || (upperBound < Float.MAX_VALUE && upperBound != 0f);
	}

	@Override
	public boolean isValid() {
		return valid;
	}

	private float getValue(Constraint constraint) {
		float bound = (constraint.getUpperBound() == Float.MAX_VALUE) ? Float.MAX_VALUE 
				: constraint.getUpperBound()
						- constraint.getExpression().getConstant();
		if (bound < Float.MAX_VALUE) return bound;
		bound = (constraint.getLowerBound() == Float.MIN_VALUE) ? Float.MIN_VALUE
				: constraint.getLowerBound()
						- constraint.getExpression().getConstant();
		return bound;
	}
	
	public String format() {
		return new StringBuilder(" ").append(name).append(SEPARATOR).append(rowName).append(SEPARATOR).append(value).toString();
	}

}
