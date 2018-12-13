package yogi.base.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Unique;

public class UniqueTest extends TestCase {

	/*
	 * Test method for 'yogi.base.Filter.filter(List<T>, Selector<? super T>) <T>'
	 */
	public void testFilter() {
		List<Integer> items = new ArrayList<Integer>();
		items.add(1);
		items.add(2);
		items.add(2);
		items.add(2);
		items.add(2);
		items.add(10);
		items.add(10);
		items.add(10);
		items.add(10);
		items.add(10);
		items.add(10);
		items.add(1);
		items.add(1);
		items.add(1);
		items.add(1);
		items.add(1);
		items.add(2);
		items.add(2);
		items.add(2);
		items.add(2);
		items.add(10);
		items.add(10);
		items.add(10);
		items.add(10);
		List<Integer> uniqueItems = Unique.unique(items, new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
			
		});
		assertEquals(3, uniqueItems.size());
		assertEquals(1, (int)uniqueItems.get(0));
		assertEquals(2, (int)uniqueItems.get(1));
		assertEquals(10, (int)uniqueItems.get(2));
	}

}
