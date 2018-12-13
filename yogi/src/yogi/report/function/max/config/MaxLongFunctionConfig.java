package yogi.report.function.max.config;


import yogi.report.function.Function;
import yogi.report.function.max.MaxLongFunction;
import yogi.report.server.config.FunctionConfig;

public class MaxLongFunctionConfig extends FunctionConfig<Long>{
	private static final long serialVersionUID = 1L;
	public MaxLongFunctionConfig() {
		super("Max");
	}
	@Override
	public Function<Long> getFunction() {
		return new MaxLongFunction();
	}

}
