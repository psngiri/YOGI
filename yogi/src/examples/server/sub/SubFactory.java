package examples.server.sub;

import yogi.base.Factory;

public class SubFactory extends Factory<Sub> {
	private static SubFactory itsInstance = new SubFactory(SubManager.get());

	protected SubFactory(SubManager manager) {
		super(manager);
	}

	public static SubFactory get() {
		return itsInstance;
	}
}
