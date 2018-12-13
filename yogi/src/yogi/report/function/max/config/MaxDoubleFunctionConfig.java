package yogi.report.function.max.config;


import yogi.report.function.Function;
import yogi.report.function.max.MaxDoubleFunction;
import yogi.report.server.config.FunctionConfig;

public class MaxDoubleFunctionConfig extends FunctionConfig<Double>{
	private static final long serialVersionUID = 1L;
	public MaxDoubleFunctionConfig() {
		super("Max");
	}
	@Override
	public Function<Double> getFunction() {
		return new MaxDoubleFunction();
	}

}
