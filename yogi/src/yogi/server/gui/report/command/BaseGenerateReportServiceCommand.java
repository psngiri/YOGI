package yogi.server.gui.report.command;

import java.util.ArrayList;
import java.util.List;

import yogi.base.app.Executor;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandAdapter;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.report.server.Filter;
import yogi.report.server.Query;
import yogi.server.gui.report.Report;


public abstract class BaseGenerateReportServiceCommand<R> extends CommandAdapter<R> {

	private static final long serialVersionUID = -6849748273507429406L;
	private String name;
	private String partitionCode;
	private String[] filterValues;
	private String[] dataSets;
	private String queryUserId;
	
	public BaseGenerateReportServiceCommand(String name, String queryUserId, String partitionCode, String[] filterValues, String[] dataSets) {
		super(null);
		this.name = name;
		this.queryUserId = queryUserId;
		this.partitionCode = partitionCode;
		this.filterValues = filterValues;
		this.dataSets = dataSets;
	}


	@Override
	public R execute() {
		ReportGetCommand reportGetCommand = new ReportGetCommand(name, queryUserId, partitionCode);
			Report reportData = Executor.get().execute(reportGetCommand);
			Query updatedQuery = getUpdateQuery(reportData);
			CommandAdapter<R> command= getCommand(updatedQuery, queryUserId);
			try {
				return  MultiServerCommandExecutor.get().execute(updatedQuery.getServerType(),command);
			} catch (CommandException e) {
				throw new RuntimeException(e.getMessage(),e);
				//return new ArrayList<Object[]>();
			}
	}


	protected abstract CommandAdapter<R> getCommand(Query updatedQuery, String queryUserId);

	private Query getUpdateQuery(Report reportData) {
		Query query = reportData.getData().getQuery();
		List<Filter> filters = query.getFilters();
		List<Filter> updatedFilters  = new ArrayList<Filter>(filters.size());
		for(int i = 0; i < filterValues.length; i++){
			String filterValue = filterValues[i];
			if(filterValue != null){
				if(i < filters.size()){
					Filter filter = filters.get(i);
					updatedFilters.add(new Filter(filter.getColumnName(), filter.getCondition(), filterValue, filter.getAlias()));
				}
			}
		}
		for(int i = filterValues.length; i < filters.size(); i ++){
			updatedFilters.add(filters.get(i));
		}
		Query updatedQuery = new Query(query.getServerType(), dataSets, query.getReportName(), query.getSelectedColumns(), updatedFilters, query.getGroupFilters(),query.isSingleGrouping(),!query.isGroupReport());
		return updatedQuery;
	}

}