package yogi.report.server.tuple.evaluators.complex;

import yogi.base.io.Formatter;

public class StringAddComplexSeparatorTupleFormatterEvaluator extends StringAddComplexSeparatorTupleEvaluator {
	
	private Formatter formatter;
	
	public StringAddComplexSeparatorTupleFormatterEvaluator(String name, char separator, int by, Formatter formatter, String... dependentColumnNames) {
		super(name, separator, by, dependentColumnNames);
		this.formatter = formatter;
	}

	protected void add(StringBuilder rtnValue, Object[] values) {
		for(Object value: values){
			if(value != null){
					rtnValue.append(formatter.format(value));
				}		
			}
	}
}