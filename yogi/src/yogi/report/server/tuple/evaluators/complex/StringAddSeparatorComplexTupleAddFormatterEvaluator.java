package yogi.report.server.tuple.evaluators.complex;

import yogi.base.io.Formatter;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.io.FileChannelReader;


public class StringAddSeparatorComplexTupleAddFormatterEvaluator<T> extends StringAddComplexSeparatorTupleEvaluator{
	private Formatter<T> formatter;
	private char separator;
	
	public StringAddSeparatorComplexTupleAddFormatterEvaluator(String name, Character separator, Formatter<T> formatter, String... dependentColumnNames) {
		super(name, separator, 1, dependentColumnNames);
		this.formatter = formatter;
		this.separator = separator;
	}
	
	@Override
	public String parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String rtn = super.parse(fileChannelReader,tupleRow);
		while(!rtn.isEmpty() && ((Character)rtn.charAt(0)).equals(separator))
			rtn = rtn.substring(1);
		while(!rtn.isEmpty() && ((Character)rtn.charAt(rtn.length()-1)).equals(separator))
			rtn = rtn.substring(0,rtn.length()-1);
		return rtn;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void add(StringBuilder rtnValue, Object[] values) {
		for(Object value: values){
			if(value != null){
				rtnValue.append(formatter.format((T)value));
			}
		}
	}
	
}
