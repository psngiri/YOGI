package yogi.report.function.sum.config;


import yogi.report.function.Function;
import yogi.report.function.sum.LongSumFunction;
import yogi.report.server.config.FunctionConfig;

public class LongSumFunctionConfig extends FunctionConfig<Long>{
	private static final long serialVersionUID = 1L;
	public LongSumFunctionConfig() {
		super("Sum");
	}
	@Override
	public Function<Long> getFunction() {
		return new LongSumFunction();
	}

}
