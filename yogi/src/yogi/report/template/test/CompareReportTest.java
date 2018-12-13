package yogi.report.template.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Selector;
import yogi.base.evaluator.Evaluator;
import yogi.base.io.Formatter;
import yogi.base.io.MultiLineFormatter;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.RelationshipObjectImpl;
import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.period.interval.io.IntervalScanner;
import yogi.report.LineWriter;
import yogi.report.column.ColumnComparator;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnDefinitionImpl;
import yogi.report.column.ColumnGroupBy;
import yogi.report.column.GroupColumnComparator;
import yogi.report.column.GroupColumnDefinitionImpl;
import yogi.report.compare.CompareGroup;
import yogi.report.compare.CompareReportGenerator;
import yogi.report.compare.function.average.FloatAverageCompareFunction;
import yogi.report.compare.function.average.IntegerAverageCompareFunction;
import yogi.report.compare.function.diff.FloatDiffCompareFunction;
import yogi.report.compare.function.diff.IntegerDiffCompareFunction;
import yogi.report.compare.template.CompareReportTemplate;
import yogi.report.compare.template.SimpleCompareReportTemplate;
import yogi.report.compare.template.TemplateCompareGroupFormatterAdapter;
import yogi.report.compare.template.TemplateCompareGroupObjectFormatter;
import yogi.report.compare.template.TemplateCompareGroupTotalFooter;
import yogi.report.compare.template.TemplateCompareGroupTotalFormatter;
import yogi.report.compare.template.TemplateCompareGroupTotalHeader;
import yogi.report.dated.columns.year.YearColumn;
import yogi.report.dated.group.DatedGroup;
import yogi.report.dated.group.DatedGroupBy;
import yogi.report.dated.group.DatedGroupEvaluator;
import yogi.report.function.average.FloatAverageFunction;
import yogi.report.function.count.CountFunction;
import yogi.report.function.sum.IntegerSumFunction;
import yogi.report.function.sum.LongSumFunction;
import yogi.report.group.Group;
import yogi.report.group.GroupMultiplierCalculator;

public class CompareReportTest extends TestCase {
	
	public void testBasic()
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("a", "b", "c", 10, 1000.50f));
		items.add(new Item("a", "b", "c", 12, 1020.50f));
		items.add(new Item("a", "b", "c1", 11, 1001.50f));
		items.add(new Item("a", "b1", "c", 12, 1002.50f));
		items.add(new Item("a1", "b", "c", 13, 1003.50f));
		items.add(new Item("a", "b2", "c", 14, 1004.50f));
		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
		List<Item> items1 = new ArrayList<Item>();
		items1.add(new Item("a", "b", "c", 20, 2000.50f));
		items1.add(new Item("a", "b", "c1", 21, 2001.50f));
		items1.add(new Item("a", "b1a", "c", 22, 2002.50f));
		items1.add(new Item("a1", "b", "c", 23, 2003.50f));
		items1.add(new Item("a", "b2", "c", 24, 2004.50f));
		ImmutableList<Item> immutableItems1 = new ImmutableList<Item>(items1);
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setItems(immutableItems, immutableItems1);
		SimpleCompareReportTemplate<Item> reportTemplate = new SimpleCompareReportTemplate<Item>();
		ColumnDefinition<Item> column1= new ColumnDefinitionImpl<Item, String>("Key1", 10, new ItemKey1Evaluator(), null, null, null);
		ColumnDefinition<Item> column2= new ColumnDefinitionImpl<Item, String>("Key2", 10, new ItemKey2Evaluator(), null, null, null);
		ColumnDefinition<Item> column3= new ColumnDefinitionImpl<Item, String>("Key3", 10, new ItemKey3Evaluator(), null, null, null);
		ColumnDefinition<Item> column4= new ColumnDefinitionImpl<Item, Integer>("Value1", 10, new ItemValue1Evaluator(), null, new IntegerSumFunction(), null);
		ColumnDefinition<Item> column5= new ColumnDefinitionImpl<Item, Float>("Value2", 10, new ItemValue2Evaluator(), null, new FloatAverageFunction(), null);
		reportTemplate.addColumn(column1);
		reportTemplate.addColumn(column2);
		reportTemplate.addColumn(column3);
		reportTemplate.addColumn(column4);
		reportTemplate.addColumn(column5);
		reportTemplate.setNumberOfDataSets(2);
		reportTemplate.setDataSetNames("Curr", "Hist");
		reportTemplate.setCompareFunction(3, new IntegerDiffCompareFunction(0,1));
		reportTemplate.setCompareFunction(4, new FloatDiffCompareFunction(0,1));
		reportTemplate.setCompareFunction(3, new IntegerAverageCompareFunction(0,1));
		reportTemplate.setCompareFunction(4, new FloatAverageCompareFunction(0,1));
		reportGenerator.getReportBuilder().setGroupFormatter(new MyTemplateGroupFormatter(reportTemplate));
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>());
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column1));
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column2));
		reportTemplate.apply(reportGenerator);
		reportGenerator.generateReport();
		for(String line: reportGenerator.getReport()) System.out.println(line);		
		assertEquals(32, reportGenerator.getReport().size());
	}
	
	public void testCompare1()
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
		
		List<Item> items1 = new ArrayList<Item>();
		items1.add(new Item("a", "b", "c", 20, 2000.50f));
		items1.add(new Item("a", "b", "c1", 21, 2001.50f));
		items1.add(new Item("a", "b1", "c", 22, 2002.50f));
		items1.add(new Item("a1", "b", "c", 23, 2003.50f));
		items1.add(new Item("a", "b2", "c", 24, 2004.50f));
		items1.add(new Item("a", "b", "c", 27, 3000.50f));
		items1.add(new Item("a", "b", "c1", 21, 5001.50f));
		items1.add(new Item("a", "b1", "c", 62, 4002.50f));
		items1.add(new Item("a1", "b", "c", 23, 8003.50f));
		items1.add(new Item("a", "b2", "c", 94, 9004.50f));
		items1.add(new Item("a", "b", "c", 20, 10000.50f));
		items1.add(new Item("a", "b", "c1", 22, 2001.50f));
		items1.add(new Item("a", "b1", "c", 52, 2002.50f));
		items1.add(new Item("a1", "b", "c", 73, 2003.50f));
		items1.add(new Item("a", "b2", "c", 94, 2004.50f));
		addIntervals(items1);
		ImmutableList<Item> immutableItems1 = new ImmutableList<Item>(items1);
		
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setItems(immutableItems, immutableItems1);
		SimpleCompareReportTemplate<Item> reportTemplate = new SimpleCompareReportTemplate<Item>();
		reportTemplate.setNumberOfDataSets(2);
		reportTemplate.setDataSetNames("Curr", "Hist");
		reportGenerator.getReportBuilder().setGroupFormatter(new MyTemplateGroupFormatter(reportTemplate));
		ColumnDefinition<Item> column1= new ColumnDefinitionImpl<Item, String>("Key1", 10, new ItemKey1Evaluator(), null, null, null);
		ColumnDefinition<Item> column2= new ColumnDefinitionImpl<Item, String>("Key2", 10, new ItemKey2Evaluator(), null, null, null);
		ColumnDefinition<Item> column3= new ColumnDefinitionImpl<Item, String>("Key3", 10, new ItemKey3Evaluator(), null, null, null);
		ColumnDefinition<Item> column4= new ColumnDefinitionImpl<Item, Integer>("Value1", 10, new ItemValue1Evaluator(), null, new IntegerSumFunction(), null);
		ColumnDefinition<Item> column5= new ColumnDefinitionImpl<Item, Float>("Value2", 10, new ItemValue2Evaluator(), null, new FloatAverageFunction(), null);
		ColumnDefinition<Item> column6= new GroupColumnDefinitionImpl<Item, Integer>("Number of Days", 15, new GroupNumberOfDaysEvaluator(), null, new CountFunction(), null);
		ColumnDefinition<Item> column8= new GroupColumnDefinitionImpl<Item, String>("Period", 20, new GroupIntervalEvaluator(), null, null, null);
		ColumnDefinition<Item> column9= new YearColumn<Item>();

		reportTemplate.addColumn(column1);
		reportTemplate.addColumn(column2);
		reportTemplate.addColumn(column9);
		reportTemplate.addColumn(column3);
		reportTemplate.addColumn(column4);
		reportTemplate.addColumn(column5);
		reportTemplate.addColumn(column6);
		reportTemplate.addColumn(column8);
		reportTemplate.setComparator(new ColumnComparator<Item>(column1, column2, column3));
		DatedGroupBy<Item> datedGroupBy = new DatedGroupBy<Item>();
		datedGroupBy.setIntervalEvaluator(new DatedIntervalEvaluator());
		reportTemplate.addGroupBy(datedGroupBy);
		reportTemplate.addGroupBy(new DatedGroupBy<Item>((DatedGroupBy<Item>)reportTemplate.getLastGroupBy(), column1));
		reportTemplate.addGroupBy(new DatedGroupBy<Item>((DatedGroupBy<Item>)reportTemplate.getLastGroupBy(), column2));
		reportTemplate.addGroupBy(new DatedGroupBy<Item>((DatedGroupBy<Item>)reportTemplate.getLastGroupBy(), column9));
		reportTemplate.apply(reportGenerator);
		reportGenerator.setGroupComparator(new GroupColumnComparator<Item>(column4, column5));
		reportGenerator.setGroupSelector(new YearSelector(column9));
		reportGenerator.generateReport();
		for(String line: reportGenerator.getReport()) System.out.println(line);
		assertEquals(45, reportGenerator.getReport().size());
		
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


	class MyTemplateGroupFormatter extends TemplateCompareGroupFormatterAdapter<Item>
	{

		public MyTemplateGroupFormatter(CompareReportTemplate<Item> reportTemplate) {
			super(reportTemplate);
		}

		@Override
		public MultiLineFormatter<CompareGroup<Item>> getGroupObjectFormatter() {
			return new TemplateCompareGroupObjectFormatter<Item>(getReportTemplate());
		}

		@Override
		public Formatter<CompareGroup<Item>> getGroupTotalFormatter() {
			return new TemplateCompareGroupTotalFormatter<Item>(getReportTemplate());
		}

		@Override
		public MultiLineFormatter<CompareGroup<Item>> getGroupTotalFooter() {
			return new TemplateCompareGroupTotalFooter<Item>(getReportTemplate());
		}

		@Override
		public MultiLineFormatter<CompareGroup<Item>> getGroupTotalHeader() {
			return new TemplateCompareGroupTotalHeader<Item>(getReportTemplate());
		}
		
	}
	static class MyReportGenerator extends CompareReportGenerator<Item>
	{
		MyLineWriter lineWriter = new MyLineWriter();
		public MyReportGenerator() {
			super(null);
			this.getReportBuilder().setLineWriter(lineWriter);
		}

		@Override
		public void setItems(ImmutableList<Item> ... items) {
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
			return getNumberOfDays(intervals);
		}
		private Integer getNumberOfDays(List<Interval> intervals) {
			if(intervals != null) return (int)Intervals.getNumberOfDays(intervals);
			return null;
		}
		public Integer evaluate(Item object) {
			return getNumberOfDays(object.intervals);
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
	
	class NumberOfDaysEvaluator implements Evaluator<Item, Long>
	{
		public Long evaluate(Item item) {
			return Intervals.getNumberOfDays(item.intervals);
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
		
	class MonthMultiplierCalculator implements GroupMultiplierCalculator<Item>
	{

		public int getMultiplier(Group<Item> group, int indexInGroup) {
			
			return (int) Intervals.getNumberOfDays(group.getItem(indexInGroup).intervals);
		}
		
	}
	class MyLongSumFunctionIM extends LongSumFunction
	{

		@Override
		public void process(Long object, int multiplier) {
			super.process(object, 1);
		}
		
	}
	
}
