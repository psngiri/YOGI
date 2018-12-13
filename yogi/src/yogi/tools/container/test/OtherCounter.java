package yogi.tools.container.test;

public class OtherCounter {
	static int count = 0;
	String name;
	public OtherCounter(String name) {
		super();
		this.name = name;
		count++;
		System.out.println("Instantiated Other Counter:" + name + " Count " + count);
	}
	
	public String getName() {
		return name;
	}
}
