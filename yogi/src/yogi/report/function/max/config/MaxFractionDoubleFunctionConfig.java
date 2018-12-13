package yogi.report.function.max.config;


import yogi.base.util.FractionDouble;
import yogi.report.function.Function;
import yogi.report.function.max.MaxFractionDoubleFunction;
import yogi.report.server.config.FunctionConfig;

public class MaxFractionDoubleFunctionConfig extends FunctionConfig<FractionDouble>{
	private static final long serialVersionUID = 1L;
	public MaxFractionDoubleFunctionConfig() {
		super("Max");
	}
	@Override
	public Function<FractionDouble> getFunction() {
		return new MaxFractionDoubleFunction();
	}

}