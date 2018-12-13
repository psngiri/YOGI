
package yogi.report.server;

import java.util.List;

import yogi.base.util.store.Element;
import yogi.base.util.store.secure.SecureListStore;
import yogi.report.server.config.ReportConfig;
import yogi.report.server.config.ReportConfigProvider;

public class ReportServerImpl  implements ReportServer{
	public static int MaxNumberOfRowsForGroupReport=1000;
	public static int MaxNumberOfRowsForPagingReport=100000;
    private static ReportServer itsInstance = new ReportServerImpl();
    
	private SecureListStore<ReportDataItems<?>,String> queries=new SecureListStore<ReportDataItems<?>,String>() ;
	
	public static ReportServer get()
	{
		return itsInstance;
	}
	
	public ReportTableData generateReport(Query query, int queryId, String userId) {
		ReportDataItems<?> reportDataItems = null;
		reportDataItems = getReportDataItems(query, queryId, userId);
		return reportDataItems.generateReport(query);
	}

	public ReportTableData generateReport(Query query, String userId) {
		ReportDataItems<?> reportDataItems = null;
		List<Column> selectedColumns = query.getSelectedColumns();
		int queryId = queries.generateKey();
		boolean groupBy = false;
		for(Column column: selectedColumns){
			if(column.isGroupBy()){ 
				groupBy = true;
				break;
			}
		}
		if(groupBy){
			reportDataItems = getReportDataItems(query,-1, userId);
		}
		else{
			reportDataItems = new BaseReportDataItems<Object>(queryId, userId, new ReportConfigProvider<Object>(query.getReportName()));
		}
		return reportDataItems.generateReport(query);
	}
	

	
	public synchronized ReportDataItems<?> getReportDataItems(Query query, int queryId, String userId) {
		ReportDataItems<?> reportDataItems;
		if(query.isGroupReport()){
			if(queryId == -1){
				queryId = queries.generateKey();
			}
			reportDataItems = new BaseReportDataItems<Object>(queryId, userId, new ReportConfigProvider<Object>(query.getReportName()), MaxNumberOfRowsForGroupReport);
			queries.set(queryId, reportDataItems,userId);
		}else
		{
			reportDataItems = new BaseReportDataItems<Object>(queryId, userId, new ReportConfigProvider<Object>(query.getReportName()), MaxNumberOfRowsForPagingReport);
		}
		return reportDataItems;
	}


	@Override
	public ReportDataRows expandGroup(int queryId, int groupIndex, List<ReportDataIndex> indexes, String userId) {
		Element<Integer, ReportDataItems<?>> element = queries.get(queryId, userId);
		if(element == null) throw new RuntimeException(String.format("DataSet %s removed from cache rerun", queryId));
		ReportDataItems<?> reportDataItems = element.getValue();
		return reportDataItems.expandGroup(groupIndex, indexes);
	}

	@Override
	public List<String> getReports() {
		
		return ReportConfigProvider.getReports();
	}

	@Override
	public ReportConfig<?> getReportConfiguration(String reportName) {
            return ReportConfigProvider.getReportConfig(reportName); 
	}

	@Override
	public boolean closeReport(int queryId, String userId) {
		try{
			queries.set(queryId, null, userId);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean closeReports() {
		try{
			queries.clear();
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

}