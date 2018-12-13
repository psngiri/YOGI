package yogi.report.function.average.config;


import yogi.report.function.Function;
import yogi.report.function.average.FloatAverageFunction;
import yogi.report.server.config.FunctionConfig;

public class FloatAverageFunctionConfig extends FunctionConfig<Float>{
	private static final long serialVersionUID = 1L;
	public FloatAverageFunctionConfig() {
		super("Average");
	}
	@Override
	public Function<Float> getFunction() {
		return new FloatAverageFunction();
	}

}
