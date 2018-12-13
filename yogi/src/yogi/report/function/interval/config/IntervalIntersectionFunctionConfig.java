package yogi.report.function.interval.config;


import java.util.List;

import yogi.period.interval.Interval;
import yogi.report.function.Function;
import yogi.report.function.interval.IntervalIntersectionFunction;
import yogi.report.server.config.FunctionConfig;

public class IntervalIntersectionFunctionConfig extends FunctionConfig<List<Interval>>{
	private static final long serialVersionUID = 1L;
	public IntervalIntersectionFunctionConfig() {
		super("Intersection");
	}
	@Override
	public Function<List<Interval>> getFunction() {
		return new IntervalIntersectionFunction();
	}

}
