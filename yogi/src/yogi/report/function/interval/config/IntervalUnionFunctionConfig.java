package yogi.report.function.interval.config;


import java.util.List;

import yogi.period.interval.Interval;
import yogi.report.function.Function;
import yogi.report.function.interval.IntervalUnionFunction;
import yogi.report.server.config.FunctionConfig;

public class IntervalUnionFunctionConfig extends FunctionConfig<List<Interval>>{
	private static final long serialVersionUID = 1L;
	public IntervalUnionFunctionConfig() {
		super("Union");
	}
	@Override
	public Function<List<Interval>> getFunction() {
		return new IntervalUnionFunction();
	}

}
