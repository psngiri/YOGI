package yogi.report.server.tuple;

import java.util.Arrays;
import java.util.Map;

import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;


public class TupleRowImpl implements TupleRow{
	protected Map<String, Integer> columnIndexMap;
	protected Object[] values;
	
	protected TupleRowImpl(Map<String, Integer> columnIndexMap) {
		this(columnIndexMap, new Object[columnIndexMap.size()]);
	}

	protected TupleRowImpl(Map<String, Integer> columnIndexMap, Object[] values) {
		super();
		this.columnIndexMap = columnIndexMap;
		this.values = values;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V> V getValue(String name) {
		int index = columnIndexMap.get(name);
		V value = null;
		if(values != null){
			value = (V)values[index];
			if(value != null) return value;
		}
		return value;
	}


	@SuppressWarnings("unchecked")
	@Override
	public <V> V getValue(String name, TupleRowBaseEvaluator<V> evaluator) {
		return (V) getValue(name);
	}

	@Override
	public String toString() {
		return Arrays.toString(values);
	}

}
