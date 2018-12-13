package yogi.report.function.frequency.config;

import yogi.period.frequency.Frequency;
import yogi.report.compare.function.CompareFunction;
import yogi.report.function.frequency.FrequencyDiffCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class FrequencyReverseDiffCompareFunctionConfig extends CompareFunctionConfig<Frequency>{
	private static final long serialVersionUID = 1L;
	public FrequencyReverseDiffCompareFunctionConfig() {
		super("Diff(A-B)");
	}
	@Override
	public CompareFunction<Frequency> getCompareFunction(int dataSet1, int dataSet2) {
		return new FrequencyDiffCompareFunction(dataSet2,dataSet1);
	}

}
