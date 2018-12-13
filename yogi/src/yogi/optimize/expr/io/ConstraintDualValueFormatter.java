package yogi.optimize.expr.io;

import yogi.base.io.Formatter;
import yogi.optimize.expr.Constraint;

public class ConstraintDualValueFormatter implements Formatter<Constraint> {
	public String format(Constraint constraint) {
		StringBuilder sb = new StringBuilder();
		sb.append(constraint.getName());
		sb.append(" Dual Value:");
		sb.append(constraint.getDualValue());
		return sb.toString();
	}

}
