package examples.server.odservice.binary.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.RecordReader;

import examples.server.odservice.evaluators.ODServiceOpTypeBaseTupleEvaluator;

public class ODServiceOpTypeStringTupleEvaluator extends ODServiceOpTypeBaseTupleEvaluator<String> {

	public ODServiceOpTypeStringTupleEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	public String parse(RecordReader recordReader, Range<Integer> range, boolean trim) {
		try {
			String rtnValue = new String(recordReader.getByteArray(range.getStart(), range.getEnd()));
			if(trim){
				rtnValue = rtnValue.trim();
			}
			return rtnValue;
		} catch (Exception e) {
			throw new RuntimeException("Range:"+range, e);
		}
	}

}
