package examples.timeline.app;

import yogi.base.app.BaseModule;
import yogi.base.app.BaseProcessor;
import yogi.base.app.ManagerChecker;
import yogi.base.app.multithread.MultiThreadProcessor;

import examples.airport.AirportManager;
import examples.flight.FlightManager;
import examples.timeline.bc.TimelineFlightConstraintGenerator;
import examples.timeline.bp.mt.MultiTimelineGenerator;

public class MultiTimelineModule extends BaseModule{
	public static boolean RUN = true;
	public MultiTimelineModule() {
		super();
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		addRuntimeChecker(new ManagerChecker(AirportManager.get()));
		addRuntimeChecker(new ManagerChecker(FlightManager.get()));
		
		MultiThreadProcessor multiProcessor = new MultiThreadProcessor();
		addProcessor(multiProcessor);
		multiProcessor.addProcessor(new MultiTimelineGenerator());
		multiProcessor.addProcessor(new DummyProcessor());
		addProcessor(new TimelineFlightConstraintGenerator());
	}

	static class DummyProcessor extends BaseProcessor
	{
		public boolean isActivated() {
			return true;
		}

		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
