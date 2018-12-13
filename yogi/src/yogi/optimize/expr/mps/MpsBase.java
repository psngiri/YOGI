package yogi.optimize.expr.mps;

public abstract class MpsBase implements MpsObject {
	protected String name;
	protected float value;

	public boolean isValid() {
		return true;
	}
}
