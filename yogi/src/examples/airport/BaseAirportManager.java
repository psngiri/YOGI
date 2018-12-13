package examples.airport;

import yogi.base.ObjectNotFoundException;
import yogi.base.relationship.index.IndexedManager;


public abstract class BaseAirportManager<T extends Airport> extends IndexedManager<T, String>{
	protected BaseAirportManager() {
		super();
	}
	public T getAirport(String airportCode)
	{
		try {
			return this.getObject(airportCode);
		} catch (ObjectNotFoundException e) {
			return null;
		}
	}

}
