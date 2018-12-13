package examples.flight.io;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.FileWriter;

import examples.flight.Flight;
import examples.flight.FlightFactory;


public class FlightWriter extends FactoryWriter<Flight> {
	public FlightWriter(String fileName, Selector<Flight> selector)
	{
		super(FlightFactory.get(), new FileWriter<Flight>(fileName, new FlightFormatter()), selector);
	}
	public FlightWriter(String fileName)
	{
		this(fileName, null);
	}
}
