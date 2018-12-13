package examples.server.odservice.evaluators;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;

public class ODServiceDominantCarrierEvaluator extends TupleRowComplexEvaluator<String>{
	private static String[] legMiles = {"legMiles1","legMiles2","legMiles3","legMiles4","legMiles5","legMiles6"};
	private static String[] legAirline = {"legAirline1","legAirline2","legAirline3","legAirline4","legAirline5","legAirline6"};

	public ODServiceDominantCarrierEvaluator(String name) {
		super(name, null, false, legMiles[0], legMiles[1], legMiles[2], legMiles[2], legMiles[2], legMiles[5], legAirline[0], legAirline[1], legAirline[2], legAirline[2], legAirline[2], legAirline[5]);
	}

	@Override
	public String parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String rtnCarrier = tupleRow.getValue(legAirline[0]);
		long rtnValue = tupleRow.getValue(legMiles[0]);
		for(int i = 1; i < legMiles.length; i ++){
			String airline = tupleRow.getValue(legAirline[i]);
			if(airline == null) break;
			Long value = tupleRow.getValue(legMiles[i]);
			if(value == null) value = 0l;
			if(value > rtnValue){
				rtnValue = value;
				rtnCarrier = airline;
			}
		}
		return rtnCarrier;
	}
}
