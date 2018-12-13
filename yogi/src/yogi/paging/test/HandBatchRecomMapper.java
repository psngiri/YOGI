package yogi.paging.test;

import java.util.ArrayList;
import java.util.List;

import yogi.paging.BaseTableDataMapper;
import yogi.paging.TableData;
import yogi.paging.changes.ColumnChange;
import yogi.paging.column.TableColumnConfig;
import yogi.paging.column.TypeConfig;
import yogi.paging.column.config.ColumnAlignment;
import yogi.paging.column.config.ColumnFieldType;
import yogi.paging.column.config.ColumnFilterType;
import yogi.paging.column.types.DoubleTableColumnConfig;
import yogi.paging.column.types.FloatTableColumnConfig;
import yogi.paging.column.types.IntegerTableColumnConfig;
import yogi.paging.column.types.StringNumberTableColumnConfig;
import yogi.paging.column.types.StringTableColumnConfig;
import yogi.report.server.config.BaseValidator;

public class HandBatchRecomMapper extends BaseTableDataMapper<Recom, Object> {

	private static final long serialVersionUID = 8792157596027785062L;
	
	public HandBatchRecomMapper() {
		super(constructTableColumnConfigs(), null, null, true, null);
		super.setDefaultFindColumnIndex(18);
	}
	
	@Override
	public Object getColumnValue(int columnHeaderIndex, Recom rowData) {
		Object columnValue = null;
		switch(columnHeaderIndex) {
		case 0:
			columnValue = rowData.act;			break;
		case 1:
			columnValue = rowData.orig; 		break;
		case 2:
			columnValue = rowData.dest; 		break;
		case 3:
			columnValue = rowData.fareBasis; 	break;
		case 4:
			columnValue = rowData.owrt; 		break;
		case 5:
			columnValue = rowData.rtg; 			break;
		case 6:
			columnValue = rowData.fn; 			break;
		case 7:
			columnValue = rowData.cur; 			break;
		case 8:
			columnValue = rowData.amount; 		break;
		case 9:
			columnValue = rowData.prevAmount;	break;
		case 10:
			columnValue = rowData.fareDiff; 	break;
		case 11:
			columnValue = rowData.slaYld; 		break;
		case 12:
			columnValue = rowData.hCxr; 		break;
		case 13:
			columnValue = rowData.hShr; 		break;
		case 14:
			columnValue = rowData.cxr; 			break;
		case 15:
			columnValue = rowData.shr; 			break;
		case 16:
			columnValue = rowData.recFbc; 		break;
		case 17:
			columnValue = rowData.recFn; 		break;
		case 18:
			columnValue = rowData.market; 		break;
		case 19:
			columnValue = rowData.chgRtg; 		break;
		case 20:
			columnValue = rowData.chgFn; 		break;
		}			
		return columnValue;
	}
	
	private static List<TableColumnConfig<?>> constructTableColumnConfigs() {
		List<TableColumnConfig<?>> tableColumnConfigs = new ArrayList<TableColumnConfig<?>>();
		tableColumnConfigs.add(new StringTableColumnConfig(		"Action", 			ColumnFieldType.COMBOBOX, 		34, false, 	true, 	true, true, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING, getActConfig(), getActValidator(), null));
		tableColumnConfigs.add(new StringTableColumnConfig(		"Origin", 		ColumnFieldType.TEXTFIELD, 		38, false, 	true, 	true, true, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringTableColumnConfig(		"Destination", 		ColumnFieldType.TEXTFIELD, 		40, false, 	true, 	true, true, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringNumberTableColumnConfig("FareBasis", 	ColumnFieldType.TEXTFIELD, 		74, false, 	true, 	true, true, 	ColumnAlignment.LEFT, 		ColumnFilterType.STRING));
		tableColumnConfigs.add(new IntegerTableColumnConfig(		"OWRT", 		ColumnFieldType.COMBOBOX, 		47, false, 	true, 	true, false, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING, getOWRTConfig(), getOWRTValidator(), null));
		tableColumnConfigs.add(new StringTableColumnConfig(		"RoutingNumber", 			ColumnFieldType.TEXTFIELD, 		35, false, 	true, 	true, false, 	ColumnAlignment.RIGHT, 		ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringNumberTableColumnConfig("FootNote", 			ColumnFieldType.TEXTFIELD, 		32, false, 	true, 	true, false, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringTableColumnConfig(		"Currency", 			ColumnFieldType.TEXTFIELD, 		36, false, 	true, 	true, false, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING));
		tableColumnConfigs.add(new FloatTableColumnConfig(		"FareAmount", 			ColumnFieldType.TEXTFIELD, 		61, false, 	true, 	true, false, 	ColumnAlignment.RIGHT, 		ColumnFilterType.STRING));
		tableColumnConfigs.add(new FloatTableColumnConfig(		"FareAmount WithTax", 		ColumnFieldType.TEXTFIELD, 		61, true, 	true, 	true, false, 	ColumnAlignment.RIGHT, 		ColumnFilterType.STRING));
		tableColumnConfigs.add(new FloatTableColumnConfig(		"TariffNumber", 	ColumnFieldType.TEXTFIELD, 		57, true, 	true, 	true, false, 	ColumnAlignment.RIGHT, 		ColumnFilterType.STRING));
		tableColumnConfigs.add(new DoubleTableColumnConfig(		"SLAYld", 		ColumnFieldType.TEXTFIELD, 		53, true, 	true, 	true, false, 	ColumnAlignment.RIGHT, 		ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringTableColumnConfig(		"HCxr", 		ColumnFieldType.TEXTFIELD, 		43, true, 	true, 	true, false, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING));
		tableColumnConfigs.add(new FloatTableColumnConfig(		"HShr", 		ColumnFieldType.TEXTFIELD, 		44, true, 	true, 	true, false, 	ColumnAlignment.RIGHT, 		ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringTableColumnConfig(		"Cxr", 			ColumnFieldType.TEXTFIELD, 		36, true, 	true, 	true, false, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING));
		tableColumnConfigs.add(new FloatTableColumnConfig(		"Shr", 			ColumnFieldType.TEXTFIELD, 		48, true, 	true, 	true, false, 	ColumnAlignment.RIGHT, 		ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringNumberTableColumnConfig("RecFBC", 		ColumnFieldType.TEXTFIELD, 		73, true, 	true, 	true, false, 	ColumnAlignment.LEFT, 		ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringNumberTableColumnConfig("RecFN", 		ColumnFieldType.TEXTFIELD, 		50, true, 	true, 	true, false, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringTableColumnConfig(		"Market", 		ColumnFieldType.TEXTFIELD, 		60, true, 	true, 	true, false, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING));
		tableColumnConfigs.add(new IntegerTableColumnConfig(		"ChgRtg", 		ColumnFieldType.TEXTFIELD, 		55, false, 	true, 	true, false, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING));
		tableColumnConfigs.add(new StringNumberTableColumnConfig("ChgFN", 		ColumnFieldType.TEXTFIELD, 		51, false, 	true, 	true, false, 	ColumnAlignment.CENTER, 	ColumnFilterType.STRING));
		return tableColumnConfigs;
	}
	
	private static BaseValidator getActValidator() {
		return new BaseValidator("^(C|I|N|R|X)$", "ACT must be a valid value");
	}

	private static BaseValidator getOWRTValidator() {
		return new BaseValidator("^[0-2]*$", "OWRT must be a valid value");
	}

	private static TypeConfig getActConfig() {
		TypeConfig actConfig = new TypeConfig();
		actConfig.addItem("N", "N");
		actConfig.addItem("C", "C");
		actConfig.addItem("I", "I");
		actConfig.addItem("R", "R");
		actConfig.addItem("X", "X");
		return actConfig;
	}
	
	private static TypeConfig getOWRTConfig() {
		TypeConfig owrtConfig = new TypeConfig();
		owrtConfig.addItem("0", "0");
		owrtConfig.addItem("1", "1");
		owrtConfig.addItem("2", "2");
		return owrtConfig;
	}
	
	@Override
	public List<ColumnChange> getModifiedValuesForDependentColumn(int rowDataIndex, int columnHeaderIndex, String oldValue, String newValue, TableData<Recom> tableData) {		
		List<ColumnChange> columnChanges = new ArrayList<ColumnChange>();
		int ACT_INDEX = 0;
		switch(columnHeaderIndex) {
		case 8:			
			int FAREDIFF_INDEX = 10;
			int PREVAMT_INDEX = 9;			
			String oldAct = reduce(tableData.getDataValue(rowDataIndex, ACT_INDEX));
			String newAct = "C";
			Float newAmt = Float.valueOf(newValue);
			Float oldFareDiff = reduce(tableData.getDataValue(rowDataIndex, FAREDIFF_INDEX));
			Float prevAmt = reduce(tableData.getDataValue(rowDataIndex, PREVAMT_INDEX));
			
			Float newFareDiff = newAmt - prevAmt;
			if(newFareDiff >= 0) { 
				newAct = "I"; 
			} else {
				newAct = "R";
			}
			String newFareDiffStr = newFareDiff.toString();
			columnChanges.add(new ColumnChange(ACT_INDEX, oldAct, newAct));
			columnChanges.add(new ColumnChange(FAREDIFF_INDEX, oldFareDiff.toString(), newFareDiffStr));
			break;			
		case 19:
		case 20:
			columnChanges.add(new ColumnChange(ACT_INDEX, (String) reduce(tableData.getDataValue(rowDataIndex, ACT_INDEX)), "C"));
			break;
		default:
			break;
		}
		return columnChanges;
	}
}