package yogi.report.function.sumhalf.config;


import yogi.report.function.Function;
import yogi.report.function.sumhalf.FloatSumHalfFunction;
import yogi.report.server.config.FunctionConfig;

public class FloatSumHalfFunctionConfig extends FunctionConfig<Float>{
	private static final long serialVersionUID = 1L;
	public FloatSumHalfFunctionConfig() {
		super("Sum/2");
	}
	@Override
	public Function<Float> getFunction() {
		return new FloatSumHalfFunction();
	}

}
