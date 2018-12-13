package yogi.report.function.dateranges.config;

import java.util.List;

import yogi.period.date.range.DateRange;
import yogi.report.function.Function;
import yogi.report.function.dateranges.DateRangesUnionFunction;
import yogi.report.server.config.FunctionConfig;

public class DateRangesUnionFunctionConfig extends FunctionConfig<List<DateRange>>{
	private static final long serialVersionUID = 1L;
	public DateRangesUnionFunctionConfig() {
		super("Union");
	}
	@Override
	public Function<List<DateRange>> getFunction() {
		return new DateRangesUnionFunction();
	}

}
