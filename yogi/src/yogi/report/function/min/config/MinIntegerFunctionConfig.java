package yogi.report.function.min.config;


import yogi.report.function.Function;
import yogi.report.function.min.MinIntegerFunction;
import yogi.report.server.config.FunctionConfig;

public class MinIntegerFunctionConfig extends FunctionConfig<Integer>{
	private static final long serialVersionUID = 1L;
	public MinIntegerFunctionConfig() {
		super("Min");
	}
	@Override
	public Function<Integer> getFunction() {
		return new MinIntegerFunction();
	}

}
