package yogi.report.function.average.config;


import yogi.report.function.Function;
import yogi.report.function.average.DoubleAverageFunction;
import yogi.report.server.config.FunctionConfig;

public class DoubleAverageFunctionConfig extends FunctionConfig<Double>{
	private static final long serialVersionUID = 1L;
	public DoubleAverageFunctionConfig() {
		super("Avg");
	}
	@Override
	public Function<Double> getFunction() {
		return new DoubleAverageFunction();
	}

}
