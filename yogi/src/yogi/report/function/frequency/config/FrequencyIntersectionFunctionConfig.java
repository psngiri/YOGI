package yogi.report.function.frequency.config;

import yogi.period.frequency.Frequency;
import yogi.report.function.Function;
import yogi.report.function.frequency.FrequencyIntersectionFunction;
import yogi.report.server.config.FunctionConfig;

public class FrequencyIntersectionFunctionConfig extends FunctionConfig<Frequency>{
	private static final long serialVersionUID = 1L;
	public FrequencyIntersectionFunctionConfig() {
		super("Intersection");
	}
	@Override
	public Function<Frequency> getFunction() {
		return new FrequencyIntersectionFunction();
	}

}
