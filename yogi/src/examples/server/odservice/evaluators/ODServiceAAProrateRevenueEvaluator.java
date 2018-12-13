package examples.server.odservice.evaluators;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;

public class ODServiceAAProrateRevenueEvaluator extends TupleRowComplexEvaluator<Double> {
	private static String[] fareRatio = {"fareRatio1","fareRatio2","fareRatio3","fareRatio4","fareRatio5","fareRatio6"};
	private static String[] legAirline = {"legAirline1","legAirline2","legAirline3","legAirline4","legAirline5","legAirline6"};
	private static String revenue = "revenue";
	private String carrier = "AA";

	public ODServiceAAProrateRevenueEvaluator(String name) {
		super(name, null, false, fareRatio[0], fareRatio[1], fareRatio[2], fareRatio[3], fareRatio[4], fareRatio[5], legAirline[0], legAirline[1], legAirline[2], legAirline[3], legAirline[4], legAirline[5], revenue);
	}

	@Override
	public Double parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		double fr = 0;
		for(int i = 0; i < fareRatio.length; i ++){
			String airline = tupleRow.getValue(legAirline[i]);
			if(airline.equals(carrier)){
				Double value = tupleRow.getValue(fareRatio[i]);
				fr = fr + value;
			}
		}
		Double r = tupleRow.getValue(revenue);
		return fr*r;
	}
}
