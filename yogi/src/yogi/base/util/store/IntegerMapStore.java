package yogi.base.util.store;

public class IntegerMapStore<T> extends ElementMapStore<Integer, T> {
	private int key = 0;
	public IntegerMapStore() {
		super();
	}

	@Override
	public Integer generateKey() {
		return key++;
	}

}
