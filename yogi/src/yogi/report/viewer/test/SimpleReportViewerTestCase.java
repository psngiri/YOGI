package yogi.report.viewer.test;

import java.util.ArrayList;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.report.column.ColumnComparator;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnDefinitionImpl;
import yogi.report.function.average.FloatAverageFunction;
import yogi.report.function.sum.IntegerSumFunction;
import yogi.report.template.BaseReportTemplate;
import yogi.report.template.SimpleReportTemplate;
import yogi.report.viewer.ReportViewer;


public class SimpleReportViewerTestCase
{
	public static void main(String[] args) {
	BaseReportTemplate<Item> reportTemplate = getReportTemplate();
	List<Item> items = getItems();
	new ReportViewer<Item>(items, reportTemplate);
    }
	
	private static BaseReportTemplate<Item> getReportTemplate() {
		BaseReportTemplate<Item> reportTemplate = new SimpleReportTemplate<Item>();
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
		reportTemplate.setComparator(new ColumnComparator<Item>(column1, column2, column3));
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>());
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column1));
//		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column2));
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
		return items;
	}

	static class Item
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
}

