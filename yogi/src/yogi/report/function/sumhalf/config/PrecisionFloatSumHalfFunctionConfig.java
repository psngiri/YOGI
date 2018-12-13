package yogi.report.function.sumhalf.config;


import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;
import yogi.report.function.sumhalf.PrecisionFloatSumHalfFunction;
import yogi.report.server.config.FunctionConfig;

public class PrecisionFloatSumHalfFunctionConfig extends FunctionConfig<PrecisionFloat>{
	private static final long serialVersionUID = 1L;
	public PrecisionFloatSumHalfFunctionConfig() {
		super("Sum/2");
	}
	@Override
	public Function<PrecisionFloat> getFunction() {
		return new PrecisionFloatSumHalfFunction();
	}

}
