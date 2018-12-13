package yogi.report.function.list.config;


import java.util.List;

import yogi.report.function.Function;
import yogi.report.function.list.ListUnionFunction;
import yogi.report.server.config.FunctionConfig;

public class ListUnionFunctionConfig<T> extends FunctionConfig<List<T>>{
	private static final long serialVersionUID = 1L;
	public ListUnionFunctionConfig() {
		super("Union");
	}
	@Override
	public Function<List<T>> getFunction() {
		return new ListUnionFunction<T>();
	}

}
