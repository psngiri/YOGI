package yogi.report.function.min.config;


import yogi.report.function.Function;
import yogi.report.function.min.MinFloatFunction;
import yogi.report.server.config.FunctionConfig;

public class MinFloatFunctionConfig extends FunctionConfig<Float>{
	private static final long serialVersionUID = 1L;
	public MinFloatFunctionConfig() {
		super("Min");
	}
	@Override
	public Function<Float> getFunction() {
		return new MinFloatFunction();
	}

}
