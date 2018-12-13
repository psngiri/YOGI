package yogi.report.function.timeranges.config;

import java.util.List;

import yogi.period.time.range.TimeRange;
import yogi.report.function.Function;
import yogi.report.function.timeranges.TimeRangesIntersectionFunction;
import yogi.report.server.config.FunctionConfig;

public class TimeRangesIntersectionFunctionConfig extends FunctionConfig<List<TimeRange>>{
	private static final long serialVersionUID = 1L;
	public TimeRangesIntersectionFunctionConfig() {
		super("Intersection");
	}
	@Override
	public Function<List<TimeRange>> getFunction() {
		return new TimeRangesIntersectionFunction();
	}

}
