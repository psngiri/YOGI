package yogi.base.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Filter;
import yogi.base.Selector;
import yogi.base.selectors.AndSelector;
import yogi.base.selectors.OrSelector;

public class FilterTest extends TestCase {

	private final class SingleDigitSelector implements Selector<Integer> {
		public boolean select(Integer item) {
			return item < 10;
		}
	}
	private final class EvenNumberSelector implements Selector<Integer> {
		public boolean select(Integer item) {
			return item % 2 == 0;
		}
	}

	/*
	 * Test method for 'yogi.base.Filter.filter(List<T>, Selector<? super T>) <T>'
	 */
	public void testFilter() {
		List<Integer> items = new ArrayList<Integer>();
		items.add(1);
		items.add(2);
		items.add(10);
		List<Integer> filteredItems = Filter.filter(items, new SingleDigitSelector());
		assertEquals(2, filteredItems.size());
		assertEquals(1, (int)filteredItems.get(0));
		assertEquals(2, (int)filteredItems.get(1));
	}

	public void testFilterRemoveSelector() {
		List<Integer> items = new ArrayList<Integer>();
		items.add(1);
		items.add(2);
		items.add(10);
		List<Integer> filteredItems = Filter.filterRemoveSelected(items, new SingleDigitSelector());
		assertEquals(2, filteredItems.size());
		assertEquals(1, (int)filteredItems.get(0));
		assertEquals(2, (int)filteredItems.get(1));
		assertEquals(1, items.size());
		assertEquals(10, (int)items.get(0));
	}
	public void testFilterMultiSelector() {
		List<Integer> items = new ArrayList<Integer>();
		items.add(1);
		items.add(2);
		items.add(10);
		List<Integer> filteredItems = Filter.filter(items, new AndSelector<Integer>(new SingleDigitSelector(), new EvenNumberSelector()));
		assertEquals(1, filteredItems.size());
		assertEquals(2, (int)filteredItems.get(0));
	}
	
	public void testFilterAndSelector() {
		List<Integer> items = new ArrayList<Integer>();
		items.add(1);
		items.add(2);
		items.add(10);
		List<Integer> filteredItems = Filter.filter(items, new AndSelector<Integer>(new SingleDigitSelector(), new EvenNumberSelector()));
		assertEquals(1, filteredItems.size());
		assertEquals(2, (int)filteredItems.get(0));
	}
	
	public void testFilterORSelector() {
		List<Integer> items = new ArrayList<Integer>();
		items.add(1);
		items.add(2);
		items.add(10);
		List<Integer> filteredItems = Filter.filter(items, new OrSelector<Integer>(new SingleDigitSelector(), new EvenNumberSelector()));
		assertEquals(3, filteredItems.size());
		assertEquals(1, (int)filteredItems.get(0));
		assertEquals(2, (int)filteredItems.get(1));
		assertEquals(10, (int)filteredItems.get(2));
	}
}
