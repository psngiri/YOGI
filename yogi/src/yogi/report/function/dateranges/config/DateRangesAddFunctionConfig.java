package yogi.report.function.dateranges.config;

import java.util.List;

import yogi.period.date.range.DateRange;
import yogi.report.function.Function;
import yogi.report.function.dateranges.DateRangesAddFunction;
import yogi.report.server.config.FunctionConfig;

public class DateRangesAddFunctionConfig extends FunctionConfig<List<DateRange>>{
	private static final long serialVersionUID = 1L;
	public DateRangesAddFunctionConfig() {
		super("Add");
	}
	@Override
	public Function<List<DateRange>> getFunction() {
		return  new DateRangesAddFunction();
	}

}
