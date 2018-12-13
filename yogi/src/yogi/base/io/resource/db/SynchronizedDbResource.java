package yogi.base.io.resource.db;

import java.sql.Connection;

public class SynchronizedDbResource extends SimpleDbResource {
		
	public SynchronizedDbResource() {
		super();
	}

	public SynchronizedDbResource(String jdbcUrl) {
		super(jdbcUrl);
	}
	
	@Override
	public synchronized Connection getConnection()  {
			return super.getConnection();
	}

}
