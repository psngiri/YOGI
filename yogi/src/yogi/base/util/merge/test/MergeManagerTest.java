package yogi.base.util.merge.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.util.merge.Mergable;
import yogi.base.util.merge.MergeManager;

public class MergeManagerTest extends TestCase {

	public void test()
	{
		List<Item> items = new ArrayList<Item>();
		populate(items);
		System.out.println(items);
		MergeManager.merge(items, new Comparator<Item>()
				{

					public int compare(Item o1, Item o2) {
						return o1.getValue() - o2.getValue();
					}
			
				});
		System.out.println(items);
	}
	private void populate(List<Item> items) {
		items.add(new Item(4));
		items.add(new Item(3));
		items.add(new Item(3));
		items.add(new Item(2));
		items.add(new Item(4));
		items.add(new Item(3));
		items.add(new Item(4));
		items.add(new Item(2));
		items.add(new Item(1));
		items.add(new Item(1));
		items.add(new Item(5));
		items.add(new Item(2));
		items.add(new Item(3));
	}
	static class Item implements Mergable<Item>
	{
		private int value;
		
		public Item(int value) {
			super();
			this.value = value;
		}
		public int getValue()
		{
			return value;
		}
		public boolean isMergable(Item object) {
			return value == object.getValue();
		}

		public void merge(Item object) {
		}
		@Override
		public String toString() {
			return String.valueOf(value);
		}
		
	}

}
