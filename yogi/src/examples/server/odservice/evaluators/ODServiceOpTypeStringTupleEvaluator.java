package examples.server.odservice.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.RecordReader;

public class ODServiceOpTypeStringTupleEvaluator extends ODServiceOpTypeBaseTupleEvaluator<String> {

	public ODServiceOpTypeStringTupleEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	public String parse(RecordReader recordReader, Range<Integer> range, boolean trim) {
		try {
			return new String(recordReader.getCharArray(range.getStart(), range.getEnd(), isTrim()));
		} catch (Exception e) {
			throw new RuntimeException("Range:"+range, e);
		}
	}

}
