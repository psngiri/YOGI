package examples.server.odservice.evaluators;

import java.util.ArrayList;
import java.util.List;

import yogi.report.group.Group;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowBaseGroupEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;

public class ODServiceProrateFactorEvaluator extends TupleRowBaseGroupEvaluator<Double>{
	private static String[] fareRatio = {"fareRatio1","fareRatio2","fareRatio3","fareRatio4","fareRatio5","fareRatio6"};
	private static String[] legAirline = {"legAirline1","legAirline2","legAirline3","legAirline4","legAirline5","legAirline6"};
	private static String[] legSegment = {"Segment1","Segment2","Segment3","Segment4","Segment5","Segment6"};
	private String[] dependentColumnNames;

	public ODServiceProrateFactorEvaluator(String name) {
		super(name, null, false);
		List<String> dependentColumnsList = new ArrayList<String>();
		for(String item: fareRatio){
			dependentColumnsList.add(item);
		}
		for(String item: legAirline){
			dependentColumnsList.add(item);
		}
		for(String item: legSegment){
			dependentColumnsList.add(item);
		}
		dependentColumnNames = dependentColumnsList.toArray(new String[0]);
	}

	@Override
	public Double parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		return evaluateProrateFactor(tupleRow, null, null);
	}

	@Override
	public Double evaluate(Group<TupleRow> group, int indexInGroup) {
		TupleRow tupleRow = group.getItem(indexInGroup);
		Object prorateCarrier = group.getKeyValue("ProrateCarrier");
		Object prorateSegment = group.getKeyValue("ProrateSegment");
		return evaluateProrateFactor(tupleRow, prorateCarrier, prorateSegment);
	}

	private Double evaluateProrateFactor(TupleRow tupleRow, Object prorateCarrier, Object prorateSegment) {
		double fr = 0;
		for(int i = 0; i < fareRatio.length; i ++){
			String airline = tupleRow.getValue(legAirline[i]);
			String segment = tupleRow.getValue(legSegment[i]);
			if((prorateCarrier == null || airline.equals(prorateCarrier))&&(prorateSegment == null || segment.equals(prorateSegment))){
				Double value = tupleRow.getValue(fareRatio[i]);
				fr = fr + value;
			}
		}
		return fr;
	}

	@Override
	public String[] getDependentColumnNames() {
		return dependentColumnNames;
	}
}
