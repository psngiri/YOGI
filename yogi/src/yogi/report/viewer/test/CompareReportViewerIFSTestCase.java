package yogi.report.viewer.test;

import java.util.ArrayList;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnDefinitionImpl;
import yogi.report.column.ColumnGroupBy;
import yogi.report.compare.function.average.FloatAverageCompareFunction;
import yogi.report.compare.function.average.IntegerAverageCompareFunction;
import yogi.report.compare.function.diff.FloatDiffCompareFunction;
import yogi.report.compare.function.diff.IntegerDiffCompareFunction;
import yogi.report.compare.template.BaseCompareReportTemplate;
import yogi.report.compare.template.SimpleCompareReportTemplate;
import yogi.report.function.average.FloatAverageFunction;
import yogi.report.function.sum.IntegerSumFunction;
import yogi.report.viewer.compare.CompareReportViewer;


public class CompareReportViewerIFSTestCase
{
	public static void main(String[] args) {
	BaseCompareReportTemplate<Item> reportTemplate = getReportTemplate();
	List<Item>[] items = getItems();
	new CompareReportViewer<Item>(reportTemplate, items);
    }
	
	private static BaseCompareReportTemplate<Item> getReportTemplate() {
		SimpleCompareReportTemplate<Item> reportTemplate = new SimpleCompareReportTemplate<Item>();
		ColumnDefinition<Item> column1= new ColumnDefinitionImpl<Item, String>("POO", 10, new ItemKey1Evaluator(), null, null, null);
		ColumnDefinition<Item> column2= new ColumnDefinitionImpl<Item, String>("OD", 10, new ItemKey2Evaluator(), null, null, null);
		ColumnDefinition<Item> column3= new ColumnDefinitionImpl<Item, String>("Carrier", 10, new ItemKey3Evaluator(), null, null, null);
		ColumnDefinition<Item> column4= new ColumnDefinitionImpl<Item, Integer>("Revenue", 10, new ItemValue1Evaluator(), null, new IntegerSumFunction(), null);
		ColumnDefinition<Item> column5= new ColumnDefinitionImpl<Item, Float>("Market Share", 10, new ItemValue2Evaluator(), null, new FloatAverageFunction(), null);
		reportTemplate.addColumn(column1);
		reportTemplate.addColumn(column2);
		reportTemplate.addColumn(column3);
		reportTemplate.addColumn(column4);
		reportTemplate.addColumn(column5);
		reportTemplate.setNumberOfDataSets(2);
		reportTemplate.setDataSetNames("Curr", "Base");
		reportTemplate.setCompareFunction(3, new IntegerDiffCompareFunction(0,1));
		reportTemplate.setCompareFunction(4, new FloatDiffCompareFunction(0,1));
		reportTemplate.setCompareFunction(3, new IntegerAverageCompareFunction(0,1));
		reportTemplate.setCompareFunction(4, new FloatAverageCompareFunction(0,1));
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>());
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column1));
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column2));
		reportTemplate.addGroupBy(new ColumnGroupBy<Item>(reportTemplate.getLastGroupBy(), column3));
		return reportTemplate;
	}

	@SuppressWarnings("unchecked")
	private static List<Item>[] getItems() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("TPA", "TPASYD", "AA", 100, 10.50f));
		items.add(new Item("TPA", "TPALON", "AA", 120, 20.50f));
		items.add(new Item("TPA", "TPALON", "BA", 110, 5.50f));
		items.add(new Item("DFW", "DFWSYD", "AA", 126, 22.50f));
		items.add(new Item("DFW", "DFWSYD", "QA", 135, 30.50f));
		items.add(new Item("DFW", "DFWLON", "AA", 140, 44.50f));
		items.add(new Item("DFW", "DFWLON", "BA", 120, 34.50f));
		items.add(new Item("DFW", "DFWLON", "CA", 120, 34.50f));
		List<Item> items1 = new ArrayList<Item>();
		items1.add(new Item("TPA", "TPASYD", "AA", 90, 8.50f));
		items1.add(new Item("TPA", "TPALON", "AA", 140, 25.50f));
		items1.add(new Item("TPA", "TPALON", "BA", 117, 6.50f));
		items1.add(new Item("DFW", "DFWSYD", "AA", 136, 25.50f));
		items1.add(new Item("DFW", "DFWSYD", "QA", 145, 34.50f));
		items1.add(new Item("DFW", "DFWLON", "AA", 120, 24.50f));
		items1.add(new Item("DFW", "DFWLON", "BA", 140, 44.50f));
		List<Item>[] rtnValue = new ArrayList[2];
		rtnValue[0] = items;
		rtnValue[1] = items1;
		return rtnValue;
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

