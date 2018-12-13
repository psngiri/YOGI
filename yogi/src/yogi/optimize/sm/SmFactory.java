package yogi.optimize.sm;

import yogi.base.Factory;

public class SmFactory extends Factory<Sm> {
	private static SmFactory itsInstance = new SmFactory(SmManager.get());

	protected SmFactory(SmManager manager) {
		super(manager);
	}

	public static SmFactory get() {
		return itsInstance;
	}
}
