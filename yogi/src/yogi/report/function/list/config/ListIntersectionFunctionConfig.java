package yogi.report.function.list.config;


import java.util.List;

import yogi.report.function.Function;
import yogi.report.function.list.ListIntersectionFunction;
import yogi.report.server.config.FunctionConfig;

public class ListIntersectionFunctionConfig<T> extends FunctionConfig<List<T>>{
	private static final long serialVersionUID = 1L;
	public ListIntersectionFunctionConfig() {
		super("Intersection");
	}
	@Override
	public Function<List<T>> getFunction() {
		return new ListIntersectionFunction<T>();
	}

}
