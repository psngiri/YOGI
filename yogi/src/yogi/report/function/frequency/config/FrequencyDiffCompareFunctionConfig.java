package yogi.report.function.frequency.config;

import yogi.period.frequency.Frequency;
import yogi.report.compare.function.CompareFunction;
import yogi.report.function.frequency.FrequencyDiffCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class FrequencyDiffCompareFunctionConfig extends CompareFunctionConfig<Frequency>{
	private static final long serialVersionUID = 1L;
	public FrequencyDiffCompareFunctionConfig() {
		super("Diff(B-A)");
	}
	@Override
	public CompareFunction<Frequency> getCompareFunction(int dataSet1, int dataSet2) {
		return new FrequencyDiffCompareFunction(dataSet1,dataSet2);
	}

}
