package yogi.optimize.expr.mps;

public class MpsObjective  extends MpsBase{
	private static MpsObjective mpsObjective = new MpsObjective();
	
	private String indicator;
	
	public MpsObjective initialize(String name)
	{
		this.name = name;
		return this;
	}
	
	public String format() {
		return new StringBuilder(" ").append(indicator).append(SEPARATOR).append(name).toString();
	}
	public static MpsObjective getMpsObjective() {
		return mpsObjective;
	}
	
}
