package yogi.report.function.min.config;


import yogi.report.function.Function;
import yogi.report.function.min.MinLongFunction;
import yogi.report.server.config.FunctionConfig;

public class MinLongFunctionConfig extends FunctionConfig<Long>{
	private static final long serialVersionUID = 1L;
	public MinLongFunctionConfig() {
		super("Min");
	}
	@Override
	public Function<Long> getFunction() {
		return new MinLongFunction();
	}

}
