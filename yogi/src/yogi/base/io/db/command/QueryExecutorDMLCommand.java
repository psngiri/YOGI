package yogi.base.io.db.command;

import java.sql.SQLException;

import yogi.base.io.db.dump.DumpProperties;
import yogi.base.io.resource.db.SingleConnectionDbResource;
import yogi.remote.client.app.CommandAdapter;
import yogi.server.util.BaseServerAssistant;

/**
 * @author Vikram Vadavala
 *
 */
public abstract class QueryExecutorDMLCommand<T> extends CommandAdapter<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient SingleConnectionDbResource dbResource = null;

	public QueryExecutorDMLCommand(String userId) {
		super(userId);
	}
	
	private void initiliazeSingleConnectionDbResource(){
			dbResource = new SingleConnectionDbResource(BaseServerAssistant.get().getDbResource());
	}

	@Override
	public final T execute() {
		initiliazeSingleConnectionDbResource();
		try{
			T rtnValue = executeCommand();
			if(dbResource != null) {
				if (DumpProperties.LoadToDb) {
					dbResource.commit();
				} else {
					dbResource.rollback();
				}
			}
			return rtnValue;
		} catch (Exception e) {
			try {
				if(dbResource != null) dbResource.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				if(dbResource != null) dbResource.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	protected SingleConnectionDbResource getDbResource() {
		return dbResource;
	}
	
	protected abstract T executeCommand() throws Exception;
	
}
