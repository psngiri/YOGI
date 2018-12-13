package yogi.report.viewer.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.RelationshipObjectImpl;
import yogi.period.frequency.FrequencyManager;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.period.interval.io.IntervalScanner;
import yogi.report.column.ColumnComparator;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnDefinitionImpl;
import yogi.report.column.GroupColumnDefinitionBaseImpl;
import yogi.report.column.GroupColumnDefinitionImpl;
import yogi.report.dated.columns.dow.DayOfWeekColumn;
import yogi.report.dated.columns.month.MonthColumn;
import yogi.report.dated.columns.year.YearColumn;
import yogi.report.dated.group.DatedGroup;
import yogi.report.dated.group.DatedGroupBy;
import yogi.report.dated.group.DatedGroupEvaluator;
import yogi.report.function.average.FloatAverageFunction;
import yogi.report.function.count.CountFunction;
import yogi.report.function.interval.IntervalIntersectionFunction;
import yogi.report.function.sum.IntegerSumFunction;
import yogi.report.template.BaseReportTemplate;
import yogi.report.template.SimpleReportTemplate;
import yogi.report.viewer.ReportViewer;



public class IntervalReportViewerTestCase1
{
	public static void main(String[] args) {
	BaseReportTemplate<Item> reportTemplate = getReportTemplate();
	List<Item> items = getItems();
	new ReportViewer<Item>(items, reportTemplate);
    }
	
	private static BaseReportTemplate<Item> getReportTemplate() {
		SimpleReportTemplate<Item> reportTemplate = new SimpleReportTemplate<Item>();
		ColumnDefinition<Item> column1= new ColumnDefinitionImpl<Item, String>("Key1", 10, new ItemKey1Evaluator(), null, null, null);
		ColumnDefinition<Item> column2= new ColumnDefinitionImpl<Item, String>("Key2", 10, new ItemKey2Evaluator(), null, null, null);
		ColumnDefinition<Item> column3= new ColumnDefinitionImpl<Item, String>("Key3", 10, new ItemKey3Evaluator(), null, null, null);
		ColumnDefinition<Item> column4= new ColumnDefinitionImpl<Item, Integer>("Value1", 10, new ItemValue1Evaluator(), null, new IntegerSumFunction(), null);
		ColumnDefinition<Item> column5= new ColumnDefinitionImpl<Item, Float>("Value2", 10, new ItemValue2Evaluator(), null, new FloatAverageFunction(), null);
		ColumnDefinition<Item> column6= new GroupColumnDefinitionImpl<Item, Integer>("Number of Days", 15, new GroupNumberOfDaysEvaluator(), null, new CountFunction(), null);
		ColumnDefinition<Item> column7= new MonthColumn<Item>();
		ColumnDefinition<Item> column8= new GroupColumnDefinitionImpl<Item, String>("Period", 20, new GroupIntervalEvaluator(), null, null, null);
		ColumnDefinition<Item> column9= new YearColumn<Item>();
//		ColumnDefinition<Item> column10= new DateColumn<Item>();
		ColumnDefinition<Item> column11= new GroupColumnDefinitionBaseImpl<Item, List<Interval>>("Non-Ops", 20, new MyIntervalEvaluator(), null, new IntervalIntersectionFunction(), null, new IntervalListComparator());

//		ColumnDefinition<Item> column10= new DayOfMonthColumn<Item>();
		ColumnDefinition<Item> column10= new DayOfWeekColumn<Item>();
		reportTemplate.addColumn(column1);
		reportTemplate.addColumn(column2);
		reportTemplate.addColumn(column9);
		reportTemplate.addColumn(column7);
		reportTemplate.addColumn(column10);
		reportTemplate.addColumn(column3);
		reportTemplate.addColumn(column4);
		reportTemplate.addColumn(column5);
		reportTemplate.addColumn(column6);
		reportTemplate.addColumn(column8);
		reportTemplate.addColumn(column11);
		reportTemplate.setComparator(new ColumnComparator<Item>(column1, column2, column3));
		DatedGroupBy<Item> datedGroupBya = new DatedGroupBy<Item>();
		datedGroupBya.setIntervalEvaluator(new DatedIntervalEvaluator());
		reportTemplate.addGroupBy(datedGroupBya);
		DatedGroupBy<Item> datedGroupByb = new DatedGroupBy<Item>(datedGroupBya, column1);
		reportTemplate.addGroupBy(datedGroupByb);
		DatedGroupBy<Item> datedGroupBy = new DatedGroupBy<Item>(datedGroupByb, column2);
		reportTemplate.addGroupBy(datedGroupBy);
		DatedGroupBy<Item> datedGroupBy1 = new DatedGroupBy<Item>(datedGroupBy, column9);
		reportTemplate.addGroupBy(datedGroupBy1);
//		DatedGroupBy<Item> datedGroupBy3 = new DatedGroupBy<Item>(column1, column2, column9, column7);
//		datedGroupBy3.setIntervalEvaluator(new DatedIntervalEvaluator());
//		reportTemplate.addGroupBy(datedGroupBy3);
//		DatedGroupBy<Item> datedGroupBy2 = new DatedGroupBy<Item>(column1, column2, column9, column7, column10);
//		datedGroupBy2.setIntervalEvaluator(new DatedIntervalEvaluator());
//		reportTemplate.addGroupBy(datedGroupBy2);
		return reportTemplate;
	}

	private static List<Item> getItems() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("a", "b", "c", 10, 1000.50f));
		items.add(new Item("a", "b", "c1", 11, 1001.50f));
		items.add(new Item("a", "b1", "c", 12, 1002.50f));
		items.add(new Item("a1", "b", "c", 13, 1003.50f));
		items.add(new Item("a", "b2", "c", 14, 1004.50f));
		items.add(new Item("a", "b", "c", 17, 2000.50f));
		items.add(new Item("a", "b", "c1", 11, 4001.50f));
		items.add(new Item("a", "b1", "c", 52, 3002.50f));
		items.add(new Item("a1", "b", "c", 13, 7003.50f));
		items.add(new Item("a", "b2", "c", 84, 8004.50f));
		items.add(new Item("a", "b", "c", 10, 9000.50f));
		items.add(new Item("a", "b", "c1", 12, 1001.50f));
		items.add(new Item("a", "b1", "c", 42, 1002.50f));
		items.add(new Item("a1", "b", "c", 63, 1003.50f));
		items.add(new Item("a", "b2", "c", 84, 1004.50f));
		addIntervals(items);
		return items;
	}

	private static void addIntervals(List<yogi.report.viewer.test.IntervalReportViewerTestCase1.Item> items) {
		IntervalScanner intervalScanner = new IntervalScanner();
		int i = 0;
		for(Item item: items)
		{
			if(i%2 == 0)
			{
				item.add(intervalScanner.scan("14JAN2006 15MAR2007 11111.."));
				item.add(intervalScanner.scan("14APR2006 15MAY2007 111..11"));
			} else
			{
				item.add(intervalScanner.scan("10FEB2007 25MAR2007 .....11"));
				item.add(intervalScanner.scan("10APR2007 25APR2007 11...11"));
			}
			i ++;
		}
	}

	static class Item extends RelationshipObjectImpl implements RelationshipObject
	{
		String key1;
		String key2;
		String key3;
		int value1;
		float value2;
		List<Interval> intervals = new ArrayList<Interval>();
		
		public Item(String key1, String key2, String key3, int value1, float value2) {
			super();
			this.key1 = key1;
			this.key2 = key2;
			this.key3 = key3;
			this.value1 = value1;
			this.value2 = value2;
		}
		
		public void add(Interval interval)
		{
			intervals.add(interval);
		}

		public String getName() {
			return "";
		}

	}
	static class ItemKey1Evaluator implements Evaluator<Item, String>
	{

		public String evaluate(Item item) {
			return item.key1;
		}
		
	}
	static class ItemKey2Evaluator implements Evaluator<Item, String>
	{

		public String evaluate(Item item) {
			return item.key2;
		}
		
	}
	static class ItemKey3Evaluator implements Evaluator<Item, String>
	{

		public String evaluate(Item item) {
			return item.key3;
		}
		
	}
	static class ItemValue1Evaluator implements Evaluator<Item, Integer>
	{

		public Integer evaluate(Item item) {
			return item.value1;
		}
		
	}
	static class ItemValue2Evaluator implements Evaluator<Item, Float>
	{

		public Float evaluate(Item item) {
			return item.value2;
		}
		
	}
	static class GroupIntervalEvaluator extends DatedGroupEvaluator<Item, String>
	{
		public String evaluate(DatedGroup<Item> group, int indexInGroup) {
			List<Interval> intervals = group.getIntervals(indexInGroup);
			if(intervals != null) return intervals.toString();
			return null;
		}

		public String evaluate(Item object) {
			return object.intervals.toString();
		}

	}
	
	static class MyIntervalEvaluator extends DatedGroupEvaluator<Item, List<Interval>>
	{
		Interval interval = new IntervalScanner().scan("14JAN2006 30MAY2007 1111111");
		public List<Interval> evaluate(DatedGroup<Item> group, int indexInGroup) {
			List<Interval> intervals = group.getIntervals(indexInGroup);
			if(intervals == null) intervals = new ArrayList<Interval>(0);
			return nonOps(intervals);
		}

		public List<Interval> evaluate(Item object) {
			return nonOps(object.intervals);
		}

		private List<Interval> nonOps(List<Interval> list) {
			List<Interval> subtract = Intervals.subtract(interval, list);
			List<Interval> rtnValue = new ArrayList<Interval>();
			for(Interval myInterval: subtract)
			{
				if(myInterval.getFrequency() == FrequencyManager.AllDayFrequency) rtnValue.add(myInterval);
			}
			return rtnValue;
		}
	}
	
	static class GroupNumberOfDaysEvaluator extends DatedGroupEvaluator<Item, Integer>
	{
		public Integer evaluate(DatedGroup<Item> group, int indexInGroup) {
			List<Interval> intervals = group.getIntervals(indexInGroup);
			if(intervals != null) return (int)Intervals.getNumberOfDays(intervals);
			return null;
		}

		public Integer evaluate(Item object) {
			return (int)Intervals.getNumberOfDays(object.intervals);
		}
	}
	
	static class IntervalEvaluator implements Evaluator<Item, String>
	{
		public String evaluate(Item object) {
			return object.intervals.toString();
		}
	}
	
	static class DatedIntervalEvaluator implements Evaluator<Item, List<Interval>>
	{
		public List<Interval> evaluate(Item object) {
			return object.intervals;
		}
	}
	
	static class NumberOfDaysEvaluator implements Evaluator<Item, Long>
	{
		public Long evaluate(Item item) {
			return Intervals.getNumberOfDays(item.intervals);
		}
	}

	static class IntervalListComparator implements Comparator<List<Interval>>
	{

		public int compare(List<Interval> o1, List<Interval> o2) {
			if(o1.isEmpty()) return -1;
			if(o2.isEmpty()) return 1;
			return o1.get(0).compareTo(o2.get(0));
		}
		
	}
}

