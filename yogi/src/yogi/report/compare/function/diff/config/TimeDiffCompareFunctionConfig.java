package yogi.report.compare.function.diff.config;


import yogi.period.time.Time;
import yogi.report.compare.function.CompareFunction;
import yogi.report.compare.function.diff.TimeDiffCompareFunction;
import yogi.report.server.config.CompareFunctionConfig;

public class TimeDiffCompareFunctionConfig extends CompareFunctionConfig<Time>{

	private static final long serialVersionUID = -6434060238562094294L;

	public TimeDiffCompareFunctionConfig() {
		super("Diff");
	}

	@Override
	public CompareFunction<Time> getCompareFunction(int dataSet1, int dataSet2) {
		return new TimeDiffCompareFunction(dataSet1, dataSet2);
	}

}
