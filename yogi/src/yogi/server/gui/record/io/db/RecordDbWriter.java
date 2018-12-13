package yogi.server.gui.record.io.db;

import yogi.base.Manager;
import yogi.base.Selector;
import yogi.base.app.ApplicationProperties;
import yogi.base.io.db.BaseIncrementalWriter;
import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbWriter;
import yogi.base.io.db.dump.DumpProperties;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;

public class RecordDbWriter<K extends Key, D extends RecordData, T extends Record<K, D>> extends BaseIncrementalWriter<T>
{	
	public RecordDbWriter(DbResource resource, Selector<? super T> selector, DbFormatter<T> formater, Manager<T> manager){	
		super(new DbWriter<T>(resource, formater), manager, selector);
	}
	
	public RecordDbWriter(DbResource resource, Selector<? super T> selector, DbFormatter<T> formater, Manager<T> manager, Class<?> readerClass){	
		super(new DbWriter<T>(resource, formater, ApplicationProperties.InputDataLocation + DumpProperties.getDumpLocation()+readerClass.getName()+".dump"), manager, selector);
	}
}