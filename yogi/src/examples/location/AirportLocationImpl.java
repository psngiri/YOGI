package examples.location;

import yogi.base.relationship.RelationshipObjectImpl;

import examples.airport.Airport;

public class AirportLocationImpl extends RelationshipObjectImpl<AirportLocation> implements AirportLocation{
	private Airport airport;
	private float lattitude;
	private float longitude;
	
	
	protected AirportLocationImpl(Airport airport, float lattitude, float longitude) {
		super();
		this.airport = airport;
		this.lattitude = lattitude;
		this.longitude = longitude;
	}

	public Airport getAirport() {
		return airport;
	}

	public float getLattitude() {
		return lattitude;
	}
	public float getLongitude() {
		return longitude;
	}
	
	public String getCode() {
		return airport.getCode();
	}

	public String getIndex() {
		return airport.getIndex();
	}

	public String getName() {
		return airport.getName();
	}

	@Override
	public int compareTo(Airport o) {
		return airport.compareTo(o);
	}
	
}
