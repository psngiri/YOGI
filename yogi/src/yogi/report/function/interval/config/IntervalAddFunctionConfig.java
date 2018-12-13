package yogi.report.function.interval.config;


import java.util.List;

import yogi.period.interval.Interval;
import yogi.report.function.Function;
import yogi.report.function.interval.IntervalAddFunction;
import yogi.report.server.config.FunctionConfig;

public class IntervalAddFunctionConfig extends FunctionConfig<List<Interval>>{
	private static final long serialVersionUID = 1L;
	public IntervalAddFunctionConfig() {
		super("Add");
	}
	@Override
	public Function<List<Interval>> getFunction() {
		return new IntervalAddFunction();
	}

}
