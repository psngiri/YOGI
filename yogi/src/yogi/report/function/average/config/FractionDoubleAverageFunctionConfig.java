package yogi.report.function.average.config;


import yogi.base.util.FractionDouble;
import yogi.report.function.Function;
import yogi.report.function.average.FractionDoubleAverageFunction;
import yogi.report.server.config.FunctionConfig;

public class FractionDoubleAverageFunctionConfig extends FunctionConfig<FractionDouble>{
	private static final long serialVersionUID = 1L;
	public FractionDoubleAverageFunctionConfig() {
		super("Avg");
	}
	@Override
	public Function<FractionDouble> getFunction() {
		return new FractionDoubleAverageFunction();
	}

}
