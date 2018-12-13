package examples.server.odservice.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;
import yogi.report.server.tuple.io.RecordReader;

import examples.server.nonstopop.NonStopOpIndexAssistant;

public abstract class ODServiceNonstopOpBaseTupleEvaluator<T> extends TupleRowComplexEvaluator<T> {
	private int index = 0;
	private String LegKey;
	public ODServiceNonstopOpBaseTupleEvaluator(String name, Range<Integer> range, boolean trim, String legKey) {
		super(name, range, trim, ODServiceTypeBaseTupleEvaluator.TYPE, legKey);
		char charAt = legKey.charAt(legKey.length()-1);
		index = charAt-48;
		this.LegKey = legKey;
	}

	@Override
	public T parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		Range<Integer> range = getRange();
			Long legKey = tupleRow.getValue(LegKey);
			if(legKey == 0) return null;
			FileChannelReader fileChannelReaderNonStop = fileChannelReader.getFileChannelReader(0);
			legKey = NonStopOpIndexAssistant.get().getValue(fileChannelReaderNonStop.getFileResource(), legKey);
			long offset = (legKey -1)*fileChannelReaderNonStop.getRecordLength();
			RecordReader recordReader = fileChannelReaderNonStop.getRecordReader(index-1);
			recordReader.read(offset);
		return parse(recordReader, range, this.isTrim());
	}
	
	protected abstract T parse(RecordReader recordReader, Range<Integer> range, boolean trim);
}
