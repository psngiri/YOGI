package yogi.report.function.config;


import yogi.report.function.DummyFunction;
import yogi.report.function.Function;
import yogi.report.server.config.FunctionConfig;

public class DummyFunctionConfig<R> extends FunctionConfig<R>{
	
	private static final long serialVersionUID = 1L;
	
	public DummyFunctionConfig() {
		super("Sample");
	}
	@Override
	public Function<R> getFunction() {
		return new DummyFunction<R>();
	}

}
