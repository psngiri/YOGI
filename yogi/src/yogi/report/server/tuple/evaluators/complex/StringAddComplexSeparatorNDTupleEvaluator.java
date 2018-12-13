package yogi.report.server.tuple.evaluators.complex;

import java.util.Arrays;

public class StringAddComplexSeparatorNDTupleEvaluator extends	StringAddComplexSeparatorTupleEvaluator {

	public StringAddComplexSeparatorNDTupleEvaluator(String name, char separator, int by, String... dependentColumnNames) {
		super(name, separator, by, dependentColumnNames);
	}

	@Override
	protected void add(StringBuilder rtnValue, Object[] values) {
		Arrays.sort(values);
		super.add(rtnValue, values);
	}

}
