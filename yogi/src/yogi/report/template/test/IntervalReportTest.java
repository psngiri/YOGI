package yogi.report.template.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Selector;
import yogi.base.evaluator.Evaluator;
import yogi.base.io.MultiLineFormatter;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.RelationshipObjectImpl;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.period.interval.io.IntervalScanner;
import yogi.report.LineWriter;
import yogi.report.ReportGenerator;
import yogi.report.column.ColumnComparator;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnDefinitionImpl;
import yogi.report.column.GroupColumnComparator;
import yogi.report.column.GroupColumnDefinitionImpl;
import yogi.report.dated.columns.year.YearColumn;
import yogi.report.dated.group.DatedGroup;
import yogi.report.dated.group.DatedGroupBy;
import yogi.report.dated.group.DatedGroupEvaluator;
import yogi.report.function.average.FloatAverageFunction;
import yogi.report.function.count.CountFunction;
import yogi.report.function.sum.IntegerSumFunction;
import yogi.report.group.Group;
import yogi.report.template.ReportTemplate;
import yogi.report.template.SimpleReportTemplate;
import yogi.report.template.TemplateGroupFormatterAdapter;
import yogi.report.template.TemplateGroupFormatterImpl;
import yogi.report.template.TemplateGroupObjectFormatter;

public class IntervalReportTest extends TestCase {
	
//	public void testMultiRecordReport()
//	{
//		List<Item> items = new ArrayList<Item>();
//		items.add(new Item("a", "b", "c", 10, 1000.50f));
//		items.add(new Item("a", "b", "c1", 11, 1001.50f));
//		items.add(new Item("a", "b1", "c", 12, 1002.50f));
//		items.add(new Item("a1", "b", "c", 13, 1003.50f));
//		items.add(new Item("a", "b2", "c", 14, 1004.50f));
//		items.add(new Item("a", "b", "c", 17, 2000.50f));
//		items.add(new Item("a", "b", "c1", 11, 4001.50f));
//		items.add(new Item("a", "b1", "c", 52, 3002.50f));
//		items.add(new Item("a1", "b", "c", 13, 7003.50f));
//		items.add(new Item("a", "b2", "c", 84, 8004.50f));
//		items.add(new Item("a", "b", "c", 10, 9000.50f));
//		items.add(new Item("a", "b", "c1", 12, 1001.50f));
//		items.add(new Item("a", "b1", "c", 42, 1002.50f));
//		items.add(new Item("a1", "b", "c", 63, 1003.50f));
//		items.add(new Item("a", "b2", "c", 84, 1004.50f));
//		addIntervals(items);
//		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
//		MyReportGenerator reportGenerator = new MyReportGenerator();
//		reportGenerator.setItems(immutableItems);
//		SimpleReportTemplate<Item> reportTemplate = new SimpleReportTemplate<Item>();
//		ColumnDefinition<Item> column1= new ColumnDefinitionImpl<Item, String>("Key1", 10, new ItemKey1Evaluator(), null, null, null);
//		ColumnDefinition<Item> column2= new ColumnDefinitionImpl<Item, String>("Key2", 10, new ItemKey2Evaluator(), null, null, null);
//		ColumnDefinition<Item> column3= new ColumnDefinitionImpl<Item, String>("Key3", 10, new ItemKey3Evaluator(), null, null, null);
//		ColumnDefinition<Item> column4= new ColumnDefinitionImpl<Item, Integer>("Value1", 10, new ItemValue1Evaluator(), null, new IntegerSumFunction(), null);
//		ColumnDefinition<Item> column5= new ColumnDefinitionImpl<Item, Float>("Value2", 10, new ItemValue2Evaluator(), null, new FloatAverageCompareFunction(), null);
//		ColumnDefinition<Item> column6= new ColumnDefinitionImpl<Item, Long>("Number of Days", 15, new NumberOfDaysEvaluator(), null, new MyLongSumFunctionIM(), null);
//		ColumnDefinition<Item> column7= new ColumnDefinitionImpl<Item, String>("Period", 20, new IntervalEvaluator(), null, null, null);
//		reportTemplate.addColumn(column1);
//		reportTemplate.addColumn(column2);
//		reportTemplate.addColumn(column3);
//		reportTemplate.addColumn(column4);
//		reportTemplate.addColumn(column5);
//		reportTemplate.addColumn(column6);
//		reportTemplate.addColumn(column7);
//		reportTemplate.setComparator(new TemplateComparator<Item>(column1, column2, column3));
//		reportTemplate.addGroupBy(new TemplateGroupBy<Item>());
//		reportTemplate.addGroupBy(new TemplateGroupBy<Item>(column1));
//		TemplateGroupBy<Item> templateGroupBy = new TemplateGroupBy<Item>(column1, column2);
//		templateGroupBy.setMultiplierCalculator(new MyMultiplierCalculator());
//		reportTemplate.addGroupBy(templateGroupBy);
//		reportTemplate.apply(reportGenerator);
//		reportGenerator.generateReport();
////		for(String line: reportGenerator.report) System.out.println(line);
//		assertEquals(36, reportGenerator.report.size());
//	}
	
	public void testGroupByMonth()
	{
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
		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setItems(immutableItems);
		SimpleReportTemplate<Item> reportTemplate = new SimpleReportTemplate<Item>();
		reportGenerator.getReportBuilder().setGroupFormatter(new TemplateGroupFormatterImpl<Item>(reportTemplate));
		ColumnDefinition<Item> column1= new ColumnDefinitionImpl<Item, String>("Key1", 10, new ItemKey1Evaluator(), null, null, null);
		ColumnDefinition<Item> column2= new ColumnDefinitionImpl<Item, String>("Key2", 10, new ItemKey2Evaluator(), null, null, null);
		ColumnDefinition<Item> column3= new ColumnDefinitionImpl<Item, String>("Key3", 10, new ItemKey3Evaluator(), null, null, null);
		ColumnDefinition<Item> column4= new ColumnDefinitionImpl<Item, Integer>("Value1", 10, new ItemValue1Evaluator(), null, new IntegerSumFunction(), null);
		ColumnDefinition<Item> column5= new ColumnDefinitionImpl<Item, Float>("Value2", 10, new ItemValue2Evaluator(), null, new FloatAverageFunction(), null);
		ColumnDefinition<Item> column6= new GroupColumnDefinitionImpl<Item, Integer>("Number of Days", 15, new GroupNumberOfDaysEvaluator(), null, new CountFunction(), null);
//		ColumnDefinition<Item> column7= new MonthColumn<Item>();
		ColumnDefinition<Item> column8= new GroupColumnDefinitionImpl<Item, String>("Period", 20, new GroupIntervalEvaluator(), null, null, null);
		ColumnDefinition<Item> column9= new YearColumn<Item>();
//		ColumnDefinition<Item> column10= new DateColumn<Item>();

//		ColumnDefinition<Item> column10= new DayOfWeekColumn<Item>();
		reportTemplate.addColumn(column1);
		reportTemplate.addColumn(column2);
		reportTemplate.addColumn(column9);
//		reportTemplate.addColumn(column7);
//		reportTemplate.addColumn(column10);
		reportTemplate.addColumn(column3);
		reportTemplate.addColumn(column4);
		reportTemplate.addColumn(column5);
		reportTemplate.addColumn(column6);
		reportTemplate.addColumn(column8);
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
//		DatedGroupBy<Item> datedGroupBy = new DatedGroupBy<Item>(column1, column2, column9, column7);
//		datedGroupBy.setIntervalEvaluator(new DatedIntervalEvaluator());
//		reportTemplate.addGroupBy(datedGroupBy);
//		DatedGroupBy<Item> datedGroupBy2 = new DatedGroupBy<Item>(column1, column2, column9, column7, column10);
//		datedGroupBy2.setIntervalEvaluator(new DatedIntervalEvaluator());
//		reportTemplate.addGroupBy(datedGroupBy2);
		reportTemplate.apply(reportGenerator);
		reportGenerator.setGroupComparator(new GroupColumnComparator<Item>(column4, column5));
		reportGenerator.setGroupSelector(new YearSelector(column9));
		reportGenerator.generateReport();
		for(String line: reportGenerator.getReport()) System.out.println(line);
		assertEquals(44, reportGenerator.getReport().size());
		
	}

	static class YearSelector implements Selector<Group<Item>>
	{
		ColumnDefinition<Item> yearColumn;
		
		public YearSelector(ColumnDefinition<Item> yearColumn) {
			super();
			this.yearColumn = yearColumn;
		}

		public boolean select(Group<Item> group) {
			Integer value = (Integer) group.getValues()[yearColumn.getIndex()];
			if(value == null) return true;
			return value == 2007;
		}
		
	}
	private void addIntervals(List<Item> items) {
		IntervalScanner intervalScanner = new IntervalScanner();
		int i = 0;
		for(Item item: items)
		{
			if(i%2 == 0)
				item.add(intervalScanner.scan("14JAN2006 15MAR2007 11111.."));
			else
				item.add(intervalScanner.scan("10FEB2007 25MAR2007 .....11"));
			i ++;
		}
	}


	class MyTemplateGroupFormatter extends TemplateGroupFormatterAdapter<Item>
	{

		public MyTemplateGroupFormatter(ReportTemplate<Item> reportTemplate) {
			super(reportTemplate);
		}

		@Override
		public MultiLineFormatter<Group<Item>> getGroupObjectFormatter() {
			return new TemplateGroupObjectFormatter<Item>(getReportTemplate());
		}
		
	}
	static class MyReportGenerator extends ReportGenerator<Item>
	{
		MyLineWriter lineWriter = new MyLineWriter();
		public MyReportGenerator() {
			super(null);
			this.getReportBuilder().setLineWriter(lineWriter);
		}

		@Override
		public void setItems(ImmutableList<Item> items) {
			super.setItems(items);
		}
			
		public List<String> getReport() {
			return lineWriter.report;
		}

		class MyLineWriter implements LineWriter
		{

			List<String> report = new ArrayList<String>();
			public void writeLine(String string) {
				report.add(string);
			}
			
		}
	}
	
	class GroupIntervalEvaluator extends DatedGroupEvaluator<Item, String>
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
	
	class GroupNumberOfDaysEvaluator extends DatedGroupEvaluator<Item, Integer>
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
	
	class IntervalEvaluator implements Evaluator<Item, String>
	{
		public String evaluate(Item object) {
			return object.intervals.toString();
		}
	}
	
	class DatedIntervalEvaluator implements Evaluator<Item, List<Interval>>
	{
		public List<Interval> evaluate(Item object) {
			return object.intervals;
		}
	}
	
	class Item extends RelationshipObjectImpl implements RelationshipObject
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
	
	class ItemKey1Evaluator implements Evaluator<Item, String>
	{

		public String evaluate(Item item) {
			return item.key1;
		}
		
	}
	class ItemKey2Evaluator implements Evaluator<Item, String>
	{

		public String evaluate(Item item) {
			return item.key2;
		}
		
	}
	class ItemKey3Evaluator implements Evaluator<Item, String>
	{

		public String evaluate(Item item) {
			return item.key3;
		}
		
	}
	class ItemValue1Evaluator implements Evaluator<Item, Integer>
	{

		public Integer evaluate(Item item) {
			return item.value1;
		}
		
	}
	class ItemValue2Evaluator implements Evaluator<Item, Float>
	{

		public Float evaluate(Item item) {
			return item.value2;
		}
		
	}
	
}
