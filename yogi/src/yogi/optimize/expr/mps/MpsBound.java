package yogi.optimize.expr.mps;

import yogi.optimize.expr.Variable;

public class MpsBound extends MpsBase{
	private static MpsBound mpsBound = new MpsBound();
	private String variableName;
	protected float lowerValue;
	protected float upperValue;
	
	public static MpsBound getMpsBound() {
		return mpsBound;
	}

	public MpsBound initialize(Variable variable)
	{
		name = "BOUND";
		lowerValue = 0.0f;
		upperValue = Float.MAX_VALUE;
		variableName = variable.getName();
		setBounds(variable);
		return this;
	}

	private void setBounds(Variable variable) {
		if(isLowerBound(variable)){
			lowerValue = variable.getLowerBound();
		}
		if (isUpperBound(variable))
		{
			upperValue = variable.getUpperBound();
		}
	}
	
	private boolean isLowerBound(Variable variable)
	{
		if(variable.getLowerBound() == 0f || variable.getLowerBound() == Float.MIN_VALUE) return false;
		return true;
	}
	
	private boolean isUpperBound(Variable variable)
	{
		if (variable.getUpperBound() == Float.MAX_VALUE) return false;
		return true;
	}
	
	public String format() {
		StringBuilder sb = new StringBuilder();
		if(lowerValue != 0.0f){
			sb.append(" LO").append(SEPARATOR).append(name).append(SEPARATOR).append(variableName).append(SEPARATOR).append(lowerValue).toString();
		}
		if(upperValue != Float.MAX_VALUE){
			if(sb.length() > 0) sb.append("\n");
			sb.append(" UP").append(SEPARATOR).append(name).append(SEPARATOR).append(variableName).append(SEPARATOR).append(upperValue).toString();
		}
		return sb.toString();
	}

	@Override
	public boolean isValid() {
		return (lowerValue != 0 || upperValue != Float.MAX_VALUE);
	}

}
