package yogi.report.server.tuple.evaluators.binary;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class TupleRowBinaryStringEvaluator extends TupleRowBaseEvaluator<String> {

	public TupleRowBinaryStringEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public String parse(FileChannelReader fileChannelReader) {
		String rtnValue = new String(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd()));
		if(this.isTrim()){
			rtnValue = rtnValue.trim();
		}
		return rtnValue;
	}
}
