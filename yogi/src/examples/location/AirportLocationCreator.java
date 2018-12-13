package examples.location;

import yogi.base.Creator;

import examples.airport.Airport;

public class AirportLocationCreator implements Creator<AirportLocation> {
	private Airport airport;
	private float lattitude;
	private float longitude;

	
	public AirportLocationCreator() {
		super();
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


	public void setAirport(Airport airport) {
		this.airport = airport;
	}


	public void setLattitude(float lattitude) {
		this.lattitude = lattitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


	public AirportLocation create() {
		return new AirportLocationImpl(airport, lattitude, longitude);
	}

}
