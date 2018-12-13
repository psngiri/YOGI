package yogi.paging.command;


import java.util.ArrayList;
import java.util.List;

import yogi.paging.TableDataMapper;
import yogi.paging.changes.ChangeRecord;
import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.report.server.Query;

public abstract class BaseHandBatchCommand extends BaseHandBatchPrimaryCommand {
    
	private static final long serialVersionUID = 8865001402321447639L;
	protected transient List<OutputDataMapper<?>> outputDataMappers;
	
	public BaseHandBatchCommand(List<Integer> idList, Query query, String userId) {
		super(idList, query, userId);
	}

	protected void addOutputDataMapper(OutputDataMapper<?> outputDataMapper) {
		outputDataMappers.add(outputDataMapper);
	}

	protected void execute(List<ChangeRecord> changesList) {		
		if(changesList == null) return;
		for(int i = 1; i < idList.size(); i++){
			List<?> dataList = generateOutputData(changesList, outputDataMappers.get(i-1));	
			if (idList.get(i) == -1) {
				submitData(i);
				accumulateData(dataList, i);
			} else {
				if( dataList.size() > 0) {			
					accumulateData(dataList, i);
				} 
			}		
		}
	}

	protected void initialize() {
		outputDataMappers = new ArrayList<OutputDataMapper<?>>();		
	}

	private <T> List<T> generateOutputData(List<ChangeRecord> changeList, OutputDataMapper<T> outputDataMapper) {
		List<T> outputDataList = new ArrayList<T>();
		for (ChangeRecord changeRecord : changeList) {
			T data = outputDataMapper.populateOutputData(changeRecord);
			if(data != null) outputDataList.add(data);
		}		
		return outputDataList;
	}	
	
	
	@SuppressWarnings("unchecked")
	protected <T> void submitData(int index) {
		List<T> outputDataList = new ArrayList<T>();
		TableDataMapper<T, ?> dataMapper = (TableDataMapper<T, ?>) outputDataMappers.get(index-1).getTableDataMapper();
		int dataSetId = idList.get(index);
		try {
			dataSetId = MultiServerCommandExecutor.get().execute(PAGING, new SubmitTableDataCommand<T>(outputDataList, dataMapper, dataSetId,getUserId()));
		} catch (CommandException e) {
			throw new RuntimeException(e);
		}
		setSecondaryDataSetId(index, dataSetId);
	}
	
	protected <T> boolean accumulateData(List<T> recommendationList, int index) {
		boolean added = false;
		int dataSetId = idList.get(index);		
		try {
			added = MultiServerCommandExecutor.get().execute(PAGING, new AccumulateToTableDataCommand<T>(recommendationList, dataSetId,getUserId()));
		} catch (CommandException e) {
			throw new RuntimeException(e);
		}
		return added;
	}
	
	protected void setSecondaryDataSetId(int index, int id) {		
		idList.set(index, id);
	}
	
}