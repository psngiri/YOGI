package yogi.base.selectors;

import yogi.base.Selector;

public class OrSelector<T>extends  BaseOrSelector<T, Selector<? super T>>{
	public OrSelector(Selector<? super T> ... selectors) {
		super(selectors);
	}

	public OrSelector() {
		super();
	}
}
