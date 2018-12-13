package examples.airport;

import yogi.base.Creator;

public class AirportCreator implements Creator<Airport> {
	private String code;
	
	public Airport create() {
		Airport airport = new AirportImpl(code);
		return airport;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
