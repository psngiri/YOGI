package yogi.report.function.max.config;


import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;
import yogi.report.function.max.MaxPrecisionFloatFunction;
import yogi.report.server.config.FunctionConfig;

public class PrecisionFloatMaxFunctionConfig extends FunctionConfig<PrecisionFloat>{
	private static final long serialVersionUID = 1L;
	public PrecisionFloatMaxFunctionConfig() {
		super("Max");
	}
	@Override
	public Function<PrecisionFloat> getFunction() {
		return new MaxPrecisionFloatFunction();
	}

}
