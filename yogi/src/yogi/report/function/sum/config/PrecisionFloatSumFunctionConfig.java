package yogi.report.function.sum.config;


import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;
import yogi.report.function.sum.PrecisionFloatSumFunction;
import yogi.report.server.config.FunctionConfig;

public class PrecisionFloatSumFunctionConfig extends FunctionConfig<PrecisionFloat>{
	private static final long serialVersionUID = 1L;
	public PrecisionFloatSumFunctionConfig() {
		super("Sum");
	}
	@Override
	public Function<PrecisionFloat> getFunction() {
		return new PrecisionFloatSumFunction();
	}

}
