package yogi.report.presentation;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class ReportListModel<T> extends AbstractListModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4690948793417051209L;
	private ArrayList<ReportListObject<T>> items = new ArrayList<ReportListObject<T>>();

	public int getSize() {
		return items.size();
	}

	public Object getElementAt(int index) {
		return items.get(index);
	}

	public void addObject(ReportListObject<T> object)
	{
		items.add(object);
	}
}
