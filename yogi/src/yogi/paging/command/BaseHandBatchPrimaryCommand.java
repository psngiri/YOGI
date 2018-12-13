package yogi.paging.command;

import java.util.ArrayList;
import java.util.List;

import yogi.paging.BaseTableDataMapper;
import yogi.paging.changes.ChangeRecord;
import yogi.paging.column.TableColumnConfig;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandAdapter;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.report.server.ReportDataRows;
import yogi.report.server.ReportTableData;
import yogi.report.server.command.GenerateReportCommand;

public abstract class BaseHandBatchPrimaryCommand extends CommandAdapter<List<Integer>> {
    
	private static final long serialVersionUID = 548374695723177038L;
	public static String PAGING = "Paging";
	protected List<Integer> idList;
	protected Query query;
	private transient ColumnNameConfig inputColumnNameConfig;
	
	public BaseHandBatchPrimaryCommand(String userId) {
		super(userId);
	}

	public BaseHandBatchPrimaryCommand(List<Integer> idList, Query query, String userId) {
		super(userId);
		this.idList = idList;
		this.query = query;
	}
	
	protected boolean isQuery(){
		return false;
	}
	
	@Override
	public List<Integer> execute() {	
		initialize();	
		List<ChangeRecord> changesList = getChangesList();
		execute(changesList);
		adjustSavePointer();
		return idList;
	}
		
	private void adjustSavePointer() {
		AdjustSavePointerCommand adjustSavePointerCommand = new AdjustSavePointerCommand(getPrimaryDataSetId(), getUserId());
		try {
			MultiServerCommandExecutor.get().execute(PAGING, adjustSavePointerCommand);
		} catch (CommandException e) {
			throw new RuntimeException("Exception occured while trying to adjust save pointer", e);			
		}	
	}

	protected abstract void execute(List<ChangeRecord> changesList);

	protected abstract void initialize();
		
	protected List<ChangeRecord> getChangesList() {
		List<ChangeRecord> changesList;		
		if(isQuery()){
			ReportTableData reportTableData;
			try {
				reportTableData = MultiServerCommandExecutor.get().execute(query.getServerType(), new GenerateReportCommand(query, getUserId()));
			} catch (CommandException e) {
				throw new RuntimeException(e);
			}
			changesList = convertToChangeRecord(reportTableData);
		}else{
			changesList = getPrimaryTablePageChanges();
			if(changesList.isEmpty()) {
				throw new RuntimeException("Please make sure there are some changes in the query report");
			}
		}
		return changesList;
	}
	
	protected List<ChangeRecord> convertToChangeRecord(ReportTableData reportTableData) {
		generateColumnMap(reportTableData);
		List<ChangeRecord> crList = new ArrayList<ChangeRecord>();
		ChangeRecord cr = null;
		String[] oldValue = null;
		String[] newValue = null;
		int columnSize = inputColumnNameConfig.getSize();
		
		for(ReportData reportData : ((ReportDataRows) reportTableData).getRows()){
		    	newValue = new String[columnSize];
		    	int j = 0;
		    	for(int columnIndex : inputColumnNameConfig.getColumnIndexes()) {
				newValue[j++] = reportData.getValue(columnIndex).toString();
		    	}
			cr = new ChangeRecord(oldValue, newValue);
			crList.add(cr);			
		}
		return crList;
	}	
	
	public void setInputColumnNameConfig(ColumnNameConfig inputColumnNameConfig) {
		this.inputColumnNameConfig = inputColumnNameConfig;
	}

	private void generateColumnMap(ReportTableData reportTableData) {
		List<TableColumnConfig<?>> columnConfigs = null;
		columnConfigs = (reportTableData != null) ? reportTableData.getTableColumnConfigs() : getPrimaryTableDataMapper().getTableColumnConfigs();
		int index = 0;
		for (TableColumnConfig<?> config : columnConfigs) {		    	
			inputColumnNameConfig.processColumn(config.getName(), index++);
		}
		inputColumnNameConfig.validateColumns();			
	}	
	
	protected List<ChangeRecord> getPrimaryTablePageChanges() {
		generateColumnMap(null);
		List<ChangeRecord> changeList;
		try {			    	
		    changeList = (List<ChangeRecord>) MultiServerCommandExecutor.get().execute(PAGING, new GetTablePageChangesCommand(getPrimaryDataSetId(), getUserId(), inputColumnNameConfig.getColumnIndexes()));
		} catch (CommandException e) {
			throw new RuntimeException(e);
		}		
		return changeList;
	}	
	
	protected BaseTableDataMapper<?, ?> getPrimaryTableDataMapper() {
		BaseTableDataMapper<?, ?> primaryTableDataMapper;
		try {
			primaryTableDataMapper = (BaseTableDataMapper<?, ?>)MultiServerCommandExecutor.get().execute(PAGING, new GetTableDataMapperCommand(getPrimaryDataSetId(),getUserId()));		
		} catch (CommandException e) {
			throw new RuntimeException(e);
		}
		return primaryTableDataMapper;
	}
	
	protected BaseTableDataMapper<?, ?> getTableDataMapper(Integer id) {
		BaseTableDataMapper<?,?> tableDataMapper;
		try {
			tableDataMapper = (BaseTableDataMapper<?, ?>)MultiServerCommandExecutor.get().execute(PAGING, new GetTableDataMapperCommand(id,getUserId()));		
		} catch (CommandException e) {
			throw new RuntimeException(e);
		}
		return tableDataMapper;
	}
	
	public Integer getPrimaryDataSetId() {
		return idList.isEmpty() ? null : idList.get(0);
	}
}