package yogi.report.server.config;

import java.io.Serializable;

import yogi.report.compare.function.CompareFunction;

public abstract class CompareFunctionConfig<C> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	
	public CompareFunctionConfig(String functionName) {
		super();
		this.name = functionName;
	}

	public String getName() {
		return name;
	}

	public abstract CompareFunction<C> getCompareFunction(int dataSet1, int dataSet2);

	@Override
	public String toString() {
		return name;
	}
	
}