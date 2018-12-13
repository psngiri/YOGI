package yogi.report.function.sumhalf.config;


import yogi.report.function.Function;
import yogi.report.function.sumhalf.LongSumHalfFunction;
import yogi.report.server.config.FunctionConfig;

public class LongSumHalfFunctionConfig extends FunctionConfig<Long>{
	private static final long serialVersionUID = 1L;
	public LongSumHalfFunctionConfig() {
		super("Sum/2");
	}
	@Override
	public Function<Long> getFunction() {
		return new LongSumHalfFunction();
	}

}
