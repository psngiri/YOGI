package examples.flight;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;


public class FlightAssistant extends RelationshipAssistant<Flight>{
	private static FlightAssistant flightAssistant = new FlightAssistant();
	private static OneToOneSimpleRelationship<Flight, String> flightMarket = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(Flight.class, String.class, "Market");
	public static FlightAssistant get()
	{
		return flightAssistant;
	}
	
	public String getMarket(Flight flight) {
		String market = getRelationship(flight, flightMarket);
		if(market == null)
		{
			market = flight.getDepartureAirport().getCode()+flight.getArrivalAirport().getCode();
			setRelationship(flight, flightMarket, market);
		}
		return market;
	}

	public String getMarketDontCompute(Flight flight) {
		String market = getRelationship(flight, flightMarket);
		return market;
	}
	
	@SuppressWarnings("unchecked")
	public void clearMarkets()
	{
		this.reset(FlightManager.get(), null, flightMarket);
	}

}
