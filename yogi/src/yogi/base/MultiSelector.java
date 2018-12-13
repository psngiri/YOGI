package yogi.base;

import java.util.ArrayList;
import java.util.List;

public class MultiSelector<T> implements Selector<T> {
	private List<Selector<? super T>> selectors = new ArrayList<Selector<? super T>>();

	public MultiSelector() {
		super();
	}

	public MultiSelector(List<Selector<? super T>> selectors) {
		super();
		this.selectors = selectors;
	}
	
	public MultiSelector(Selector<? super T>... selectors)
	{
		for(Selector<? super T> selector: selectors)
		{
			this.selectors.add(selector);
		}
	}

	public void addSelector(Selector<? super T> selector)
	{
		selectors.add(selector);
	}
	
	public boolean select(T item) {
		for(Selector<? super T> selector: selectors)
		{
			if(!selector.select(item)) return false;
		}
		return true;
	}


}
