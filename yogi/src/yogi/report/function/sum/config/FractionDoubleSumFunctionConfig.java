package yogi.report.function.sum.config;


import yogi.base.util.FractionDouble;
import yogi.report.function.Function;
import yogi.report.function.sum.FractionDoubleSumFunction;
import yogi.report.server.config.FunctionConfig;

public class FractionDoubleSumFunctionConfig extends FunctionConfig<FractionDouble>{
	private static final long serialVersionUID = 1L;
	public FractionDoubleSumFunctionConfig() {
		super("Sum");
	}
	@Override
	public Function<FractionDouble> getFunction() {
		return new FractionDoubleSumFunction();
	}

}
