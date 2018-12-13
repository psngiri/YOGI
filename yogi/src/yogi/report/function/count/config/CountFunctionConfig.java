package yogi.report.function.count.config;


import yogi.report.function.Function;
import yogi.report.function.count.CountFunction;
import yogi.report.server.config.FunctionConfig;

public class CountFunctionConfig extends FunctionConfig<Integer>{
	private static final long serialVersionUID = 1L;
	public CountFunctionConfig() {
		super("Count");
	}
	
	@Override
	public Function<Integer> getFunction() {
		return new CountFunction();
	}

}
