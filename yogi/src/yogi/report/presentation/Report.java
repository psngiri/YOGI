package yogi.report.presentation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import yogi.base.comparators.AndComparator;
import yogi.base.comparators.ReverseComparator;
import yogi.base.util.immutable.ImmutableList;
import yogi.report.column.ColumnComparator;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnGroupBy;
import yogi.report.column.GroupColumnComparator;
import yogi.report.group.Group;
import yogi.report.template.BaseReportTemplate;
import yogi.report.template.SimpleReportTemplate;
import yogi.report.viewer.ReportViewer;

public abstract class Report<T> {
	private ReportListModel<T> reportListModel;
	private ReportTableModel<T> reportTableModel;
	
	public Report() {
		super();
		JFrame frame = new JFrame("Report");
		
		frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent we) {
			System.exit(0);
		    }
		});
		reportListModel = new ReportListModel<T>();
		reportTableModel = new ReportTableModel<T>();
		JList list = new JList();
		JTable table = new JTable();
		JLabel listLabel = new JLabel();
		JLabel tableLabel = new JLabel();
		JPanel listPanel = new JPanel();
		JPanel tablePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JButton button = new JButton();
		JButton clearButton = new JButton();
		JSplitPane splitPane = new JSplitPane();
		listLabel.setText("Available Columns");
		tableLabel.setText("Selected Columns");
		listPanel.setLayout(new BorderLayout());
		listPanel.add(listLabel, BorderLayout.NORTH);
		listPanel.add(new JScrollPane(list), BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(tableLabel, BorderLayout.NORTH);
		tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
		list.setModel(reportListModel);
		table.setModel(reportTableModel);
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setRightComponent(tablePanel);
		splitPane.setLeftComponent(listPanel);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		button.setText("Generate Report");
		clearButton.setText("Clear");
		buttonPanel.add(clearButton);
		buttonPanel.add(button);
		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		list.addMouseListener(new MyListMouseListener<T>(list, reportTableModel));
		button.addActionListener(new GenerateReportButtonActionListener());
		clearButton.addActionListener(new ClearButtonActionListener<T>(reportTableModel));
		populateList();
		frame.pack();
		frame.setVisible(true);
	}

	private void populateList() {
		for(ColumnDefinition<T> column: getColumns())
		{
			reportListModel.addObject(new ReportListObject<T>(column));
		}
	}

	public abstract List<ColumnDefinition<T>> getColumns();
	
	public void generateReport()
	{
		new ReportViewer<T>(getItems(), getReportTemplate(), "Report", false);
		
	}
	
	public abstract List<T> getItems();

	@SuppressWarnings("unchecked")
	private BaseReportTemplate<T> getReportTemplate() {
		SimpleReportTemplate<T> reportTemplate = new SimpleReportTemplate<T>();
		ImmutableList<ReportTableObject<T>> tableObjects = reportTableModel.getObjects();
		List<ReportTableObject<T>> myTableObjects = new ArrayList<ReportTableObject<T>>();
		reportTemplate.addGroupBy(new ColumnGroupBy<T>());
		boolean buildGroupBy = true;
		for(ReportTableObject<T> tableObject: tableObjects )
		{
			reportTemplate.addColumn(tableObject.getColumn());
			if(tableObject.getSortIndex() != -1) myTableObjects.add(tableObject);
			if(!tableObject.getColumn().isKey()) buildGroupBy = false;
			if(buildGroupBy)
			{
				reportTemplate.addGroupBy(new ColumnGroupBy<T>(reportTemplate.getLastGroupBy(), tableObject.getColumn()));
			}
		}
		Collections.sort(myTableObjects, new SortIndexComparator());
		AndComparator<Group<T>> groupColumnComparators = new AndComparator<Group<T>>();
		AndComparator<T> columnComparators = new AndComparator<T>();
		
		for(ReportTableObject<T> tableObject: myTableObjects )
		{
			if(tableObject.isSortAssending())
			{
				groupColumnComparators.add(new GroupColumnComparator<T>(tableObject.getColumn()));
				columnComparators.add(new ColumnComparator<T>(tableObject.getColumn()));
			}else
			{
				groupColumnComparators.add(new ReverseComparator<Group<T>>(new GroupColumnComparator<T>(tableObject.getColumn())));
				columnComparators.add(new ReverseComparator<T>(new ColumnComparator<T>(tableObject.getColumn())));
			}
		}
		reportTemplate.setGroupComparator(groupColumnComparators);
		reportTemplate.setComparator(columnComparators);

		return reportTemplate;
	}
	
	private final class SortIndexComparator implements Comparator<ReportTableObject> {
		public int compare(ReportTableObject o1, ReportTableObject o2) {
			return o1.getSortIndex() - o2.getSortIndex();
		}
	}

	private static class MyListMouseListener<T> extends MouseAdapter
	{
		private JList list;
		private ReportTableModel<T> reportTableModel;
		
		public MyListMouseListener(JList list, ReportTableModel<T> reportTableModel) {
			super();
			this.list = list;
			this.reportTableModel = reportTableModel;
		}

		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2)
			{
				for(Object item: list.getSelectedValues())
				{
					addItem(item);
				}
			}
			
		}
		
		@SuppressWarnings({"unchecked"})
		void addItem(Object item)
		{
			ReportListObject<T> listObject = (ReportListObject<T>) item;
			reportTableModel.addObject(new ReportTableObject<T>(listObject.getColumn()));
		}
	}
	
	private class GenerateReportButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			generateReport();
		}

	}
	private class ClearButtonActionListener<M> implements ActionListener
	{
		private ReportTableModel<M> reportTableModel;
		public ClearButtonActionListener(ReportTableModel<M> reportTableModel) {
			super();
			this.reportTableModel = reportTableModel;
		}
		public void actionPerformed(ActionEvent e) {
			reportTableModel.clear();
		}

	}
}
