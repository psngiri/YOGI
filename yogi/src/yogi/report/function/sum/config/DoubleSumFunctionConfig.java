package yogi.report.function.sum.config;


import yogi.report.function.Function;
import yogi.report.function.sum.DoubleSumFunction;
import yogi.report.server.config.FunctionConfig;

public class DoubleSumFunctionConfig extends FunctionConfig<Double>{
	private static final long serialVersionUID = 1L;
	public DoubleSumFunctionConfig() {
		super("Sum");
	}
	@Override
	public Function<Double> getFunction() {
		return new DoubleSumFunction();
	}

}
