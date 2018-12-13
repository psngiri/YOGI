package yogi.report.function.max.config;


import yogi.report.function.Function;
import yogi.report.function.max.MaxFloatFunction;
import yogi.report.server.config.FunctionConfig;

public class MaxFloatFunctionConfig extends FunctionConfig<Float>{
	private static final long serialVersionUID = 1L;
	public MaxFloatFunctionConfig() {
		super("Max");
	}
	@Override
	public Function<Float> getFunction() {
		return new MaxFloatFunction();
	}

}
