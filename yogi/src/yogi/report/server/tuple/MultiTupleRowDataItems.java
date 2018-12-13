package yogi.report.server.tuple;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.base.app.multithread.BaseTask;
import yogi.base.app.multithread.MultiThreadExecutorProcessor;
import yogi.report.server.ColumnAndSelector;
import yogi.report.server.Query;
import yogi.report.server.tuple.io.TupleRowReader;

public class MultiTupleRowDataItems extends TupleReportDataItems{
	LinkedHashMap<String, TupleReportDataItems> tupleRowDataItemsMap = new LinkedHashMap<String, TupleReportDataItems>();


	public MultiTupleRowDataItems(Class<? extends TupleRowReader> klass) {
		super(klass);
		this.reportFileError = false;
	}

	public void add(String reportName, TupleReportDataItems tupleReportDataItems){
		tupleRowDataItemsMap.put(reportName, tupleReportDataItems);
		tupleReportDataItems.reportFileError = false;
	}
	
	@Override
	public List<List<TupleRow>> getItems(Query query, String userId, int maxOutput) {
		int length = query.getObject().length;
		List<List<TupleRow>> rtnValue = new ArrayList<List<TupleRow>>(length);
		for(int i = 0; i < length; i++)
		{
			rtnValue.add(new ArrayList<TupleRow>(0));
		}

		MultiThreadExecutorProcessor<MyCallable> multiThreadExecutorProcessor = new MultiThreadExecutorProcessor<MyCallable>();
		multiThreadExecutorProcessor.addUnit(new MyCallable(ErrorReporter.get(), null, this, query, userId));
		for(Entry<String, TupleReportDataItems> entry :tupleRowDataItemsMap.entrySet()){
			multiThreadExecutorProcessor.addUnit(new MyCallable(ErrorReporter.get(), entry.getKey(), entry.getValue(), query, userId));
		}
		Executor.get().execute(multiThreadExecutorProcessor);
		for(MyCallable callable: multiThreadExecutorProcessor.getUnits()){
			List<List<TupleRow>> items = callable.getItems();
			if(items == null) continue;
			for(int i = 0; i < items.size(); i++){
				List<TupleRow> item = items.get(i);
				if(item != null) rtnValue.get(i).addAll(item);
			}
		}

		return rtnValue;
	}

	
	@Override
	public ColumnAndSelector<TupleRow> enhance(ColumnAndSelector<TupleRow> columnSelector) {
		for(TupleReportDataItems item: tupleRowDataItemsMap.values()){
			item.enhance(columnSelector);
		}
		return super.enhance(columnSelector);
	}

	public static class MyCallable extends BaseTask
	{
		private String reportName;
		private TupleReportDataItems tupleReportDataItems;
		private Query query;
		private String userId;
		private List<List<TupleRow>> items;
		

		protected MyCallable(ErrorReporter errorReporter, String reportName, TupleReportDataItems tupleReportDataItems,
				Query query, String userId) {
			super(errorReporter);
			this.reportName = reportName;
			this.tupleReportDataItems = tupleReportDataItems;
			this.query = query;
			this.userId = userId;
		}
		
		@Override
		public void run() {
			super.run();
			if(reportName != null){
				query = getQuery(query, reportName);
			}else {
				reportName = query.getReportName();
			}
			try {
				items=tupleReportDataItems.getItems(query, userId, reportName);
			} catch (Throwable e) {
				errorReporter.error("Error in MultiTupleRowDataItems", e.getMessage(), e);
				throw e;
			}
		}
		
		private Query getQuery(Query query, String reportName){
			return new Query(query.getServerType(), query.getObject(), reportName, query.getSelectedColumns(), query.getFilters(), query.getGroupFilters(), query.isSingleGrouping(), query.isGroupReport(), query.getCompoundFilter());
		}

		List<List<TupleRow>> getItems() {
			return items;
		}
		
	}

}
