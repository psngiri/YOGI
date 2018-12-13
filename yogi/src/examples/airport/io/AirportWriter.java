package examples.airport.io;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.FileWriter;

import examples.airport.Airport;
import examples.airport.AirportFactory;

public class AirportWriter extends FactoryWriter<Airport> {
	public AirportWriter(String fileName, Selector<Airport> selector)
	{
		super(AirportFactory.get(), new FileWriter<Airport>(fileName, new AirportFormatter()), selector);
	}
	public AirportWriter(String fileName)
	{
		this(fileName, null);
	}
}
