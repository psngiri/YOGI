package yogi.report.compare.function.average.config;


import yogi.base.util.PrecisionFloat;
import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.average.PrecisionFloatAverageCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class PrecisionFloatAverageCompareFunctionConfig extends CompareFunctionConfig<PrecisionFloat>{

	private static final long serialVersionUID = -6434060238562094294L;

	public PrecisionFloatAverageCompareFunctionConfig() {
		super("Average");
	}

	@Override
	public CompareFunction<PrecisionFloat> getCompareFunction(int dataSet1, int dataSet2) {
		return new PrecisionFloatAverageCompareFunction(dataSet1, dataSet2);
	}

}
