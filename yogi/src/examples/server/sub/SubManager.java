package examples.server.sub;

import yogi.base.ObjectNotFoundException;
import yogi.base.relationship.index.IndexedManager;

public class SubManager extends IndexedManager<Sub, Integer> {
	private static SubManager itsInstance = new SubManager();

	protected SubManager() {
		super();
	}

	public static SubManager get() {
		return itsInstance;
	}

	public Sub getSub(int subNumber)
	{
		try {
			return this.getObject(subNumber);
		} catch (ObjectNotFoundException e) {
			throw new RuntimeException("Unable to find Subs with sub number " + subNumber, e);
		}
	}
}
