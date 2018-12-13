package examples.server.odservice.binary.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;

import examples.airport.Airport;
import examples.airport.AirportManager;



public class AirportEvaluator extends TupleRowBaseEvaluator<Airport> {

	public AirportEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Airport parse(FileChannelReader fileChannelReader) {
		return AirportManager.get().getAirport(new String(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd())));
	}
}
