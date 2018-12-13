package yogi.base.util.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

public class DuplicatesTest extends TestCase {

	
	public void testFilter() {
		List<Integer> items = new ArrayList<Integer>();
		items.add(1);
		items.add(1);
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
		
		List<List<IndexItem<Integer>>> checkDuplicates = Duplicates.checkDuplicates(items, new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
			
		});
		assertEquals(3,checkDuplicates.size());
	}

}
