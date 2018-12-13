package yogi.report.server.row.config;


import java.util.ArrayList;
import java.util.List;

import yogi.base.app.ErrorReporter;
import yogi.base.app.multithread.BaseTask;
import yogi.base.app.multithread.ThreadPoolExecutor;
import yogi.report.server.ColumnAndSelector;
import yogi.report.server.Query;
import yogi.report.server.ReportDataItemsProvider;
import yogi.report.server.row.Row;
import yogi.report.server.row.RowExecutor;

public class RowReportDataItems implements ReportDataItemsProvider<Row> {	
	
	private RowExecutor rowExecutor;
	private boolean multiThread;

	public RowReportDataItems(RowExecutor rowExecutor, Boolean multiThread) {
		super();
		this.rowExecutor = rowExecutor;
		this.multiThread = multiThread;
	}


	@Override
	public List<List<Row>> getItems(Query query, String userId, int maxOutput) {
		validate(query);
		long currentTimeMillis = System.currentTimeMillis();
		
		String[] dataSets = query.getObject();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(dataSets.length);
		List<List<Row>> rtnVal = new ArrayList<List<Row>>(dataSets.length);
		List<MyExecutorCallable> callables = new ArrayList<MyExecutorCallable>(dataSets.length);
		try {
			for(int index = 0; index< dataSets.length; index++){
				MyExecutorCallable callable = new MyExecutorCallable(ErrorReporter.get(),rowExecutor,query, index, userId, maxOutput);
				callables.add(callable);
				if(multiThread){
					threadPoolExecutor.execute(callable);
				}else{
					callable.run();
				}
			}
			threadPoolExecutor.waitForTaskCompletion();
			for(MyExecutorCallable callable: callables){
				rtnVal.add(callable.getSparkNSRows());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		ErrorReporter.get().info("Executed in seconds:"+(System.currentTimeMillis()-currentTimeMillis)/1000);
		
		
		return rtnVal;
	}
	

	protected void validate(Query query) {
		if(query.getObject() ==null ||  query.getObject().length==0) throw new RuntimeException("Please select atleast one dataset to generate the report.");
	}
	
	public static class MyExecutorCallable extends BaseTask
	{
		private RowExecutor rowExecutor;
		private Query query;
		private List<Row> sparkNSRows;
		private int index;
		private String userId;
		private int maxOutput;

		public MyExecutorCallable(ErrorReporter errorReporter,
				RowExecutor rowExecutor, Query query, int index, String userId, int maxOutput) {
			super(errorReporter);
			this.rowExecutor = rowExecutor;
			this.query = query;
			this.index = index;
			this.userId =userId;
			this.maxOutput = maxOutput;
		}

		@Override
		public void run() {
			super.run();
			long currentTimeMillis = System.currentTimeMillis();
			sparkNSRows = rowExecutor.execute(query, index, userId, maxOutput);
			ErrorReporter.get().info("Time taken to execute Query "+"("+sparkNSRows.size()+")"+" :" + (System.currentTimeMillis()-currentTimeMillis)/1000);
		}

		public List<Row> getSparkNSRows() {
			return sparkNSRows;
		}

		
	}


	@Override
	public ColumnAndSelector<Row> enhance(ColumnAndSelector<Row> columnSelector) {
		return columnSelector;
	}

}