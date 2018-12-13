package yogi.report.function.sum.config;


import yogi.report.function.Function;
import yogi.report.function.sum.IntegerSumFunction;
import yogi.report.server.config.FunctionConfig;

public class IntegerSumFunctionConfig extends FunctionConfig<Integer>{
	private static final long serialVersionUID = 1L;
	public IntegerSumFunctionConfig() {
		super("Sum");
	}
	@Override
	public Function<Integer> getFunction() {
		return new IntegerSumFunction();
	}

}
