package yogi.server.gui.record.base.io.db;

import yogi.base.Manager;
import yogi.base.Selector;
import yogi.base.app.ApplicationProperties;
import yogi.base.io.db.BaseIncrementalWriter;
import yogi.base.io.db.DbFormatter;
import yogi.base.io.db.DbWriter;
import yogi.base.io.db.dump.DumpProperties;
import yogi.base.io.resource.db.DbResource;
import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.record.base.BaseRecord;

public class BaseRecordDbWriter<K extends RelationshipObject, T extends BaseRecord<K>> extends BaseIncrementalWriter<T>
{	
	public BaseRecordDbWriter(DbResource resource, Selector<? super T> selector, DbFormatter<T> formater, Manager<T> manager){	
		super(new DbWriter<T>(resource, formater), manager, selector);
	}
	
	public BaseRecordDbWriter(DbResource resource, Selector<? super T> selector, DbFormatter<T> formater, Manager<T> manager, Class<?> readerClass){	
		super(new DbWriter<T>(resource, formater, ApplicationProperties.InputDataLocation + DumpProperties.getDumpLocation()+readerClass.getName()+".dump"), manager, selector);
	}
}