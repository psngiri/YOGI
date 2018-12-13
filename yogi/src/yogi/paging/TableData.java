package yogi.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import yogi.base.app.ErrorReporter;
import yogi.base.indexing.Indexer;
import yogi.base.indexing.MultiIndexer;
import yogi.base.util.collections.IndexItem;
import yogi.paging.changes.Change;
import yogi.paging.changes.ChangeComparator;
import yogi.paging.changes.ChangeRecord;
import yogi.paging.changes.Changes;
import yogi.paging.changes.ColumnChange;
import yogi.paging.column.TableColumnConfig;
import yogi.report.condition.Condition;
import yogi.report.server.config.ConditionConfig;

public class TableData<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8590508204312486665L;

	private Integer tableDataSetId;
	private List<T> data;
	private List<Integer> rowDataView;
	private List<Integer> columnHeaderView;
	private TableDataMapper<T, ?> tableDataMapper;
	private List<Object[]> addedRows = new ArrayList<Object[]>();	
	private Indexer<Integer, Integer> addedRowChangeIndex = new Indexer<Integer, Integer>();
	private MultiIndexer<Object> updatedValues = new MultiIndexer<Object>(2);
	private MultiIndexer<Integer> updatedRowChangeIndex = new MultiIndexer<Integer>(2);
	private List<Integer> deletedRowIndexes = new ArrayList<Integer>();
	private Indexer<Integer, Integer> deletedRowChangeIndex = new Indexer<Integer, Integer>();
	private List<Change> changesHistory = new ArrayList<Change>();
	private int changePointer = -1;
	private int savePointer = -1;
	private static final Object NULL = "null";
	
	public TableData(List<T> data, TableDataMapper<T, ?> tableDataMapper, Integer tableDataSetId) {
		super();
		this.data = data;
		this.tableDataMapper = tableDataMapper;
		this.tableDataSetId = tableDataSetId;
		this.rowDataView = new ArrayList<Integer>(data.size());
		for(int i = 0; i < data.size(); i++) {
			rowDataView.add(i);
		}
		this.columnHeaderView = new ArrayList<Integer>(tableDataMapper.getColumnCount());
		for(int i = 0; i < tableDataMapper.getColumnCount(); i++) {
			columnHeaderView.add(i);
		}
	}

	public List<Integer> getRowDataView() {
		return rowDataView;
	}

	public TableColumnConfig<?>[] getTableColumnConfigs() {
		TableColumnConfig<?>[] columnConfigs = new TableColumnConfig<?>[columnHeaderView .size()];
		for (int i = 0; i < columnConfigs.length; i++) {
			columnConfigs[i] = tableDataMapper.getColumnConfig(columnHeaderView .get(i));
		}
		return columnConfigs;
	}
	
	public HashMap<String, String> getColumnDisplayNames() {
		return tableDataMapper.getColumnDisplayNames();
	}
	
	public List<TableActionConfig> getTableActionConfigs() {
		return tableDataMapper.getTableActionConfigs();
	}
	
	public Object getInput() {
		return tableDataMapper.getInput();
	}
	
	public int getDefaultFindColumnIndex() {
		return tableDataMapper.getDefaultFindColumnIndex();
	}
	
	public boolean isDefaultEditSelectAllValue() {
		return tableDataMapper.isDefaultEditSelectAllValue();
	}

	public String[] getColumnHeaderNames() {
		String[] columnNames = new String[columnHeaderView.size()];
		for(int i = 0; i < columnNames.length; i++) {
			columnNames[i] = tableDataMapper.getColumnConfig(columnHeaderView.get(i)).getName();
		}
		return columnNames;
	}
		
	public List<Integer> getColumnHeaderIndexes(List<String> columnNames) {
		List<Integer> rtnValue = new ArrayList<Integer>(columnNames.size());
		List<String> missingColumnNames = new ArrayList<String>();
		for(String columnName: columnNames){
			Integer columnIndex = getColumnIndex(columnName);
			if(columnIndex == null){
				if(missingColumnNames == null ) missingColumnNames = new ArrayList<String>();
				missingColumnNames.add(columnName);
			}else{
				rtnValue.add(columnIndex);
			}
		}
		if(missingColumnNames != null && !missingColumnNames.isEmpty()) throw new RuntimeException("Missing columns : " + missingColumnNames);
		return  rtnValue;
	}

	public Integer getColumnIndex(String columnName) {
		Integer columnHeaderIndex = tableDataMapper.getColumnIndex(columnName);
		if(columnHeaderIndex == null) return null;
		return columnHeaderView.indexOf(columnHeaderIndex);
	}
	
	
	public TablePage getTablePage(int startRow, int endRow) {
		if(startRow == 0 && endRow >= rowDataView.size()) {
			endRow = rowDataView.size() - 1;
		}
		PageChanges pageChanges = getPageChanges(startRow, endRow);
		return new TablePage(getPage(startRow, endRow), rowDataView.size(), tableDataSetId, pageChanges);
	}
	
	private PageChanges getPageChanges(int startRow, int endRow) {
		PageChanges pageChanges = new PageChanges();
		for(int i = startRow; i <= endRow; i++)
		{
			int rowDataIndex = rowDataView.get(i);
			if(rowDataIndex >= data.size())
			 {
				Integer addedPointerIndex = addedRowChangeIndex.get(rowDataIndex);
				if(addedPointerIndex != null && addedPointerIndex > savePointer) {
					pageChanges.setAddedRow(i);
				}
			 }			 
			for(int j = 0; j < columnHeaderView.size(); j++) {
				int columnHeaderIndex = columnHeaderView.get(j);
				Integer updatedPointerIndex = updatedRowChangeIndex.get(rowDataIndex, columnHeaderIndex);
				if(updatedPointerIndex != null && updatedPointerIndex > savePointer) {
					pageChanges.setModifiedCell(i,  j);
				}
			}
		}
		if(savePointer < changePointer) pageChanges.setSavePending(true);
	    return pageChanges;
	}

	public void sort(final List<SortConfig> sortData) {
		List<Integer> oldRowViewList = new ArrayList<Integer>(rowDataView);
		Collections.sort(rowDataView, new Comparator<Integer>() {
			@Override
			public int compare(Integer rowDataIndex1, Integer rowDataIndex2) {
				int rtnValue = 0;
				for(int i = sortData.size() - 1; i >= 0; i--) {
					SortConfig sortConfig = sortData.get(i);
					int columnViewIndex = sortConfig.getColumnIndex();
					int columnHeaderIndex = columnHeaderView.get(columnViewIndex);
					rtnValue = tableDataMapper.compare(columnHeaderIndex, getDataValue(rowDataIndex1, columnHeaderIndex), getDataValue(rowDataIndex2, columnHeaderIndex));
					if(rtnValue != 0){
						if(sortConfig.getAscendingOrder()) {
							return rtnValue; 
						} else { 
							return -rtnValue; 
						}
					}
				}
				return 0;
			}
		});
		List<Integer> newRowViewList = new ArrayList<Integer>(rowDataView);
		Change change = new Change(ChangeType.FILTER_SORT, oldRowViewList, newRowViewList);
		List<Change> myHistory = new ArrayList<Change>();
		myHistory.add(change);
		recordChanges(myHistory);
	}

	public void filter(List<FilterConfig> filterData) {
		if(filterData.isEmpty()) {
			return;
		}
		List<Integer> oldRowViewList = new ArrayList<Integer>(rowDataView);
		List<Condition<Object>> conditions = getConditionsFromFilterData(filterData);
		Iterator<Integer> iterator = rowDataView.iterator();
		while(iterator.hasNext()) {
			int rowDataIndex = iterator.next();
			for(int i = 0; i < filterData.size(); i++) {
				int columnViewIndex = filterData.get(i).getColumnIndex();				
				int columnHeaderIndex = columnHeaderView.get(columnViewIndex);
				if(!conditions.get(i).satisfied(getDataValue(rowDataIndex, columnHeaderIndex))) {
					iterator.remove();
					break;
				}
			}
		}
		List<Integer> newRowViewList = new ArrayList<Integer>(rowDataView);
		Change change = new Change(ChangeType.FILTER_SORT, oldRowViewList, newRowViewList);
		List<Change> myHistory = new ArrayList<Change>();
		myHistory.add(change);
		recordChanges(myHistory);
	}

	private List<Condition<Object>> getConditionsFromFilterData(final List<FilterConfig> filterData) {
		List<Condition<Object>> conditions = new ArrayList<Condition<Object>>();
		for(FilterConfig filterConfig : filterData) {
			int columnViewIndex = filterConfig.getColumnIndex();
			int columnHeaderIndex = columnHeaderView.get(columnViewIndex);
			Condition<Object> condition = getCondition(filterConfig.getConditionName(), filterConfig.getValue(), tableDataMapper.getColumnConfig(columnHeaderIndex));
			conditions.add(condition);
		}
		return conditions;
	}

	private Condition<Object> getCondition(String conditionName, String value,
			TableColumnConfig<?> tableColumnConfig) {
		@SuppressWarnings("unchecked")
		ConditionConfig<Object> conditionConfig = (ConditionConfig<Object>) tableColumnConfig.getCondition(conditionName);
		return conditionConfig.getCondition(value);		
	}
	
	public void clear() {
		while(changePointer > savePointer){
			undo();
		}
	}

	public void clearFilter() {
		List<Integer> oldRowViewList = new ArrayList<Integer>(rowDataView);

		int initialDataCount = data.size();
		int addedDataCount = addedRows.size();

		this.rowDataView = new ArrayList<Integer>();
		for(int i = 0; i < initialDataCount; i++){
			if(!deletedRowIndexes.contains(i)) rowDataView.add(i);
		}
		for(int i = 0 ; i < addedDataCount; i++){			
			int addedRowDataIndex = initialDataCount + i;
			if(!deletedRowIndexes.contains(addedRowDataIndex)) rowDataView.add(addedRowDataIndex);
		}		

		List<Integer> newRowViewList = new ArrayList<Integer>(rowDataView);
		Change change = new Change(ChangeType.FILTER_SORT, oldRowViewList, newRowViewList);
		List<Change> myHistory = new ArrayList<Change>();
		myHistory.add(change);
		recordChanges(myHistory);

	}

	public Changes edit(EditCriteria editCriteria) {
		List<EditConfig> editData = editCriteria.getEditData();
		if(editData.isEmpty()) {
			return new Changes(new ArrayList<Change>());
		}
		List<Integer> rows = null;
		if(editCriteria.isAllSelected()) {
			int count = rowDataView.size();
			rows = new ArrayList<Integer>(count);
			for(int i = 0; i < count; i++) {
				rows.add(i);
			}
		} else {
			rows = editCriteria.getRows();
		}
		int rowsLength = rows.size();
		int colsLength = editData.size();
		List<Change> history = new ArrayList<Change>();
		for(int i = 0; i < rowsLength; i++) {
			int rowIndex = rows.get(i);
			int rowViewIndex = rowIndex;
			int rowDataIndex = rowDataView.get(rowViewIndex);
			for(int j = 0; j < colsLength; j++) {
				EditConfig editConfig = editData.get(j);
				int columnViewIndex = editConfig.getColumnIndex();
				int columnHeaderIndex = columnHeaderView.get(columnViewIndex);
				String oldValue = getValue(rowViewIndex, columnViewIndex);
				String modifyFactor = editConfig.getValue();
				String newValue = getModifiedValue(rowViewIndex, columnViewIndex, modifyFactor);
				if(!oldValue.equals(newValue)) {
				    	if(tableDataMapper.isChangeAllowed(rowDataIndex, columnHeaderIndex, this)){
				    	    history.add(new Change(ChangeType.MOD, rowIndex, columnViewIndex, oldValue, newValue, null, true, true));
				    	}
				}
			}
		}
		Changes changes = new Changes(history, true);
		return changes;
	}

	public Changes deleteAll() {
		int count = rowDataView.size();
		List<Integer> rows = new ArrayList<Integer>(count);
		for(int i = 0; i < count; i++) {
			rows.add(i);
		}
		int rowsLength = rows.size();
		List<Change> history = new ArrayList<Change>();
		for(int i = rowsLength - 1; i >= 0; i--) {
			int rowIndex = rows.get(i);
			history.add(new Change(ChangeType.DEL, rowIndex, -1, "", "", null, true, true));
		}
		markGroupUndoRedoBoundary(history);
		return new Changes(history);
	}
	
	private List<ColumnChange> identifyChangesForDependentColumns(int rowViewIndex, int columnViewIndex, String oldValue, String newValue) {
		int rowDataIndex = rowDataView.get(rowViewIndex);
		int columnHeaderIndex = columnHeaderView.get(columnViewIndex);
		return tableDataMapper.getModifiedValuesForDependentColumn(rowDataIndex, columnHeaderIndex, oldValue, newValue, this);
	}

	private void markGroupUndoRedoBoundary(List<Change> history) {
		if(!history.isEmpty()) {
			history.get(0).setGroupUndoAction(false);
			history.get(history.size() - 1).setGroupRedoAction(false);
		}
	}

	public void applyChanges(Changes changes) {
		List<Change> history = changes.getChangesHistory();		
        List<Change> myHistory = new ArrayList<Change>();
        for(Change change : history) {        	
			ErrorReporter.get().fine("Change Type : " + change.getChangeType());

			int rowIndex = change.getRowIndex();
			change.setRowIndex(rowIndex);
			ErrorReporter.get().fine("rowIndex : " + rowIndex);
			
			if(ChangeType.MOD == change.getChangeType()) {
				int rowDataIndex = rowDataView.get(rowIndex);  				
				int columnIndex = change.getColumnIndex();
				Integer columnHeaderIndex = columnHeaderView.get(columnIndex);        	
				change.setRowDataIndex(rowDataIndex);
				change.setColumnHeaderIndex(columnHeaderIndex);        				
				ErrorReporter.get().fine("rowDataIndex : " + rowDataIndex);				
				ErrorReporter.get().fine("columnHeaderIndex : " + columnHeaderIndex);
				if(!tableDataMapper.isChangeAllowed(rowDataIndex, columnHeaderIndex, this)) continue;
				List<ColumnChange> dependentColumnChanges = identifyChangesForDependentColumns(rowIndex, columnIndex, change.getOldValue(), change.getNewValue());
				setValue(rowDataIndex, columnHeaderIndex, change.getNewValue());
								
				myHistory.add(change);
				change.setPointerIndex(changePointer+myHistory.size());
				change.setOldPointerIndex(updatedRowChangeIndex.get(rowDataIndex, columnHeaderIndex));
				updatedRowChangeIndex.index(changePointer+myHistory.size(), rowDataIndex, columnHeaderIndex);

				if(dependentColumnChanges != null && !dependentColumnChanges.isEmpty()) {
					for(ColumnChange columnChange : dependentColumnChanges) {
						Change change1 = new Change(ChangeType.MOD, rowIndex, columnChange.getColIndex(), columnChange.getOldValue(), columnChange.getNewValue(), null, true, true);
						change1.setRowDataIndex(rowDataIndex);
						change1.setColumnHeaderIndex(columnHeaderView.get(columnChange.getColIndex()));
						setValue(rowDataIndex, columnHeaderView.get(columnChange.getColIndex()), change1.getNewValue());

						myHistory.add(change1);
						change1.setPointerIndex(changePointer+myHistory.size());
						change1.setOldPointerIndex(updatedRowChangeIndex.get(rowDataIndex, columnHeaderIndex));
						updatedRowChangeIndex.index(changePointer+myHistory.size(), rowDataIndex, columnHeaderIndex);
						
					}                    		
				}

			} else if(ChangeType.DEL == change.getChangeType()){
				int rowDataIndex = rowDataView.get(rowIndex);
				change.setRowDataIndex(rowDataIndex);
				ErrorReporter.get().fine("rowDataIndex : " + rowDataIndex);    
				deletedRowIndexes.add(rowDataIndex);
				rowDataView.remove(rowIndex);
				
				myHistory.add(change);
				change.setPointerIndex(changePointer+myHistory.size());
				change.setOldPointerIndex(deletedRowChangeIndex.get(rowDataIndex));
				deletedRowChangeIndex.index(rowDataIndex, changePointer+myHistory.size());
				
			} else {		
				//Add or copy part
				int rowDataIndex = addRow(change.getRow() == null ? populateEmptyValues() : change.getRow());
				change.setRowDataIndex(rowDataIndex);
				ErrorReporter.get().fine("rowDataIndex : " + rowDataIndex);
				rowDataView.add(rowIndex, rowDataIndex);
				
				myHistory.add(change);
				change.setPointerIndex(changePointer+myHistory.size());
				change.setOldPointerIndex(addedRowChangeIndex.get(rowDataIndex));
				addedRowChangeIndex.index(rowDataIndex, changePointer+myHistory.size());
				
			}
		}
        if(changes.isGroupBoundaryToBeMarked()) markGroupUndoRedoBoundary(myHistory);    		
		recordChanges(myHistory);
	}

	private String[] populateEmptyValues() {
		int columnCount = tableDataMapper.getColumnCount();
		String[] values = new String[columnCount];
		for(int i = 0; i < columnCount; i++) {
			values[i] = "";
		}
		return values;
	}	
	
	public Changes convertToChanges(List<T> newRows) {
		List<Change> history = new ArrayList<Change>();
		int rowIndex = getCurrentRowCount();
		
		for(T rowData : newRows) {
			int columnCount = tableDataMapper.getColumnCount();
			String[] row = new String[columnCount];
			for(int columnIndex = 0; columnIndex < columnCount; columnIndex++) {				
				row[columnIndex] = tableDataMapper.convertColumn(columnIndex, tableDataMapper.getColumnValue(columnIndex, rowData));			
			}	
			history.add(new Change(ChangeType.ADD, rowIndex, -1, null, null, row, true, true));
			rowIndex++;
		}
		markGroupUndoRedoBoundary(history);
		return new Changes(history);
	}

	public Integer adjustSavePointer() {
		savePointer = changePointer;
		if(changePointer == -1) return -1;
		Change change = changesHistory.get(changePointer);
		return change.getRowIndex();
	}	

	public List<ChangeRecord> getChanges(List<Integer> columnIndexes, boolean isRowIndicesNeeded) {
		int numberOfColumns = columnIndexes.size();

		List<ChangeRecord> changesList = new ArrayList<ChangeRecord>();
		if(changesHistory.isEmpty()) return changesList;
		List<Change> changes = new ArrayList<Change>();
		if (savePointer < changePointer) {			
			changes = new ArrayList<Change>(changesHistory.subList(savePointer+1, changePointer+1));
		} 
		if(changes.isEmpty()) return changesList;
		Collections.sort(changes, new ChangeComparator());
		
		List<IndexItem<ChangeRecord>> indexedChangesList = new ArrayList<IndexItem<ChangeRecord>>();
		Iterator<Change> iterator = changes.iterator();
		Change lastItem = iterator.next();
		while (iterator.hasNext()) {
			Change item = iterator.next();
			if (lastItem.getRowDataIndex() == item.getRowDataIndex()) continue;
			createAndAddChangeRecord(columnIndexes, numberOfColumns, indexedChangesList, lastItem, isRowIndicesNeeded);
			lastItem = item;
		}
		createAndAddChangeRecord(columnIndexes, numberOfColumns, indexedChangesList, lastItem, isRowIndicesNeeded);
		Collections.sort(indexedChangesList);
		for(IndexItem<ChangeRecord> item:indexedChangesList){
			changesList.add(item.getItem());
		}
		return changesList;
	}

	private void createAndAddChangeRecord(List<Integer> columnIndexes, int numberOfColumns, List<IndexItem<ChangeRecord>> indexedChangesList, Change change, boolean isRowIndicesNeeded) {
		if(change.getChangeType().ordinal() <=  ChangeType.MOD.ordinal()){
			ChangeRecord changeRecord = createChangeRecord(columnIndexes, numberOfColumns, change, isRowIndicesNeeded);
			if(changeRecord != null) {
				indexedChangesList.add(new IndexItem<ChangeRecord>(changeRecord, change.getPointerIndex()));
			}
		}
	}

	private ChangeRecord createChangeRecord(List<Integer> columnIndexes, int numberOfColumns, Change change, boolean isRowIndicesNeeded) {
	    Integer rowDataIndex = change.getRowDataIndex();
	    ChangeType type = change.getChangeType();
	    String[] oldRow = null;
	    String[] newRow = null;
	    if(type == ChangeType.DEL) {
	    	if(rowDataIndex >= data.size()) {
	    		if(addedRowChangeIndex.get(rowDataIndex) == null) return null; //Added row was deleted
	    	} else {
	    		if(deletedRowChangeIndex.get(rowDataIndex) == null) return null; //Deleted row was re-added
	    	}
	        oldRow = getOldRow(columnIndexes, numberOfColumns, rowDataIndex);
	    } else if(type == ChangeType.ADD) {
	        newRow = getNewRow(columnIndexes, numberOfColumns, rowDataIndex);	
	    } else {
	        oldRow = getOldRow(columnIndexes, numberOfColumns, rowDataIndex);
	        newRow = getNewRow(columnIndexes, numberOfColumns, rowDataIndex);
	        if(Arrays.equals(oldRow, newRow)) return null;
	    }
	    int rowViewIndex = -1;
	    if(isRowIndicesNeeded) rowViewIndex = rowDataView.indexOf(rowDataIndex);
	    ChangeRecord changeRecord = new ChangeRecord(oldRow, newRow, rowViewIndex, rowDataIndex);
	    return changeRecord;
	}

	private String[] getNewRow(List<Integer> columnIndexes, int numberOfColumns, int rowDataIndex) {
		String[] newRow = new String[numberOfColumns];
	    int j = 0;	    
	    for(int columnIndex : columnIndexes) {	
			Object newValue = updatedValues.get(rowDataIndex, columnIndex);			
			if(newValue != null) {
			    if(newValue == NULL) { 
			    	newRow[j++] = "";
			    } else { 
			    	newRow[j++] = tableDataMapper.convertColumn(columnIndex, newValue);
			    }
			} else {
				newRow[j++] = tableDataMapper.convertColumn(columnIndex, getOldValue(rowDataIndex, columnIndex));
			}
	    }
	    return newRow;
	}

	private String[] getOldRow(List<Integer> columnIndexes, int arrSize, int rowDataIndex) {
		String[] oldRow = new String[arrSize];
	    int j = 0;	    
	    for(int columnIndex : columnIndexes) {
	    	String cellStrValue = tableDataMapper.convertColumn(columnIndex, getAdjustedOldValue(rowDataIndex, columnIndex));
	    	oldRow[j++] = cellStrValue;					
	    }
	    return oldRow;
	}

	private Object getAdjustedOldValue(int rowDataIndex, int columnIndex) {
		Object cellValue = null;
		if(rowDataIndex >= data.size()) {
			cellValue = addedRows.get(rowDataIndex - data.size())[columnIndex];
		} else {
			T rowData = data.get(rowDataIndex);
			cellValue = tableDataMapper.getColumnValue(columnIndex, rowData);
		}
		cellValue = getAdjustedOldValue(rowDataIndex, columnIndex, cellValue);
		return cellValue;
	}

	private Object getAdjustedOldValue(int rowDataIndex, int columnIndex, Object cellValue) {
		Integer cellLastUpdatedPointer = updatedRowChangeIndex.get(rowDataIndex, columnIndex);
		while(cellLastUpdatedPointer != null && cellLastUpdatedPointer > savePointer) {
			cellLastUpdatedPointer = changesHistory.get(cellLastUpdatedPointer).getOldPointerIndex();
		}
		if(cellLastUpdatedPointer != null) {
			String cellStrValue = changesHistory.get(cellLastUpdatedPointer).getNewValue();
			cellValue = tableDataMapper.convertColumnValue(columnIndex, cellStrValue);			
		}
		return cellValue;
	}

	public int undo() {
		int rtnVal = -1;
		while(changePointer > savePointer) {
			Change change = changesHistory.get(changePointer--);
			if(change != null) {
				undoChange(change);
				if(change.isGroupUndoAction()) {
					continue;
				} else {
					rtnVal = change.getRowIndex();
					break;
				}
			}
		}
		return rtnVal;
	}

	private void undoChange(Change change) {
		ChangeType changeType = change.getChangeType();
		ErrorReporter.get().fine("UNDO TYPE : " + changeType);
		ErrorReporter.get().fine("history # : " + changePointer);
		ErrorReporter.get().fine("rowIndex : " + change.getRowIndex());
		ErrorReporter.get().fine("rowDataIndex : " + change.getRowDataIndex());
		
		if(changeType == ChangeType.MOD) {
			Integer rowDataIndex = change.getRowDataIndex();
			Integer columnHeaderIndex = change.getColumnHeaderIndex();
			String oldValue = change.getOldValue();
			setValue(rowDataIndex, columnHeaderIndex, oldValue);
			
			updatedRowChangeIndex.index(change.getOldPointerIndex(), rowDataIndex, columnHeaderIndex);
			
		} else if(changeType == ChangeType.DEL) {
			int rowIndex = change.getRowIndex();
			Integer rowDataIndex = change.getRowDataIndex();
			deletedRowIndexes.remove(rowDataIndex);
			rowDataView.add(rowIndex, rowDataIndex);

			deletedRowChangeIndex.index(rowDataIndex, change.getOldPointerIndex());

		} else if(changeType == ChangeType.ADD) {
		    // Add or Copy change type
			int rowIndex = change.getRowIndex();
			int rowDataIndex = change.getRowDataIndex(); 
			rowDataView.remove(rowIndex);	

			addedRowChangeIndex.index(rowDataIndex, change.getOldPointerIndex());

		} else if(changeType == ChangeType.FILTER_SORT) {
			rowDataView = change.getOldRowViewList();
		}
	}

	public int redo() {
		int rtnVal = -1;
		while(changePointer >= -1 && (changePointer < changesHistory.size() - 1)) {
			Change change = changesHistory.get(++changePointer);
			if(change != null) {
				redoChange(change);
				if(change.isGroupRedoAction()) {
					continue;
				} else {
					rtnVal = change.getRowIndex();
					break;
				}
			}
		}
		return rtnVal;
	}

	private void redoChange(Change change) {
	    ChangeType changeType = change.getChangeType();
		ErrorReporter.get().fine("REDO TYPE : " + changeType);
		ErrorReporter.get().fine("history # : " + changePointer);
		ErrorReporter.get().fine("rowIndex : " + change.getRowIndex());
		ErrorReporter.get().fine("rowDataIndex : " + change.getRowDataIndex());

		if(changeType == ChangeType.MOD) {
			Integer rowDataIndex = change.getRowDataIndex();
			Integer columnHeaderIndex = change.getColumnHeaderIndex();
			String newValue = change.getNewValue();
			setValue(rowDataIndex, columnHeaderIndex, newValue);

			updatedRowChangeIndex.index(change.getPointerIndex(), rowDataIndex, columnHeaderIndex);
			
		} else if(changeType == ChangeType.DEL) {
			int rowIndex = change.getRowIndex();
			Integer rowDataIndex = change.getRowDataIndex();
			deletedRowIndexes.add(rowDataIndex);
			rowDataView.remove(rowIndex);

			deletedRowChangeIndex.index(rowDataIndex, change.getPointerIndex());

		} else if(changeType == ChangeType.ADD) {
		    // Add or Copy change type
			int rowIndex = change.getRowIndex();
			int rowDataIndex = change.getRowDataIndex();
			rowDataView.add(rowIndex, rowDataIndex);

			addedRowChangeIndex.index(rowDataIndex, change.getPointerIndex());
		} else if(changeType == ChangeType.FILTER_SORT) {
			rowDataView = change.getNewRowViewList();
		}
	}
	
	private void recordChanges(List<Change> history) {	    	
		if(changePointer != -1 && changePointer < changesHistory.size() - 1 && savePointer > changePointer) {
			savePointer = changePointer;
		}
		for(int i = changesHistory.size() - 1; i > changePointer; i--) {
			changesHistory.remove(i);
		}
		changesHistory.addAll(history);
		if(history.size() != 0) {
			changePointer = changesHistory.size() - 1;
		}
	}

	public boolean revertRowChanges(int rowViewIndex) {
		Integer rowDataIndex = rowDataView.get(rowViewIndex);
		List<Change> revertHistory = new ArrayList<Change>();		
		for(int j = 0; j < columnHeaderView.size(); j++) {
			int columnHeaderIndex = columnHeaderView.get(j);
			Integer updatedPointerIndex = updatedRowChangeIndex.get(rowDataIndex, columnHeaderIndex);
			
			if(updatedPointerIndex != null && updatedPointerIndex > savePointer) {
				Object value = updatedValues.get(rowDataIndex, columnHeaderIndex);
				if(value == NULL) value = null;
				String newCellStrValue = tableDataMapper.convertColumn(columnHeaderIndex, value);
				String adjustedOldCellStrValue = tableDataMapper.convertColumn(columnHeaderIndex, getAdjustedOldValue(rowDataIndex, columnHeaderIndex));
				
				Change change = new Change(ChangeType.MOD, rowViewIndex, columnHeaderIndex, newCellStrValue, adjustedOldCellStrValue, null, true, true);
				change.setRowDataIndex(rowDataIndex);
				change.setColumnHeaderIndex(columnHeaderIndex);
				setValue(rowDataIndex, columnHeaderIndex, adjustedOldCellStrValue);
				revertHistory.add(change);
				
				change.setPointerIndex(changePointer + revertHistory.size());
				change.setOldPointerIndex(updatedRowChangeIndex.get(rowDataIndex, columnHeaderIndex));
				//updatedRowChangeIndex.index(changePointer + revertHistory.size(), rowDataIndex, columnHeaderIndex);
				updatedRowChangeIndex.index(null, rowDataIndex, columnHeaderIndex);
			}
		}
		markGroupUndoRedoBoundary(revertHistory);
		recordChanges(revertHistory);
		return true;
	}
	
	public boolean revertAddedRows(List<Integer> rowViewIndices) {
		Collections.sort(rowViewIndices, new Comparator<Integer>() {
			@Override
			public int compare(Integer rowViewIndex1, Integer rowViewIndex2) {
				return rowViewIndex2 - rowViewIndex1;
			}
		});
		List<Change> revertHistory = new ArrayList<Change>();		
		for(Integer rowViewIndex : rowViewIndices) {
			Integer rowDataIndex = rowDataView.get(rowViewIndex);
			
			Change change = new Change(ChangeType.DEL, rowViewIndex, -1, "", "", null, true, true);
			change.setRowDataIndex(rowDataIndex);
			change.setColumnHeaderIndex(-1);
			rowDataView.remove(rowViewIndex.intValue());
			deletedRowIndexes.add(rowDataIndex);
			revertHistory.add(change);		
			
			change.setPointerIndex(changePointer + revertHistory.size());
			change.setOldPointerIndex(deletedRowChangeIndex.get(rowDataIndex));

			deletedRowChangeIndex.index(rowDataIndex, changePointer + revertHistory.size());
			addedRowChangeIndex.index(rowDataIndex, null);
		}
		markGroupUndoRedoBoundary(revertHistory);
		recordChanges(revertHistory);
		return true;
	}
	
	public boolean revertDeletedRows(List<Integer> rowDataIndices) {
		//return deletedRowIndexes.remove(rowDataIndices);
		for(Integer rowDataIndex : rowDataIndices) {
			deletedRowChangeIndex.index(rowDataIndex, null);
		}
		return true;
	}
	
	public int findRecord(int findFromRowIndex, int columnViewIndex, String columnValue) {
		Map<String,String> columnViewIndexValueMap = new HashMap<String, String>(1);
		columnViewIndexValueMap.put(tableDataMapper.getColumnConfig(columnHeaderView.get(columnViewIndex)).getName(), columnValue);
		return findRecord(findFromRowIndex, columnViewIndexValueMap);
	}

	public int findRecord(int findFromRowIndex, Map<String,String> columnViewIndexValueMap) {
		int index = 0;
		if(findFromRowIndex < rowDataView.size()) {
			index = findFromRowIndex;
		}
		int value =  getRowIndex(index, rowDataView.size(), columnViewIndexValueMap);
		if(value!=-1) return value;
		return getRowIndex(0, findFromRowIndex, columnViewIndexValueMap);
	}

	private int getRowIndex(int findFromRowIndex, int toIndex, Map<String, String> columnViewIndexValueMap) {
		for(; findFromRowIndex < toIndex; findFromRowIndex++) {
			int rowViewIndex = findFromRowIndex;
			int rowDataIndex = rowDataView.get(rowViewIndex);
			boolean same = true;
			for(String columnName : columnViewIndexValueMap.keySet()){//checking selected columns in a row
				int columnHeaderIndex = tableDataMapper.getColumnIndex(columnName);
				String currentValue = tableDataMapper.convertColumn(columnHeaderIndex, getDataValue(rowDataIndex, columnHeaderIndex));
				if(currentValue.equalsIgnoreCase(columnViewIndexValueMap.get(columnName))) {
					continue;
				}else{
					same = false;
					break;
				}
			}
			if(same) return rowViewIndex;
		}
		return -1;
	}

	public List<Integer> getFilteredRowDataIndices(FilterCriteria filterCriteria) {
		if(filterCriteria == null || filterCriteria.getFilterData() == null
				|| filterCriteria.getFilterData().isEmpty()) {
			return rowDataView;
		}
		List<FilterConfig> filterData = filterCriteria.getFilterData();
		List<Condition<Object>> conditions = getConditionsFromFilterData(filterData);
		List<Integer> filteredRowDataIndices = new ArrayList<Integer>();
		Iterator<Integer> iterator = rowDataView.iterator();
		while(iterator.hasNext()) {
			int rowDataIndex = iterator.next();
			int i = 0;
			for(; i < filterData.size(); i++) {
				int columnViewIndex = filterData.get(i).getColumnIndex();
				int columnHeaderIndex = columnHeaderView.get(columnViewIndex);
				if(!conditions.get(i).satisfied(getDataValue(rowDataIndex, columnHeaderIndex))) {
					break;
				}
			}
			if(i == filterData.size())
				filteredRowDataIndices.add(rowDataIndex);
		}
		return filteredRowDataIndices;
	}
	
	public List<String[]> getSpecifiedTableData(List<Integer> rowDataIndices, List<Integer> columnIndices) {
		if(columnIndices == null || columnIndices.isEmpty()) {
			return getSpecifiedTableDataForAllColumns(rowDataIndices);
		}
		List<String[]> rtnValue = new ArrayList<String[]>();
		for(int i = 0; i < rowDataIndices.size(); i++)
		{
			int rowDataIndex = rowDataIndices.get(i);
			String[] rowData = new String[columnIndices.size()];
			for(int j = 0; j < columnIndices.size(); j++) {
				Integer columnViewIndex = columnIndices.get(j);
				if(columnViewIndex == null) {
					rowData[j] = null;					
				} else {
					int columnHeaderIndex = columnHeaderView.get(columnViewIndex);
					try {
						rowData[j] = getSpecifiedValue(rowDataIndex, columnHeaderIndex);
					} catch (IndexOutOfBoundsException e) {
						ErrorReporter.get().warning("Trying to access more rows/columns than existing");
						return rtnValue;
					}
				}
			}
			// rowData = compactStrings(rowData);
			rtnValue.add(rowData);
		}
		return rtnValue;
	}

	private List<String[]> getSpecifiedTableDataForAllColumns(List<Integer> rowDataIndices) {
		List<String[]> rtnValue = new ArrayList<String[]>(rowDataIndices.size());
		for(int i = 0; i < rowDataIndices.size(); i++) {
			int rowDataIndex = rowDataIndices.get(i);
			String[] rowData = new String[columnHeaderView.size()];
			for(int j = 0; j < columnHeaderView.size(); j++) {
				int columnHeaderIndex = columnHeaderView.get(j);
				try {					
					rowData[j] = getSpecifiedValue(rowDataIndex, columnHeaderIndex);
				} catch (IndexOutOfBoundsException e) {
					ErrorReporter.get().warning("Trying to access more rows/columns than existing");
					return rtnValue;
				}
			}
			// rowData = compactStrings(rowData);
			rtnValue.add(rowData);
		}
		return rtnValue;
	}

	@SuppressWarnings("unused")
	private static String [] compactStrings (String [] strings) {
		String [] result = new String [strings.length];
		
		int offset = 0;
		for (int i = 0; i < strings.length; ++ i) {
			offset += strings [i].length ();
		}

		// Can't use StringBuffer due to how it manages capacity
		char [] allchars = new char [offset];

		offset = 0;
		for (int i = 0; i < strings.length; ++ i) {
			strings [i].getChars (0, strings [i].length (), allchars, offset);
			offset += strings [i].length ();
		}

		String allstrings = new String (allchars);
		offset = 0;
		for (int i = 0; i < strings.length; ++ i) {
			result [i] = allstrings.substring (offset, offset += strings [i].length ());
		}
		return result;
	}
	
	private String getSpecifiedValue(int rowDataIndex, int columnHeaderIndex) throws IndexOutOfBoundsException {
		return tableDataMapper.convertColumn(columnHeaderIndex, getDataValue(rowDataIndex, columnHeaderIndex));
	}

	private List<String[]> getPage(int startRow, int endRow) {
		List<String[]> rtnValue = new ArrayList<String[]>(endRow-startRow+1);
		for(int i = startRow; i <= endRow; i++)
		{
			String[] rowData = new String[columnHeaderView.size()];
			for(int j = 0; j < columnHeaderView.size(); j++) {
				try {
					rowData[j] = getValue(i, j);
				} catch (IndexOutOfBoundsException e) {
					ErrorReporter.get().warning("Trying to access more rows than existing");
					return rtnValue;
				}
			}
			rtnValue.add(rowData);
		}
		return rtnValue;
	}
	
	private String getValue(int rowViewIndex, int columnViewIndex) throws IndexOutOfBoundsException {
		int rowDataIndex = rowDataView.get(rowViewIndex);
		int columnHeaderIndex = columnHeaderView.get(columnViewIndex);
		return tableDataMapper.convertColumn(columnHeaderIndex, getDataValue(rowDataIndex, columnHeaderIndex));
	}

	public Object getDataValue(int rowDataIndex, int columnHeaderIndex) {
		Object rtnValue = updatedValues.get(rowDataIndex, columnHeaderIndex);
		
		if(rtnValue != null){
		    if(rtnValue == NULL) 
			return null;
		    else 
			return rtnValue;
		}
		
		return getOldValue(rowDataIndex, columnHeaderIndex);
	}

	private Object getOldValue(int rowDataIndex, int columnHeaderIndex) {
	    if(rowDataIndex < data.size()) {
	    	T rowData = data.get(rowDataIndex);
	    	return tableDataMapper.getColumnValue(columnHeaderIndex, rowData);
	    }
	    else {
	    	return addedRows.get(rowDataIndex - data.size())[columnHeaderIndex];
	    }
	}
	
	private String getModifiedValue(int rowViewIndex, int columnViewIndex, String modifyFactor) throws IndexOutOfBoundsException {
		int rowDataIndex = rowDataView.get(rowViewIndex);
		int columnHeaderIndex = columnHeaderView.get(columnViewIndex);
		return tableDataMapper.getModifiedColumn(columnHeaderIndex, getDataValue(rowDataIndex, columnHeaderIndex), modifyFactor);
	}

	private int addRow(String[] values) {
		Object[] objectValues = new Object[tableDataMapper.getColumnCount()];
		for(int i = 0; i < values.length; i++) {
			Integer columnHeaderIndex = columnHeaderView.get(i);
			objectValues[columnHeaderIndex] = tableDataMapper.convertColumnValue(columnHeaderIndex, values[i]);
		}
		addedRows.add(objectValues);
		return addedRows.size()+data.size()-1;
	}

	private void setValue(Integer rowDataIndex, Integer columnHeaderIndex, String value) {
		ErrorReporter.get().fine("row : " + rowDataIndex);
		ErrorReporter.get().fine("col : " + columnHeaderIndex);
		ErrorReporter.get().fine("value : " + value);
		
		Object convertColumnValue = tableDataMapper.convertColumnValue(columnHeaderIndex, value);
		if(convertColumnValue == null) convertColumnValue = NULL;
		updatedValues.index(convertColumnValue, rowDataIndex, columnHeaderIndex);
	}	

	public int getCurrentRowCount() {
		return rowDataView.size();
	}

	public int getTableDataSetId() {
		return this.tableDataSetId;		
	}

	public TableDataMapper<T, ?> getTableDataMapper() {
		return this.tableDataMapper;
	}
}
