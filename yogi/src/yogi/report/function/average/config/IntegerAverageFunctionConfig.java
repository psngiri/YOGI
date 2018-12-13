package yogi.report.function.average.config;


import yogi.report.function.Function;
import yogi.report.function.average.IntegerAverageFunction;
import yogi.report.server.config.FunctionConfig;

public class IntegerAverageFunctionConfig extends FunctionConfig<Integer>{
	private static final long serialVersionUID = 1L;
	public IntegerAverageFunctionConfig() {
		super("Average");
	}
	@Override
	public Function<Integer> getFunction() {
		return new IntegerAverageFunction();
	}

}
