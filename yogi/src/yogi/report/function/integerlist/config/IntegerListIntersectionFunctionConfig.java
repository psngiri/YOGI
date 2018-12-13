package yogi.report.function.integerlist.config;


import java.util.List;

import yogi.report.function.Function;
import yogi.report.function.integerlist.IntegerListIntersectionFunction;
import yogi.report.server.config.FunctionConfig;

public class IntegerListIntersectionFunctionConfig extends FunctionConfig<List<Integer>>{
	private static final long serialVersionUID = 1L;
	public IntegerListIntersectionFunctionConfig() {
		super("Intersection");
	}
	@Override
	public Function<List<Integer>> getFunction() {
		return new IntegerListIntersectionFunction();
	}

}
