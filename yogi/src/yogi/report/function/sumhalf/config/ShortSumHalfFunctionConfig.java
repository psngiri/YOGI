package yogi.report.function.sumhalf.config;


import yogi.report.function.Function;
import yogi.report.function.sumhalf.ShortSumHalfFunction;
import yogi.report.server.config.FunctionConfig;

public class ShortSumHalfFunctionConfig extends FunctionConfig<Short>{
	private static final long serialVersionUID = 1L;
	public ShortSumHalfFunctionConfig() {
		super("Sum/2");
	}
	@Override
	public Function<Short> getFunction() {
		return new ShortSumHalfFunction();
	}

}
