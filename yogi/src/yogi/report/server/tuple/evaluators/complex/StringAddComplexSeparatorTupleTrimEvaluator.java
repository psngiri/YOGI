package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class StringAddComplexSeparatorTupleTrimEvaluator extends StringAddComplexSeparatorTupleEvaluator {

	public StringAddComplexSeparatorTupleTrimEvaluator(String name, char separator, int by, String... dependentColumnNames) {
		super(name, separator, by, dependentColumnNames);
	}

	@Override
	public String parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String rtn  = super.parse(fileChannelReader,tupleRow);
		while(rtn.contains("//"))
			rtn = rtn.replaceAll("//", "/");
		if(rtn.equals("/"))
			return "";
		return rtn;
	}

}
