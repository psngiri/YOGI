package yogi.optimize.expr.mps;

import yogi.optimize.expr.Constraint;

public class MpsRange  extends MpsBase{
	private static MpsRange mpsRange = new MpsRange();
	private String rowName;
	private float value;
	private boolean valid;
	
	public static MpsRange getMpsRange() {
		return mpsRange;
	}

	public MpsRange initialize(Constraint constraint)
	{
		name = "FRANGE";
		rowName = constraint.getName();
		value = (constraint.getLowerBound() == Float.MIN_VALUE)? Float.MIN_VALUE : constraint.getUpperBound() - constraint.getLowerBound();
		valid = (constraint.getLowerBound() > Float.MIN_VALUE && constraint.getUpperBound() < Float.MAX_VALUE && constraint.getLowerBound() != constraint.getUpperBound());
		return this;
	}
	
	@Override
	public boolean isValid() {
		return valid;
	}

	public String format() {
		return new StringBuilder(" ").append(name).append(SEPARATOR).append(rowName).append(SEPARATOR).append(value).toString();
	}

}
