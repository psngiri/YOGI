package yogi.report.viewer;

import java.util.List;

import yogi.base.util.node.Node;
import yogi.report.group.Group;
import yogi.report.group.GroupGenerator;

public class ReportDataGenerator<T> {
	private GroupGenerator<T> groupGenerator;

	public ReportDataGenerator(GroupGenerator<T> groupGenerator) {
		super();
		this.groupGenerator = groupGenerator;
	}

	public GroupGenerator<T> getGroupGenerator() {
		return groupGenerator;
	}

	public void generateReportData(int groupIndex, Node<ReportData<T>> node) {
		ReportData<T> reportData = node.getData();
		List<Group<T>> groups = groupGenerator.generateGroups(groupIndex, reportData.getGroup());
		if(!groups.isEmpty())
		{
			for(Group<T> group: groups)
			{
				new Node<ReportData<T>>(node, new GroupReportData<T>(group));
			}
		}else
		{
			Group<T> group = reportData.getGroup();
			int itemsInGroup = group.getEndIndex() - group.getStartIndex() +1;
			for(int indexInGroup = 0; indexInGroup < itemsInGroup; indexInGroup ++)
			{
				if(!group.isValid(indexInGroup)) continue;
				new Node<ReportData<T>>(node, new ItemReportData<T>(group, indexInGroup));
			}
		}
	}
}
