package examples.location.inheritance;

import yogi.base.Creator;

public class AirportLocationCreator implements Creator<AirportLocation> {
	private String code;
	private float lattitude;
	private float longitude;

	
	public AirportLocationCreator() {
		super();
	}


	public String getCode() {
		return code;
	}


	public float getLattitude() {
		return lattitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public void setLattitude(float lattitude) {
		this.lattitude = lattitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


	public AirportLocation create() {
		return new AirportLocationImpl(code, lattitude, longitude);
	}

}
