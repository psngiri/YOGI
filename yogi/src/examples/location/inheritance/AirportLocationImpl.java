package examples.location.inheritance;

import examples.airport.BaseAirportImpl;

public class AirportLocationImpl extends BaseAirportImpl<AirportLocation> implements AirportLocation{
	private float lattitude;
	private float longitude;
	
	
	AirportLocationImpl(String code, float lattitude, float longitude) {
		super(code);
		this.lattitude = lattitude;
		this.longitude = longitude;
	}
	
	public float getLattitude() {
		return lattitude;
	}
	public float getLongitude() {
		return longitude;
	}
	
}
