package yogi.report.function.min.config;


import yogi.base.util.FractionDouble;
import yogi.report.function.Function;
import yogi.report.function.min.MinFractionDoubleFunction;
import yogi.report.server.config.FunctionConfig;

public class MinFractionDoubleFunctionConfig extends FunctionConfig<FractionDouble>{
	private static final long serialVersionUID = 1L;
	public MinFractionDoubleFunctionConfig() {
		super("Min");
	}
	@Override
	public Function<FractionDouble> getFunction() {
		return new MinFractionDoubleFunction();
	}

}
