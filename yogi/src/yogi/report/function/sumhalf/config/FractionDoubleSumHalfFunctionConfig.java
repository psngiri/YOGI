package yogi.report.function.sumhalf.config;


import yogi.base.util.FractionDouble;
import yogi.report.function.Function;
import yogi.report.function.sumhalf.FractionDoubleSumHalfFunction;
import yogi.report.server.config.FunctionConfig;

public class FractionDoubleSumHalfFunctionConfig extends FunctionConfig<FractionDouble>{
	private static final long serialVersionUID = 1L;
	public FractionDoubleSumHalfFunctionConfig() {
		super("Sum/2");
	}
	@Override
	public Function<FractionDouble> getFunction() {
		return new FractionDoubleSumHalfFunction();
	}

}
