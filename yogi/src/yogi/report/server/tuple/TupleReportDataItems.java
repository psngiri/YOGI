package yogi.report.server.tuple;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.base.app.multithread.BaseTask;
import yogi.base.app.multithread.MultiThreadExecutorProcessor;
import yogi.report.server.Column;
import yogi.report.server.ColumnAndSelector;
import yogi.report.server.Filter;
import yogi.report.server.Query;
import yogi.report.server.ReportDataItemsProvider;
import yogi.report.server.config.ReportConfigProvider;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.TupleRowReader;


public class TupleReportDataItems implements ReportDataItemsProvider<TupleRow> {	
	private ColumnAndSelector<TupleRow> columnSelector;
	private Class<? extends TupleRowReader> klass;
	protected boolean reportFileError = true;
	
	public TupleReportDataItems(Class<? extends TupleRowReader> klass) {
		super();
		this.klass = klass;
	}

	@Override
	public List<List<TupleRow>> getItems(Query query, String userId, int maxOutput) {
		String reportName = query.getReportName();
		return getItems(query, userId, reportName);
	}

	protected List<List<TupleRow>> getItems(Query query, String userId, String reportName) {
		ReportConfigProvider<TupleRow> reportConfigProvider = new ReportConfigProvider<TupleRow>(reportName);
		List<Column> selectedColumns = query.getSelectedColumns();
		Map<String, Integer> columnIndexMap = new LinkedHashMap<String, Integer>();
		Map<String, TupleRowBaseEvaluator<?>> filterColumnEvaluatorsMap = new LinkedHashMap<String, TupleRowBaseEvaluator<?>>();
		Map<String, TupleRowBaseEvaluator<?>> columnEvaluatorsMap = new LinkedHashMap<String, TupleRowBaseEvaluator<?>>();
		Set<String> filterColumns = new LinkedHashSet<String>();
		MultiThreadExecutorProcessor<DataSetLoaderCallable> multiThreadExecutorProcessor = new MultiThreadExecutorProcessor<DataSetLoaderCallable>();
		String[] dataSets = query.getObject();
		List<List<TupleRow>> rtnVal = new ArrayList<List<TupleRow>>(dataSets.length);
		for(String dataSet: dataSets){
			multiThreadExecutorProcessor.addUnit(createCallable(dataSet, columnIndexMap, filterColumnEvaluatorsMap, columnEvaluatorsMap, userId));
		}
		if(!fileExists(multiThreadExecutorProcessor.getUnits())){
			for(int i = 0; i < multiThreadExecutorProcessor.getUnits().size(); i++){
				rtnVal.add(new ArrayList<TupleRow>());
			}	
			return rtnVal;
		}
		for(Filter filter: query.getValidFilters())
		{
			filterColumns.add(filter.getColumnName());
		}
		
		int i = 0;
		for(String columnName: filterColumns){
			i = popluateMaps(reportConfigProvider, columnName, i, columnIndexMap, filterColumnEvaluatorsMap);			
		}
		columnEvaluatorsMap.putAll(filterColumnEvaluatorsMap);
		for(Column column: selectedColumns){
			String columnName = column.getName();
			if(filterColumns.contains(columnName)) continue;
			i = popluateMaps(reportConfigProvider, columnName, i, columnIndexMap, columnEvaluatorsMap);
		}

		Executor.get().execute(multiThreadExecutorProcessor);
		for(DataSetLoaderCallable callable: multiThreadExecutorProcessor.getUnits()){
			rtnVal.add(callable.getTupleRows());
		}
		return rtnVal;
	}
	
	private boolean fileExists(List<DataSetLoaderCallable> units){
		for(DataSetLoaderCallable callable: units){
			if(callable.fileExists()) return true;
		}
		return false;
	}

	private int popluateMaps(ReportConfigProvider<TupleRow> reportConfigProvider, String columnName, int i, Map<String, Integer> columnIndexMap, Map<String, TupleRowBaseEvaluator<?>> columnEvaluatorsMap) {
		TupleRowBaseEvaluator<?> evaluator = (TupleRowBaseEvaluator<?>) reportConfigProvider.getEvaluator(columnName);
		for(String dependentColumnName: evaluator.getDependentColumnNames()){
			i = popluateMaps(reportConfigProvider, dependentColumnName, i, columnIndexMap, columnEvaluatorsMap);
		}
		if(!columnIndexMap.containsKey(columnName)){
			columnIndexMap.put(columnName, i++);
			columnEvaluatorsMap.put(columnName, evaluator);
		}
		return i;
	}

	private DataSetLoaderCallable createCallable(String dataSet, Map<String, Integer> columnIndexMap, Map<String, TupleRowBaseEvaluator<?>> filterColumnEvaluatorsMap, Map<String, TupleRowBaseEvaluator<?>> columnEvaluatorsMap, String userId){
		TupleRowReader reader;
		try {
			reader = klass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		reader.setUserId(userId);
		reader.setDataSet(dataSet);
		reader.set(columnIndexMap, filterColumnEvaluatorsMap, columnEvaluatorsMap, columnSelector);
		return new DataSetLoaderCallable(ErrorReporter.get(), reader, reportFileError);

	}
	
	@Override
	public ColumnAndSelector<TupleRow> enhance(ColumnAndSelector<TupleRow> columnSelector) {
		this.columnSelector = columnSelector;
		return columnSelector;
	}
	public static class DataSetLoaderCallable extends BaseTask
	{
		private TupleRowReader reader;
		private List<TupleRow> tupleRows;
		private boolean reportFileError;
		public DataSetLoaderCallable(ErrorReporter errorReporter, TupleRowReader reader, boolean reportFileError) {
			super(errorReporter);
			this.reader = reader;
			this.reportFileError = reportFileError;
		}

		@Override
		public void run() {
			super.run();
			boolean canRead = fileExists();
			if(canRead){
				try {
					tupleRows = Executor.get().execute(reader);
				} catch (Throwable e) {
					errorReporter.error("Error in DataSetLoaderCallable", e.getMessage(), e);
					throw e;
				}
			}
		}

		public boolean fileExists() {
			boolean canRead = true;
			if(!reportFileError){
				try {
					canRead = reader.getFileResource().canRead();
				} catch (Throwable e) {
					canRead = false;
				}
			}
			return canRead;
		}

		List<TupleRow> getTupleRows() {
			return tupleRows;
		}
		
	}
}