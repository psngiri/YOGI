package yogi.report.function.min.config;


import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;
import yogi.report.function.min.MinPrecisionFloatFunction;
import yogi.report.server.config.FunctionConfig;

public class PrecisionFloatMinFunctionConfig extends FunctionConfig<PrecisionFloat>{
	private static final long serialVersionUID = 1L;
	public PrecisionFloatMinFunctionConfig() {
		super("Min");
	}
	@Override
	public Function<PrecisionFloat> getFunction() {
		return new MinPrecisionFloatFunction();
	}

}
