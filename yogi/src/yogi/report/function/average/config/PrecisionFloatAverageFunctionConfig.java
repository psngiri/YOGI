package yogi.report.function.average.config;


import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;
import yogi.report.function.average.PrecisionFloatAverageFunction;
import yogi.report.server.config.FunctionConfig;

public class PrecisionFloatAverageFunctionConfig extends FunctionConfig<PrecisionFloat>{
	private static final long serialVersionUID = 1L;
	public PrecisionFloatAverageFunctionConfig() {
		super("Average");
	}
	@Override
	public Function<PrecisionFloat> getFunction() {
		return new PrecisionFloatAverageFunction();
	}

}
