package yogi.base.selectors;

import yogi.base.Selector;

public class AndSelector<T> extends  BaseAndSelector<T, Selector<? super T>>{
	public AndSelector(Selector<? super T> ... selectors) {
		super(selectors);
	}

	public AndSelector() {
		super();
	}
	
}
