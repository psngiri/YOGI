package yogi.report.client.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.AbstractTableModel;

import yogi.report.server.config.ColumnConfig;
import yogi.report.server.config.ConditionConfig;

public class FilterTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	protected List<FilterTableRow> rows= new ArrayList<FilterTableRow>() ;
	
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
		rows.add(new FilterTableRow(columnConfig));
		this.fireTableRowsInserted(index, index+1);
	}

	
	public void addRows(Collection<ColumnConfig<?,?>> columnConfigs)
	{
		int index = rows.size();
		for(ColumnConfig<?,?> columnConfig: columnConfigs){
			rows.add(new FilterTableRow(columnConfig));
		}
		this.fireTableRowsInserted(index, index+columnConfigs.size());
	}
	
	public FilterTableRow getRow(int rowIndex)
	{
		return rows.get(rowIndex);
	}
	public List<FilterTableRow> getRows()
	{
		return rows;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		FilterTableRow FilterColumnsTableRow = getRow(rowIndex);
		switch(columnIndex){
		case 0: 
			if((FilterColumnsTableRow.getColumnConfig()==null))
				return null;
			return FilterColumnsTableRow.getColumnConfig().toString();
		case 1:
			if((FilterColumnsTableRow.getConditionConfig()==null))
				return null;
		    return FilterColumnsTableRow.getConditionConfig().toString();
		case 2:
			return FilterColumnsTableRow.getValue();
		case 3:
			return FilterColumnsTableRow.getAlias();
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
			return "Alias";
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 0: return ColumnConfig.class;
			case 1: return ConditionConfig.class;
		}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return true;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		FilterTableRow FilterColumnsTableRow = getRow(rowIndex);
		switch(columnIndex){
		case 0:
			FilterColumnsTableRow.setColumnConfig((ColumnConfig<?, ?>) aValue);
			break;
		case 1:
			ConditionConfig<?> value = null;
			if(aValue instanceof ConditionConfig<?>) value = (ConditionConfig<?>)aValue;
			FilterColumnsTableRow.setConditionConfig(value);
			break;
		case 2:
//			String value1= (String) aValue;
//			if(validatePattern(FilterColumnsTableRow.getConditionConfig().getValidator().getRegEx(),value1))
//			FilterColumnsTableRow.setValue((String)aValue);
//			else
//				FilterColumnsTableRow.setValue(null);	
			FilterColumnsTableRow.setValue((String)aValue);
			break;
		case 3:
			FilterColumnsTableRow.setAlias((String) aValue);
			
		}
		this.fireTableCellUpdated(rowIndex, columnIndex);
	}

	public void removeRow(FilterTableRow row) {
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
