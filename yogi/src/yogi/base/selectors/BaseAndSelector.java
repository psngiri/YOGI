package yogi.base.selectors;

import java.util.ArrayList;
import java.util.List;

import yogi.base.Selector;

public class BaseAndSelector<T, S extends Selector<? super T>> implements Selector<T>{
	List<S> selectors;
	
	public BaseAndSelector(S ... selectors) {
		this();
		for(S selector: selectors)
		{
			addSelector(selector);
		}
	}

	public BaseAndSelector() {
		super();
		this.selectors = new ArrayList<S>();
	}
	
	public void addSelector(S selector)
	{
		selectors.add(selector);
	}
	
	public boolean select(T item) {
		for(S selector: selectors)
		{
			if(selector == null) continue;
			if(!selector.select(item)) return false;
		}
		return true;
	}

	public List<S> getSelectors() {
		return selectors;
	}
	
	

}
