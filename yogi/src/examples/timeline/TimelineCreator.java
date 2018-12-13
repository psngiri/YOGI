package examples.timeline;

import yogi.base.Creator;

import examples.airport.Airport;

public class TimelineCreator implements Creator<Timeline> {
	private Airport airport;
	
	public Timeline create() {
		Timeline timeline = new TimelineImpl(airport);
		return timeline;
	}
	public Airport getAirport() {
		return airport;
	}
	public void setAirport(Airport airport) {
		this.airport = airport;
	}

}
