package yogi.report.function.sumhalf.config;


import yogi.report.function.Function;
import yogi.report.function.sumhalf.DoubleSumHalfFunction;
import yogi.report.server.config.FunctionConfig;

public class DoubleSumHalfFunctionConfig extends FunctionConfig<Double>{
	private static final long serialVersionUID = 1L;
	public DoubleSumHalfFunctionConfig() {
		super("Sum/2");
	}
	@Override
	public Function<Double> getFunction() {
		return new DoubleSumHalfFunction();
	}

}
