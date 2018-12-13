package yogi.report.viewer.compare;

import java.util.List;

import yogi.base.util.node.Node;
import yogi.report.compare.CompareGroup;
import yogi.report.compare.CompareGroupGenerator;
import yogi.report.compare.CompareItem;
import yogi.report.compare.CompareItemGenerator;
import yogi.report.compare.KeyColumnComparator;
import yogi.report.compare.template.BaseCompareReportTemplate;

public class CompareReportDataGenerator<T> {
	private BaseCompareReportTemplate<T> reportTemplate;
	private CompareGroupGenerator<T> compareGroupGenerator;
	
	public CompareReportDataGenerator(BaseCompareReportTemplate<T> reportTemplate, CompareGroupGenerator<T> compareGroupGenerator) {
		this.reportTemplate = reportTemplate;
		this.compareGroupGenerator = compareGroupGenerator;
	}

	public void generateReportData(int groupIndex, Node<CompareReportData<T>> node) {
		CompareReportData<T> compareReportData = node.getData();
		List<CompareGroup<T>> compareGroups = compareGroupGenerator.generateCompareGroups(groupIndex, compareReportData.getCompareGroup());
		for(CompareGroup<T> compareGroup: compareGroups)
		{
			new Node<CompareReportData<T>>(node, new CompareGroupReportData<T>(compareGroup));
		}
		if(compareGroups.isEmpty())
		{
			CompareGroup<T> group = compareReportData.getCompareGroup();
			CompareItemGenerator<T> compareItemGenerator = new CompareItemGenerator<T>(group, new KeyColumnComparator<T>(reportTemplate));
			List<CompareItem<T>> compareItems = compareItemGenerator.generate();
			for(CompareItem<T> item: compareItems)
			{
				for(int itemIndex = 0; itemIndex < item.getNumberOfItems(); itemIndex ++)
				{
					new Node<CompareReportData<T>>(node, new CompareItemReportData<T>(group, item, itemIndex));
				}
			}
		}
	}
}
