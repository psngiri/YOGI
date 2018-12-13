package yogi.report.split.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Selector;
import yogi.base.evaluator.Evaluator;
import yogi.base.io.MultiLineFormatter;
import yogi.base.util.immutable.ImmutableList;
import yogi.report.LineWriter;
import yogi.report.ReportGenerator;
import yogi.report.column.ColumnComparator;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnDefinitionImpl;
import yogi.report.column.ColumnGroupBy;
import yogi.report.column.ColumnSelector;
import yogi.report.column.GroupColumnSelector;
import yogi.report.column.serial.number.SerialNumberColumn;
import yogi.report.condition.EqualsCondition;
import yogi.report.condition.LessThanNumberCondition;
import yogi.report.function.average.FloatAverageFunction;
import yogi.report.function.sum.IntegerSumFunction;
import yogi.report.group.Group;
import yogi.report.split.Splitter;
import yogi.report.template.ReportTemplate;
import yogi.report.template.SimpleReportTemplate;
import yogi.report.template.TemplateGroupFormatterAdapter;
import yogi.report.template.TemplateGroupFormatterImpl;
import yogi.report.template.TemplateGroupObjectFormatter;

public class SplitReportTemplateTest extends TestCase {

	
	public void testGroupReport()
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("a", "b,b1", "c", 10, 1000.50f));
		items.add(new Item("a", "b", "c1", 11, 1001.50f));
		items.add(new Item("a", "b1,b2", "c", 12, 1002.50f));
		items.add(new Item("a1", "b", "c", 13, 1003.50f));
		items.add(new Item("a", "b2", "c", 14, 1004.50f));
		items.add(new Item("a", "b,b2", "c", 17, 2000.50f));
		items.add(new Item("a", "b", "c1", 11, 4001.50f));
		items.add(new Item("a", "b1", "c", 52, 3002.50f));
		items.add(new Item("a1", "b,b1", "c", 13, 7003.50f));
		items.add(new Item("a", "b2", "c", 84, 8004.50f));
		items.add(new Item("a", "b,b2", "c", 10, 9000.50f));
		items.add(new Item("a", "b", "c1", 12, 1001.50f));
		items.add(new Item("a", "b1,b2", "c", 42, 1002.50f));
		items.add(new Item("a1", "b", "c", 63, 1003.50f));
		items.add(new Item("a", "b2", "c", 84, 1004.50f));
		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setItems(immutableItems);
		SimpleReportTemplate<Item> reportTemplate = new SimpleReportTemplate<Item>();
		reportGenerator.getReportBuilder().setGroupFormatter(new TemplateGroupFormatterImpl<Item>(reportTemplate));
		ColumnDefinition<Item> column1= new ColumnDefinitionImpl<Item, String>("Key1", 10, new ItemKey1Evaluator(), null, null, null);
		ColumnDefinitionImpl<Item, String> column2= new ColumnDefinitionImpl<Item, String>("Key2", 10, new ItemKey2Evaluator(), null, null, null);
		ColumnDefinition<Item> column3= new ColumnDefinitionImpl<Item, String>("Key3", 10, new ItemKey3Evaluator(), null, null, null);
		ColumnDefinition<Item> column4= new ColumnDefinitionImpl<Item, Integer>("Value1", 10, new ItemValue1Evaluator(), null, new IntegerSumFunction(), null);
		ColumnDefinition<Item> column5= new ColumnDefinitionImpl<Item, Float>("Value2", 10, new ItemValue2Evaluator(), null, new FloatAverageFunction(), null);
		column2.setSplitter(new MySplitter());
		reportTemplate.addColumn(column1);
		reportTemplate.addColumn(column2);
		reportTemplate.addColumn(column3);
		reportTemplate.addColumn(column4);
		reportTemplate.addColumn(column5);
		reportTemplate.setComparator(new ColumnComparator<Item>(column1, column2, column3));
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>());
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column1));
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column2));
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column3));
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(column1, column2));
		reportTemplate.apply(reportGenerator);
		reportGenerator.generateReport();
		for(String line: reportGenerator.getReport()) System.out.println(line);
//		assertEquals(36, reportGenerator.getReport().size());
	}
	
	public void testGroupReport1()
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("a", "b,b1", "c", 10, 1000.50f));
		items.add(new Item("a", "b", "c1", 11, 1001.50f));
		items.add(new Item("a", "b1,b2", "c", 12, 1002.50f));
		items.add(new Item("a1", "b", "c", 13, 1003.50f));
		items.add(new Item("a", "b2", "c", 14, 1004.50f));
		items.add(new Item("a", "b,b2", "c", 17, 2000.50f));
		items.add(new Item("a", "b", "c1", 11, 4001.50f));
		items.add(new Item("a", "b1", "c", 52, 3002.50f));
		items.add(new Item("a1", "b,b1", "c", 13, 7003.50f));
		items.add(new Item("a", "b2", "c", 84, 8004.50f));
		items.add(new Item("a", "b,b2", "c", 10, 9000.50f));
		items.add(new Item("a", "b", "c1", 12, 1001.50f));
		items.add(new Item("a", "b1,b2", "c", 42, 1002.50f));
		items.add(new Item("a1", "b", "c", 63, 1003.50f));
		items.add(new Item("a", "b2", "c", 84, 1004.50f));
		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setItems(immutableItems);
		SimpleReportTemplate<Item> reportTemplate = new SimpleReportTemplate<Item>();
		reportGenerator.getReportBuilder().setGroupFormatter(new TemplateGroupFormatterImpl<Item>(reportTemplate));
		ColumnDefinition<Item> column1= new ColumnDefinitionImpl<Item, String>("Key1", 10, new ItemKey1Evaluator(), null, null, null);
		ColumnDefinitionImpl<Item, String> column2= new ColumnDefinitionImpl<Item, String>("Key2", 10, new ItemKey2Evaluator(), null, null, null);
		ColumnDefinition<Item> column3= new ColumnDefinitionImpl<Item, String>("Key3", 10, new ItemKey3Evaluator(), null, null, null);
		ColumnDefinition<Item> column4= new ColumnDefinitionImpl<Item, Integer>("Value1", 10, new ItemValue1Evaluator(), null, new IntegerSumFunction(), null);
		ColumnDefinition<Item> column5= new ColumnDefinitionImpl<Item, Float>("Value2", 10, new ItemValue2Evaluator(), null, new FloatAverageFunction(), null);
		column2.setSplitter(new MySplitter());
		reportTemplate.addColumn(column1);
		reportTemplate.addColumn(column2);
		reportTemplate.addColumn(column3);
		reportTemplate.addColumn(column4);
		reportTemplate.addColumn(column5);
//		reportTemplate.setComparator(new ColumnComparator<Item>(column1, column2, column3));
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>());
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column1));
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column2));
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column3));
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(column1, column2));
		reportTemplate.apply(reportGenerator);
		reportGenerator.generateReport();
		for(String line: reportGenerator.getReport()) System.out.println(line);
//		assertEquals(36, reportGenerator.getReport().size());
	}
	
	public void testGroupReport2()
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("a", "b,b1", "c", 10, 1000.50f));
		items.add(new Item("a", "b", "c1", 11, 1001.50f));
		items.add(new Item("a", "b1,b2", "c", 12, 1002.50f));
		items.add(new Item("a1", "b", "c", 13, 1003.50f));
		items.add(new Item("a", "b2", "c", 14, 1004.50f));
		items.add(new Item("a", "b,b2", "c", 17, 2000.50f));
		items.add(new Item("a", "b", "c1", 11, 4001.50f));
		items.add(new Item("a", "b1", "c", 52, 3002.50f));
		items.add(new Item("a1", "b,b1", "c", 13, 7003.50f));
		items.add(new Item("a", "b2", "c", 84, 8004.50f));
		items.add(new Item("a", "b,b2", "c", 10, 9000.50f));
		items.add(new Item("a", "b", "c1", 12, 1001.50f));
		items.add(new Item("a", "b1,b2", "c", 42, 1002.50f));
		items.add(new Item("a1", "b", "c", 63, 1003.50f));
		items.add(new Item("a", "b2", "c", 84, 1004.50f));
		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setItems(immutableItems);
		SimpleReportTemplate<Item> reportTemplate = new SimpleReportTemplate<Item>();
		reportGenerator.getReportBuilder().setGroupFormatter(new TemplateGroupFormatterImpl<Item>(reportTemplate));
		ColumnDefinition<Item> column1= new ColumnDefinitionImpl<Item, String>("Key1", 10, new ItemKey1Evaluator(), null, null, null);
		ColumnDefinitionImpl<Item, String> column2= new ColumnDefinitionImpl<Item, String>("Key2", 10, new ItemKey2Evaluator(), null, null, null);
		ColumnDefinition<Item> column3= new ColumnDefinitionImpl<Item, String>("Key3", 10, new ItemKey3Evaluator(), null, null, null);
		ColumnDefinition<Item> column4= new ColumnDefinitionImpl<Item, Integer>("Value1", 10, new ItemValue1Evaluator(), null, new IntegerSumFunction(), null);
		ColumnDefinition<Item> column5= new ColumnDefinitionImpl<Item, Float>("Value2", 10, new ItemValue2Evaluator(), null, new FloatAverageFunction(), null);
		column2.setSplitter(new MySplitter());
		reportTemplate.addColumn(column1);
		reportTemplate.addColumn(column2);
		reportTemplate.addColumn(column3);
		reportTemplate.addColumn(column4);
		reportTemplate.addColumn(column5);
		reportTemplate.setComparator(new ColumnComparator<Item>(column1, column2, column3));
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>());
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column1));
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column2));
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column3));
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(column1, column2));
		reportTemplate.apply(reportGenerator);
		reportGenerator.generateReport();
		for(String line: reportGenerator.getReport()) System.out.println(line);
//		assertEquals(36, reportGenerator.getReport().size());
	}
	public void testGroupReport3()
	{
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("a", "b,b1", "c", 10, 1000.50f));
		items.add(new Item("a", "b", "c1", 11, 1001.50f));
		items.add(new Item("a", "b1,b2", "c", 12, 1002.50f));
		items.add(new Item("a1", "b", "c", 13, 1003.50f));
		items.add(new Item("a", "b2", "c", 14, 1004.50f));
		items.add(new Item("a", "b,b2", "c", 17, 2000.50f));
		items.add(new Item("a", "b", "c1", 11, 4001.50f));
		items.add(new Item("a", "b1", "c", 52, 3002.50f));
		items.add(new Item("a1", "b,b1", "c", 13, 7003.50f));
		items.add(new Item("a", "b2", "c", 84, 8004.50f));
		items.add(new Item("a", "b,b2", "c", 10, 9000.50f));
		items.add(new Item("a", "b", "c1", 12, 1001.50f));
		items.add(new Item("a", "b1,b2", "c", 42, 1002.50f));
		items.add(new Item("a1", "b", "c", 63, 1003.50f));
		items.add(new Item("a", "b2", "c", 84, 1004.50f));
		ImmutableList<Item> immutableItems = new ImmutableList<Item>(items);
		MyReportGenerator reportGenerator = new MyReportGenerator();
		reportGenerator.setItems(immutableItems);
		SimpleReportTemplate<Item> reportTemplate = new SimpleReportTemplate<Item>();
		reportGenerator.getReportBuilder().setGroupFormatter(new TemplateGroupFormatterImpl<Item>(reportTemplate));
		ColumnDefinition<Item> column1= new ColumnDefinitionImpl<Item, String>("Key1", 10, new ItemKey1Evaluator(), null, null, null);
		ColumnDefinitionImpl<Item, String> column2= new ColumnDefinitionImpl<Item, String>("Key2", 10, new ItemKey2Evaluator(), null, null, null);
		ColumnDefinition<Item> column3= new ColumnDefinitionImpl<Item, String>("Key3", 10, new ItemKey3Evaluator(), null, null, null);
		ColumnDefinition<Item> column4= new ColumnDefinitionImpl<Item, Integer>("Value1", 10, new ItemValue1Evaluator(), null, new IntegerSumFunction(), null);
		ColumnDefinition<Item> column5= new ColumnDefinitionImpl<Item, Float>("Value2", 10, new ItemValue2Evaluator(), null, new FloatAverageFunction(), null);
		column2.setSplitter(new MySplitter());
		reportTemplate.addColumn(column1);
		reportTemplate.addColumn(column2);
		reportTemplate.addColumn(column3);
		reportTemplate.addColumn(column4);
		reportTemplate.addColumn(column5);
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(column1, column2, column3));
		reportTemplate.apply(reportGenerator);
		reportGenerator.generateReport();
		for(String line: reportGenerator.getReport()) System.out.println(line);
//		assertEquals(36, reportGenerator.getReport().size());
	}
	static class MySplitter implements Splitter{

		@Override
		public Comparable[] split(Object value) {
			String data = (String) value;
			return data.split(",");
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
