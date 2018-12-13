package yogi.report.function.sum.config;


import yogi.report.function.Function;
import yogi.report.function.sum.FloatSumFunction;
import yogi.report.server.config.FunctionConfig;

public class FloatSumFunctionConfig extends FunctionConfig<Float>{
	private static final long serialVersionUID = 1L;
	public FloatSumFunctionConfig() {
		super("Sum");
	}
	@Override
	public Function<Float> getFunction() {
		return new FloatSumFunction();
	}

}
