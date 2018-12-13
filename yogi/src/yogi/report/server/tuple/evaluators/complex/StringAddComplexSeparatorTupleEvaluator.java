package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class StringAddComplexSeparatorTupleEvaluator extends TupleRowComplexEvaluator<String> {

	private char separator;
	private int by;

	public StringAddComplexSeparatorTupleEvaluator(String name, char separator, int by, String... dependentColumnNames) {
		super(name, null, false, dependentColumnNames);
		this.separator = separator;
		this.by = by;
	}

	@Override
	public String parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String[] dependentColumnNames = this.getDependentColumnNames();
		StringBuilder rtnValue = new StringBuilder();
		int size = dependentColumnNames.length/by;
		Object[] values = new Object[by];
		rtnValue.append(separator);
		for(int i = 0; i < size; i ++){
			for(int j = 0; j < by; j++){
				values[j] = tupleRow.getValue(dependentColumnNames[i*by+j]);
			}
			add(rtnValue, values);
			rtnValue.append(separator);
		}
		return rtnValue.toString();
	}

	protected void add(StringBuilder rtnValue, Object[] values) {
		for(Object value: values){
			if(value != null){
				rtnValue.append(value);
			}
		}
	}

}
