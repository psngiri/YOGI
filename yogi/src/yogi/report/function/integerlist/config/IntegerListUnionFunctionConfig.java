package yogi.report.function.integerlist.config;


import java.util.List;

import yogi.report.function.Function;
import yogi.report.function.integerlist.IntegerListUnionFunction;
import yogi.report.server.config.FunctionConfig;

public class IntegerListUnionFunctionConfig extends FunctionConfig<List<Integer>>{
	private static final long serialVersionUID = 1L;
	public IntegerListUnionFunctionConfig() {
		super("Union");
	}
	@Override
	public Function<List<Integer>> getFunction() {
		return new IntegerListUnionFunction();
	}

}
