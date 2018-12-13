package yogi.report.server.tuple;

import java.util.Map;

import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;


public class MutalbleTupleRow extends TupleRowImpl {
	private FileChannelReader fileChannelReader1;
	private Map<String, TupleRowBaseEvaluator<?>> columnEvaluatorsMap;
	public MutalbleTupleRow(Map<String, Integer> columnIndexMap, Map<String, TupleRowBaseEvaluator<?>> columnEvaluatorsMap) {
		super(columnIndexMap);
		this.columnEvaluatorsMap = columnEvaluatorsMap;
	}

	public long set(FileChannelReader fileChannelReader1, long offset) {
		this.fileChannelReader1 = fileChannelReader1;
		values = new Object[columnIndexMap.size()];
		return fileChannelReader1.read(offset);
	}

	@Override
	public <V> V getValue(String name) {
		@SuppressWarnings("unchecked")
		TupleRowBaseEvaluator<V> evaluator = (TupleRowBaseEvaluator<V>) columnEvaluatorsMap.get(name);
		return getValue(name, evaluator);
	}

	@SuppressWarnings("unchecked")
	public <V> V getValue(String name, TupleRowBaseEvaluator<V> evaluator) {
		int index = columnIndexMap.get(name);
		V value = null;
		if(values != null){
			value = (V)values[index];
			if(value != null) return value;
		}
		value = evaluator.parse(fileChannelReader1, this);
		values[index] = value;
		return value;
	}
	
	public TupleRow getTupleRow(){
		return new TupleRowImpl(columnIndexMap, values);
	}
}
