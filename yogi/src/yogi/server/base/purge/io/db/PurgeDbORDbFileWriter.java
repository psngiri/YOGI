package yogi.server.base.purge.io.db;

import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.db.DbFormatter;
import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.PurgeSelector;
import yogi.server.base.purge.Purgeable;
import yogi.server.base.purge.io.PurgeDbFileWriter;


public abstract class PurgeDbORDbFileWriter<T extends Purgeable> extends FactoryWriter<T> {
	
	public static boolean writeToDbFile=true;
	
	public PurgeDbORDbFileWriter(DbResource resource, Factory<T> factory, DbFormatter<T> formatter, Class<?> klass, String path, Selector<T> selector)
	{
		super(factory, new PurgeDbFileWriter<T>(resource, formatter, klass, path, writeToDbFile), selector);
	}
	
	public PurgeDbORDbFileWriter(DbResource resource, Factory<T> factory, DbFormatter<T> formatter, Class<?> klass, String path)
	{
		this(resource, factory, formatter, klass, path, new PurgeSelector<T>());
	}
	
}
