package examples.timeline.bc;

import java.util.ArrayList;
import java.util.List;

import yogi.base.app.BaseProcessor;
import yogi.optimize.expr.Constraint;
import yogi.optimize.expr.Expression;
import yogi.optimize.expr.LinearExpressionTerm;
import yogi.optimize.expr.VariableAssistant;

import examples.flight.Flight;
import examples.timeline.Timeline;
import examples.timeline.TimelineManager;

public class TimelineFlightConstraintGenerator extends BaseProcessor {
	public static boolean RUN = true;
	
	public boolean isActivated() {
		return RUN;
	}

	public void run() {
		List<Timeline> timelines = TimelineManager.get().findAll();
		createTimelineFlightConstraints(timelines);
	}

	private List<Constraint> createTimelineFlightConstraints(List<Timeline> timelines) {
		List<Constraint> constraints = new ArrayList<Constraint>();
		for(Timeline timeline: timelines)
		{
			constraints.add(createTimelineFlightConstraint(timeline));
		}
		return constraints;
	}

	private Constraint createTimelineFlightConstraint(Timeline timeline) {
		Expression expression = new Expression();
		for(Flight arrivingFlight: timeline.getArrivingFlights())
		{
			LinearExpressionTerm term = new LinearExpressionTerm(VariableAssistant.get().getVariable(arrivingFlight));
			expression.plus(term);
		}
		for(Flight departingFlight: timeline.getDepartingFlights())
		{
			LinearExpressionTerm term = new LinearExpressionTerm(VariableAssistant.get().getVariable(departingFlight));
			expression.minus(term);
		}
		return new Constraint( "T" , timeline, expression, 0, 0);
	}

}
