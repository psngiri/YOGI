package examples.timeline;

import yogi.base.SynchronizedFactory;
import yogi.base.relationship.RelationshipManager;

public class TimelineFactory extends SynchronizedFactory<Timeline> {
	private static TimelineFactory timelineFactory = new TimelineFactory(TimelineManager.get());
	

	protected TimelineFactory(RelationshipManager<Timeline> relationshipManager) {
		super(relationshipManager);
	}


	public static TimelineFactory get() {
		return timelineFactory;
	}
	
}
