package yogi.paging.test;

import java.util.ArrayList;
import java.util.List;

public class ItemSet{
	
	private int size;
	List<Item> data = new ArrayList<Item>();
		
	public ItemSet() {
		super();
	}

	public ItemSet(int size) {
		this.size = size;
		data = new ArrayList<Item>(size);
	}

	public void setUp() {
		for(int i = 0; i < size; i++) {
			if(i < 5) {
				//data.add(new Item("Switch", "Yellow", i, 1226.00, "High", true));
				data.add(new Item("Switch", "Yellow", i + "", 1226.00 + "", "High", "true"));
			} else {
				data.add(new Item("Switch", "Yellow", i, 1220.00, "High", false));
			}
		}
	}
	
	public List<Item> getData() {
		setUp();
		return data;
	}

	public int getSize() {
		return size;
	}	

	public List<Item> getDataForSort(int size){
		this.size = size;
		data = new ArrayList<Item>(size);
		data.add(new Item("Switch", "Yellow", 1, 300.00, "High", true));
		data.add(new Item("Router", "Violet", 2, 1200.00, "Low", false));
		data.add(new Item("Keyboard", "Yellow", 20, 600.00, "High", true));
		data.add(new Item("Switch", "Yellow", 15, 300.00, "Medium", false));
		data.add(new Item("CPU", "Green", 10, 1000.00, "High", true));
		data.add(new Item("Switch", "Yellow", 9, 300.00, "High", true));
		data.add(new Item("Laptop", "Yellow", 5, 1226.00, "Low", true));
		data.add(new Item("Switch", "Orange", 18, 300.00, "Low", false));
		data.add(new Item("Mouse", "Green", 9, 1226.00, "Medium", true));
		data.add(new Item("Switch", "Yellow", 2, 300.00, "High", false));
		data.add(new Item("Harddisk", "Orange", 4, 500.00, "High", true));
		data.add(new Item("RAM", "Yellow", 3, 800.00, "Medium", false));
		data.add(new Item("RAM", "Black", 7, 800.00, "High", false));
		return data;
	}
}
