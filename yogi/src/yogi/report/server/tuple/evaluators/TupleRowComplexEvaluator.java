package yogi.report.server.tuple.evaluators;

import java.util.Arrays;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.io.FileChannelReader;

public abstract class TupleRowComplexEvaluator<V> extends TupleRowBaseEvaluator<V> {

	private String[] dependentColumnNames;

	public TupleRowComplexEvaluator(String name, Range<Integer> range, boolean trim, String... dependentColumnNames) {
		super(name, range, trim);
		this.dependentColumnNames = dependentColumnNames;
	}

	@Override
	public String[] getDependentColumnNames() {
		return dependentColumnNames;
	}
	
	protected void addDependentColumnNames(String ...dependentColumns)
	{
		int orignalLength = dependentColumnNames.length;
		dependentColumnNames = Arrays.copyOf(dependentColumnNames, dependentColumnNames.length+dependentColumns.length);
		for(int i=0;i < dependentColumns.length;i++)
		{
			dependentColumnNames[orignalLength+i]=dependentColumns[i];
		}
	}
	
	public V parse(FileChannelReader fileChannelReader){
		return null;
	}
	
	public abstract V parse(FileChannelReader fileChannelReader, TupleRow tupleRow);

}
