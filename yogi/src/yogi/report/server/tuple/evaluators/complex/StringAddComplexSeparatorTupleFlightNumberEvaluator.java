package yogi.report.server.tuple.evaluators.complex;

public class StringAddComplexSeparatorTupleFlightNumberEvaluator extends StringAddComplexSeparatorTupleEvaluator {
	
	public StringAddComplexSeparatorTupleFlightNumberEvaluator(String name, char separator, int by, String... dependentColumnNames) {
		super(name, separator, by, dependentColumnNames);
	}

	protected void add(StringBuilder rtnValue, Object[] values) {
		for(Object value: values){
			if(value != null){
				if(!value.equals(0)){
					rtnValue.append(value);
				}		
			}
		}
	}
}