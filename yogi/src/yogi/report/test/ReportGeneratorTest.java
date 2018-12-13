package yogi.report.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Selector;
import yogi.base.io.Formatter;
import yogi.base.io.MultiLineFormatter;
import yogi.base.util.immutable.ImmutableList;
import yogi.report.GroupFormatterAdapter;
import yogi.report.GroupObjectFormatter;
import yogi.report.Header;
import yogi.report.LineWriter;
import yogi.report.ReportGenerator;
import yogi.report.column.ColumnDefinition;
import yogi.report.function.average.FloatAverageFunction;
import yogi.report.function.sum.IntegerSumFunction;
import yogi.report.group.BaseGroupBy;
import yogi.report.group.Group;
import yogi.report.group.GroupBy;
import yogi.report.group.GroupProcessor;

public class ReportGeneratorTest extends TestCase {

	public void testBasic()
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("a", "b", "c", 10, 1000.50f));
		items.add(new Item("a", "b", "c1", 11, 1001.50f));
		items.add(new Item("a", "b1", "c", 12, 1002.50f));
		items.add(new Item("a1", "b", "c", 13, 1003.50f));
		items.add(new Item("a", "b2", "c", 14, 1004.50f));
		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setGroupProcessor(new MyGroupProcessor());
		reportGenerator.setItems(immutableItems);
		reportGenerator.getReportBuilder().setGroupFormatter(new GroupFormatterAdapter<Group<Item>>(){
			MyGroupObjectFormatter formatter = new MyGroupObjectFormatter();

			@Override
			public MultiLineFormatter<Group<Item>> getGroupObjectFormatter() {
				return formatter;
			}
			
		});
		reportGenerator.generateReport();
		assertEquals("a b c 10 1000.5", reportGenerator.getReport().get(0));
		assertEquals("a b c1 11 1001.5", reportGenerator.getReport().get(1));
		assertEquals("a b1 c 12 1002.5", reportGenerator.getReport().get(2));
		assertEquals("a1 b c 13 1003.5", reportGenerator.getReport().get(3));
		assertEquals("a b2 c 14 1004.5", reportGenerator.getReport().get(4));
	}
	
	public void testSelector()
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("a", "b", "c", 10, 1000.50f));
		items.add(new Item("a", "b", "c1", 11, 1001.50f));
		items.add(new Item("a", "b1", "c", 12, 1002.50f));
		items.add(new Item("a1", "b", "c", 13, 1003.50f));
		items.add(new Item("a", "b2", "c", 14, 1004.50f));
		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setItems(immutableItems);
		reportGenerator.setGroupProcessor(new MyGroupProcessor());
		reportGenerator.getReportBuilder().setGroupFormatter(new GroupFormatterAdapter<Group<Item>>(){
			MyGroupObjectFormatter formatter = new MyGroupObjectFormatter();
			@Override
			public MultiLineFormatter<Group<Item>> getGroupObjectFormatter() {
				return formatter;
			}
			
		});
		reportGenerator.setSelector(new Selector<Item>(){
			public boolean select(Item item) {
				return item.key1.equals("a") && item.key2.equals("b");
			}
		});
		reportGenerator.generateReport();
		assertEquals(2, reportGenerator.getReport().size());
		assertEquals("a b c 10 1000.5", reportGenerator.getReport().get(0));
		assertEquals("a b c1 11 1001.5", reportGenerator.getReport().get(1));
	}

	public void testComparator()
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("a", "b", "c", 10, 1000.50f));
		items.add(new Item("a", "b", "c1", 11, 1001.50f));
		items.add(new Item("a", "b1", "c", 12, 1002.50f));
		items.add(new Item("a1", "b", "c", 13, 1003.50f));
		items.add(new Item("a", "b2", "c", 14, 1004.50f));
		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setGroupProcessor(new MyGroupProcessor());
		reportGenerator.setItems(immutableItems);
		reportGenerator.getReportBuilder().setGroupFormatter(new GroupFormatterAdapter<Group<Item>>(){
			MyGroupObjectFormatter formatter = new MyGroupObjectFormatter();
			@Override
			public MultiLineFormatter<Group<Item>> getGroupObjectFormatter() {
				return formatter;
			}
			
		});
		reportGenerator.setComparator(new MyComparator());
		reportGenerator.generateReport();
		assertEquals("a b c 10 1000.5", reportGenerator.getReport().get(0));
		assertEquals("a b c1 11 1001.5", reportGenerator.getReport().get(1));
		assertEquals("a b1 c 12 1002.5", reportGenerator.getReport().get(2));
		assertEquals("a b2 c 14 1004.5", reportGenerator.getReport().get(3));
		assertEquals("a1 b c 13 1003.5", reportGenerator.getReport().get(4));
	}
	
	public void testGroupReport()
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
		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setItems(immutableItems);
		reportGenerator.setGroupProcessor(new MyGroupProcessor());
		reportGenerator.getReportBuilder().setGroupFormatter(new GroupFormatterAdapter<Group<Item>>(){
			MyReportGroupObjectFormatter formatter = new MyReportGroupObjectFormatter();
			MyGroupTotalFormatter totalFormatter = new MyGroupTotalFormatter();
			MyGroupTotalHeader totalHeader = new MyGroupTotalHeader();
			MyGroupTotalFooter totalFooter = new MyGroupTotalFooter();
			@Override
			public MultiLineFormatter<Group<Item>> getGroupObjectFormatter() {
				return formatter;
			}
			@Override
			public Formatter<Group<Item>> getGroupTotalFormatter() {
				return totalFormatter;
			}
			@Override
			public MultiLineFormatter<Group<Item>> getGroupTotalFooter() {
				return totalFooter;
			}
			@Override
			public MultiLineFormatter<Group<Item>> getGroupTotalHeader() {
				return totalHeader;
			}
			
		});
		reportGenerator.setReportHeader(new MyReportHeader());
		reportGenerator.setComparator(new MyComparator());
		List<GroupBy<Item>> groupBys = new ArrayList<GroupBy<Item>>();
		groupBys.add(new TotalGroupBy());
		groupBys.add(new MyOtherGroupBy());
		groupBys.add(new MyGroupBy());
		reportGenerator.setGroupBys(groupBys);
		reportGenerator.generateReport();
		for(String line: reportGenerator.getReport()) System.out.println(line);
		assertEquals(35, reportGenerator.getReport().size());
	}
	
	class MyReportHeader implements Header
	{
		List<String> header;
		
		public MyReportHeader() {
			super();
			header = new ArrayList<String>();
			header.add("Key1 Key2 Key3 Value1 Value2");
			header.add("____________________________");
		}

		public List<String> getHeader() {
			return header;
		}
		
	}
	class MyGroupTotalHeader implements MultiLineFormatter<Group<Item>>
	{
		List<String> totalHeader;
		
		public MyGroupTotalHeader() {
			super();
			totalHeader = new ArrayList<String>();
			totalHeader.add("____________________________");
		}

		public List<String> format(Group<Item> group) {
			if(group.getGroupBy() == null) return null;
			return totalHeader;
		}
		
	}

	class MyGroupTotalFooter implements MultiLineFormatter<Group<Item>>
	{
		List<String> totalHeader;
		
		public MyGroupTotalFooter() {
			super();
			totalHeader = new ArrayList<String>();
			totalHeader.add("____________________________");
		}

		public List<String> format(Group<Item> group) {
			return totalHeader;
		}
		
	}
	
	class MyGroupProcessor implements GroupProcessor<Item>
	{
		IntegerSumFunction value1Function = new IntegerSumFunction();
		FloatAverageFunction value2Function = new FloatAverageFunction();
		
		public void reset() {
			value1Function.reset();
			value2Function.reset();
		}

		public void processObject(Group<Item> group, int indexInGroup, int multiplier) {
			Item item = group.getItem(indexInGroup);
			value1Function.process(item.value1, multiplier);
			value2Function.process(item.value2, multiplier);
		}
		
		public Object[] getValues(Group<Item> group) {
			Object[] rtnValue = new Object[5];
			GroupBy<Item> groupBy = group.getGroupBy();
			if(groupBy == null) return rtnValue;
			Item item = group.getItems().get(group.getStartIndex());
			if(groupBy.hasKey("key1"))
			{
				rtnValue[0] = item.key1;
			}
			if(groupBy.hasKey("key2"))
			{
				rtnValue[1] = item.key2;
			}
			if(groupBy.hasKey("key3"))
			{
				rtnValue[2] = item.key3;
			}
			rtnValue[3] = value1Function.getValue();
			rtnValue[4] = value2Function.getValue();
			return rtnValue;
		}
		
	}
	class MyGroupTotalFormatter implements Formatter<Group<Item>>
	{
		public String format(Group<Item> group) {
			Object key1 = "*";
			Object key2 = "*";
			Object key3 = "*";
			Object[] values = group.getValues();
			boolean keyed = false;
			if(values[0] != null)
			{
				key1 = values[0];
				keyed = true;
			}
			if(values[1] != null)
			{
				key2 = values[1];
				keyed = true;
			}
			if(values[2] != null)
			{
				key3 = values[2];
				keyed = true;
			}
			if(keyed)
			{
				return String.format("%4s %4s %4s %6s %6s", key1, key2, key3,
						values[3], values[4]);
			}else
			{
				return String.format("  Total:       %6s %6s",
						values[3], values[4]);
			}
		}

	}
	class TotalGroupBy extends BaseGroupBy<Item>
	{


		public List<ColumnDefinition<Item>> getColumns() {
			return null;
		}

		public int compare(Item row1, Item row2) {
			return 0;
		}
		
	}
	class MyGroupBy extends BaseGroupBy<Item>
	{
		public MyGroupBy() {
			super();
			addKey("key1");
			addKey("key2");
		}

		public int compare(Item o1, Item o2) {
			return (o1.key1.equals(o2.key1) && o1.key2.equals(o2.key2))?0:1;
		}

		public List<ColumnDefinition<Item>> getColumns() {
			return null;
		}
	}
	class MyOtherGroupBy extends  BaseGroupBy<Item>
	{
		public MyOtherGroupBy() {
			super();
			addKey("key1");
		}


		public int compare(Item o1, Item o2) {
			return (o1.key1.equals(o2.key1))?0:1;
		}


		public List<ColumnDefinition<Item>> getColumns() {
			return null;
		}

	}
	class MyComparator implements Comparator<Item>
	{

		public int compare(Item o1, Item o2) {
			int rtnValue = o1.key1.compareTo(o2.key1);
			if(rtnValue != 0) return rtnValue;
			rtnValue = o1.key2.compareTo(o2.key2);
			if(rtnValue != 0) return rtnValue;
			rtnValue = o1.key3.compareTo(o2.key3);
			return rtnValue;
		}
		
	}
	class MyReportGroupObjectFormatter extends GroupObjectFormatter<Group<Item>>
	{

		public String format(Group<Item> group, int indexInGroup) {
			Item object = group.getItem(indexInGroup);
			String[] keys = new String[3];
			keys[0] = object.key1;
			keys[1] = object.key2;
			keys[2] = object.key3;
			GroupBy<Item> groupBy = group.getGroupBy();
			if(groupBy.hasKey("key1")) keys[0] = "";
			if(groupBy.hasKey("key2")) keys[1] = "";
			if(groupBy.hasKey("key3")) keys[2] = "";
			return String.format("%4s %4s %4s %6s %6s", keys[0], keys[1], keys[2],
					object.value1, object.value2);
		}
		
	}
	class MyGroupObjectFormatter extends GroupObjectFormatter<Group<Item>>
	{

		public String format(Group<Item> group, int indexInGroup) {
			Item object = group.getItem(indexInGroup);
			return String.format("%s %s %s %s %s", object.key1, object.key2, object.key3,
					object.value1, object.value2);
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
	
	
	class Item
	{
		String key1;
		String key2;
		String key3;
		int value1;
		float value2;
		public Item(String key1, String key2, String key3, int value1, float value2) {
			super();
			this.key1 = key1;
			this.key2 = key2;
			this.key3 = key3;
			this.value1 = value1;
			this.value2 = value2;
		}
	}
}
