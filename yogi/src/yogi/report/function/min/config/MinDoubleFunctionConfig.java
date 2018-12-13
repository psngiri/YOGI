package yogi.report.function.min.config;


import yogi.report.function.Function;
import yogi.report.function.min.MinDoubleFunction;
import yogi.report.server.config.FunctionConfig;

public class MinDoubleFunctionConfig extends FunctionConfig<Double>{
	private static final long serialVersionUID = 1L;
	public MinDoubleFunctionConfig() {
		super("Min");
	}
	@Override
	public Function<Double> getFunction() {
		return new MinDoubleFunction();
	}

}
