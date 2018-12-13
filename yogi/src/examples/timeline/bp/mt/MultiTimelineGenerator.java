package examples.timeline.bp.mt;

import java.util.List;

import yogi.base.app.BaseProcessor;
import yogi.base.app.multithread.Callable;
import yogi.base.app.multithread.MultiCaller;

import examples.airport.Airport;
import examples.airport.AirportManager;
import examples.flight.FlightManager;
import examples.timeline.Timeline;
import examples.timeline.TimelineCreator;
import examples.timeline.TimelineFactory;

public class MultiTimelineGenerator extends BaseProcessor {
	public static boolean RUN = true;

	public boolean isActivated() {
		return RUN;
	}

	public void run() {
		List<Airport> airports = AirportManager.get().findAll();
		new MultiCaller<Airport>().call(airports, TimelineGeneratorCallable.class);
	}
	
	public static class TimelineGeneratorCallable implements Callable<Airport>
	{
		TimelineCreator timelineCreator;

		public TimelineGeneratorCallable() {
			super();
			timelineCreator = new TimelineCreator();
		}

		public void call(Airport airport) {
			generateTimeline(airport);
		}
		
		private Timeline generateTimeline(Airport airport) {
			timelineCreator.setAirport(airport);
			Timeline timeline = TimelineFactory.get().create(timelineCreator);
			timeline.addAllArrivingFlights(FlightManager.get().getArrivingFlights(airport));
			timeline.addAllDepartingFlights(FlightManager.get().getDepartingFlights(airport));
			return timeline;
		}

		public boolean open() {
			return true;
		}

		public boolean close() {
			return true;
		}
		
	}
}
