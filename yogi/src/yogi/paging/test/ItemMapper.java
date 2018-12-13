package yogi.paging.test;

import java.util.ArrayList;
import java.util.List;

import yogi.paging.BaseTableDataMapper;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.paging.column.types.BooleanTableColumnConfig;
import yogi.paging.column.types.StringTableColumnConfig;

public class ItemMapper extends BaseTableDataMapper<Item, Object> {

	private static final long serialVersionUID = -2981922910940607048L;
	
	public ItemMapper() {
		super(constructTableColumnConfigs(new String[]{"Name", "Color", "Qty", "Amount", "Pri", "Xmit"}), null, null, true, null);
	}

	@Override
	public Object getColumnValue(int columnHeaderIndex, Item rowData) {
		Object columnValue = null;
		switch(columnHeaderIndex) {
		case 0:
			columnValue = rowData.name;		break;
		case 1:
			columnValue = rowData.color;	break;
		case 2:
			columnValue = rowData.quantity;	break;
		case 3:
			columnValue = rowData.amount;	break;
		case 4:
			columnValue = rowData.priority;	break;
		case 5:
			columnValue = rowData.transmit;	break;
		}			
		return columnValue;
	}
	
	private static List<TableColumnConfig<?>> constructTableColumnConfigs(String[] header) {
		List<TableColumnConfig<?>> tableColumnConfigs = new ArrayList<TableColumnConfig<?>>(header.length);
		tableColumnConfigs.add(new StringTableColumnConfig(header[0], ColumnFieldType.TEXTFIELD, 50, false, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringTableColumnConfig(header[1], ColumnFieldType.TEXTFIELD, 50, false, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringTableColumnConfig(header[2], ColumnFieldType.TEXTFIELD, 50, false, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringTableColumnConfig(header[3], ColumnFieldType.TEXTFIELD, 50, false, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringTableColumnConfig(header[4], ColumnFieldType.TEXTFIELD, 50, false, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING, getPriConfig(), null, null));
		tableColumnConfigs.add(new BooleanTableColumnConfig(header[5], ColumnFieldType.CHECKBOXFIELD, 50, false, true, true, false, ColumnAlignment.CENTER, ColumnFilterType.STRING));
		return tableColumnConfigs;
	}
	
	private static TypeConfig getPriConfig() {		
		TypeConfig config = new TypeConfig();
		config.addItem("Low", "Low");
		config.addItem("Medium", "Medium");
		config.addItem("High", "High");
		return config;
	}
	
}