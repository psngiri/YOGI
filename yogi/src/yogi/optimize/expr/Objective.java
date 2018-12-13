package yogi.optimize.expr;

public class Objective extends BaseConstraint {
	public static final Objective objectiveFunction = new Objective();
	
	protected Objective() {
		super(new Expression());
	}

	@Override
	public String getName() {
		return "obj";
	}
	
	public double getObjectiveValue()
	{
		return getValue();
	}
}
