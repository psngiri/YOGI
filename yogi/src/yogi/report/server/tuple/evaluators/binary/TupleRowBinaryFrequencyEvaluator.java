package yogi.report.server.tuple.evaluators.binary;

import yogi.base.util.range.Range;
import yogi.period.frequency.Frequency;
import yogi.report.condition.frequency.MondayToSundayOneToSevenFrequencyScanner;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;

public class TupleRowBinaryFrequencyEvaluator extends TupleRowComplexEvaluator<Frequency>{

	public String columnName;
	
	public TupleRowBinaryFrequencyEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
		columnName = name;
	}

	@Override
	public Frequency parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String days = new String(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd()));
		MondayToSundayOneToSevenFrequencyScanner scanner = new MondayToSundayOneToSevenFrequencyScanner();	
		return scanner.scan(days).create();
	}
}