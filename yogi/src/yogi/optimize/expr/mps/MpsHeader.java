package yogi.optimize.expr.mps;

public class MpsHeader  extends MpsBase{
	private static MpsHeader mpsHeader = new MpsHeader();
	
	public static MpsHeader getMpsHeader() {
		return mpsHeader;
	}

	public MpsHeader initialize(String name)
	{
		this.name = name;
		return this;
	}
	
	public String format() {
		return name;
	}
	
}
