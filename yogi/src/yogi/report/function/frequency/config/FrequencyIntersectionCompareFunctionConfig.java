package yogi.report.function.frequency.config;

import yogi.period.frequency.Frequency;
import yogi.report.compare.function.CompareFunction;
import yogi.report.function.frequency.FrequencyIntersectionCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class FrequencyIntersectionCompareFunctionConfig extends CompareFunctionConfig<Frequency>{
	private static final long serialVersionUID = 1L;
	public FrequencyIntersectionCompareFunctionConfig() {
		super("Intersection");
	}
	@Override
	public CompareFunction<Frequency> getCompareFunction(int dataSet1, int dataSet2) {
		return new FrequencyIntersectionCompareFunction(dataSet1,dataSet2);
	}

}
