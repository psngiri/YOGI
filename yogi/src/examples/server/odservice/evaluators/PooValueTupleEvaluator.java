package examples.server.odservice.evaluators;

import yogi.report.group.Group;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowBaseGroupEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;

public class PooValueTupleEvaluator<T> extends TupleRowBaseGroupEvaluator<T>{
	private String[] dependentColumns;
	public PooValueTupleEvaluator(String name, String outboundColumnName, String returnColumnName) {
		super(name, null, false);
		dependentColumns = new String[3];
		dependentColumns[0] = "POO";
		dependentColumns[1] = outboundColumnName;
		dependentColumns[2] = returnColumnName;
	}

	public T evaluate(Group<TupleRow> group, int indexInGroup) {
		TupleRow object = group.getItem(indexInGroup);
		Object pooValue = group.getKeyValue("POO");
		if(pooValue == null) return null;
		Object departureAirportcode = object.getValue("departureAirportCode");
		if(pooValue.equals(departureAirportcode)){
			return object.getValue(dependentColumns[1]);
		}else{
			return object.getValue(dependentColumns[2]);
		}
	}

	@Override
	public String[] getDependentColumnNames() {
		return dependentColumns;
	}

	@Override
	public T parse(FileChannelReader fileChannelReader) {
		return null;
	}
}
