package yogi.report.function.sumhalf.config;


import yogi.report.function.Function;
import yogi.report.function.sumhalf.IntegerSumHalfFunction;
import yogi.report.server.config.FunctionConfig;

public class IntegerSumHalfFunctionConfig extends FunctionConfig<Integer>{
	private static final long serialVersionUID = 1L;
	public IntegerSumHalfFunctionConfig() {
		super("Sum/2");
	}
	@Override
	public Function<Integer> getFunction() {
		return new IntegerSumHalfFunction();
	}

}
