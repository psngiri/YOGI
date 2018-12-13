package examples.server.odservice.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;
import yogi.report.server.tuple.io.RecordReader;

public abstract class ODServiceOpTypeBaseTupleEvaluator<T> extends TupleRowComplexEvaluator<T> {
	private static final String OpODKey = "operationalODKey";

	public ODServiceOpTypeBaseTupleEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim, ODServiceTypeBaseTupleEvaluator.TYPE, OpODKey);
	}

	@Override
	public T parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		Range<Integer> range = getRange();
		String type = tupleRow.getValue(ODServiceTypeBaseTupleEvaluator.TYPE);
		RecordReader recordReader =  fileChannelReader.getRecordReader();
		if(type.equals("*")){
			Long opSvcKey = tupleRow.getValue(OpODKey);
			long offset = (opSvcKey -1)*fileChannelReader.getRecordLength();
			recordReader = fileChannelReader.getRecordReader(0);
			recordReader.read(offset);
		}
		return parse(recordReader, range, this.isTrim());
	}
	
	protected abstract T parse(RecordReader recordReader, Range<Integer> range, boolean trim);
}
