package yogi.report.client.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import yogi.report.function.Function;
import yogi.report.server.config.ColumnConfig;
import yogi.report.server.config.FunctionConfig;

public class SelectedColumnsTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	protected List<SelectedColumnsTableRow> rows= new ArrayList<SelectedColumnsTableRow>() ;
	
	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}
	
	public void remove(int rowIndex)
	{
		rows.remove(rowIndex);
		this.fireTableRowsDeleted(rowIndex, rowIndex);
	}
	public void clear()
	{
		rows.clear();
		this.fireTableRowsDeleted(0, rows.size());
	}
	public void addRow(ColumnConfig<?,?> columnConfig)
	{
		int index = rows.size();
		rows.add(new SelectedColumnsTableRow(columnConfig));
		this.fireTableRowsInserted(index, index+1);
	}

	
	public void addRows(Collection<ColumnConfig<?,?>> columnConfigs)
	{
		int index = rows.size();
		for(ColumnConfig<?,?> columnConfig: columnConfigs){
			rows.add(new SelectedColumnsTableRow(columnConfig));
		}
		this.fireTableRowsInserted(index, index+columnConfigs.size());
	}
	
	public SelectedColumnsTableRow getRow(int rowIndex)
	{
		return rows.get(rowIndex);
	}
	public List<SelectedColumnsTableRow> getRows()
	{
		return rows;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SelectedColumnsTableRow selectedColumnsTableRow = getRow(rowIndex);
		switch(columnIndex){
		case 0: 
			return selectedColumnsTableRow.getColumnConfig().toString();
		case 1:
			if((selectedColumnsTableRow.getFunctionConfig()==null))
				return new FunctionConfig<Object>(""){
					private static final long serialVersionUID = 1L;

					@Override
					public Function<Object> getFunction() {
						return null;
					}
				
			};
		    return selectedColumnsTableRow.getFunctionConfig().toString();
		case 2: 
			return selectedColumnsTableRow.isGroupBy();
		case 3:
			return selectedColumnsTableRow.getSortOrder();
		case 4:
			return selectedColumnsTableRow.getGroupSortOrder();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch(column){
		case 0: 
			return "Column";
		case 1:
			return "Function";
		case 2: 
			return "Group By";
		case 3:
			return "Sort Order";
		case 4:
			return "Group Sort Order";
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 1: return FunctionConfig.class;
		case 2: return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
    	switch(columnIndex){
    	case 0: return false;
    	case 1: 
    		if(!groupBy()) return false;
    		return !this.getRow(rowIndex).isGroupBy();
    	default: return true;
    	}
	}

	private boolean groupBy() {
		for(SelectedColumnsTableRow row:rows)
		{
			if(row.isGroupBy()) return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		SelectedColumnsTableRow selectedColumnsTableRow = getRow(rowIndex);
		switch(columnIndex){
		case 0:
			selectedColumnsTableRow.setColumnConfig((ColumnConfig<?, ?>) aValue);
			break;
		case 1:
			FunctionConfig<?> value = null;
			if(aValue instanceof FunctionConfig) value = (FunctionConfig<?>)aValue;
			selectedColumnsTableRow.setFunctionConfig(value);
			break;
		case 2: 
			selectedColumnsTableRow.setGroupBy((Boolean) aValue);
			break;
		case 3:
			selectedColumnsTableRow.setSortOrder(Integer.parseInt((String) aValue));
			break;
		case 4:
			selectedColumnsTableRow.setGroupSortOrder(Integer.parseInt((String) aValue));
			
		}
		this.fireTableCellUpdated(rowIndex, columnIndex);
	}


}
