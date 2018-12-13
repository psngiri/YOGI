package yogi.report.function.max.config;


import yogi.report.function.Function;
import yogi.report.function.max.MaxIntegerFunction;
import yogi.report.server.config.FunctionConfig;

public class MaxIntegerFunctionConfig extends FunctionConfig<Integer>{
	private static final long serialVersionUID = 1L;
	public MaxIntegerFunctionConfig() {
		super("Max");
	}
	@Override
	public Function<Integer> getFunction() {
		return new MaxIntegerFunction();
	}

}
