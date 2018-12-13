package yogi.report.client.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.AbstractTableModel;

import yogi.report.server.config.ColumnConfig;
import yogi.report.server.config.ConditionConfig;

public class GroupFilterTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	protected List<GroupFilterTableRow> rows= new ArrayList<GroupFilterTableRow>() ;
	
	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
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
		rows.add(new GroupFilterTableRow(columnConfig));
		this.fireTableRowsInserted(index, index+1);
	}

	
	public void addRows(Collection<ColumnConfig<?,?>> columnConfigs)
	{
		int index = rows.size();
		for(ColumnConfig<?,?> columnConfig: columnConfigs){
			rows.add(new GroupFilterTableRow(columnConfig));
		}
		this.fireTableRowsInserted(index, index+columnConfigs.size());
	}
	
	public GroupFilterTableRow getRow(int rowIndex)
	{
		return rows.get(rowIndex);
	}
	public List<GroupFilterTableRow> getRows()
	{
		return rows;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		GroupFilterTableRow groupFilterTableRow = getRow(rowIndex);
		switch(columnIndex){
		case 0: 
			if((groupFilterTableRow.getColumnConfig()==null))
				return null;
			return groupFilterTableRow.getColumnConfig().toString();
		case 1:
			if((groupFilterTableRow.getConditionConfig()==null))
				return null;
		    return groupFilterTableRow.getConditionConfig().toString();
		case 2:
			return groupFilterTableRow.getValue();
		case 3:
			if((groupFilterTableRow.getMarketLevelColumnConfig()==null))
				return null;
			return groupFilterTableRow.getMarketLevelColumnConfig().toString();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch(column){
		case 0: 
			return "Column";
		case 1:
			return "Condition";
		case 2: 
			return "Value";
		case 3:
			return "Group Level";
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0: return ColumnConfig.class;
		case 1: return ConditionConfig.class;
		case 3: return ColumnConfig.class;
	}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return true;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		GroupFilterTableRow groupFilterTableRow = getRow(rowIndex);
		switch(columnIndex){
		case 0:
			groupFilterTableRow.setColumnConfig((ColumnConfig<?, ?>) aValue);
			break;
		case 1:
			ConditionConfig<?> value = null;
			if(aValue instanceof ConditionConfig<?>) value = (ConditionConfig<?>)aValue;
			groupFilterTableRow.setConditionConfig(value);
			break;
		case 2:
			groupFilterTableRow.setValue((String)aValue);
			break;
		case 3:
			groupFilterTableRow.setMarketLevelColumnConfig((ColumnConfig<?, ?>) aValue);
			break;
			
		}
		this.fireTableCellUpdated(rowIndex, columnIndex);
	}

	public void removeRow(GroupFilterTableRow row) {
		rows.remove(row);
	}

	 public boolean validatePattern(String regex, String messageToken) {
			
	        if (messageToken == null || messageToken.equals("")) {
	             return false;
	        }

	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(messageToken);
	       
	        if (matcher.find()) {
	             return true;
	        }

	        return false;
	    }
	    
}
