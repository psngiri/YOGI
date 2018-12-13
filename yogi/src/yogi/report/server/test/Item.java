package yogi.report.server.test;


public class Item {
	String key1;
	String key2;
	String key3;
	int value1;
	float value2;
	
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(String key1, String key2, String key3, int value1, float value2) {
		super();
		this.key1 = key1;
		this.key2 = key2;
		this.key3 = key3;
		this.value1 = value1;
		this.value2 = value2;
	}
	public String getKey1() {
		return key1;
	}
	public String getKey2() {
		return key2;
	}
	public String getKey3() {
		return key3;
	}
	public int getValue1() {
		return value1;
	}
	public float getValue2() {
		return value2;
	}
	
}
