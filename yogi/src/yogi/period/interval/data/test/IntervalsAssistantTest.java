package yogi.period.interval.data.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.relationship.DerivedRelationshipObjectImpl;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.RelationshipObjectImpl;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.date.DateManager;
import yogi.period.frequency.FrequencyManager;
import yogi.period.interval.Interval;
import yogi.period.interval.data.IntervalsAssistant;
import yogi.period.interval.data.IntervalsObject;
import yogi.period.interval.io.IntervalScanner;

public class IntervalsAssistantTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();
	private MyIntervalsAssistant intervalsAssistant = new MyIntervalsAssistant();

	/*
	 * Test method for 'yogi.period.interval.data.IntervaledDataManager.getIntervals(T) <T>'
	 */
	public void testGetIntervals() {

	}

	/*
	 * Test method for 'yogi.period.interval.data.IntervaledDataManager.addInterval(T, Interval) <T>'
	 */
	public void testAddInterval() {
		MyRelationshipObject object = new MyRelationshipObjectImpl();
		Interval interval = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		assertEquals(0, intervalsAssistant.getIntervals(object).size());
		intervalsAssistant.addInterval(object, interval);
		ImmutableList<Interval> intervals = intervalsAssistant.getIntervals(object);
		assertEquals(1, intervals.size());
		assertEquals(interval, intervals.get(0));
		
		MyRelationshipObject object1 = new MyRelationshipObjectImpl();
		Interval interval1 = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		intervalsAssistant.addInterval(object1, interval1);
		ImmutableList<Interval> intervals1 = intervalsAssistant.getIntervals(object1);
		assertEquals(1, intervals.size());
		assertEquals(interval1, intervals1.get(0));
		
		Interval interval2 = intervalScanner.scan("16MAR2007 15APR2007 1111111");
		new Interval(DateManager.get().getDate(2007, 2, 16), 
				DateManager.get().getDate(2007, 3, 15), FrequencyManager.AllDayFrequency);
		intervalsAssistant.addInterval(object, interval2);
		assertEquals(2, intervals.size());
		assertEquals(interval, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		intervalsAssistant.deleteInterval(object, interval);
		assertEquals(1, intervals.size());
		assertEquals(interval2, intervals.get(0));
	}

	public void testAddIntervals1() {
		MyRelationshipObject object = new MyRelationshipObjectImpl();
		Interval interval1 = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		Interval interval2 = intervalScanner.scan("01APR2007 15MAY2007 1111111");
		Interval interval3 = intervalScanner.scan("01APR2007 25MAY2007 1111111");
		assertEquals(0, intervalsAssistant.getIntervals(object).size());
		intervalsAssistant.addInterval(object, interval1);
		ImmutableList<Interval> intervals = intervalsAssistant.getIntervals(object);
		assertEquals(1, intervals.size());
		assertEquals(interval1, intervals.get(0));
		
		List<Interval> addIntervals = new ArrayList<Interval>();
		addIntervals.add(interval2);
		addIntervals.add(interval3);
		intervalsAssistant.addIntervals(object, addIntervals);
		assertEquals(3, intervals.size());
		assertEquals(interval1, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		Interval interval3expected = intervalScanner.scan("16MAY2007 25MAY2007 1111111");
		assertEquals(interval3expected, intervals.get(2));
		
		MyRelationshipObject derivedObject = new MyDerivedRelationshipObjectImpl(object);
		ImmutableList<Interval> derivedIntervals = intervalsAssistant.getIntervals(derivedObject);
		assertEquals(3, derivedIntervals.size());
		assertEquals(interval1, derivedIntervals.get(0));
		assertEquals(interval2, derivedIntervals.get(1));
		assertEquals(interval3expected, derivedIntervals.get(2));
		Interval interval4 = intervalScanner.scan("01JUN2007 15JUN2007 1111111");
		Interval interval5 = intervalScanner.scan("01JUL2007 15AUG2007 1111111");
		intervalsAssistant.addInterval(derivedObject, interval4);
		derivedIntervals = intervalsAssistant.getIntervals(derivedObject);
		assertEquals(1, derivedIntervals.size());
		assertEquals(interval4, derivedIntervals.get(0));
		assertEquals(3, intervals.size());
		assertEquals(interval1, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		assertEquals(interval3expected, intervals.get(2));
		List<Interval> addIntervals1 = new ArrayList<Interval>();
		addIntervals1.add(interval4);
		addIntervals1.add(interval5);
		intervalsAssistant.addIntervals(derivedObject, addIntervals1);
		assertEquals(2, derivedIntervals.size());
		assertEquals(interval4, derivedIntervals.get(0));
		assertEquals(interval5, derivedIntervals.get(1));
		assertEquals(3, intervals.size());
		assertEquals(interval1, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		assertEquals(interval3expected, intervals.get(2));
		
	}

	public void testAddIntervals() {
		MyRelationshipObject object = new MyRelationshipObjectImpl();
		Interval interval1 = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		Interval interval2 = intervalScanner.scan("01APR2007 15MAY2007 1111111");
		Interval interval3 = intervalScanner.scan("01FEB2007 25MAR2007 1111111");
		assertEquals(0, intervalsAssistant.getIntervals(object).size());
		intervalsAssistant.addInterval(object, interval1);
		ImmutableList<Interval> intervals = intervalsAssistant.getIntervals(object);
		assertEquals(1, intervals.size());
		assertEquals(interval1, intervals.get(0));
		
		List<Interval> addIntervals = new ArrayList<Interval>();
		addIntervals.add(interval2);
		addIntervals.add(interval3);
		intervalsAssistant.addIntervals(object, addIntervals);
		assertEquals(3, intervals.size());
		assertEquals(interval1, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		Interval interval3expected = intervalScanner.scan("16MAR2007 25MAR2007 1111111");
		assertEquals(interval3expected, intervals.get(2));
		
		
	}

	public void testDeleteIntervals() {
		MyRelationshipObject object = new MyRelationshipObjectImpl();
		Interval interval1 = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		Interval interval2 = intervalScanner.scan("01APR2007 15MAY2007 1111111");
		Interval interval3 = intervalScanner.scan("01APR2007 25MAY2007 1111111");
		assertEquals(0, intervalsAssistant.getIntervals(object).size());
		intervalsAssistant.addInterval(object, interval1);
		ImmutableList<Interval> intervals = intervalsAssistant.getIntervals(object);
		assertEquals(1, intervals.size());
		assertEquals(interval1, intervals.get(0));
		
		List<Interval> addIntervals = new ArrayList<Interval>();
		addIntervals.add(interval2);
		addIntervals.add(interval3);
		intervalsAssistant.addIntervals(object, addIntervals);
		assertEquals(3, intervals.size());
		assertEquals(interval1, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		Interval interval3expected = intervalScanner.scan("16MAY2007 25MAY2007 1111111");
		assertEquals(interval3expected, intervals.get(2));
		intervalsAssistant.deleteInterval(object, interval3);
		assertEquals(1, intervals.size());
		assertEquals(interval1, intervals.get(0));
		intervalsAssistant.addIntervals(object, addIntervals);
		assertEquals(3, intervals.size());
		assertEquals(interval1, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		assertEquals(interval3expected, intervals.get(2));
		intervalsAssistant.deleteIntervals(object, addIntervals);
		assertEquals(1, intervals.size());
		assertEquals(interval1, intervals.get(0));
		intervalsAssistant.addIntervals(object, addIntervals);
		assertEquals(3, intervals.size());
		assertEquals(interval1, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		assertEquals(interval3expected, intervals.get(2));
		
		MyRelationshipObject derivedObject = new MyDerivedRelationshipObjectImpl(object);
		ImmutableList<Interval> derivedIntervals = intervalsAssistant.getIntervals(derivedObject);
		assertEquals(3, derivedIntervals.size());
		assertEquals(interval1, derivedIntervals.get(0));
		assertEquals(interval2, derivedIntervals.get(1));
		assertEquals(interval3expected, derivedIntervals.get(2));
		intervalsAssistant.deleteInterval(derivedObject, interval3);
		derivedIntervals = intervalsAssistant.getIntervals(derivedObject);
		assertEquals(1, derivedIntervals.size());
		assertEquals(interval1, derivedIntervals.get(0));
		assertEquals(3, intervals.size());
		assertEquals(interval1, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		assertEquals(interval3expected, intervals.get(2));
		addIntervals.add(interval1);
		intervalsAssistant.deleteIntervals(derivedObject, addIntervals);
		assertEquals(0, derivedIntervals.size());
		assertEquals(3, intervals.size());
		assertEquals(interval1, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		assertEquals(interval3expected, intervals.get(2));
		
		derivedObject = new MyDerivedRelationshipObjectImpl(object);
		Interval interval4 = intervalScanner.scan("01JUN2007 15JUN2007 1111111");
		Interval interval5 = intervalScanner.scan("01JUL2007 15AUG2007 1111111");
		List<Interval> addIntervals1 = new ArrayList<Interval>();
		addIntervals1.add(interval4);
		addIntervals1.add(interval5);
		addIntervals1.add(interval2);
		addIntervals1.add(interval3);
		intervalsAssistant.deleteIntervals(derivedObject, addIntervals1);
		derivedIntervals = intervalsAssistant.getIntervals(derivedObject);
		assertEquals(1, derivedIntervals.size());
		assertEquals(interval1, derivedIntervals.get(0));
		assertEquals(3, intervals.size());
		assertEquals(interval1, intervals.get(0));
		assertEquals(interval2, intervals.get(1));
		assertEquals(interval3expected, intervals.get(2));
		
	}

	static interface MyRelationshipObject extends RelationshipObject
	{
		
	}
	static class MyRelationshipObjectImpl extends RelationshipObjectImpl<MyRelationshipObject> implements MyRelationshipObject
	{

		public String getName() {
			return "";
		}
		
	}
	static class MyDerivedRelationshipObjectImpl extends DerivedRelationshipObjectImpl<MyRelationshipObject, MyRelationshipObject> implements MyRelationshipObject
	{

		protected MyDerivedRelationshipObjectImpl(MyRelationshipObject parent) {
			super(parent);
		}

		public String getName() {
			return "";
		}
		
	}
	
	static class MyIntervalsAssistant extends IntervalsAssistant<MyRelationshipObject>
	{
		private static OneToOneSimpleRelationship<MyRelationshipObject, IntervalsObject> myIntervalsRelationship = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(MyRelationshipObject.class, IntervalsObject.class, "Intervals");
		@Override
		protected OneToOneSimpleRelationship<MyRelationshipObject, IntervalsObject> getIntervaledDataRelationship() {
			return myIntervalsRelationship;
		}
		
	}
}
