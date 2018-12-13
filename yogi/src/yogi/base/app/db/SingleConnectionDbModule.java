package yogi.base.app.db;

import java.sql.SQLException;

import yogi.base.app.BaseModule;
import yogi.base.app.ErrorReporter;
import yogi.base.io.db.dump.DumpProperties;
import yogi.base.io.resource.db.DbResource;
import yogi.base.io.resource.db.SingleConnectionDbResource;

public class SingleConnectionDbModule extends BaseModule{
	private SingleConnectionDbResource dbResource;
	
	public SingleConnectionDbModule(DbResource dbResource){
		this.dbResource = new SingleConnectionDbResource(dbResource);
	}
	
	public SingleConnectionDbResource getDbResource() {
		return dbResource;
	}

	@Override
	public void run() {
		try {
			super.run();
			if(dbResource != null) {
				if (DumpProperties.LoadToDb) {
					dbResource.commit();
				} else {
					dbResource.rollback();
				}
			}
		} catch (Exception e) {
			try {
				ErrorReporter.get().warning("Error occurred, Started Rolling back all tables");
				if(dbResource != null){
					dbResource.rollback();
					ErrorReporter.get().warning("Finished Rolling back all tables");
				}
			} catch (SQLException e1) {
				throw new RuntimeException(e1.getMessage(), e1);
			}
			throw new RuntimeException(e.getMessage(), e);
		}finally{
			try {
				if(dbResource != null){
					dbResource.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	public void setup() {
		
	}
}
