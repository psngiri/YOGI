package yogi.report.function.dateranges.config;

import java.util.List;

import yogi.period.date.range.DateRange;
import yogi.report.function.Function;
import yogi.report.function.dateranges.DateRangesIntersectionFunction;
import yogi.report.server.config.FunctionConfig;

public class DateRangesIntersectionFunctionConfig extends FunctionConfig<List<DateRange>>{
	private static final long serialVersionUID = 1L;
	public DateRangesIntersectionFunctionConfig() {
		super("Intersection");
	}
	@Override
	public Function<List<DateRange>> getFunction() {
		return new DateRangesIntersectionFunction();
	}

}
