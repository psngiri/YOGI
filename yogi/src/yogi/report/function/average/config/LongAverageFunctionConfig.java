package yogi.report.function.average.config;


import yogi.report.function.Function;
import yogi.report.function.average.LongAverageFunction;
import yogi.report.server.config.FunctionConfig;

public class LongAverageFunctionConfig extends FunctionConfig<Long>{
	private static final long serialVersionUID = 1L;
	public LongAverageFunctionConfig() {
		super("Avg");
	}
	@Override
	public Function<Long> getFunction() {
		return new LongAverageFunction();
	}

}
