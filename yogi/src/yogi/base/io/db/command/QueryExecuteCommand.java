package yogi.base.io.db.command;

import java.util.List;

import yogi.base.io.db.DBException;
import yogi.base.io.db.QueryExecutor;
import yogi.base.io.resource.db.DbResource;
import yogi.remote.client.app.CommandAdapter;
import yogi.server.util.BaseServerAssistant;

public abstract class QueryExecuteCommand extends CommandAdapter<List<Object[]>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1739645111055385916L;
	private String query;
	private Object[] parameters;

	public QueryExecuteCommand(String query, Object[] parameters) {
		super(null);
		this.query = query;
		this.parameters = parameters;
	}

	public QueryExecuteCommand(String query) {
		this(query, new Object[0]);
	}
	@Override
	public List<Object[]> execute() {
		try {
			return QueryExecutor.get().executeQuery(getDbResource(), query, parameters);
		} catch (DBException e) {
			throw new RuntimeException(e);
		}
	}

	public DbResource getDbResource(){
		return BaseServerAssistant.get().getDbResource();
	}
	
	
}
