package yogi.report.presentation;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import yogi.base.util.immutable.ImmutableList;

public class ReportTableModel<T> extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2011538377141058209L;
	private ArrayList<ReportTableObject<T>> rows = new ArrayList<ReportTableObject<T>>();
	public int getRowCount() {
		return rows.size();
	}

	public void addObject(ReportTableObject<T> object)
	{
		int index = rows.size();
		rows.add(object);
		this.fireTableRowsInserted(index, index);
	}
	
	public ImmutableList<ReportTableObject<T>> getObjects()
	{
		return new ImmutableList<ReportTableObject<T>>(rows);
	}
	
	
	public int getColumnCount() {
		return 3;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		ReportTableObject<T> reportObject = rows.get(rowIndex);
		switch(columnIndex){
		case 0:
			return reportObject.getColumn().getName();
		case 1:
			if(reportObject.getSortIndex() >= 0)
			{
			return reportObject.getSortIndex();
			}else return null;
		case 2:
			if(reportObject.getSortIndex() >= 0)
			{
				return reportObject.isSortAssending();
			}else return null;
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
			return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public String getColumnName(int column) {
		switch(column){
		case 0:
			return "Column Name";
		case 1:
			return "Sort Index";
		case 2:
			return "Sort Assending";
		}
		return super.getColumnName(column);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0:
			return false;
		case 1:
			return true;
		case 2:
			return rows.get(rowIndex).getSortIndex() >= 0;
		}
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ReportTableObject<T> reportObject = rows.get(rowIndex);
		switch(columnIndex){
		case 1:
			if(aValue == null) aValue = -1;
			reportObject.setSortIndex((Integer) aValue);
			break;
		case 2:
			reportObject.setSortAssending((Boolean) aValue);
			break;
		}
	}

	public void clear() {
		rows.clear();
		this.fireTableDataChanged();
	}

}
