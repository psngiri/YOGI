package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class StringAddSeparatorComplexTupleEvaluator extends TupleRowComplexEvaluator<String> {
	private Character separator;

	public StringAddSeparatorComplexTupleEvaluator(String name, Character separator, String... dependentColumnNames) {
		super(name, null, false, dependentColumnNames);
		this.separator = separator;
	}

	@Override
	public String parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String[] dependentColumnNames = this.getDependentColumnNames();
		StringBuilder rtnValue = new StringBuilder();
		int i = 0;
		for(; i < dependentColumnNames.length -1; i++){
			Object value = tupleRow.getValue(dependentColumnNames[i]);
			if(value != null) rtnValue.append(value);
			if(separator!=null) rtnValue.append(separator);
		}
		Object value = tupleRow.getValue(dependentColumnNames[i]);
		if(value != null) rtnValue.append(value);
		String rtn = rtnValue.toString();
		while(!rtn.isEmpty() && ((Character)rtn.charAt(0)).equals(separator))
			rtn = rtn.substring(1);
		while(!rtn.isEmpty() && ((Character)rtn.charAt(rtn.length()-1)).equals(separator))
			rtn = rtn.substring(0,rtn.length()-1);
		return rtn;
	}
	
}
