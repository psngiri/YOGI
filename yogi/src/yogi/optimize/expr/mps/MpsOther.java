package yogi.optimize.expr.mps;

public class MpsOther  extends MpsBase{
	private static MpsOther mpsOther = new MpsOther();
	
	public static MpsOther getMpsOther() {
		return mpsOther;
	}

	public MpsOther initialize(String name)
	{
		this.name = name;
		return this;
	}
	
	public String format() {
		return new StringBuilder(" ").append(name).toString();
	}
	
}
