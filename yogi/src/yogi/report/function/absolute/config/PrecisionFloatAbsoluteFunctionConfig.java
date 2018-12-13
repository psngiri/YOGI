package yogi.report.function.absolute.config;

import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;
import yogi.report.function.absolute.AbsolutePrecisionFloatFunction;
import yogi.report.server.config.FunctionConfig;

public class PrecisionFloatAbsoluteFunctionConfig extends FunctionConfig<PrecisionFloat>{
	private static final long serialVersionUID = 1L;
	public PrecisionFloatAbsoluteFunctionConfig() {
		super("Absolute");
	}
	@Override
	public Function<PrecisionFloat> getFunction() {
		return new AbsolutePrecisionFloatFunction();
	}

}
