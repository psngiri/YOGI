package yogi.server.base.purge.io.db;

import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.server.base.purge.Purgeable;

/**
 * @author Vikram Vadavala
 *
 * @param <K>
 * @param <V>
 * @param <T>
 */
public abstract class PurgeDbFormatter<T extends Purgeable> implements DbFormatter<T> {

	protected PurgeDbFormatter() {
		super();
	}

	public String query() {		
		return new QueryReader(new ClassPathResource("purgeQuery.txt", this.getClass())).read();
	}

	public String cleanUpQuery() {
		return null;
	}

	public abstract DbRecord format(T record);

}
