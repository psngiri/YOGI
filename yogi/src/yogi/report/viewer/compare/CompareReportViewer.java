package yogi.report.viewer.compare;

import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.node.Node;
import yogi.report.compare.CompareGroup;
import yogi.report.compare.CompareGroupGenerator;
import yogi.report.compare.template.BaseCompareReportTemplate;
import yogi.report.group.GroupGenerator;
import yogi.report.viewer.ReportTreeNodeImpl;
import yogi.tools.viewers.TreeTableViewer;


public class CompareReportViewer<T>
{
	private TreeTableViewer<CompareReportData<T>> treeTableViewer;

	@SuppressWarnings("unchecked")
	public CompareReportViewer(BaseCompareReportTemplate<T> reportTemplate, List<T>... items ) {
		super();
		ImmutableList<T>[] immutableItems = new ImmutableList[items.length];
		for(int i = 0; i < items.length; i ++)
		{
			immutableItems[i] = new ImmutableList<T>(items[i]);
		}
		CompareReportTreeTableDataHelper<T> compareReportTreeTableDataHelper = new CompareReportTreeTableDataHelper<T>(reportTemplate);
		GroupGenerator<T> groupGenerator = new GroupGenerator<T>();
		reportTemplate.apply(groupGenerator);
		CompareGroupGenerator<T> compareGroupGenerator = new CompareGroupGenerator<T>(groupGenerator);
		CompareGroup<T> rootCompareGroup = compareGroupGenerator.generateRootCompareGroup(immutableItems);
		Node<CompareReportData<T>> rootCompareReportNode = new Node<CompareReportData<T>>(null, new CompareGroupReportData<T>(rootCompareGroup));
		CompareReportDataGenerator<T> compareReportDataGenerator = new CompareReportDataGenerator<T>(reportTemplate, compareGroupGenerator);
		compareReportDataGenerator.generateReportData(0, rootCompareReportNode);
		ReportTreeNodeImpl<CompareReportData<T>> rootNode = new ReportTreeNodeImpl<CompareReportData<T>>(rootCompareReportNode);
		treeTableViewer = new TreeTableViewer<CompareReportData<T>>(compareReportTreeTableDataHelper, "Compare Report Viewer",rootNode);
		treeTableViewer.getTreeTable().addTreeWillExpandListener(new CompareReportTreeExpantionListener<T>(compareReportDataGenerator));
	}

	public TreeTableViewer<CompareReportData<T>> getTreeTableViewer() {
		return treeTableViewer;
	}
	
}

