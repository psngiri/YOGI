package yogi.optimize.expr.mps;

import yogi.optimize.expr.Constraint;

public class MpsRow  extends MpsBase{
	private static MpsRow mpsRow = new MpsRow();
	private String indicator;

	public static MpsRow getMpsRow() {
		return mpsRow;
	}

	public MpsRow initialize(Constraint constraint)
	{
		name = constraint.getName();
		indicator = constraint.getEqualityType().getCode();
		return this;
	}

	
	public String format() {
		return new StringBuilder(" ").append(indicator).append(SEPARATOR).append(name).toString();
	}

}
