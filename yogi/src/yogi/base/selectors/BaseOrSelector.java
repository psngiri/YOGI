package yogi.base.selectors;

import java.util.ArrayList;
import java.util.List;

import yogi.base.Selector;

public class BaseOrSelector<T, S extends Selector<? super T>> implements Selector<T>{
	List<S> selectors;
	
	public BaseOrSelector(S ... selectors) {
		this();
		for(S selector: selectors)
		{
			addSelector(selector);
		}
	}

	public BaseOrSelector() {
		super();
		this.selectors = new ArrayList<S>();
	}
	
	public void addSelector(S selector)
	{
		selectors.add(selector);
	}
	
	public boolean select(T item) {
		for(Selector<? super T> selector: selectors)
		{
			if(selector == null) continue;
			if(selector.select(item)) return true;
		}
		return false;
	}

}
