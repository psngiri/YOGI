package yogi.server.util;

import yogi.base.io.resource.db.DbResource;
import yogi.base.io.resource.db.PooledSynchronizedDbResource;
/**
 * @author Vikram Vadavala
 *
 */
public class BaseServerAssistant {
	private static BaseServerAssistant itsInstance = new BaseServerAssistant();
	private DbResource dbresource = null;
	private static final Object lockObject = new Object();

	public static BaseServerAssistant get() {
		return itsInstance;
	}
	
	public DbResource getDbResource(){
		if (null != dbresource) return dbresource;
		synchronized (lockObject) {
			if (null == dbresource) dbresource = new PooledSynchronizedDbResource();
		}
		return dbresource;
	}
	
}
