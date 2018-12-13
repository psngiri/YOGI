package examples.timeline.app;

import yogi.base.app.BaseModule;
import yogi.base.app.ManagerChecker;

import examples.airport.AirportManager;
import examples.flight.FlightManager;
import examples.timeline.bc.TimelineFlightConstraintGenerator;
import examples.timeline.bp.TimelineGenerator;

public class TimelineModule extends BaseModule{
	public static boolean RUN = true;
	public TimelineModule() {
		super();
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addRuntimeChecker(new ManagerChecker(AirportManager.get()));
		addRuntimeChecker(new ManagerChecker(FlightManager.get()));
		
		addProcessor(new TimelineGenerator());
		addProcessor(new TimelineFlightConstraintGenerator());
	}

}
