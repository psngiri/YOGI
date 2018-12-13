package examples.timeline.bp;


import java.util.ArrayList;
import java.util.List;

import yogi.base.app.BaseProcessor;

import examples.airport.Airport;
import examples.airport.AirportManager;
import examples.flight.FlightManager;
import examples.timeline.Timeline;
import examples.timeline.TimelineCreator;
import examples.timeline.TimelineFactory;


public class TimelineGenerator extends BaseProcessor {
	public static boolean RUN = true;
	private TimelineCreator timelineCreator = new TimelineCreator();
	
	public boolean isActivated() {
		return RUN;
	}

	public void run() {
		List<Airport> airports = AirportManager.get().findAll();
		generateTimelines(airports);
	}
	
	private List<Timeline> generateTimelines(List<Airport> airports) {
		List<Timeline> timelines = new ArrayList<Timeline>();
		for(Airport airport: airports)
		{
			Timeline timeline = generateTimeline(airport);
			timelines.add(timeline);
		}
		return timelines;
	}
	
	private Timeline generateTimeline(Airport airport) {
		timelineCreator.setAirport(airport);
		Timeline timeline = TimelineFactory.get().create(timelineCreator);
		timeline.addAllArrivingFlights(FlightManager.get().getArrivingFlights(airport));
		timeline.addAllDepartingFlights(FlightManager.get().getDepartingFlights(airport));
		return timeline;
	}

}
