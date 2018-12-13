package yogi.report.function.timeranges.config;

import java.util.List;

import yogi.period.time.range.TimeRange;
import yogi.report.function.Function;
import yogi.report.function.timeranges.TimeRangesUnionFunction;
import yogi.report.server.config.FunctionConfig;

public class TimeRangesUnionFunctionConfig extends FunctionConfig<List<TimeRange>>{
	private static final long serialVersionUID = 1L;
	public TimeRangesUnionFunctionConfig() {
		super("Union");
	}
	@Override
	public Function<List<TimeRange>> getFunction() {
		return new TimeRangesUnionFunction();
	}

}
