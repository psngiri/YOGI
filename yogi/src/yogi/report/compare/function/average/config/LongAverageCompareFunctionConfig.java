package yogi.report.compare.function.average.config;


import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.average.LongAverageCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class LongAverageCompareFunctionConfig extends CompareFunctionConfig<Long>{

	private static final long serialVersionUID = -6434060238562094294L;

	public LongAverageCompareFunctionConfig() {
		super("Average");
	}

	@Override
	public CompareFunction<Long> getCompareFunction(int dataSet1, int dataSet2) {
		return new LongAverageCompareFunction(dataSet1, dataSet2);
	}

}
