package yogi.tools.container.test;

public class Counter implements CounterInt{
	static int count = 0;
	String name;
	public Counter(String[] args) {
		if(args.length > 0) name = args[0];
		else name = "default";
	}
	
	public Counter(String name) {
		super();
		this.name = name;
		count++;
		System.out.println("Instantiated :" + name + " Count " + count);
		new OtherCounter(name);
	}
	
	public String getName() {
		return name;
	}
	
	public int incrementCount()
	{
		return count++;
	}
	
	public static void main(String[] args)
	{
		Counter counter = new Counter(args[0]);
		System.out.println(" Name: " + counter.getName() + " Count " + Counter.count);
	}

}
