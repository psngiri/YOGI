package yogi.report.server.config;

import java.io.Serializable;

import yogi.report.function.Function;

public abstract class FunctionConfig<C> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	
	public FunctionConfig(String functionName) {
		super();
		this.name = functionName;
	}

	public String getName() {
		return name;
	}

	public abstract Function<C> getFunction();

	@Override
	public String toString() {
		return name;
	}
	
	
	
}