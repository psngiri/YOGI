package yogi.report.function.sum.config;


import yogi.report.function.Function;
import yogi.report.function.sum.ShortSumFunction;
import yogi.report.server.config.FunctionConfig;

public class ShortSumFunctionConfig extends FunctionConfig<Short>{
	private static final long serialVersionUID = 1L;
	public ShortSumFunctionConfig() {
		super("Sum");
	}
	@Override
	public Function<Short> getFunction() {
		return new ShortSumFunction();
	}

}
